/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author nguyn
 */
public class ClassFullInfor {
    private int classId;
    private int courseId;
    private String courseName;
    private int semester;
    private String room;
    private String professer;
    private List<TimeSlots> slot;

    public ClassFullInfor() {
    }

    public ClassFullInfor(int classId, int courseId, String courseName, int semester, String room, String professer, List<TimeSlots> slot) {
        this.classId = classId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.semester = semester;
        this.room = room;
        this.professer = professer;
        this.slot = slot;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }


    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getProfesser() {
        return professer;
    }

    public void setProfesser(String professer) {
        this.professer = professer;
    }

    public List<TimeSlots> getSlot() {
        return slot;
    }

    public void setSlot(List<TimeSlots> slot) {
        this.slot = slot;
    }




    
 }
