/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.AccountDao;
import dal.AttendanceDao;
import dal.ClassDao;
import dal.MajorDao;
import dal.StudentDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Accounts;
import model.Major;
import model.Students;
import util.Validation;

/**
 *
 * @author nguyn
 */
public class StudentService {

    private StudentDao dsa = new StudentDao();
    private AccountDao daa = new AccountDao();
    private MajorDao md = new MajorDao();
    private AttendanceDao ad = new AttendanceDao();
    private ClassDao cd = new ClassDao();

    public void createStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception {
        String select = "SELECT";
        String report = "Process success !!!";
//////////////////////////////////////////////////////////////////////////////////////////
        String name = req.getParameter("studentName").trim();
        name = Validation.standardizedName(name);
        String phoneNumber = req.getParameter("studentPhone").trim();
        String majorId = req.getParameter("studentMajor").trim();
        String yearOfStudy = req.getParameter("studentYear").trim();
        String address = req.getParameter("studentAddress").trim();
        if (!Validation.checkPhoneNum(phoneNumber)) {
            report = "Invalid phone number!!";
        } else {
            int mId = Integer.parseInt(majorId);
            String majorName = md.getMajorById(mId).getMajorName();
            Students studentCre = new Students(0, name, "", phoneNumber, mId, yearOfStudy, address, majorName);

            try {
                studentCre = dsa.createStudent(studentCre);
                Accounts acc = new Accounts(studentCre.getStudentID(), studentCre.getEmail(), Validation.getPasswordAuto(name, md.getMajorById(mId).getMajorName(), studentCre.getStudentID()), 1, studentCre.getStudentID(), 0);
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
        List<Major> groupMajor = md.getFullGroupOfMajor();
        req.setAttribute("data2", groupMajor);
        req.setAttribute("select", select);
        req.setAttribute("report", report);
        req.getRequestDispatcher("/views/admin/admin_student.jsp").forward(req, resp);
    }

    public void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String select = "SELECT";
        String report = "Process success !!!";
        String name = req.getParameter("studentName").trim();
        if (name.length() != 0) {
            name = Validation.standardizedName(name);
        }
        String phoneNumber = req.getParameter("studentPhone").trim();
        String majorId = req.getParameter("studentMajor");
        String yearOfStudy = req.getParameter("studentYear").trim();
        String address = req.getParameter("studentAddress").trim();
        if (phoneNumber.length() != 0 && !Validation.checkPhoneNum(phoneNumber)) {
            report = "Invalid phone number!!";
        } else {
            int mId = Integer.parseInt(majorId);
            String email = req.getParameter("studentEmail").trim();
            String id = req.getParameter("studentID").trim();
            int studentId = Integer.parseInt(id);
            Students studentUpd = new Students(studentId, name, email, phoneNumber, mId, yearOfStudy, address);

            try {
                if (!dsa.isExit(studentId)) {
                    report = "This id is not exit!";
                } else {
                    dsa.updateStudent(studentId, studentUpd);
                    dsa.updateEmail(studentId, email);
                    List<Students> groupOfStudent = dsa.selectStudentById(id);
                    req.setAttribute("data", groupOfStudent);
                }
            } catch (Exception e) {
                report = e.getMessage();
            }
            req.setAttribute("studentupd", studentUpd);
        }
        List<Major> groupMajor = md.getFullGroupOfMajor();
        req.setAttribute("data2", groupMajor);
        req.setAttribute("select", select);
        req.setAttribute("report", report);
        req.getRequestDispatcher("/views/admin/admin_student.jsp").forward(req, resp);
    }

    public void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String select = "DELETE";
        String report = "Process success !!!";
        String id = req.getParameter("id");
        Students studentDel = null;
        Accounts acc = null;
        try {
            acc = daa.deleteAccountsById(Integer.parseInt(id));
            ad.removeAllAttendanceOfStudent(Integer.parseInt(id));
            cd.removeStudentFromAllClass(Integer.parseInt(id));
            studentDel = dsa.deleteStudent(Integer.parseInt(id));
            if (studentDel == null) {
                report = "Student id is not exit!";
            }
        } catch (Exception e) {
            report = "Invalid value";
        }
        req.setAttribute("inputDel", id);
        req.setAttribute("studentDel", studentDel);
        req.setAttribute("accountDel", acc);
        List<Major> groupMajor = md.getFullGroupOfMajor();
        List<Students> myListStudent = dsa.selectFullListOfStudents();
        req.setAttribute("data", myListStudent);
        req.setAttribute("data2", groupMajor);
        req.setAttribute("select", select);
        req.setAttribute("report", report);
        req.getRequestDispatcher("/views/admin/admin_student.jsp").forward(req, resp);

    }

