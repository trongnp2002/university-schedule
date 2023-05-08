/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.BuildingDao;
import dal.RoomDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Buildings;
import model.Departments;
import model.Rooms;
import model.Students;

/**
 *
 * @author nguyn
 */
public class ArchitectureService {

    BuildingDao bd = new BuildingDao();
    RoomDao rd = new RoomDao();

    public void addBuilding(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String report = "";
        String building = req.getParameter("building").trim();
        bd.addBuilding(building);
        report = "process sucess!";
        req.setAttribute("report", report);
        String select = "BUILDING";
        req.setAttribute("select", select);
        getGroupArchitecture(req, resp);
    }

    public void addRoom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String select = req.getParameter("sub");
        String report = "";
        String building = req.getParameter("selectBulding");
        String floor = req.getParameter("floor");
        int roomFloor = Integer.parseInt(floor);
        int buildingId = bd.getBuildingIdByName(building);
        String roomName = rd.setRoomName(buildingId, roomFloor);
        Rooms room = new Rooms(0, roomName, roomFloor, buildingId);
        rd.addRoom(room);
        select = "ROOM";
        List<Rooms> groupOfRoom = rd.getGroupOfRoomByParfOfName(roomName);
        List<Buildings> groupOfBuilding = bd.getGroupOfBuilding();
        req.setAttribute("rooms", groupOfRoom);
        req.setAttribute("data", groupOfBuilding);
        req.setAttribute("report", report);
        req.setAttribute("select", select);
        req.getRequestDispatcher("/views/admin/admin_architecture.jsp").forward(req, resp);
    }

    public void getGroupOfRoomsByCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option");
        String search = req.getParameter("search").trim();
        List<Rooms> groupOfRoom = new ArrayList();
        String report = "";
        if (option.equals("buldingNameOption")) {
            groupOfRoom = rd.getGroupOfRoomByBuldingName(search);
        }
        if (option.equals("floorOption")) {
            try {
                int floor = Integer.parseInt(search);
                groupOfRoom = rd.getGroupOfRoomByFloor(floor);
            } catch (NumberFormatException e) {
                report = "invalid number";
            }
        }
        if (option.equals("roomName")) {
            groupOfRoom = rd.getGroupOfRoomByParfOfName(search);
        }
        String select = "ROOM";
        req.setAttribute("select", select);
        List<Buildings> groupOfBuilding = bd.getGroupOfBuilding();
        req.setAttribute("data", groupOfBuilding);
        req.setAttribute("rooms", groupOfRoom);
        req.setAttribute("report", report);
        req.getRequestDispatcher("/views/admin/admin_architecture.jsp").forward(req, resp);
    }

    public void getGroupArchitecture(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Buildings> groupOfBuilding = bd.getGroupOfBuilding();
        List<Rooms> groupOfRoom = rd.getGroupOfRoom();
        req.setAttribute("rooms", groupOfRoom);
        req.setAttribute("data", groupOfBuilding);
        req.getRequestDispatcher("/views/admin/admin_architecture.jsp").forward(req, resp);
    }

    public void deleteBuilding(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String report = "Procces sucessful";
        Buildings building = bd.deleteBuildingById(Integer.parseInt(id));
        if (building == null) {
            report = "delete fail!";
        } else {
            report = "Delete department success: id: " + building.getBuildingId() + " name: " + building.getBuildingName();
        }
        String select = "BUILDING";
        List<Buildings> groupOfBuilding = bd.getGroupOfBuilding();
        req.setAttribute("report", report);
        req.setAttribute("select", select);
        req.setAttribute("data", groupOfBuilding);
        req.getRequestDispatcher("/views/admin/admin_architecture.jsp").forward(req, resp);
    }

    public void deleteRoom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String report = "Procces sucessful";
        Rooms room = rd.getRoomById(Integer.parseInt(id));
        if (room == null) {
            report = "delete fail!";
        } else {
            rd.deleteRoom(Integer.parseInt(id));
            report = "Delete room success: Room name " + room.getName() + " buildung: " + room.getBuildingName();
        }
        String select = "ROOM";
        req.setAttribute("report", report);
        req.setAttribute("select", select);
        getGroupArchitecture(req, resp);

    }

}
