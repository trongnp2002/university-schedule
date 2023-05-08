/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Departments;

/**
 *
 * @author nguyn
 */
public class DepartmentDao extends DBContext{
    
    public void createDepartment(Departments department){
            String sql = "insert into Departments values (?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, department.getDepartmentsId());
            st.setString(2, department.getDepartmentName());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("createDepartment" + e.getMessage());
        }
    }
}
