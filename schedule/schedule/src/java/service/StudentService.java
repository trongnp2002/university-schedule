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
import java.io.IOException;
import java.util.List;
import model.Accounts;
import model.Students;
import util.Validation;

/**
 *
 * @author nguyn
 */
public class StudentService {

    private StudentDao dsa = new StudentDao();
    private AccountDao daa = new AccountDao();

    public void createStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception {
        String select = "SUBMIT";
        String report = "Process success !!!";
////////////////////////////////////////////////////////////////////////////////////////
        String name = req.getParameter("studentName");
        name = Validation.standardizedName(name);
        String phoneNumber = req.getParameter("studentPhone");
        String major = req.getParameter("studentMajor");
        String yearOfStudy = req.getParameter("studentYear");
        String address = req.getParameter("studentAddress");
        if (!Validation.checkPhoneNum(phoneNumber)) {
            report = "Invalid phone number!!";
        } else {
            Students studentCre = new Students(0, name, "", phoneNumber, major, yearOfStudy, address);

            try {
                studentCre = dsa.createStudent(studentCre);
                Accounts acc = new Accounts(studentCre.getStudentID(), studentCre.getEmail(), Validation.getPasswordAuto(name, major, studentCre.getStudentID()), 1, studentCre.getStudentID(), 0);
                daa.createAccount(acc);
                List<Students> list = dsa.selectStudentById(String.valueOf(studentCre.getStudentID()));
                req.setAttribute("data", list);
            } catch (Exception e) {
                System.out.println("admin_create" + e);
                report = e.getMessage();
                select = "CREATE";
                dsa.deleteStudent(studentCre.getStudentID());

            }
            req.setAttribute("studentcre", studentCre);
        }
        req.setAttribute("select", select);
        req.setAttribute("report", report);
        req.getRequestDispatcher("/views/admin/admin_student.jsp").forward(req, resp);
    }

