/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.ClassDao;
import dal.CourseDao;
import dal.ProfessorDao;
import dal.SlotDao;
import dal.StudentDao;
import dal.StudentScheduleDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Accounts;
import model.Classes;
import model.Courses;
import model.Professors;
import model.Slot;
import model.StudentSchedule;
import model.Students;

/**
 *
 * @author nguyn
 */
public class StudentScheduleService {

    StudentScheduleDao ssd = new StudentScheduleDao();
    SlotDao slotDao = new SlotDao();
    ClassDao classDao = new ClassDao();
    StudentDao studentDao = new StudentDao();
    ProfessorDao professorDao = new ProfessorDao();
    CourseDao courseDao = new CourseDao();
    
    public void studentScheduleStart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate today = LocalDate.now();
        LocalDate start = today;
        LocalDate end = today;
        while (!start.getDayOfWeek().toString().equals("MONDAY")) {
            start = start.minusDays(1);
        }
        while (!end.getDayOfWeek().toString().equals("SUNDAY")) {
            end = end.plusDays(1);
        }
        HttpSession session = req.getSession();
        Accounts acc = (Accounts) session.getAttribute("account");

        List<Slot> groupOfAllSlot = slotDao.getGroupOfSlot();
        List<StudentSchedule> schedule = ssd.getStudentScheduleByStudentIdInWeek(acc.getStudentId(), start, end);
        req.setAttribute("slots", groupOfAllSlot);
        req.setAttribute("startDate", start);
        req.setAttribute("endDate", end);
        req.setAttribute("schedule", schedule);
        req.getRequestDispatcher("/views/schedules/student/studentSchedule.jsp").forward(req, resp);
    }

    public void moveScheduleWeek(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentOption = req.getParameter("submit");
        String dayInOriginSchedule = req.getParameter("dateInSchedule").trim();
        String dayArray[] = dayInOriginSchedule.split("To");
        String start = dayArray[0].trim();
        String end = dayArray[1].trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(start, formatter);
        LocalDate endDate = LocalDate.parse(end, formatter);
        if (studentOption.equalsIgnoreCase("Previous")) {
            startDate = startDate.minusDays(7);
            endDate = endDate.minusDays(7);
        } else {
            startDate = startDate.plusDays(7);
            endDate = endDate.plusDays(7);
        }
        HttpSession session = req.getSession();
        Accounts acc = (Accounts) session.getAttribute("account");
        List<Slot> groupOfAllSlot = slotDao.getGroupOfSlot();
        List<StudentSchedule> schedule = ssd.getStudentScheduleByStudentIdInWeek(acc.getStudentId(), startDate, endDate);
        req.setAttribute("slots", groupOfAllSlot);
        req.setAttribute("startDate", startDate);
        req.setAttribute("endDate", endDate);
        req.setAttribute("schedule", schedule);
        req.getRequestDispatcher("/views/schedules/student/studentSchedule.jsp").forward(req, resp);
    }
    
    public void getFullClassCourseInformation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String csId = req.getParameter("csId");
        int classCourseId = Integer.parseInt(csId);
        List<Students> listStudentInClass = studentDao.getFullStudentFromClassWithCourse(classCourseId);
        Professors professor =  professorDao.getProfessorByClassCourseId(classCourseId);
        Courses course = courseDao.getCourseByClassCourseId(classCourseId);
        Classes theClass = classDao.getClassByClassCourseId(classCourseId);
        req.setAttribute("classinfor", theClass);
        req.setAttribute("students", listStudentInClass);
        req.setAttribute("professor", professor);
        req.setAttribute("course", course);
        req.getRequestDispatcher("/views/schedules/student/studentClassFullInfor.jsp").forward(req, resp);
        
    }

    public static void main(String[] args) {
        String date = "2023-03-13";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(date, formatter);
        System.out.println(startDate.getDayOfWeek());
        System.out.println(startDate.minusDays(7).getDayOfWeek());
    }
}
