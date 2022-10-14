package com.pds.smartUs.BackEnd.appback.services.dwp.meetingroom;

import com.pds.smartUs.BackEnd.appback.entities.dwpusemonitor.*;
import com.pds.smartUs.BackEnd.appback.repositories.dwp.usemonitor.ReservationRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getReservations() {
        return (List<Reservation>) reservationRepository.findAll();
    }

    public Reservation getReservation(int reservation_id) {
        return reservationRepository.findById(reservation_id).get();
    }

    public String getNextAvailableDate(int room_id) throws ParseException {
        List<Reservation> reservations = getReservations();
        Date date1 = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        for (Reservation reservation: reservations) {
            if(reservation.getId_room() == room_id) {
                String date = reservation.getEnd_date() + " " + reservation.getEnd_hour();
                Date currentDate = formatter.parse(date);
                if(Objects.isNull(date1)) {
                    date1 = currentDate;
                } else {
                    if(currentDate.after(date1)) date1 = currentDate;
                }
            }
        }
        if(Objects.nonNull(date1)) {
            // Plus 15 min
            date1 = (new Date(date1.getTime() + (15 * 60 * 1000)));
            // Validate is not after 22h
            int hour = Integer.parseInt(new SimpleDateFormat("HH").format(date1));
            System.out.println(hour);
            if(hour > 21) {
                Date tomorrow = new Date(date1.getTime() + (12 * 60 * 60 * 1000));
                Calendar calendar=Calendar.getInstance();
                calendar.setTime(tomorrow);
                calendar.set(Calendar.HOUR_OF_DAY, 9);
                calendar.set(Calendar.MINUTE, 0);
                tomorrow = calendar.getTime();
                return formatter.format(tomorrow);
            }
            else {
                return formatter.format(date1);
            }
        } else {
            return formatter.format(new Date(System.currentTimeMillis()));
        }
    }

    public boolean isAvailableNow(int room_id) throws ParseException {
        List<Reservation> reservations = getReservations();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date now = new Date(System.currentTimeMillis());
        for (Reservation reservation: reservations) {
            if(reservation.getId_room() == room_id) {
                String endDateStr = reservation.getEnd_date() + " " + reservation.getEnd_hour();
                Date endDate = formatter.parse(endDateStr);
                String startDateStr = reservation.getStart_date() + " " + reservation.getStart_hour();
                Date startDate = formatter.parse(startDateStr);
                System.out.print(startDate);
                System.out.print(" | ");
                System.out.print(endDate);
                System.out.print(" | ");
                System.out.print(now.after(startDate));
                System.out.print(" | ");
                System.out.print(now.before(endDate));
                System.out.println();
                if(now.before(endDate) && now.after(startDate)) {
                    return false;
                }
            }
        }

        return true;
    }

}
