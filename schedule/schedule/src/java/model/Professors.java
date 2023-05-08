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
    private String professorId;
    private String name;
    private String email;
    private String office;

    public Professors() {
    }

    public Professors(String professorId, String name, String email, String office) {
        this.professorId = professorId;
        this.name = name;
        this.email = email;
        this.office = office;
    }

    
    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
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

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    @Override
    public String toString() {
        return professorId +" | "+ name +" | "+ email +" | " + office;
    }
    
    
}
