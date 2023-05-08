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
import model.Buildings;

/**
 *
 * @author nguyn
 */
public class BuildingDao extends DBContext {

    Connection cnn;
    PreparedStatement st;
    ResultSet rs;

    public BuildingDao() {
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

    public List<Buildings> getGroupOfBuilding() {
        List<Buildings> list = new ArrayList();
        String sql = "Select * from Buildings";
        try {
            st = cnn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Buildings building = new Buildings(rs.getInt(1), rs.getString(2));
                list.add(building);
            }
        } catch (SQLException e) {
            System.out.println("getGroupOfBuilding: " + e.getMessage());
        }
        return list;
    }

    public void addBuilding(String name) {
        String sql = "insert into Buildings values (?)";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, name);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("addBuilding" + e.getMessage());
        }
    }

    public int getBuildingIdByName(String name) {
        String sql = "Select building_id from Buildings where building_name = ?";
        int id = 0;
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, name);
            rs = st.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("getBuildingIdByName: " + e.getMessage());
        }
        return id;

    }

    public String getBuildingName(int id) {
        String sql = "Select building_name from Buildings where building_id = ?";
        String name = "";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("getBuildingName: " + e.getMessage());
        }
        return name;
    }

    public Buildings deleteBuildingById(int id) {
        Buildings delete = getBuildingById(id);
        String sql = "Delete from Buildings where building_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            return delete;
        } catch (SQLException e) {
            System.out.println("deleteBuildingById: " + e.getMessage());
        }
        return null;
    }

    public Buildings getBuildingById(int id) {
        String sql = "Select * from Buildings where building_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Buildings b = new Buildings(rs.getInt(1), rs.getString(2));
                return b;
            }

        } catch (SQLException e) {
            System.out.println("deleteDepartment: " + e.getMessage());
        }
        return null;
    }
}
