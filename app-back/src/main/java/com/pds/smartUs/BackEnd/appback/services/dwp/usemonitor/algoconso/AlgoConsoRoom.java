package com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor.algoconso;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.Room;
import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DwpRoomConsumption;
import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.RoomTest;
import com.pds.smartUs.BackEnd.appback.helpers.Helper;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap.RoomRepository;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.DwpRoomConsumptionRepository;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.RoomTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class AlgoConsoRoom implements Runnable {
    @Autowired
    private DwpRoomConsumptionRepository dwpRoomConsumptionRepository;

    @Autowired
    private RoomTestRepository roomRepository;

    @Override
    public void run() {
        while (true) {
            Locale currentLocale = Locale.getDefault();
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
            otherSymbols.setDecimalSeparator('.');
            otherSymbols.setGroupingSeparator('.');
            DecimalFormat df = new DecimalFormat("0.00", otherSymbols);
            List<RoomTest> rooms = new ArrayList<RoomTest>((List<RoomTest>) roomRepository.findAll());
            for (RoomTest room : rooms) {
                if (dwpRoomConsumptionRepository.findByRoomId(room.getId_room()) == null) {
                    if (dwpRoomConsumptionRepository.getSumConsumption(room.getId_room()) != null) {
                        float sumConso = dwpRoomConsumptionRepository.getSumConsumption(room.getId_room());
                        DwpRoomConsumption newConso = new DwpRoomConsumption(Float.valueOf(df.format(sumConso)).floatValue(), Helper.getDateNow(), room.getId_room());
                        dwpRoomConsumptionRepository.save(newConso);
                    }
                } else {
                    float sumConso = dwpRoomConsumptionRepository.getSumConsumption(room.getId_room());
                    DwpRoomConsumption dwpRoomConsumption = dwpRoomConsumptionRepository.findByRoomId(room.getId_room());
                    dwpRoomConsumption.setConsumption(Float.valueOf(df.format(sumConso)).floatValue());
                    dwpRoomConsumptionRepository.save(dwpRoomConsumption);
                }
            }
        }

    }
}
