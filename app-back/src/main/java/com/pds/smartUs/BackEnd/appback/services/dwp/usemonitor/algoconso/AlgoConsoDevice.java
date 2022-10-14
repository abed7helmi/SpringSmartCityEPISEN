package com.pds.smartUs.BackEnd.appback.services.dwp.usemonitor.algoconso;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DwpDevice;
import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.DwpDeviceConsumption;
import com.pds.smartUs.BackEnd.appback.helpers.Helper;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.DeviceActivityRepository;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.DwpDeviceConsumptionRepository;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.DwpDeviceRepository;
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
@RestController("AlgoConsumption")
public class AlgoConsoDevice {

    @Autowired
    private DwpDeviceRepository dwpDeviceRepository;

    @Autowired
    private DwpDeviceConsumptionRepository dwpDeviceConsumptionRepository;

    @Autowired
    private DeviceActivityRepository deviceActivityRepository;

    @Scheduled(fixedRate = 3000)
    public void startConsoDevice() {
        Locale currentLocale = Locale.getDefault();
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", otherSymbols);
        List<DwpDevice> devices = new ArrayList<DwpDevice>((List<DwpDevice>) dwpDeviceRepository.findAll());
        for (DwpDevice device : devices) {
            if (device.getType().equals("PC") || device.getType().equals("ecran")) {
                if (deviceActivityRepository.is_active(device.getId())) {
                    if (device.getType().equals("PC")) {
                        float consoPC = 0;
                        consoPC += 35;
                        DwpDeviceConsumption consoPCdevice = dwpDeviceConsumptionRepository.findByDeviceId(device.getId());
                        String newConso = df.format(consoPC + consoPCdevice.getConsumption());
                        consoPCdevice.setConsumption(Float.valueOf(newConso).floatValue());
                        consoPCdevice.setDate_conso(Helper.getDateNow());
                        dwpDeviceConsumptionRepository.save(consoPCdevice);
                    } else if (device.getType().equals("ecran")) {
                        float consoEcran = 0;
                        //while (deviceActivityRepository.is_active(device.getId())) {
                        consoEcran += 9.6;
                        try {
                            Thread.sleep(5000);
                            DwpDeviceConsumption consoEcrandevice = dwpDeviceConsumptionRepository.findByDeviceId(device.getId());
                            Float newConso = consoEcran + consoEcrandevice.getConsumption();
                            consoEcrandevice.setConsumption(Float.valueOf(df.format(newConso)).floatValue());
                            consoEcrandevice.setDate_conso(Helper.getDateNow());
                            dwpDeviceConsumptionRepository.save(consoEcrandevice);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }
}
