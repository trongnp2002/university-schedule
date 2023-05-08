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
import service.ArchitectureService;

/**
 *
 * @author nguyn
 */
public class AdminArchitectureController extends HttpServlet {

    ArchitectureService as = new ArchitectureService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin_architecture/building":
                as.addBuilding(req, resp);
                break;
            case "/admin_architecture/room":
                String select = req.getParameter("sub");
                if (select.equalsIgnoreCase("ADD")) {
                    as.addRoom(req, resp);
                }else{
                    as.getGroupOfRoomsByCondition(req, resp);
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin_architecture/buildingDelete":
                as.deleteBuilding(req, resp);
                break;
            case "/admin_architecture/roomDelete":
                as.deleteRoom(req, resp);
                break;
            default:
                throw new AssertionError();
        }
    }

}
