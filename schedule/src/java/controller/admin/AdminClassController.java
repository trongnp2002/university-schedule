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
import service.ClassService;

/**
 *
 * @author nguyn
 */
public class AdminClassController extends HttpServlet {

    ClassService cs = new ClassService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin_class/classaction":
                String select = req.getParameter("sub");
                if (select.equalsIgnoreCase("ADD")) {
                    cs.createClass(req, resp);
                } else {
                    cs.selectClass(req, resp);
                }
                break;
            case "/admin_class/addCourse":
                String submit = req.getParameter("sub");
                if (submit != null && submit.equalsIgnoreCase("ADD")) {
                    cs.addClassCourseMethod(req, resp);
                } else {
                    cs.getFullAddInforAfterChooseDepartment(req, resp);
                }
                break;
            case "/admin_class/addStudent":
                cs.addStudentToClass(req, resp);
                break;

            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin_class/sdelete":
                cs.deleteClass(req, resp);
                break;
            case "/admin_class/classinfor":
                cs.getClassFullInfor(req, resp);
                break;
            case "/admin_class/studentInClass":
                cs.getGroupOfStudentInClass(req, resp);
                break;
            case "/admin_class/removeStu":
                cs.removeStuFromClass(req, resp);
                break;
            case "/admin_class/removeCourse":
                cs.removeCourseFromClass(req, resp);
                break;
            default:
                throw new AssertionError();
        }
    }

}
