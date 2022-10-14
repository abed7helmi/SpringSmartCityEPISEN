package pds.smartus.frontend.controllers.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pds.smartus.frontend.services.dwp.usemonitor.*;
import pds.smartus.frontend.entities.dwp.usemonitor.Reservation;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    public DwpDeviceService dwpDeviceService;

    @Autowired
    public UserService userService;

    @Autowired
    public DeviceActivityService deviceActivityService;

    @Autowired
    public ReservationService reservationService;

    @Autowired
    public RoomService roomService;

    @Autowired
    public SensorTempService sensorTempService;

    @Autowired
    public DwpRoomConsumptionService dwpRoomConsumptionService;

    @GetMapping("/login")
    public String getView() {
        return "dwp/use.monitor/login";
    }

    /*@GetMapping("/checkUser")
    public String checkUser(@RequestParam(name = "name") String name, Model md) {
        Boolean verifUser = userService.checkUser(name);
        md.addAttribute("reservations", reservationService.getReservations());
        if (verifUser) {
            return "dwp/use.monitor/reservation";
        } else {
            return "dwp/use.monitor/login";
        }
    }*/

    @GetMapping("/checkUser")
    public String checkUser(@RequestParam(name = "name") String name, Model md) {
        Boolean verifUser = userService.checkUser(name);
        md.addAttribute("reservations", reservationService.getReservationsTest());
        md.addAttribute("consoRooms", dwpRoomConsumptionService.getInfosConsoRoom());
        md.addAttribute("date_heure", sensorTempService.getDateTemp(193));
        if (verifUser) {
            if(name.equals("SG")){
                return "dwp/use.monitor/consoRoom";
            } else {
                return "dwp/use.monitor/reservationTest";
            }
        } else {
            return "dwp/use.monitor/login";
        }
    }

    @GetMapping("/reservation/monitoring")
    public String getPage(Model md) {
        md.addAttribute("reservations", reservationService.getReservationsTest());
        return "dwp/use.monitor/reservationTest";
    }
}
