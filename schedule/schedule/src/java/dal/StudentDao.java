/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import util.Validation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Classes;
import model.Students;

/**
 *
 * @author nguyn
 */
public class StudentDao extends DBContext {

    Connection cnn;
    PreparedStatement st;
    ResultSet rs;

    public StudentDao() {
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
//sql function

    public List<Students> selectFullListOfStudents() {
        List<Students> myStudentList = new ArrayList();
        String sql = "Select * from Students";
        try {
            st = cnn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Students student = new Students();
                student.setStudentID(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setEmail(rs.getString(3));
                student.setPhoneNumber(rs.getString(4));
                student.setMajor(rs.getString(5));
                student.setYearOfStudy(rs.getString(6));
                student.setAddress(rs.getString(7));
                myStudentList.add(student);
            }
        } catch (SQLException e) {
            System.out.println("getFullListOfStudents: " + e.getMessage());
        }
        return myStudentList;
    }

    public static void main(String[] args) {
//        String str = "he";
        StudentDao dsa = new StudentDao();
//        List<Students> list = dsa.selectStudentByString(str);
//        for (Students students : list) {
//            System.out.println(students);
//        }
        int a = dsa.setStudentIdAuto("K16");
        System.out.println(a);
    }

    //1. insert Student information
    public Students createStudent(Students student) throws Exception {
        if (checkDuplicateEmail(student.getEmail())) {
            throw new Exception("This email already exit!");
        }
        String sql = "insert into Students values (?, ? ,? ,? ,? ,?, ?)";
        int id = setStudentIdAuto(student.getYearOfStudy());
        String email = Validation.getMailAuto(student.getName(), id, student.getMajor());
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, student.getName());
            st.setString(3, email);
            st.setString(4, student.getPhoneNumber());
            st.setString(5, student.getMajor());
            st.setString(6, student.getYearOfStudy());
            st.setString(7, student.getAddress());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("createStudent: " + e.getMessage());
            return null;
        } catch (Exception et) {
            System.out.println("createStudent: " + et.getMessage());
        }
        student.setEmail(email);
        student.setStudentID(id);
        return student;
    }

    //2. update Student information
    public Students updateStudent(int id, Students student) throws Exception {
        checkStudentUpdate(id, student);
        String sql = "update Students set name = ?, phone_number = ?, major = ?, year_of_study = ?, address = ? where student_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, student.getName());
            st.setString(2, student.getPhoneNumber());
            st.setString(3, student.getMajor());
            st.setString(4, student.getYearOfStudy());
            st.setString(5, student.getAddress());
            st.setInt(6, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateStudent: " + e.getMessage());
        }
        return student;
    }

    public void updateEmail(int id, String email) throws Exception {
        if (checkDuplicateEmail(email)) {
            throw new Exception("email duplicate");
        }
        if (email.isEmpty()) {
            return;
        }
        String sql = "update Students set email = ? where student_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, email);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateEmail: " + e.getMessage());
        }
    }

    //3. delete Student information 
    public Students deleteStudent(int id) throws Exception {

        if (!isExit(id)) {
            throw new Exception("This id not exit!");
        }
        Students student = getStudentById(id);
        String sql = "Delete from Students where student_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    public Students getStudentById(int id) {
        String sql = "Select * from Students where student_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Students student = new Students();
                student.setStudentID(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setEmail(rs.getString(3));
                student.setPhoneNumber(rs.getString(4));
                student.setMajor(rs.getString(5));
                student.setYearOfStudy(rs.getString(6));
                student.setAddress(rs.getString(7));
                return student;
            }
        } catch (SQLException e) {
            System.out.println("getFullListOfStudents: " + e.getMessage());
        }
        return null;
    }
