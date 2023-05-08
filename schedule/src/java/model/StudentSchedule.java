/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguyn
 */
public class StudentSchedule {

    private int classCourseId;
    private String className;
    private String courseCode;
    private String room;
    private int slotId;
    private String date;
    private String dayOfWeek;
    private String attendanceStatus;

    public StudentSchedule() {
    }

    public StudentSchedule(int classCourseId, String className, String courseCode, String room, int slotId, String date, String dayOfWeek, String attendanceStatus) {
        this.classCourseId = classCourseId;
        this.className = className;
        this.courseCode = courseCode;
        this.room = room;
        this.slotId = slotId;
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.attendanceStatus = attendanceStatus;
    }

    public int getClassCourseId() {
        return classCourseId;
    }

    public void setClassCourseId(int classCourseId) {
        this.classCourseId = classCourseId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    @Override
    public String toString() {
        return classCourseId +"|" + courseCode +" | " +className +" | " + room +" | " + slotId +" | " + date +" | "+ dayOfWeek +" | " + attendanceStatus;
    }
    
    
    
}
