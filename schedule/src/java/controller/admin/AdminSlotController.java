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
import service.SlotService;

/**
 *
 * @author nguyn
 */
public class AdminSlotController extends HttpServlet {
        SlotService slotService = new SlotService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin_slot/select":
                slotService.getGroupFullSlots(req, resp);
                break;
            default:
                throw new AssertionError();
        }
    }
        
        
}
