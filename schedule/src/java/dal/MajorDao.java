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
import model.Departments;
import model.Major;

/**
 *
 * @author nguyn
 */
public class MajorDao extends DBContext {

    Connection cnn;
    PreparedStatement st;
    ResultSet rs;

    public MajorDao() {
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

    public List<Major> getFullGroupOfMajor() {
        List<Major> list = new ArrayList();
        String sql = "select * from Major";
        try {
            st = cnn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Major major = new Major(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(major);
            }
        } catch (SQLException e) {
            System.out.println("getFullGroupOfMajor: " + e.getMessage());
        }
        return list;
    }

    public void addMajor(Major major) {
        String sql = "insert into Major values(?, ?)";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, major.getMajorName());
            st.setString(2, major.getMajorGroup());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("addMajor: + " + e.getMessage());
        }
    }

    public Major deleteMajor(int majorId) {
        Major majorDel = getMajorById(majorId);

        String sql = "Delete from Major where major_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, majorId);
            st.executeQuery();
            return majorDel;
        } catch (SQLException e) {
            System.out.println("deleteMajor: + " + e.getMessage());

        }
        return null;
    }

    public Major getMajorById(int id) {
        String sql = "Select * from Major where major_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Major major = new Major(rs.getInt(1), rs.getString(2), rs.getString(3));
                return major;
            }

        } catch (SQLException e) {
            System.out.println("deleteDepartment: " + e.getMessage());
        }
        return null;
    }

}
