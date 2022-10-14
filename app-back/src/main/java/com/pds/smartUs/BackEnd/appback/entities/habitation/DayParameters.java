package com.pds.smartUs.BackEnd.appback.entities.habitation;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="day_parameters")
public class DayParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_day_parameters")
    int idDayParameters;

    @Column(name = "today_date")
    Date todayDate;

    @Column(name = "currents_time")
    Time currentsTime;

    @Column(name = "temperature")
    double temperature;


    public DayParameters() {
    }

    public DayParameters(LocalDate todayDate, LocalTime currentsTime, double temperature) {
        this.todayDate = new Date(todayDate.getYear(), todayDate.getMonthValue(), todayDate.getDayOfMonth());
        this.currentsTime = new Time(currentsTime.getHour(), currentsTime.getMinute(), currentsTime.getSecond());
        this.temperature = temperature;
    }

    public int getIdDayParameters() {
        return idDayParameters;
    }

    public void setIdDayParameters(int idDayParameters) {
        this.idDayParameters = idDayParameters;
    }

    public Date getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(Date todayDate) {
        this.todayDate = todayDate;
    }

    public Time getCurrentsTime() {
        return currentsTime;
    }

    public void setCurrentsTime(Time currentsTime) {
        this.currentsTime = currentsTime;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
