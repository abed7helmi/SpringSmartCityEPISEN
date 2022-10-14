package pds.smartus.frontend.controllers.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pds.smartus.frontend.repositories.dwp.usemonitor.DwpRoomConsumptionProxy;
import pds.smartus.frontend.services.dwp.usemonitor.*;



@Controller
public class ConsoController {

    @Autowired
    public DwpDeviceConsumptionService dwpDeviceConsumptionService;

    @Autowired
    public DwpRoomConsumptionService dwpRoomConsumptionService;

    @Autowired
    public DwpDeviceService dwpDeviceService;

    @Autowired
    public RoomService roomService;

    @Autowired
    public SensorTempService sensorTempService;


    @GetMapping("/consommation/{roomId}")
    public String getConsoDevices(@PathVariable(name = "roomId") int roomId, Model md) {
        /*HashMap<Integer, DwpDeviceConsumption> consoDevices = new HashMap<Integer, DwpDeviceConsumption>();
        for (DwpDevice device : dwpDeviceService.getEquipmentsbyRoom(roomId)) {
            if(device.getType() == )
            consoDevices.put(device.getId(), deviceActivityService.getLastActivity(device.getId()));
        }*/
        md.addAttribute("consoDevices", dwpDeviceConsumptionService.getConsoDevicesByRoom(roomId));
        //md.addAttribute("room", roomService.getRoomById(roomId));
        md.addAttribute("room", roomService.getRoomTest(roomId));
        md.addAttribute("date_heure", sensorTempService.getDateTemp(roomId));
        md.addAttribute("equipments", dwpDeviceService.getEquipmentsbyRoom(roomId));
        //md.addAttribute("consoUnEq", dwpDeviceConsumptionService.getConsobyDeviceID())
        return "dwp/use.monitor/consoDevices";
    }

    @GetMapping("/SG/consommation")
    public String getConsoRoom(Model md) {
        md.addAttribute("consoRooms", dwpRoomConsumptionService.getInfosConsoRoom());
        //md.addAttribute("room", roomService.getRoomById(roomId));
        md.addAttribute("date_heure", sensorTempService.getDateTemp(193));
        return "dwp/use.monitor/consoRoom";
    }
}
