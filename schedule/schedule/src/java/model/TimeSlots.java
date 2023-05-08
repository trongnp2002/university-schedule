/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguyn
 */
public class TimeSlots {
    private String dayOfWeek;
    private String timeSlotsId;
    private String startTime;
    private String endTime;
    private String date;

    public TimeSlots() {
    }

    public TimeSlots(String dayOfWeek, String timeSlotsId, String startTime, String endTime, String date) {
        this.dayOfWeek = dayOfWeek;
        this.timeSlotsId = timeSlotsId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getTimeSlotsId() {
        return timeSlotsId;
    }

    public void setTimeSlotsId(String timeSlotsId) {
        this.timeSlotsId = timeSlotsId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
