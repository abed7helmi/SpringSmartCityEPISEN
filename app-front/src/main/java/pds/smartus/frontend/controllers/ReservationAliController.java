package pds.smartus.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pds.smartus.frontend.entities.dwp.usemonitor.Reservation;
import pds.smartus.frontend.services.dwp.usemonitor.ReservationService;

@Controller
public class ReservationAliController {

    @Autowired
    public ReservationService reservationService;


    @GetMapping("/reservation")
    public String getPage(Model md) {
        Iterable<Reservation> reservationList = reservationService.getReservations();
        reservationList.forEach(reservation -> reservationService.getNextAvailableDate(reservation));
        md.addAttribute("reservations", reservationList);
        return "dwp/use.monitor/reservation";
    }
}
