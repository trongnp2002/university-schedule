/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import service.DepartmentAndCourseService;

/**
 *
 * @author nguyn
 */
public class AdminDepartmentAndCourseController extends HttpServlet {

    DepartmentAndCourseService dacs = new DepartmentAndCourseService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin_department/department":
                dacs.departmentAndCourseStart(req, resp);
                break;
            case "/admin_course/course":
                String submit = req.getParameter("sub");
                if (submit.equalsIgnoreCase("SELECT")) {
                    dacs.selectCourse(req, resp);
                } else {
                    dacs.addCourse(req, resp);
                }
                break;
            case "/admin_major/major":
                dacs.addMajor(req, resp);
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin_department/sdelete":
                dacs.deleteDepartments(req, resp);
                break;
            case "/admin_course/sdelete":
                dacs.deleteCourse(req, resp);
                break;
            case "/admin_major/sdelete":
                dacs.deleteMajor(req, resp);
                break;
            default:
                throw new AssertionError();
        }
    }

}
