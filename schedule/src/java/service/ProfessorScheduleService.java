/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.AttendanceDao;
import dal.ClassDao;
import dal.CourseDao;
import dal.ProfessorDao;
import dal.ProfessorScheduleDao;
import dal.SlotDao;
import dal.StudentDao;
import dal.StudentScheduleDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Accounts;
import model.Classes;
import model.Courses;
import model.ProfessorSchedule;
import model.Professors;
import model.ScheduleOfAllProfessor;
import model.Slot;
import model.StudentSchedule;
import model.Students;

/**
 *
 * @author nguyn
 */
public class ProfessorScheduleService {

    StudentScheduleDao ssd = new StudentScheduleDao();
    SlotDao slotDao = new SlotDao();
    ClassDao classDao = new ClassDao();
    StudentDao studentDao = new StudentDao();
    ProfessorDao professorDao = new ProfessorDao();
    CourseDao courseDao = new CourseDao();
    ProfessorScheduleDao professorScheduleDao = new ProfessorScheduleDao();
    AttendanceDao ad = new AttendanceDao();

    public void professorScheduleStart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        List<ProfessorSchedule> schedule = professorScheduleDao.getFullProfessorScheduleInDateRange(acc.getProfessorId(), start, end);
        req.setAttribute("slots", groupOfAllSlot);
        req.setAttribute("startDate", start);
        req.setAttribute("endDate", end);
        req.setAttribute("schedule", schedule);
        req.getRequestDispatcher("/views/schedules/professor/professorSchedule.jsp").forward(req, resp);
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
        List<ProfessorSchedule> schedule = professorScheduleDao.getFullProfessorScheduleInDateRange(acc.getProfessorId(), startDate, endDate);
        req.setAttribute("slots", groupOfAllSlot);
        req.setAttribute("startDate", startDate);
        req.setAttribute("endDate", endDate);
        req.setAttribute("schedule", schedule);
        req.getRequestDispatcher("/views/schedules/professor/professorSchedule.jsp").forward(req, resp);
    }

    public void classFullInforAndCheckAttend(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tsId = req.getParameter("timeSlotId");
        String clcId = req.getParameter("classCourseId");
        int timeSlotId = Integer.parseInt(tsId);
        int classCourseId = Integer.parseInt(clcId);
        List<Students> listStudentInClass = studentDao.getFullStudentFromClassWithCourse(classCourseId);
        List<String> attendStatus = new ArrayList();
        for (int i = 0; i < listStudentInClass.size(); i++) {
            attendStatus.add(ad.getAttendStatus(listStudentInClass.get(i).getStudentID(), timeSlotId));
        }
        int numberStudentInClass = listStudentInClass.size();
        Courses course = courseDao.getCourseByClassCourseId(classCourseId);
        Classes theClass = classDao.getClassByClassCourseId(classCourseId);

        req.setAttribute("attendStatus", attendStatus);
        req.setAttribute("classCourseId", classCourseId);
        req.setAttribute("timeSlotId", timeSlotId);
        req.setAttribute("classinfor", theClass);
        req.setAttribute("course", course);
        req.setAttribute("numOfStudent", numberStudentInClass);
        req.setAttribute("students", listStudentInClass);
        req.getRequestDispatcher("/views/schedules/professor/checkAttend.jsp").forward(req, resp);

    }

    public void getAllProfessorSlot(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LocalDate today = LocalDate.now();
        LocalDate start = today;
        LocalDate end = today;
        while (!start.getDayOfWeek().toString().equals("MONDAY")) {
            start = start.minusDays(1);
        }
        while (!end.getDayOfWeek().toString().equals("SUNDAY")) {
            end = end.plusDays(1);
        }

        List<ScheduleOfAllProfessor> listOfFUllProfessorSchedult = new ArrayList();
        List<Professors> groupOfFullProfessor = professorDao.getGroupOfProfessor();
        for (int i = 0; i < groupOfFullProfessor.size(); i++) {
            List<ProfessorSchedule> schedule = professorScheduleDao.getFullProfessorScheduleInDateRange(groupOfFullProfessor.get(i).getProfessorId(), start, end);

            ScheduleOfAllProfessor sOAP = new ScheduleOfAllProfessor(groupOfFullProfessor.get(i), schedule);
            listOfFUllProfessorSchedult.add(sOAP);
        }

        req.setAttribute("scheduleOfFullProfessor", listOfFUllProfessorSchedult);
        req.setAttribute("startDate", start);
        req.setAttribute("endDate", end);
        req.getRequestDispatcher("/views/schedules/professor/allProfessorSchedule.jsp").forward(req, resp);
    }

    public void swapWeekFullProfessorSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        List<ScheduleOfAllProfessor> listOfFUllProfessorSchedult = new ArrayList();
        List<Professors> groupOfFullProfessor = professorDao.getGroupOfProfessor();
        for (int i = 0; i < groupOfFullProfessor.size(); i++) {
            List<ProfessorSchedule> schedule = professorScheduleDao.getFullProfessorScheduleInDateRange(groupOfFullProfessor.get(i).getProfessorId(), startDate, endDate);

            ScheduleOfAllProfessor sOAP = new ScheduleOfAllProfessor(groupOfFullProfessor.get(i), schedule);
            listOfFUllProfessorSchedult.add(sOAP);
        }

        req.setAttribute("scheduleOfFullProfessor", listOfFUllProfessorSchedult);
        req.setAttribute("startDate", startDate);
        req.setAttribute("endDate", endDate);
        req.getRequestDispatcher("/views/schedules/professor/allProfessorSchedule.jsp").forward(req, resp);
    }

    public void updateAttend(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int timeSlotId = Integer.parseInt(req.getParameter("timeSlotId"));
        int numOfStudent = Integer.parseInt(req.getParameter("numOfStudent"));
        for (int i = 1; i <= numOfStudent; i++) {
            String parameter = "id" + i;
            String attendParameter = "stu" + i;
            int studentId = Integer.parseInt(req.getParameter(parameter));
            String attendStatus = req.getParameter(attendParameter);
            if (attendStatus.equalsIgnoreCase("present")) {
                attendStatus = "attended";
            }
            ad.UpdateAttendance(timeSlotId, studentId, attendStatus);
        }

        int classCourseId = Integer.parseInt(req.getParameter("classCourseId"));
        List<Students> listStudentInClass = studentDao.getFullStudentFromClassWithCourse(classCourseId);
        int numberStudentInClass = listStudentInClass.size();
        Courses course = courseDao.getCourseByClassCourseId(classCourseId);
        Classes theClass = classDao.getClassByClassCourseId(classCourseId);
        List<String> attendStatus = new ArrayList();
        for (int i = 0; i < listStudentInClass.size(); i++) {
            attendStatus.add(ad.getAttendStatus(listStudentInClass.get(i).getStudentID(), timeSlotId));
        }
        req.setAttribute("attendStatus", attendStatus);
        req.setAttribute("classCourseId", classCourseId);
        req.setAttribute("timeSlotId", timeSlotId);
        req.setAttribute("classinfor", theClass);
        req.setAttribute("course", course);
        req.setAttribute("numOfStudent", numberStudentInClass);
        req.setAttribute("students", listStudentInClass);
        req.getRequestDispatcher("/views/schedules/professor/checkAttend.jsp").forward(req, resp);

    }
}
