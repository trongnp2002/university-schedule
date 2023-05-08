/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.CourseDao;
import dal.DepartmentDao;
import dal.MajorDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Courses;
import model.Departments;
import model.Major;

/**
 *
 * @author nguyn
 */
public class DepartmentAndCourseService {

    DepartmentDao dd = new DepartmentDao();
    CourseDao cd = new CourseDao();
    MajorDao md = new MajorDao();

    public void departmentAndCourseStart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String select = req.getParameter("sub");
        String report = "";

        if (select != null && select.equalsIgnoreCase("ADD")) {
            String name = req.getParameter("name").trim();
            report = "Procces fail!!!";
            if (!name.isEmpty()) {
                dd.addDepartment(name);
                report = "Procces sucessful!!!";
            }
        }
        select = "DEPARTMENT";
        List<Major> groupMajor = md.getFullGroupOfMajor();
        List<Departments> groupDepartment = dd.getGroupOfDepartment();
        List<Courses> groupCourse = cd.getFullGroupOfCourse();
        req.setAttribute("report", report);
        req.setAttribute("select", select);
        req.setAttribute("data", groupDepartment);
        req.setAttribute("data1", groupCourse);
        req.setAttribute("data2", groupMajor);
        req.getRequestDispatcher("/views/admin/admin_courseDepartment.jsp").forward(req, resp);
    }

    public void addMajor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String report = "Process success!!!";
        String select = "MAJOR";
        String name = req.getParameter("majorName").trim();
        String group = req.getParameter("majorGroup").trim();
        if (name.isEmpty() || group.isEmpty()) {
            report = "Input can not be empty!!!";
        } else {
            Major major = new Major(0, name, group);
            md.addMajor(major);
        }
        List<Major> groupMajor = md.getFullGroupOfMajor();
        List<Departments> groupDepartment = dd.getGroupOfDepartment();
        List<Courses> groupCourse = cd.getFullGroupOfCourse();
        req.setAttribute("select", "MAJOR");
        req.setAttribute("report", report);
        req.setAttribute("select", select);
        req.setAttribute("data", groupDepartment);
        req.setAttribute("data1", groupCourse);
        req.setAttribute("data2", groupMajor);
        req.getRequestDispatcher("/views/admin/admin_courseDepartment.jsp").forward(req, resp);
    }

    public void deleteMajor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id").trim();
        String report = "Procces sucessful";
        Major major = md.deleteMajor(Integer.parseInt(id));
        if (major == null) {
            report = "delete fail!";
        } else {
            report = "Delete major success: id: " + major.getMajorId() + " name: " + major.getMajorName();
        }
        String select = "MAJOR";
        List<Departments> groupDepartment = dd.getGroupOfDepartment();
        List<Courses> groupCourse = cd.getFullGroupOfCourse();
        List<Major> groupMajor = md.getFullGroupOfMajor();
        req.setAttribute("data2", groupMajor);
        req.setAttribute("report", report);
        req.setAttribute("select", select);
        req.setAttribute("data", groupDepartment);
        req.setAttribute("data1", groupCourse);
        req.getRequestDispatcher("/views/admin/admin_courseDepartment.jsp").forward(req, resp);
    }

    public void deleteDepartments(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id").trim();
        String report = "Procces sucessful";
        Departments department = dd.deleteDepartment(Integer.parseInt(id));
        if (department == null) {
            report = "delete fail!";
        } else {
            report = "Delete department success: id: " + department.getDepartmentsId() + " name: " + department.getDepartmentName();
        }
        String select = "DEPARTMENT";
        List<Departments> groupDepartment = dd.getGroupOfDepartment();
        List<Courses> groupCourse = cd.getFullGroupOfCourse();
        List<Major> groupMajor = md.getFullGroupOfMajor();
        req.setAttribute("data2", groupMajor);
        req.setAttribute("report", report);
        req.setAttribute("select", select);
        req.setAttribute("data", groupDepartment);
        req.setAttribute("data1", groupCourse);
        req.getRequestDispatcher("/views/admin/admin_courseDepartment.jsp").forward(req, resp);
    }

    public void deleteCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String report = "Procces sucessful";
        Courses course = cd.deleteCourses(Integer.parseInt(id));
        if (course == null) {
            report = "delete fail!";
        } else {
            report = "Delete department success courseCode: " + course.getCourseCode() + " name: " + course.getCourseName();
        }
        String select = "COURSE";
        List<Departments> groupDepartment = dd.getGroupOfDepartment();
        List<Courses> groupCourse = cd.getFullGroupOfCourse();
        List<Major> groupMajor = md.getFullGroupOfMajor();
        req.setAttribute("data2", groupMajor);
        req.setAttribute("report", report);
        req.setAttribute("select", select);
        req.setAttribute("data", groupDepartment);
        req.setAttribute("data1", groupCourse);
        req.getRequestDispatcher("/views/admin/admin_courseDepartment.jsp").forward(req, resp);
    }

    public void addCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseCode = req.getParameter("courseCode").trim();
        String courseName = req.getParameter("courseName").trim();
        String select = "COURSE";
        String report = "Process successful!";
        List<Courses> groupCourse = cd.getFullGroupOfCourse();
        List<Departments> groupDepartment = dd.getGroupOfDepartment();
        int departmentId = Integer.parseInt(req.getParameter("department1"));
        int semester = Integer.parseInt(req.getParameter("semester"));
        if (courseCode.isEmpty() || courseName.isEmpty()) {
            report = "Course code and course name cannot be empty!!!";
            req.setAttribute("data1", groupCourse);
        } else {
            Courses course = new Courses(0, courseCode, courseName, departmentId, semester);
            cd.addCourse(course);
            groupCourse = cd.getFullGroupOfCourse();
            List<Courses> newCourses = new ArrayList();
            newCourses.add(groupCourse.get(groupCourse.size() - 1));
            req.setAttribute("data1", newCourses);
        }
        List<Major> groupMajor = md.getFullGroupOfMajor();
        req.setAttribute("data2", groupMajor);
        req.setAttribute("data", groupDepartment);
        req.setAttribute("report", report);
        req.setAttribute("select", select);
        req.getRequestDispatcher("/views/admin/admin_courseDepartment.jsp").forward(req, resp);
    }

    public void selectCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String select = "COURSE";
        String report = "Process successful!";
        String search = req.getParameter("search").toUpperCase().trim();
        String option = req.getParameter("option");
        List<Departments> groupDepartment = dd.getGroupOfDepartment();
        List<Courses> groupCourse = cd.getFullGroupOfCourse();
        List<Courses> selectGroupCourse = new ArrayList();
        if (option.equalsIgnoreCase("all")) {
            selectGroupCourse = getCoursesesByString(search, groupCourse);
        } else if (option.equalsIgnoreCase("name")) {
            selectGroupCourse = getCourseByPartOfName(search, groupCourse);
        } else if (option.equalsIgnoreCase("code")) {
            selectGroupCourse = getCourseByPartOfCourseCode(search, groupCourse);
        } else if (option.equalsIgnoreCase("depart")) {
            selectGroupCourse = getCourseByDepartment(search, groupCourse);
        } else {
            try {
                int semester = Integer.parseInt(search);
                selectGroupCourse = getCourseBySemester(semester, selectGroupCourse);
            } catch (NumberFormatException e) {
                report = "Semester must be a number!!!";
            }
        }
        List<Major> groupMajor = md.getFullGroupOfMajor();
        req.setAttribute("data2", groupMajor);
        req.setAttribute("report", report);
        req.setAttribute("select", select);
        req.setAttribute("data", groupDepartment);
        req.setAttribute("data1", selectGroupCourse);
        req.getRequestDispatcher("/views/admin/admin_courseDepartment.jsp").forward(req, resp);
    }

    private List<Courses> getCourseByDepartment(String department, List<Courses> fullListOfCourse) {
        List<Courses> list = new ArrayList();
        for (Courses courses : fullListOfCourse) {

            if (courses.getDepartmentName().toUpperCase().contains(department)) {
                list.add(courses);
            }
        }
        return list;
    }

    private List<Courses> getCourseByPartOfName(String name, List<Courses> fullListOfCourse) {
        List<Courses> list = new ArrayList();
        for (Courses courses : fullListOfCourse) {
            if (courses.getCourseName().toUpperCase().contains(name)) {
                list.add(courses);
            }
        }
        return list;
    }

    private List<Courses> getCourseByPartOfCourseCode(String code, List<Courses> fullListOfCourse) {
        List<Courses> list = new ArrayList();
        for (Courses courses : fullListOfCourse) {
            String courseCode = courses.getCourseCode().toUpperCase();
            if (courseCode.contains(code) || courseCode.equalsIgnoreCase(code)) {
                list.add(courses);
            }
        }
        return list;
    }

    private List<Courses> getCourseBySemester(int semester, List<Courses> fullListOfCourse) {
        List<Courses> list = new ArrayList();
        for (Courses courses : fullListOfCourse) {
            if (courses.getSemester() == semester) {
                list.add(courses);
            }
        }
        return list;
    }

    private List<Courses> getCoursesesByString(String str, List<Courses> fullListOfCourse) {
        List<Courses> list = new ArrayList();
        for (Courses courses : fullListOfCourse) {
            if (courses.getCourseCode().toUpperCase().contains(str) || courses.getCourseName().toUpperCase().contains(str)
                    || courses.getDepartmentName().toUpperCase().contains(str)) {
                list.add(courses);
            }
        }
        return list;
    }

}
