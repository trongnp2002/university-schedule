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
import model.Classes;
import model.TimeSlots;

/**
 *
 * @author nguyn
 */
public class DaoClass extends DBContext {

    Connection cnn;
    PreparedStatement st;
    ResultSet rs;

    public DaoClass() {
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

    //insert Class infor
    public void createClass(Classes theClass) {
        String sql = "insert into Classes values(? ,? ,? ,?, ?)";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, theClass.getClassID());
            st.setString(2, theClass.getClassName());
            st.setString(3, theClass.getCourseId());
            st.setString(4, theClass.getRoomId());
            st.setString(5, theClass.getProfessorID());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("createTimeSlot: " + e.getMessage());
        }
    }

    //update Class infor
    public Classes updateClass(String id, Classes theClass) {
        if (!isExit(id)) {
            return null;
        }
        String sql = "update Classes set class_name = ?, course_id = ?, room_id = ?"
                + ", professor_id = ? where class_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, theClass.getClassName());
            st.setString(2, theClass.getClassName());
            st.setString(3, theClass.getCourseId());
            st.setString(4, theClass.getRoomId());
            st.setString(5, theClass.getProfessorID());
            st.setString(6, id);
            st.executeUpdate();
            return theClass;
        } catch (SQLException e) {
            System.out.println("updateStudent: " + e.getMessage());
        }
        return null;
    }

    // delete Class infor
    public Classes deleteClass(String id) {
        if (!isExit(id)) {
            return null;
        }
        Classes theClass = selectClassById(id);
        String sql = "Delete from Classes where class_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, sql);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("deleteClass: " + e.getMessage());
        }
        return theClass;
    }

    //select Class Infor
    public Classes selectClassById(String id) {
        String sql = "Select  * from Classes where class_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Classes theClass = new Classes();
                theClass.setClassID(rs.getString(1));
                theClass.setClassName(rs.getString(2));
                theClass.setCourseId(rs.getString(3));
                theClass.setRoomId(rs.getString(4));
                theClass.setProfessorID(rs.getString(5));
                return theClass;
            }
        } catch (SQLException e) {
            System.out.println("selectClassById: " + e.getMessage());
        }
        return null;
    }

    //get list of class by condition
    public List<Classes> getListClassesById(String id) {
        id = "%" + id + "%";
        List<Classes> listOfClassById = new ArrayList();
        String sql = "Select * from Classes where class_id like ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Classes theClass = new Classes();
                theClass.setClassID(rs.getString(1));
                theClass.setClassName(rs.getString(2));
                theClass.setCourseId(rs.getString(3));
                theClass.setRoomId(rs.getString(4));
                theClass.setProfessorID(rs.getString(5));
                listOfClassById.add(theClass);
            }
        } catch (SQLException e) {
            System.out.println("getListClassesById: " + e.getMessage());
        }
        return listOfClassById;
    }

    public List<Classes> getListClassesByName(String name) {
        name = "%" + name + "%";
        List<Classes> listOfClassByName = new ArrayList();
        String sql = "Select * from Classes where class_name like ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, name);
            rs = st.executeQuery();
            while (rs.next()) {
                Classes theClass = new Classes();
                theClass.setClassID(rs.getString(1));
                theClass.setClassName(rs.getString(2));
                theClass.setCourseId(rs.getString(3));
                theClass.setRoomId(rs.getString(4));
                theClass.setProfessorID(rs.getString(5));
                listOfClassByName.add(theClass);
            }
        } catch (SQLException e) {
            System.out.println("getListClassesByName: " + e.getMessage());
        }
        return listOfClassByName;
    }

    public List<Classes> getListClassesByCourse(String course) {
        course = "%" + course + "%";
        List<Classes> listOfClassByCourse = new ArrayList();
        String sql = "Select * from Classes where course_id like ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, course);
            rs = st.executeQuery();
            while (rs.next()) {
                Classes theClass = new Classes();
                theClass.setClassID(rs.getString(1));
                theClass.setClassName(rs.getString(2));
                theClass.setCourseId(rs.getString(3));
                theClass.setRoomId(rs.getString(4));
                theClass.setProfessorID(rs.getString(5));
                listOfClassByCourse.add(theClass);
            }
        } catch (SQLException e) {
            System.out.println("getListClassesByCourse: " + e.getMessage());
        }
        return listOfClassByCourse;
    }

    public boolean isExit(String id) {
        String sql = "Select  * from Classes where class_id =?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("isExit: " + e.getMessage());
        }
        return false;
    }

//test by console

    public static void main(String[] args) {
        DaoClass dca = new DaoClass();
        Classes theClass = dca.selectClassById("SE1712PRJ301");
        System.out.println(theClass);
    }
}
