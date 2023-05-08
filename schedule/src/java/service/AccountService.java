/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.AccountDao;
import dal.StudentDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Accounts;
import model.Students;
import util.Validation;

/**
 *
 * @author nguyn
 */
public class AccountService {

    private AccountDao ad = new AccountDao();

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String account = req.getParameter("account").trim();
        String password = req.getParameter("password").trim();
        String message = "";
        Accounts userAccount = ad.login(account, password);
        if (userAccount == null) {
            boolean checkEmail = ad.checkEmail(account);
            if (checkEmail == false) {
                message = "Your account not exits!";
            } else {
                message = "Your password is not correct!";
            }
            req.setAttribute("account", account);
            req.setAttribute("message", message);
            req.getRequestDispatcher("/views/login/login.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("account", userAccount);
            if (userAccount.getRoll() == 3) {
                resp.sendRedirect(req.getContextPath() + "/admin/admin_home");
            } else if (userAccount.getRoll() == 1) {
                resp.sendRedirect(req.getContextPath() + "/student/scheduleStudent");
            }else{
                resp.sendRedirect(req.getContextPath() + "/professor/professorSchedule");
            }
        }
    }

    public void getFullListAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String select = "SELECT";
        List<Accounts> list = ad.selectAllAccounts();
        list.remove(0);
        req.setAttribute("data", list);
        req.setAttribute("select", select);
        req.getRequestDispatcher("/views/admin/admin_account.jsp").forward(req, resp);
    }

    public void getListAccountWithCondition(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String select = req.getParameter("sub");
        if (select.equalsIgnoreCase("ENTER")) {
            updateInSelect(req, resp);
        }
        String op = req.getParameter("option");
        String input = req.getParameter("search").trim();
        List<Accounts> list = ad.selectAllAccounts();
        if (op.equalsIgnoreCase("id")) {
            list = ad.selectAccountsByPartOfId(input);
        } else if (op.equalsIgnoreCase("email")) {
            list = ad.selectAccountsByPartOfEmail(input);
        } else if (op.equalsIgnoreCase("position")) {
            list = ad.selectAccountsByRolll(input);
        } else {
            list = ad.selectAccountsByPartOfString(input);
        }
        if (list.get(0).getAccountId() == 1) {
            list.remove(0);
        }
        select = "SUBMIT";
        req.setAttribute("select", select);
        req.setAttribute("data", list);
        req.getRequestDispatcher("/views/admin/admin_account.jsp").forward(req, resp);
    }

    public void deleteAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String select = "DELETE";
        String report = "Process success !!!";
        String id = req.getParameter("id");
        Accounts acc = null;
        try {
            acc = ad.deleteAccountsById(Integer.parseInt(id));
            if (acc == null) {
                report = "Account id is not exit!";
            }
        } catch (Exception e) {
            report = "Invalid value";
        }
        req.setAttribute("inputDel", id);
        req.setAttribute("accountDel", acc);
        req.setAttribute("select", select);
        req.setAttribute("report", report);
        req.getRequestDispatcher("/views/admin/admin_account.jsp").forward(req, resp);

    }

    public void createAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception {
        String select = "CREATE";
        String report = "Process success !!!";
////////////////////////////////////////////////////////////////////////////////////////
        String accId = req.getParameter("accId").trim();
        String email = req.getParameter("email").trim();
        String pass = req.getParameter("password").trim();
        String re_pass = req.getParameter("re_pass").trim();
        String roll = req.getParameter("roll").trim();
        String stuId = req.getParameter("studentId").trim();
        String proId = req.getParameter("professorId").trim();
        List<Accounts> list = null;
        if (roll.equals("0")) {
            report = "You have not choose roll";
        } else if (!pass.equals(re_pass)) {
            report = "Your re-password was not correct!";
        } else {
            int pid = 0;
            int sid = 0;
            if (!proId.isEmpty()) {
                pid = Integer.parseInt(proId);
            }
            if (!stuId.isEmpty()) {
                sid = Integer.parseInt(stuId);
            }
            Accounts acc = new Accounts(Integer.parseInt(accId), email, pass, Integer.parseInt(roll), sid, pid);

            try {
                ad.createAccount(acc);
                list = ad.selectAccountsByPartOfId(accId);
                select = "SELECT";
            } catch (Exception e) {
                report = e.getMessage();
            }
        }
        req.setAttribute("data", list);
        req.setAttribute("select", select);
        req.setAttribute("report", report);
        req.getRequestDispatcher("/views/admin/admin_account.jsp").forward(req, resp);
    }

    public void deleteAccountInSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String theId = req.getParameter("id");
        if (theId != null) {
            ad = new AccountDao();
            String report = "Process success";
            String select = "SELECT";
            try {
                ad.deleteAccountsById(Integer.parseInt(theId));
            } catch (Exception ex) {
                report = ex.getMessage();
            }
            List<Accounts> list = ad.selectAllAccounts();
            list.remove(0);
            req.setAttribute("report", report);
            req.setAttribute("select", select);
            req.setAttribute("data", list);
            req.getRequestDispatcher("/views/admin/admin_account.jsp").forward(req, resp);
        }
    }

    private void updateInSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String report = "Process success !!!";
        String pass = req.getParameter("password").trim();
        String repass = req.getParameter("re_pass").trim();
        String accId = req.getParameter("accId").trim();
        if (pass.equals(repass)) {
            try {
                ad.changePassById(Integer.parseInt(accId), pass);
            } catch (Exception e) {
                report = e.getMessage();
            }
        } else {
            report = "Your repassword was incorrect!";
        }

        req.setAttribute("report", report);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/login");
    }

}
