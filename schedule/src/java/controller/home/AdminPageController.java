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
import service.ArchitectureService;
import service.ClassService;
import service.DepartmentAndCourseService;
import service.ProfessorService;
import service.SlotService;
import service.StudentService;

/**
 *
 * @author nguyn
 */
public class AdminPageController extends HttpServlet {

    private StudentService studentService = new StudentService();
    private AccountService accService = new AccountService();
    private SlotService slotService = new SlotService();
    private DepartmentAndCourseService dacs = new DepartmentAndCourseService();
    private ArchitectureService architectureService = new ArchitectureService();
    private ProfessorService professorService = new ProfessorService();
    private ClassService classService = new ClassService();
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
            case "/admin/admin_account":
                accService.getFullListAccount(req, resp);
                break;
            case "/admin/admin_professor":
                professorService.startProfessor(req, resp);
                break;
            case "/admin/admin_slot":
                slotService.getGroupFullSlots(req, resp);
                break;
            case "/admin/admin_department":
                dacs.departmentAndCourseStart(req, resp);
                break;
            case "/admin/admin_architecture":
                architectureService.getGroupArchitecture(req, resp);
                break;
            case "/admin/admin_class":
                classService.classHomeStart(req, resp);
                break;
            default:
                throw new MyOwnRuntimeException();
        }
    }

}
