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
import java.util.logging.Level;
import java.util.logging.Logger;
import service.ProfessorService;

/**
 *
 * @author nguyn
 */
public class AdminProfessorController extends HttpServlet {

    ProfessorService ps = new ProfessorService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin_professor/create":          
              try {
                ps.createProfessor(req, resp);
            } catch (Exception ex) {
                    System.out.println("admin_professor select: " + ex.getMessage());;
            }
            break;
            case "/admin_professor/select":
                String select = req.getParameter("sub");
                if (select.equalsIgnoreCase("UPDATE")) {
                    try {
                        ps.updateProfessor(req, resp);
                    } catch (Exception ex) {
                        System.out.println("admin_professor select: " + ex.getMessage());;
                    }
                } else {
                    try {
                        ps.selectListOfProfessor(req, resp);
                    } catch (Exception ex) {
                        System.out.println("admin_professor select: " + ex.getMessage());
                    }
                }
                break;
            case "/admin_professor/delete":
                ps.deleteProfessor(req, resp);
            break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           String action = req.getServletPath();
        switch (action) {
            case "/admin_professor/sdelete":
                ps.deleteProfessorInSelect(req, resp);
               break;
        }
    }

}