//select Student by condition

    public List<Students> selectStudentById(String id) {
        List<Students> listOfStudentFindById = new ArrayList();
        String sql = "Select * from Students where student_id like ?";
        id = "%" + id + "%";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Students student = new Students();
                student.setStudentID(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setEmail(rs.getString(3));
                student.setPhoneNumber(rs.getString(4));
                student.setMajor(rs.getString(5));
                student.setYearOfStudy(rs.getString(6));
                student.setAddress(rs.getString(7));
                listOfStudentFindById.add(student);
            }
        } catch (SQLException e) {
            System.out.println("selectStudentById: " + e.getMessage());
        }
        return listOfStudentFindById;
    }

    public List<Students> selectStudentByName(String name) {
        List<Students> listOfStudentFindByName = new ArrayList();
        String sql = "Select * from Students where name like ?";
        name = "%" + name + "%";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, name);
            rs = st.executeQuery();
            while (rs.next()) {
                Students student = new Students();
                student.setStudentID(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setEmail(rs.getString(3));
                student.setPhoneNumber(rs.getString(4));
                student.setMajor(rs.getString(5));
                student.setYearOfStudy(rs.getString(6));
                student.setAddress(rs.getString(7));
                listOfStudentFindByName.add(student);
            }
        } catch (SQLException e) {
            System.out.println("selectStudentByName: " + e.getMessage());
        }
        return listOfStudentFindByName;
    }

    public List<Students> selectStudentByYearOfStudy(String year) {
        List<Students> listOfStudentFindByYear = new ArrayList();
        String sql = "Select * from Students where year_of_study like ?";
        year = "%" + year + "%";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, year);
            rs = st.executeQuery();
            while (rs.next()) {
                Students student = new Students();
                student.setStudentID(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setEmail(rs.getString(3));
                student.setPhoneNumber(rs.getString(4));
                student.setMajor(rs.getString(5));
                student.setYearOfStudy(rs.getString(6));
                student.setAddress(rs.getString(7));
                listOfStudentFindByYear.add(student);
            }
        } catch (SQLException e) {
            System.out.println("selectStudentByYearOfStudy: " + e.getMessage());
        }
        return listOfStudentFindByYear;
    }

    public List<Students> selectStudentByMajor(String major) {
        List<Students> listOfStudentFindByMajor = new ArrayList();
        String sql = "Select * from Students where major like ?";
        major = "%" + major + "%";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, major);
            rs = st.executeQuery();
            while (rs.next()) {
                Students student = new Students();
                student.setStudentID(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setEmail(rs.getString(3));
                student.setPhoneNumber(rs.getString(4));
                student.setMajor(rs.getString(5));
                student.setYearOfStudy(rs.getString(6));
                student.setAddress(rs.getString(7));
                listOfStudentFindByMajor.add(student);
            }
        } catch (SQLException e) {
            System.out.println("selectStudentByMajor: " + e.getMessage());
        }

        return listOfStudentFindByMajor;
    }

    public List<Students> selectStudentByAddress(String address) {
        List<Students> listOfStudentFindByAddress = new ArrayList();
        String sql = "Select * from Students where major like ?";
        address = "%" + address + "%";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, address);
            rs = st.executeQuery();
            while (rs.next()) {
                Students student = new Students();
                student.setStudentID(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setEmail(rs.getString(3));
                student.setPhoneNumber(rs.getString(4));
                student.setMajor(rs.getString(5));
                student.setYearOfStudy(rs.getString(6));
                student.setAddress(rs.getString(7));
                listOfStudentFindByAddress.add(student);
            }
        } catch (SQLException e) {
            System.out.println("selectStudentByAddress: " + e.getMessage());
        }
        return listOfStudentFindByAddress;
    }

    public List<Students> selectStudentByString(String str) {
        List<Students> listOfStudentFindByString = new ArrayList();
        String sql = "Select * from Students where major like ? "
                + "or name like ? "
                + "or email like ? "
                + "or address like ? "
                + "or year_of_study like ?";
        str = "%" + str + "%";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, str);
            st.setString(2, str);
            st.setString(3, str);
            st.setString(4, str);
            st.setString(5, str);

            rs = st.executeQuery();
            while (rs.next()) {
                Students student = new Students();
                student.setStudentID(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setEmail(rs.getString(3));
                student.setPhoneNumber(rs.getString(4));
                student.setMajor(rs.getString(5));
                student.setYearOfStudy(rs.getString(6));
                student.setAddress(rs.getString(7));
                listOfStudentFindByString.add(student);
            }
        } catch (SQLException e) {
            System.out.println("selectStudentByString: " + e.getMessage());
        }
        return listOfStudentFindByString;
    }

    // select full student class by student id
    public List<Classes> getFullStudentClasses(String studentId) {
        List<Classes> listStudentClasses = new ArrayList();
        String sql = "select Classes.* from Classes join Enrollments "
                + "on Classes.class_id = Enrollments.class_id "
                + "where Enrollments.student_id = ?";

        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, studentId);
            rs = st.executeQuery();
            while (rs.next()) {
                Classes theClass = new Classes();
                theClass.setClassID(rs.getString(1));
                theClass.setClassName(rs.getString(2));
                theClass.setCourseId(rs.getString(3));
                theClass.setRoomId(rs.getString(4));
                theClass.setProfessorID(rs.getString(5));
                listStudentClasses.add(theClass);
            }
        } catch (SQLException e) {
            System.out.println("getFullStudentClasses: " + e.getMessage());
        } catch (Exception st) {
            System.out.println("getFullStudentClasses: " + st.getMessage());
        }
        return listStudentClasses;
    }

    public boolean isExit(int id) {
        String sql = "Select * from Students where student_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("isExit: " + e.getMessage());
        }
        return false;
    }

    public boolean checkDuplicateEmail(String email) {
        String sql = "Select * from Students where email = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, email);
            rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("checkDuplicateEmail: " + e.getMessage());
        }
        return false;
    }

    public void checkStudentUpdate(int id, Students student) {
        Students std = getStudentById(id);
        if (student.getName().isEmpty()) {
            student.setName(std.getName());
        }
        if (student.getMajor().isEmpty()) {
            student.setMajor(std.getMajor());
        }
        if (student.getPhoneNumber().isEmpty()) {
            student.setPhoneNumber(std.getPhoneNumber());
        }
        if (student.getYearOfStudy().isEmpty()) {
            student.setYearOfStudy(std.getYearOfStudy());
        }
        if (student.getAddress().isEmpty()) {
            student.setAddress(std.getAddress());
        }
    }

    public int setStudentIdAuto(String year) {
        int num = Validation.getYearStudyInt(year);
        int idSS = (num + 1) * 10000;
        int idAuto = num * 10000 + 1;
        String sql = "Select top 1 student_id from Students where student_id < ? order by student_id desc";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, idSS);
            rs = st.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) >= idAuto) {
                    idAuto = rs.getInt(1) + 1;
                }
            }
        } catch (SQLException e) {
            System.out.println("setStudentIdAuto: " + e.getMessage());
        }
        return idAuto;
    }
}
