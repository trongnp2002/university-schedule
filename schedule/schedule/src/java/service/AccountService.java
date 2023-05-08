/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.AccountDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Accounts;



/**
 *
 * @author nguyn
 */
public class AccountService {

    private AccountDao ad = new AccountDao();

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String message = "";
        Accounts userAccount = ad.login(account, password);
        if(userAccount == null){
            boolean checkEmail = ad.checkEmail(account);
            if(checkEmail == false){
                message ="Your account not exits!";
            }else{
                message = "Your password is not correct!";
            }
            req.setAttribute("account", account);
            req.setAttribute("message", message);
            req.getRequestDispatcher("/schedule/login").forward(req, resp);
        }else{
            HttpSession session = req.getSession();
            session.setAttribute("account", userAccount);
            if(userAccount.getRoll()== 3){
                resp.sendRedirect(req.getContextPath() + "/admin/admin_home");
            }
        }
    }
}