    public void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String select = "UPDATE";
        String report = "Process success !!!";
        String name = req.getParameter("studentName");
        if (name.length() != 0) {
            name = Validation.standardizedName(name);
        }
        String phoneNumber = req.getParameter("studentPhone");
        String major = req.getParameter("studentMajor");
        String yearOfStudy = req.getParameter("studentYear");
        String address = req.getParameter("studentAddress");
        if (phoneNumber.length() != 0 && !Validation.checkPhoneNum(phoneNumber)) {
            report = "Invalid phone number!!";
        } else {
            String email = req.getParameter("studentEmail");
            String id = req.getParameter("studentID");
            int studentId = Integer.parseInt(id);
            Students studentUpd = new Students(studentId, name, email, phoneNumber, major, yearOfStudy, address);
            try {
                if (!dsa.isExit(studentId)) {
                    report = "This id is not exit!";
                } else {
                    dsa.updateStudent(studentId, studentUpd);
                    dsa.updateEmail(studentId, email);
                }
            } catch (Exception e) {
                report = e.getMessage();
            }
            req.setAttribute("studentupd", studentUpd);
        }
        req.setAttribute("select", select);
        req.setAttribute("report", report);
        req.getRequestDispatcher("/views/admin/admin_student.jsp").forward(req, resp);
    }

    public void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String select = "DELETE";
        String report = "Process success !!!";
        String id = req.getParameter("id");
        Students studentDel = null;
        try {
            studentDel = dsa.deleteStudent(Integer.parseInt(id));
            if (studentDel == null) {
                report = "Student id is not exit!";
            }
        } catch (Exception e) {
            report = "Invalid value";
        }
        req.setAttribute("inputDel", id);
        req.setAttribute("studentDel", studentDel);
        req.setAttribute("select", select);
        req.setAttribute("report", report);
        req.getRequestDispatcher("/views/admin/admin_student.jsp").forward(req, resp);

    }

    public void deleteStudentInSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String theId = req.getParameter("id");
        if (theId != null) {
            dsa = new StudentDao();
            String report = "";
            String select = "SUBMIT";
            try {
                dsa.deleteStudent(Integer.parseInt(theId));
            } catch (Exception ex) {
                report = ex.getMessage();
            }
            String inputUser = req.getParameter("search");
            String option = req.getParameter("option");
            String listIsEmpty = "true";
            List<Students> myListStudent = null;
            report = "";
            if (option.equals("all")) {
                myListStudent = dsa.selectStudentByString(inputUser);
            } else if (option.equals("id")) {
                myListStudent = dsa.selectStudentById(inputUser);
            } else if (option.equals("name")) {
                myListStudent = dsa.selectStudentByName(inputUser);
            } else if (option.equals("major")) {
                myListStudent = dsa.selectStudentByMajor(inputUser);
            } else if (option.equals("year")) {
                myListStudent = dsa.selectStudentByYearOfStudy(inputUser);
            } else {
                myListStudent = dsa.selectStudentByAddress(inputUser);
            }
            if (myListStudent.isEmpty()) {
                req.setAttribute("isEmpty", listIsEmpty);
            }
            req.setAttribute("input", inputUser);
            req.setAttribute("data", myListStudent);
            req.setAttribute("select", select);
            req.setAttribute("report", report);
            req.getRequestDispatcher("/views/admin/admin_student.jsp").forward(req, resp);
        }

    }

    public void selectStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String select = req.getParameter("sub");
        if (select.equalsIgnoreCase("ENTER")) {
            updateInSelect(req, resp);
        }
        ///lay lai list
        String inputUser = req.getParameter("search");
        String option = req.getParameter("option");
        String listIsEmpty = "true";
        List<Students> myListStudent = null;
        if (option.equals("all")) {
            myListStudent = dsa.selectStudentByString(inputUser);
        } else if (option.equals("id")) {
            myListStudent = dsa.selectStudentById(inputUser);
        } else if (option.equals("name")) {
            myListStudent = dsa.selectStudentByName(inputUser);
        } else if (option.equals("major")) {
            myListStudent = dsa.selectStudentByMajor(inputUser);
        } else if (option.equals("year")) {
            myListStudent = dsa.selectStudentByYearOfStudy(inputUser);
        } else {
            myListStudent = dsa.selectStudentByAddress(inputUser);
        }
        if (myListStudent.isEmpty()) {
            req.setAttribute("isEmpty", listIsEmpty);
        }
        select = "SUBMIT";
        req.setAttribute("input", inputUser);
        req.setAttribute("data", myListStudent);
        req.setAttribute("select", select);
        req.getRequestDispatcher("/views/admin/admin_student.jsp").forward(req, resp);
    }

    public void getFullListOfStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String select = "SUBMIT";
        String listIsEmpty = "true";
        List<Students> myListStudent = dsa.selectFullListOfStudents();

        if (myListStudent.isEmpty()) {
            req.setAttribute("isEmpty", listIsEmpty);
        } 
        req.setAttribute("data", myListStudent);
        req.setAttribute("select", select);
        req.getRequestDispatcher("/views/admin/admin_student.jsp").forward(req, resp);
    }

    private void updateInSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String report = "Process success !!!";
        String name = req.getParameter("studentName");
        if (name.length() != 0) {
            name = Validation.standardizedName(name);
        }
        String phoneNumber = req.getParameter("studentPhone");
        String major = req.getParameter("studentMajor");
        String yearOfStudy = req.getParameter("studentYear");
        String address = req.getParameter("studentAddress");
        if (phoneNumber.length() != 0 && !Validation.checkPhoneNum(phoneNumber)) {
            report = "Invalid phone number!!";
        } else {
            String email = req.getParameter("studentEmail");
            String id = req.getParameter("studentID");
            int studentId = Integer.parseInt(id);
            Students studentUpd = new Students(studentId, name, email, phoneNumber, major, yearOfStudy, address);
            try {
                if (!dsa.isExit(studentId)) {
                    report = "This id is not exit!";
                } else {
                    dsa.updateStudent(studentId, studentUpd);
                    dsa.updateEmail(studentId, email);
                }
            } catch (Exception e) {
                report = e.getMessage();
            }
            req.setAttribute("studentupd", studentUpd);
        }
        req.setAttribute("report", report);
    }

    private void createAccountAuto(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
