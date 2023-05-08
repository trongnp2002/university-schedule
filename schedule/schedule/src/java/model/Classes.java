/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguyn
 */
public class Classes {
    private String classID;
    private String className;
    private String courseId;
    private String roomId;
    private String professorID;

    public Classes() {
    }

    public Classes(String classID, String className, String courseId, String roomId, String professorID) {
        this.classID = classID;
        this.className = className;
        this.courseId = courseId;
        this.roomId = roomId;
        this.professorID = professorID;
    }



    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getProfessorID() {
        return professorID;
    }

    public void setProfessorID(String professorID) {
        this.professorID = professorID;
    }

    @Override
    public String toString() {
        return classID +" | " + className+" | "+ courseId +" | "+ roomId +" | " + professorID;
    }

    
    
}
