package com.pds.smartUs.BackEnd.appback.controllers.dwp.meetingroom;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.Reservation;
import com.pds.smartUs.BackEnd.appback.services.dwp.meetingroom.ReservationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping(path = "/resa")
public class ReservationController {
    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public Iterable<Reservation> getAllReservations() {
        return reservationService.getReservations();
    }

    @GetMapping(path="/{id}")
    public Reservation getReservationByID(@PathVariable("id") int reservation_id) {
        return reservationService.getReservation(reservation_id);
    }

    @GetMapping(path = "/available/{room_id}")
    public String getNextAvailableDate(@PathVariable("room_id") int room_id) {
        try {
            return reservationService.getNextAvailableDate(room_id);
        } catch (ParseException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @GetMapping(path = "/available/now/{room_id}")
    public Boolean isAvailableNow(@PathVariable("room_id") int room_id) {
        try {
            return reservationService.isAvailableNow(room_id);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
