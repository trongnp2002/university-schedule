/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.AccountDao;
import dal.AttendanceDao;
import dal.DepartmentDao;
import dal.ProfessorDao;
import dal.StudentDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Accounts;
import model.Departments;
import model.Professors;
import model.Students;
import org.apache.jasper.tagplugins.jstl.core.Catch;
import util.Validation;

/**
 *
 * @author nguyn
 */
public class ProfessorService {
    
    ProfessorDao pd = new ProfessorDao();
    DepartmentDao dd = new DepartmentDao();
    AccountDao ad = new AccountDao();
    AttendanceDao add = new AttendanceDao();
    
    public void startProfessor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Professors> listOfProfessor = pd.getGroupOfProfessor();
        List<Departments> listOfDepartment = dd.getGroupOfDepartment();
        req.setAttribute("data", listOfProfessor);
        req.setAttribute("select", "SELECT");
        req.setAttribute("department", listOfDepartment);
        req.getRequestDispatcher("/views/admin/admin_professor.jsp").forward(req, resp);
    }
    
    public void createProfessor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception {
        String professorName = req.getParameter("professorName").trim();
        String id = req.getParameter("department").trim();
        int departmentId = Integer.parseInt(id);
        Professors p = new Professors();
        try {
            String nickname = pd.createProfessorInfor(professorName, departmentId);
            p = pd.getProfessorByNickName(nickname);
            Accounts acc = new Accounts((p.getProfessorId() + 1), p.getEmail(), nickname, 2, 0, p.getProfessorId());
            ad.createAccount(acc);
            List<Professors> list = new ArrayList();
            list.add(p);
            req.setAttribute("data", list);
            req.setAttribute("select", "SELECT");
        } catch (Exception e) {
            ad.deleteAccountsById(p.getProfessorId());
        }
        List<Departments> listOfDepartment = dd.getGroupOfDepartment();
        req.setAttribute("department", listOfDepartment);
        req.getRequestDispatcher("/views/admin/admin_professor.jsp").forward(req, resp);
    }
    
    public void updateProfessor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception {
        String professorId = req.getParameter("professorId").trim();
        String professorName = req.getParameter("professorName").trim();
        String email = req.getParameter("professorEmail").trim();
        String departmentId = req.getParameter("department").trim();
        int dId = Integer.parseInt(departmentId);
        int pId = Integer.parseInt(professorId);
        Professors p = new Professors(pId, "", professorName, email, dId);
        pd.updateProfessor(p);
        List<Professors> updateProfessor = new ArrayList();
        Professors afterUpdate = pd.getProfessorInfor(pId);
        updateProfessor.add(afterUpdate);
        req.setAttribute("data", updateProfessor);
        req.setAttribute("select", "SELECT");
        List<Departments> listOfDepartment = dd.getGroupOfDepartment();
        req.setAttribute("department", listOfDepartment);
        req.getRequestDispatcher("/views/admin/admin_professor.jsp").forward(req, resp);
        req.getRequestDispatcher("/views/admin/admin_professor.jsp").forward(req, resp);
    }
    
    public void deleteProfessorInSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String theId = req.getParameter("id").trim();
        if (theId != null) {
            String report = "Process fails";
            String select = "SELECT";
            try {
                pd.removeAllProfessorClass(Integer.parseInt(theId));
                ad.deleteAccountsById((Integer.parseInt(theId) + 1));
                Professors p = pd.deleteProfessorInfor(Integer.parseInt(theId));
                report = "Delete success professer " + p.getNickname() + " have full name is: " + p.getName();
            } catch (Exception ex) {
                report = ex.getMessage();
            }
            List<Professors> listOfProfessor = pd.getGroupOfProfessor();
            List<Departments> listOfDepartment = dd.getGroupOfDepartment();
            req.setAttribute("data", listOfProfessor);
            req.setAttribute("department", listOfDepartment);
            req.setAttribute("select", select);
            req.setAttribute("report", report);
            req.getRequestDispatcher("/views/admin/admin_professor.jsp").forward(req, resp);
        }
        
    }
    
    public void selectListOfProfessor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception {
        String search = req.getParameter("search").trim();
        String option = req.getParameter("option").trim();
        List<Professors> list = new ArrayList();
        if (option.equalsIgnoreCase("nickname")) {
            list = pd.getListOfProfessorByNickName(search);
        }
        if (option.equalsIgnoreCase("name")) {
            list = pd.selectListProfessorsByName(search);
        }
        if (option.equalsIgnoreCase("department")) {
            list = pd.selectListProfessorsByDepartment(search);
        }
        if (option.equalsIgnoreCase("all")) {
            list = pd.selectListProfessorsByString(search);
        }
        List<Departments> listOfDepartment = dd.getGroupOfDepartment();
        req.setAttribute("department", listOfDepartment);
        req.setAttribute("select", "SELECT");
        req.setAttribute("data", list);
        req.setAttribute("report", "Process successfull!");
        req.getRequestDispatcher("/views/admin/admin_professor.jsp").forward(req, resp);
        
    }
    
    public void deleteProfessor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String select = "DELETE";
        String report = "Process success !!!";
        String nickname = req.getParameter("nickname").trim();
        Professors professorDel = null;
        Accounts acc = null;
        
        try {
            professorDel = pd.getProfessorByNickName(nickname);
            acc = ad.deleteAccountsById(professorDel.getProfessorId() + 1);
            pd.deleteProfessorByNickName(nickname);
            if (professorDel == null) {
                report = "Professor is not exit!";
            }
        } catch (Exception e) {
            report = "Invalid value";
        }
        List<Departments> listOfDepartment = dd.getGroupOfDepartment();
        req.setAttribute("inputDel", nickname);
        req.setAttribute("professorDel", professorDel);
        req.setAttribute("accountDel", acc);
        req.setAttribute("department", listOfDepartment);
        req.setAttribute("select", select);
        req.setAttribute("report", report);
        req.getRequestDispatcher("/views/admin/admin_professor.jsp").forward(req, resp);
        
    }
    
}
