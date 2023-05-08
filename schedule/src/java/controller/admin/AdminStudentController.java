/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin;

import dal.AccountDao;
import dal.StudentDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Accounts;
import model.Students;
import service.StudentService;
import util.Validation;

/**
 *
 * @author nguyn
 */
public class AdminStudentController extends HttpServlet {

    private StudentService studentService = new StudentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin_student/select":
                studentService.selectStudent(req, resp);
                break;
            case "/admin_student/delete":
                studentService.deleteStudent(req, resp);
                break;
            case "/admin_student/update":
                studentService.updateStudent(req, resp);
                break;
            case "/admin_student/create":
            {
                try {
                    studentService.createStudent(req, resp);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
                break;

            default:
                throw new AssertionError();
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        if(action.equalsIgnoreCase("/admin_student/sdelete")){
            studentService.deleteStudentInSelect(req, resp);
        }
        
    }
}
