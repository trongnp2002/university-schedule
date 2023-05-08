/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguyn
 */
public class ProfessorSchedule {
    private int classCourseId;
    private int timeSlotId;
    private String dayOfWeek;
    private String date;
    private int slotId;
    private String className;
    private String room;
    public ProfessorSchedule() {
    }

    public ProfessorSchedule(int classCourseId, int timeSlotId, String dayOfWeek, String date, int slotId, String className, String room) {
        this.classCourseId = classCourseId;
        this.timeSlotId = timeSlotId;
        this.dayOfWeek = dayOfWeek;
        this.date = date;
        this.slotId = slotId;
        this.className = className;
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }


    

    public int getClassCourseId() {
        return classCourseId;
    }

    public void setClassCourseId(int classCourseId) {
        this.classCourseId = classCourseId;
    }

    public int getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(int timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    
    
}
