/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.home;

import exception.MyOwnRuntimeException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import service.AccountService;

/**
 *
 * @author nguyn
 */
public class LoginController extends HttpServlet {
     private AccountService accS = new AccountService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/login":
                accS.login(req, resp);
                break;
            case "/logout":
                break;
            default:
                throw new MyOwnRuntimeException("Unknow action: " + action);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/login":
                req.getRequestDispatcher("/views/login/login.jsp").forward(req, resp);
                break;
            case "/logout":
                accS.logout(req, resp);
                break;
            default:
                throw new MyOwnRuntimeException("Unknow action: " + action);
        }
    }

}
