package pds.smartus.frontend.services.dwp.usemonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.smartus.frontend.entities.dwp.usemonitor.Reservation;
import pds.smartus.frontend.entities.dwp.usemonitor.ReservationTest;
import pds.smartus.frontend.repositories.dwp.usemonitor.ReservationProxy;

@Service
public class ReservationService {
    @Autowired
    private ReservationProxy reservationProxy;

    public Iterable<Reservation> getReservations() {return reservationProxy.getReservations();}

    public Iterable<ReservationTest> getReservationsTest() {return reservationProxy.getReservationsTest();}

    /*public Reservation getReservation(int id) {
        return reservationProxy.getReservationById(id);
    }*/

    public void getNextAvailableDate(Reservation reservation) {
        String nextDate =  reservationProxy.getNextAvailableDate(reservation.getId_room());
        boolean isAvailableNow = reservationProxy.isAvailableNow(reservation.getId_room());
        reservation.setNext_date(nextDate);
        if(isAvailableNow) reservation.setAvailable_now("Disponible");
        else reservation.setAvailable_now("Occupée");
    }
}
