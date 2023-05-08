/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin;

import exception.MyOwnRuntimeException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.AccountService;

/**
 *
 * @author nguyn
 */
public class AdminAccountController extends HttpServlet {

    AccountService as = new AccountService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin_account/select":
                as.getListAccountWithCondition(req, resp);
                break;
            case "/admin_account/delete":
                as.deleteAccount(req, resp);
                break;
            case "/admin_account/create":
            {
                try {
                    as.createAccount(req, resp);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
                break;

            default:
                throw new MyOwnRuntimeException();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
//        switch (action) {
//            case "/admin_acc/select":
//
//                break;
//            default:
//                throw new MyOwnRuntimeException();
//        }

    if(action.equalsIgnoreCase("/admin_account/sdelete")){
        as.deleteAccountInSelect(req, resp);
        }
    }

}
