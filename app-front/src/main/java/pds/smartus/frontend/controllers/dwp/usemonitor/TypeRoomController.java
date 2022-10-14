package pds.smartus.frontend.controllers.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pds.smartus.frontend.entities.dwp.usemonitor.DeviceActivity;
import pds.smartus.frontend.entities.dwp.usemonitor.DwpDevice;
import pds.smartus.frontend.repositories.dwp.usemonitor.SensorTempProxy;
import pds.smartus.frontend.services.dwp.usemonitor.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

@Controller
public class TypeRoomController {

    @Autowired
    public RoomService roomService;

    @Autowired
    public DeviceActivityService deviceActivityService;

    @Autowired
    public DwpDeviceService dwpDeviceService;

    @Autowired
    public SensorTempService sensorTempService;

    @Autowired
    public SensorLumiService sensorLumiService;

    @Autowired
    public RoomConsumptionService roomConsumptionService;

    @GetMapping("/configureBI/{room_id}")
    public String getBI(@PathVariable(name = "room_id") int id_room, Model md) {
        //md.addAttribute("room", roomService.getRoomById(id_room));
        md.addAttribute("room", roomService.getRoomTest(id_room));
        md.addAttribute("date_heure", sensorTempService.getDateTemp(id_room));
        md.addAttribute("equipments", dwpDeviceService.getEquipmentsbyRoom(id_room));
        md.addAttribute("PC", dwpDeviceService.getPC(id_room));
        md.addAttribute("activity", deviceActivityService.getLastActivity(dwpDeviceService.getPC(id_room)));
        md.addAttribute("RoomTemp", sensorTempService.getRoomTemp(id_room));
        md.addAttribute("roomLumi", sensorLumiService.getLumiRoom(id_room));
        md.addAttribute("consoRoom", roomConsumptionService.getRoomConsumption(id_room));
        return "dwp/use.monitor/gestionBI";
    }

    @GetMapping("/configureSR/{room_id}")
    public String getSR(@PathVariable(name = "room_id") int id_room, Model md) {
        HashMap<Integer, DeviceActivity> activities = new HashMap<Integer, DeviceActivity>();
        for (DwpDevice device: dwpDeviceService.getEquipmentsbyRoom(id_room)) {
            if (Objects.equals(device.getType(), "ecran")) {
                activities.put(device.getId(), deviceActivityService.getLastActivity(device.getId()));
            }
        }
        md.addAttribute("activities",activities);
        md.addAttribute("equipments", dwpDeviceService.getEquipmentsbyRoom(id_room));
        //md.addAttribute("room", roomService.getRoomById(id_room));
        md.addAttribute("room", roomService.getRoomTest(id_room));
        md.addAttribute("date_heure", sensorTempService.getDateTemp(id_room));
        md.addAttribute("RoomTemp", sensorTempService.getRoomTemp(id_room));
        md.addAttribute("roomLumi", sensorLumiService.getLumiRoom(id_room));
        md.addAttribute("consoRoom", roomConsumptionService.getRoomConsumption(id_room));
        return "dwp/use.monitor/gestionSR&OS";
    }

    @GetMapping("/configureOS/{room_id}")
    public String getOS(@PathVariable(name = "room_id") int id_room, Model md) {
        HashMap<Integer, DeviceActivity> activities = new HashMap<Integer, DeviceActivity>();
        for (DwpDevice device: dwpDeviceService.getEquipmentsbyRoom(id_room)) {
            //if (!device.getType().equals("chauffage") && !device.getType().equals("lumiere")) {
            if (Objects.equals(device.getType(), "ecran")) {
                activities.put(device.getId(), deviceActivityService.getLastActivity(device.getId()));
            }
        }
        md.addAttribute("activities",activities);
        //md.addAttribute("room", roomService.getRoomById(id_room));
        md.addAttribute("room", roomService.getRoomTest(id_room));
        md.addAttribute("date_heure", sensorTempService.getDateTemp(id_room));
        md.addAttribute("equipments", dwpDeviceService.getEquipmentsbyRoom(id_room));
        md.addAttribute("RoomTemp", sensorTempService.getRoomTemp(id_room));
        md.addAttribute("roomLumi", sensorLumiService.getLumiRoom(id_room));
        md.addAttribute("consoRoom", roomConsumptionService.getRoomConsumption(id_room));
        return "dwp/use.monitor/gestionSR&OS";
    }

}