    public void deleteStudentInSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String theId = req.getParameter("id");
        if (theId != null) {
            dsa = new StudentDao();
            String report = "";
            String select = "SELECT";
            try {
                
                daa.deleteAccountsById(Integer.parseInt(theId));
                 cd.removeStudentFromAllClass(Integer.parseInt(theId));
                ad.removeAllAttendanceOfStudent(Integer.parseInt(theId));
                
                dsa.deleteStudent(Integer.parseInt(theId));
                report = "Process success";
            } catch (Exception ex) {
                report = ex.getMessage();
            }
            String inputUser = req.getParameter("search");
            String option = req.getParameter("option");
            String listIsEmpty = "true";
            List<Students> myListStudent = dsa.selectFullListOfStudents();
            List<Major> groupMajor = md.getFullGroupOfMajor();
            req.setAttribute("data2", groupMajor);
            req.setAttribute("input", inputUser);
            req.setAttribute("data", myListStudent);
            req.setAttribute("select", select);
            req.setAttribute("report", report);
            req.getRequestDispatcher("/views/admin/admin_student.jsp").forward(req, resp);
        }

    }

    

    public void getFullListOfStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String select = "SELECT";
        String listIsEmpty = "true";
        List<Students> myListStudent = dsa.selectFullListOfStudents();

        if (myListStudent.isEmpty()) {
            req.setAttribute("isEmpty", listIsEmpty);
        }
        List<Major> groupMajor = md.getFullGroupOfMajor();
        req.setAttribute("data2", groupMajor);
        req.setAttribute("data", myListStudent);
        req.setAttribute("select", select);
        req.getRequestDispatcher("/views/admin/admin_student.jsp").forward(req, resp);
    }

    private String updateInSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String report = "Process success !!!";
        String name = req.getParameter("studentName");
        if (name.length() != 0) {
            name = Validation.standardizedName(name);
        }
        String phoneNumber = req.getParameter("studentPhone");
        String majorId = req.getParameter("studentMajor");
        String yearOfStudy = req.getParameter("studentYear");
        String address = req.getParameter("studentAddress");
        if (phoneNumber.length() != 0 && !Validation.checkPhoneNum(phoneNumber)) {
            report = "Invalid phone number!!";
        } else {
            String email = req.getParameter("studentEmail");
            String id = req.getParameter("studentID");
            int studentId = Integer.parseInt(id);
            int mid = Integer.parseInt(majorId);
            Students studentUpd = new Students(studentId, name, email, phoneNumber, mid, yearOfStudy, address);
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
        return report;
    }
    public void selectStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String select = req.getParameter("sub");
        String report = "";
        if (select.equalsIgnoreCase("ENTER")) {
            report = updateInSelect(req, resp);
        }
        ///lay lai list
        String inputUser = req.getParameter("search");
        String option = req.getParameter("option");
        String listIsEmpty = "true";
        List<Students> myListStudent = null;

        if (option.equals("all")) {
            myListStudent = dsa.selectStudentByString(inputUser);
        } else if (option.equals("id")) {
            try {
                int idStudent = Integer.parseInt(inputUser);
                myListStudent = dsa.selectStudentById(inputUser);
            } catch (NumberFormatException e) {
                report = "Student id must be Number";
            }

        } else if (option.equals("name")) {
            myListStudent = dsa.selectStudentByName(inputUser);
        } else if (option.equals("major")) {
            myListStudent = dsa.selectStudentByMajor(inputUser.toUpperCase());
        } else if (option.equals("year")) {
            myListStudent = dsa.selectStudentByYearOfStudy(inputUser);
        } else {
            myListStudent = dsa.selectStudentByAddress(inputUser);
        }
        if (myListStudent.isEmpty()) {
            req.setAttribute("isEmpty", listIsEmpty);
        }
        select = "SELECT";
        List<Major> groupMajor = md.getFullGroupOfMajor();
        req.setAttribute("data2", groupMajor);
        req.setAttribute("report", report);
        req.setAttribute("input", inputUser);
        req.setAttribute("data", myListStudent);
        req.setAttribute("select", select);
        req.getRequestDispatcher("/views/admin/admin_student.jsp").forward(req, resp);
    }
}
