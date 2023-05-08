/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.home;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import service.ProfessorScheduleService;

/**
 *
 * @author nguyn
 */
public class ScheduleProfessorHome extends HttpServlet {

    ProfessorScheduleService pss = new ProfessorScheduleService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/professor/professorSchedule":
                pss.moveScheduleWeek(req, resp);
                break;
            case "/professor/attend":
                pss.updateAttend(req, resp);
                break;

            case "/professor/allProfessrSchedule":
                pss.swapWeekFullProfessorSchedule(req, resp);
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/professor/professorSchedule":
                pss.professorScheduleStart(req, resp);
                break;
            case "/professor/attend":
                pss.classFullInforAndCheckAttend(req, resp);
                break;

            case "/professor/allProfessrSchedule":
                pss.getAllProfessorSlot(req, resp);
                break;
            default:
                throw new AssertionError();
        }

    }

}
