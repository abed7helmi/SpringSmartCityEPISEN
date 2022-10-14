package pds.smartus.frontend.controllers.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pds.smartus.frontend.services.dwp.usemonitor.RoomService;
import pds.smartus.frontend.services.dwp.usemonitor.SensorLumiService;
import pds.smartus.frontend.services.dwp.usemonitor.SensorTempService;

@Controller
public class TempLumiController {

    @Autowired
    public SensorTempService sensorTempService;

    @Autowired
    public SensorLumiService sensorLumiService;

    @Autowired
    public RoomService roomService;

    @GetMapping("/temperature/{room_id}")
    public String getConfigTemp(@PathVariable(name = "room_id") int id_room, Model md) {
        //md.addAttribute("room", roomService.getRoomById(id_room));
        md.addAttribute("room", roomService.getRoomTest(id_room));
        md.addAttribute("date_heure", sensorTempService.getDateTemp(id_room));
        md.addAttribute("RoomTemp", sensorTempService.getRoomTemp(id_room));
        return "dwp/use.monitor/temperature";
    }

    @GetMapping("/luminosite/{room_id}")
    public String getConfigLumi(@PathVariable(name = "room_id") int id_room, Model md) {
        //md.addAttribute("room", roomService.getRoomById(id_room));
        md.addAttribute("room", roomService.getRoomTest(id_room));
        md.addAttribute("date_heure", sensorTempService.getDateTemp(id_room));
        md.addAttribute("roomLumi", sensorLumiService.getLumiRoom(id_room));
        md.addAttribute("outdoorLumi", sensorLumiService.getOutdoorRoom(id_room));
        return "dwp/use.monitor/luminosité";
    }
}
