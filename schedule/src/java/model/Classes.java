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

    private int classId;
    private String className;
    private String studentYear;
    private int majorId;
    private String majorName;
    public Classes() {
    }

    public Classes(int classId, String className, String studentYear, int majorId) {
        this.classId = classId;
        this.className = className;
        this.studentYear = studentYear;
        this.majorId = majorId;
    }

    public Classes(int classId, String className, String studentYear, int majorId, String majorName) {
        this.classId = classId;
        this.className = className;
        this.studentYear = studentYear;
        this.majorId = majorId;
        this.majorName = majorName;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentYear() {
        return studentYear;
    }

    public void setStudentYear(String studentYear) {
        this.studentYear = studentYear;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    
    
    
    
}
