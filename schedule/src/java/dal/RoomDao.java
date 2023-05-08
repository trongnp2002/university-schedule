/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Rooms;

/**
 *
 * @author nguyn
 */
public class RoomDao extends DBContext {

    Connection cnn;
    PreparedStatement st;
    ResultSet rs;
    private BuildingDao bd = new BuildingDao();

    public RoomDao() {
        connect();
    }

    public void connect() {
        try {
            cnn = super.connection;
            if (cnn != null) {
                System.out.println("Connect success");
            }
            System.out.println("Connect succesful");
        } catch (Exception e) {
            System.out.println("Connect fail: " + e.getMessage());
        }
    }

    public List<Rooms> getGroupOfRoom() {
        List<Rooms> list = new ArrayList();
        String sql = "Select * from Rooms";
        try {
            st = cnn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Rooms room = new Rooms(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getInt(3), bd.getBuildingName(rs.getInt(3)));
                list.add(room);
            }
        } catch (SQLException e) {
            System.out.println("getGroupOfBuilding: " + e.getMessage());
        }
        return list;
    }

    public void addRoom(Rooms room) {
        String sql = "insert into Rooms values(?, ?, ?)";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, room.getName());
            st.setInt(2, room.getBuildingId());
            st.setInt(3, room.getFloor());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("addRoom: " + e.getMessage());
        }

    }
    
    public Rooms getRoomById(int id){
        String sql = "Select * from Rooms where room_id = ?";
        try{
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Rooms room = new Rooms(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getInt(3), bd.getBuildingName(rs.getInt(3)));
                return room;
            }
            
        }catch(SQLException e){
            System.out.println("getRoomById: " + e.getMessage());
        }
        return null;
    }
    
    public int getRoomIdByName(String name){
        String sql = "select room_id from Rooms where room_name = ?";
        int roomId = 0;
        try{
            st = cnn.prepareStatement(sql);
            st.setString(1, name);
            rs = st.executeQuery();
            while(rs.next()){
                roomId = rs.getInt(1);
            }
        }catch(SQLException e){
            System.out.println("");
        }
        return roomId;
    }
    
    public void deleteRoom(int id){
        String sql = "Delete from Rooms where room_id = ?";
        try{
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        }catch(SQLException e){
            System.out.println("deleteRoom: " + e);
        }
    }

    public List<Rooms> getGroupOfRoomByBuldingName(String buildintName) {
        int buildingId = bd.getBuildingIdByName(buildintName);
        String sql = "Select * from Rooms where building_id = ? ";
        List<Rooms> list = new ArrayList();
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, buildingId);
            rs = st.executeQuery();
            while (rs.next()) {
                Rooms room = new Rooms(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getInt(3), bd.getBuildingName(rs.getInt(3)));
                list.add(room);
            }
        } catch (SQLException e) {
            System.out.println("getGroupOfRoomByBuldingName: " + e.getMessage());
        }
        return list;
    }

    public List<Rooms> getGroupOfRoomByParfOfName(String name) {
        name = "%" + name + "%";
        String sql = "Select * from Rooms where room_name like ? ";
        List<Rooms> list = new ArrayList();
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, name);
            rs = st.executeQuery();
            while (rs.next()) {
                Rooms room = new Rooms(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getInt(3), bd.getBuildingName(rs.getInt(3)));
                list.add(room);
            }
        } catch (SQLException e) {
            System.out.println("getGroupOfRoomByParfOfName: " + e.getMessage());
        }
        return list;
    }

    public List<Rooms> getGroupOfRoomByFloor(int floor) {
        String sql = "Select * from Rooms where floor = ? ";
        List<Rooms> list = new ArrayList();
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, floor);
            rs = st.executeQuery();
            while (rs.next()) {
                Rooms room = new Rooms(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getInt(3), bd.getBuildingName(rs.getInt(3)));
                list.add(room);
            }
        } catch (SQLException e) {
            System.out.println("getGroupOfRoomByFloor: " + e.getMessage());
        }
        return list;
    }

    public String selectLastRoomInXFloorAndYBuilding(int buildingId, int roomFloor) {
        String sql = "select top 1 room_name  from Rooms where building_id = ? and floor = ?  order by room_name desc";
        String name = "";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, buildingId);
            st.setInt(2, roomFloor);
            rs = st.executeQuery();
            while (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("selectLastRoomInXFloorAndYBuilding: " + e.getMessage());
        }
        return name;
    }

    public String setRoomName(int buildingId, int floor) {
        String lastName = selectLastRoomInXFloorAndYBuilding(buildingId, floor);
        String buildingName = bd.getBuildingName(buildingId);
        String name = "";
        String fistName = String.valueOf(buildingName.substring(0,2).toUpperCase());
        if (lastName.isEmpty()) {
            name = fistName + floor + "01";
        } else {
            int roomNumber = Integer.parseInt(lastName.substring(3, 5)) + 1;
            if (roomNumber < 10) {
                name = fistName+ floor + "0" + String.valueOf(roomNumber);
            } else {
                name = fistName + floor + String.valueOf(roomNumber);
            }
        }

        return name;
    }

    public static void main(String[] args) {
        RoomDao rd = new RoomDao();
        System.out.println(rd.selectLastRoomInXFloorAndYBuilding(1, 1));
        System.out.println(rd.setRoomName(1, 1));
    }
}
