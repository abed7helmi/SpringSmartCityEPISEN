package com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor.algoconso;

import com.pds.smartUs.BackEnd.appback.entities.dwpmap.Room;
import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DwpDeviceConsumption;
import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DwpRoomConsumption;
import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.RoomTest;
import com.pds.smartUs.BackEnd.appback.helpers.Helper;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.dwpmap.RoomRepository;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class AlgoConsoHeating {
    @Autowired
    private RoomTestRepository roomRepository;

    @Autowired
    private HeatingRoomTempRepository heatingRoomTempRepository;

    @Autowired
    private SensorTempRepository sensorTempRepository;

    @Autowired
    private DwpDeviceConsumptionRepository dwpDeviceConsumptionRepository;

    @Scheduled(fixedRate = 5000)
    public void startConsoHeating() {
        Locale currentLocale = Locale.getDefault();
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", otherSymbols);
        List<RoomTest> rooms = new ArrayList<RoomTest>((List<RoomTest>) roomRepository.findAll());
        for (RoomTest room : rooms) {
            Integer idCHauffage = heatingRoomTempRepository.getIdChauffage(room.getId_room());
            if (heatingRoomTempRepository.heating_state(room.getId_room())) {
                System.out.println(idCHauffage.toString());
                if (Float.valueOf(sensorTempRepository.lastOutdoorTemp(room.getId_room())).floatValue() >= Float.valueOf(heatingRoomTempRepository.lastDesiredTemp(room.getId_room())).floatValue() + 1 && Integer.valueOf(heatingRoomTempRepository.lastDesiredTemp(room.getId_room())).intValue() != 0 || Float.valueOf(sensorTempRepository.lastOutdoorTemp(room.getId_room())).floatValue() <= Float.valueOf(heatingRoomTempRepository.lastDesiredTemp(room.getId_room())).floatValue() - 1 && Integer.valueOf(heatingRoomTempRepository.lastDesiredTemp(room.getId_room())).intValue() != 0) {
                    float newConsoAdd = 0;
                    Float diffTemp = Math.abs(Float.valueOf(sensorTempRepository.lastOutdoorTemp(room.getId_room())) - Float.valueOf(heatingRoomTempRepository.lastDesiredTemp(room.getId_room())));
                    newConsoAdd = (float) (0.1 * diffTemp);
                    Float sumConso = dwpDeviceConsumptionRepository.getConsoByDeviceId(idCHauffage) + newConsoAdd;
                    DwpDeviceConsumption dwpDeviceConsumption = dwpDeviceConsumptionRepository.findByDeviceId(idCHauffage);
                    dwpDeviceConsumption.setConsumption(Float.valueOf(df.format(sumConso)).floatValue());
                    dwpDeviceConsumption.setDate_conso(Helper.getDateNow());
                    dwpDeviceConsumptionRepository.save(dwpDeviceConsumption);
                }
            }
        }
    }
}
