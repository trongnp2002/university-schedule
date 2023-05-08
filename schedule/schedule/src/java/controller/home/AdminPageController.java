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
import service.StudentService;

/**
 *
 * @author nguyn
 */
public class AdminPageController extends HttpServlet {

    private StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin/admin_home":
                req.getRequestDispatcher("/views/admin/admin_home.jsp").forward(req, resp);
                break;
            case "/admin/admin_student":
                studentService.getFullListOfStudent(req, resp);
                break;
            default:
                    throw new AssertionError();
        }
    }

}
