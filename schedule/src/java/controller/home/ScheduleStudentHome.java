/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.home;

import dal.StudentScheduleDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.StudentSchedule;
import service.StudentScheduleService;

/**
 *
 * @author nguyn
 */
public class ScheduleStudentHome extends HttpServlet {
    StudentScheduleService sss = new StudentScheduleService();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sss.moveScheduleWeek(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/student/scheduleStudent":
                    sss.studentScheduleStart(req, resp);
                break;
            case "/student/classInfor":
                sss.getFullClassCourseInformation(req, resp);
                break;
            default:
                throw new AssertionError();
        }

    }

}
