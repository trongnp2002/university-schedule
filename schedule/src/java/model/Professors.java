/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguyn
 */
public class Professors {

    private int professorId;
    private String nickname;
    private String name;
    private String email;
    private int departmentId;
    private String departmentName;

    public Professors() {
    }

    public Professors(int professorId, String nickname, String name, String email, int department) {
        this.professorId = professorId;
        this.name = name;
        this.email = email;
        this.nickname = nickname;
        this.departmentId = department;
    }

    public Professors(int professorId, String nickname, String name, String email, int department, String departmentName) {
        this.professorId = professorId;
        this.name = name;
        this.email = email;
        this.nickname = nickname;
        this.departmentId = department;
        this.departmentName = departmentName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    
    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int department) {
        this.departmentId = department;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return professorId + " | " + name + " | " + email + " | " + departmentName;
    }

}
