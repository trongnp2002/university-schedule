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

/**
 *
 * @author nguyn
 */
public class DepartmentDao extends DBContext {

    Connection cnn;
    PreparedStatement st;
    ResultSet rs;

    public DepartmentDao() {
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

    public void addDepartment(String name) {
        String sql = "insert into Departments values (?)";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, name);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("addDepartment" + e.getMessage());
        }
    }

    public Departments deleteDepartment(int id) {
        Departments d = getDepartmentById(id);
        String sql = "Delete from Departments where department_id = ?";
       
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            return d;
        } catch (SQLException e) {
            System.out.println("deleteDepartment: " + e.getMessage());
        }
        return null;
    }

    public Departments getDepartmentById(int id) {
        String sql = "Select * from Departments where department_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            while(rs.next()){
                Departments d = new Departments(rs.getInt(1),rs.getString(2));
                return d;
            }
            
        } catch (SQLException e) {
            System.out.println("deleteDepartment: " + e.getMessage());
        }
        return null;
    }

    public List<Departments> getGroupOfDepartment() {
        String sql = "select * from Departments";
        List<Departments> groupDepartment = new ArrayList();
        try {
            st = cnn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Departments dp = new Departments(rs.getInt(1), rs.getString(2));
                groupDepartment.add(dp);
            }
        } catch (SQLException e) {
            System.out.println("getGroupOfDepartment: " + e.getMessage());
        }
        return groupDepartment;
    }


    
    
}
