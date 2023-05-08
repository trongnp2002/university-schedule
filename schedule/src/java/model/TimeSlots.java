/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author nguyn
 */
public class TimeSlots {
    private int timeslot_id;
    private String dayOfWeek;
    private LocalDate date;
    private int slot_id;

    public TimeSlots() {
    }

    public TimeSlots(int timeslot_id, String dayOfWeek, LocalDate date, int slot_id) {
        this.timeslot_id = timeslot_id;
        this.dayOfWeek = dayOfWeek;
        this.date = date;
        this.slot_id = slot_id;
    }

    public int getTimeslot_id() {
        return timeslot_id;
    }

    public void setTimeslot_id(int timeslot_id) {
        this.timeslot_id = timeslot_id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }
    
    
    
}
