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
public class ScheduleOfAllProfessor {
    private Professors professor;
    private List<ProfessorSchedule> list;

    public ScheduleOfAllProfessor() {
    }

    public ScheduleOfAllProfessor(Professors professor, List<ProfessorSchedule> list) {
        this.professor = professor;
        this.list = list;
    }

    public Professors getProfessor() {
        return professor;
    }

    public void setProfessor(Professors professor) {
        this.professor = professor;
    }

    public List<ProfessorSchedule> getList() {
        return list;
    }

    public void setList(List<ProfessorSchedule> list) {
        this.list = list;
    }
    
}
