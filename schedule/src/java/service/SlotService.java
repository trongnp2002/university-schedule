/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.SlotDao;
import dal.TimeSlotDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import model.Accounts;
import model.Slot;
import model.TimeSlots;

/**
 *
 * @author nguyn
 */
public class SlotService {

    SlotDao sd = new SlotDao();
    TimeSlotDao tsd = new TimeSlotDao();

    public void getGroupFullSlots(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String select = req.getParameter("sub");
        String report = "";
        if (select != null) {
            if (select.equalsIgnoreCase("ADD")) {
                report = createSlot(req, resp);
            }
            if (select.equalsIgnoreCase("UPDATE")) {
                report = updateSlot(req, resp);
            }
        }
        List<Slot> list = sd.getGroupOfSlot();
        req.setAttribute("data", list);
        req.setAttribute("select", select);
        req.setAttribute("report", report);
        req.getRequestDispatcher("/views/admin/admin_slot.jsp").forward(req, resp);
    }

    public String createSlot(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String startTime = req.getParameter("startTimeCreate");
        String endTime = req.getParameter("endTimeCreate");
        String report = "Process successfully";
        if (startTime.isEmpty() || endTime.isEmpty()) {
            return "You must input start time and end time";
        }
        try {
            Slot slot = new Slot(0, startTime, endTime);
            sd.createSlot(slot);
            createTimeSlotsAuto();
            List<Slot> list = sd.getGroupOfSlot();
        } catch (Exception e) {
            report = e.getMessage();
        }

        return report;
    }

    public String updateSlot(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String startTime = req.getParameter("startTimeUpdate");
        String endTime = req.getParameter("endTimeUpdate");
        String slotId = req.getParameter("slotId");
        if (startTime == null) {
            startTime = "";
        }
        if (endTime == null) {
            endTime = "";
        }
        String report = "Process successfully";
        Slot slot = new Slot(Integer.parseInt(slotId), startTime, endTime);
        try {
            sd.updateSlot(slot);
        } catch (Exception e) {
            report = e.getMessage();
        }
        return report;
    }

    private void createTimeSlotsAuto() {
        int slotId = getSlotLastId();
        LocalDate endDate = LocalDate.of(2023, 4, 12);
        for (LocalDate date = LocalDate.of(2023, 1, 1); date.isBefore(endDate); date = date.plusDays(1)) {
            String dayOfWeek = date.getDayOfWeek().toString();
            TimeSlots ts = new TimeSlots(0, dayOfWeek, date, slotId);
            tsd.addTimeSlot(ts);
        }
    }

    private int getSlotLastId() {
        List<Slot> list = sd.getGroupOfSlot();
        int lastId = list.get(list.size() - 1).getSlotId();
        return lastId;
    }
}
