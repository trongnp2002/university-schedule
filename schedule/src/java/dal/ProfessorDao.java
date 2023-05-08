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
import model.Departments;
import model.Professors;
import util.Validation;

/**
 *
 * @author nguyn
 */
public class ProfessorDao extends DBContext {

    private DepartmentDao dd = new DepartmentDao();
    Connection cnn;
    PreparedStatement st;
    ResultSet rs;

    public ProfessorDao() {
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

    //insert
    //get professor infor
    public Professors getProfessorInfor(int id) {
        String sql = "Select * from Professors where professor_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Professors professor = new Professors();
                professor.setProfessorId(rs.getInt(1));
                professor.setNickname(rs.getString(2));
                professor.setName(rs.getString(3));
                professor.setEmail(rs.getString(4));
                int departmentId = rs.getInt(5);
                professor.setDepartmentId(departmentId);
                Departments departmentName = dd.getDepartmentById(departmentId);
                professor.setDepartmentName(departmentName.getDepartmentName());
                return professor;
            }
        } catch (SQLException e) {
            System.out.println("getProfessorInfor: " + e.getMessage());
        }
        return null;
    }

    public int getProfessorIdByNickName(String nickname) {
        String sql = "Select professor_id from Professors where nickname = ?";
        int id = 0;
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, nickname);
            rs = st.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("getProfessorIdByNickName: " + e.getMessage());
        }
        return id;
    }

    //update
    public Professors updateProfessorInfor(Professors professor) {
        String sql = "update Professors set name = ?, email = ?, department_id = ? where professor_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, professor.getName());
            st.setString(2, professor.getEmail());
            st.setInt(3, professor.getDepartmentId());
            st.setInt(4, professor.getProfessorId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateProfessorInfor: " + e.getMessage());
        }
        return professor;
    }

    public boolean checkProfessorSlot(int professorId, String dayOfWeek, int slotId) {
        String sql = "select TimeSlots.*, Classes.class_name, Professors.nickname from ProfessorClassesCourse join ClassesCourse on ClassesCourse.classCourse_id = ProfessorClassesCourse.classCourse_id "
                + "join TimeSlotsClassesCourse on TimeSlotsClassesCourse.classCourse_id = ClassesCourse.classCourse_id "
                + "join TimeSlots on TimeSlots.timeslot_id = TimeSlotsClassesCourse.timeslot_id join Classes on Classes.class_id = ClassesCourse.class_id "
                + "join Professors on Professors.professor_id = ProfessorClassesCourse.professor_id "
                + "where ProfessorClassesCourse.professor_id = ? and TimeSlots.slot_id = ? and TimeSlots.day_of_week = ? ";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, professorId);
            st.setInt(2, slotId);
            st.setString(3, dayOfWeek);
            rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("checkProfessorSlot: " + e.getMessage());
        }
        return false;
    }
    
    public void removeAllProfessorClass(int professorId){
        String sql = "delete from ProfessorClassesCourse where ProfessorClassesCourse.professor_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, professorId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("removeAllProfessorClass" + e.getMessage());
        }
       
    }

    public static void main(String[] args) {
        ProfessorDao pd = new ProfessorDao();
        System.out.println(pd.checkProfessorSlot(5, "MONDAY", 1));
    }

    //delete professor information
    public Professors deleteProfessorInfor(int id) {

        Professors professors = getProfessorInfor(id);
        String sql = "Delete from Professors where professor_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("deleteProfessorInfor" + e.getMessage());
        }
        return professors;
    }

    public Professors deleteProfessorByNickName(String nickname) {
        Professors professors = getProfessorByNickName(nickname);
        String sql = "Delete from Professors where nickname = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, nickname);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("deleteProfessorByNickName" + e.getMessage());
        }
        return professors;

    }
    // select group of Professors with condition

    public List<Professors> getListOfProfessorByNickName(String nickname) {
        String sql = "Select * from Professors where nickname like ?";
        nickname = "%" + nickname + "%";
        List<Professors> list = new ArrayList();
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, nickname);
            rs = st.executeQuery();
            while (rs.next()) {
                int depId = rs.getInt(5);
                Professors p = new Professors(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), depId, dd.getDepartmentById(depId).getDepartmentName());
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("getListOfProfessorByNickName: " + e.getMessage());
        }
        return list;
    }

    public List<Professors> selectListProfessorsByDepartment(String department) {
        List<Professors> getListByDepartment = new ArrayList();
        List<Professors> list = getGroupOfProfessor();
        for (Professors professors : list) {
            if (professors.getDepartmentName().contains(department)) {
                getListByDepartment.add(professors);
            }
        }
        return getListByDepartment;
    }

    public List<Professors> selectListProfessorByDepartmentId(int depId) {
        String sql = "Select * from Professors where department_id = ?";
        List<Professors> list = new ArrayList();
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, depId);
            rs = st.executeQuery();
            while (rs.next()) {
                Professors p = new Professors(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), depId, dd.getDepartmentById(depId).getDepartmentName());
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("getListOfProfessorByNickName: " + e.getMessage());
        }
        return list;
    }

    public List<Professors> selectListProfessorsByString(String str) {
        List<Professors> getListByString = new ArrayList();
        List<Professors> list = getGroupOfProfessor();
        for (Professors professors : list) {
            if (professors.getDepartmentName().contains(str) || professors.getName().contains(str)
                    || professors.getEmail().contentEquals(str) || professors.getNickname().contains(str)) {
                getListByString.add(professors);
            }
        }
        return getListByString;
    }

    public List<Professors> selectListProfessorsByName(String name) {
        List<Professors> listOfProfessorsByName = new ArrayList();
        String sql = "Select * from Professors where name like ?";
        name = "%" + name + "%";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, name);
            rs = st.executeQuery();
            while (rs.next()) {
                Professors professor = new Professors();
                professor.setProfessorId(rs.getInt(1));
                professor.setNickname(rs.getString(2));
                professor.setName(rs.getString(3));
                professor.setEmail(rs.getString(4));
                int depId = rs.getInt(5);
                professor.setDepartmentId(depId);
                String depName = dd.getDepartmentById(depId).getDepartmentName();
                professor.setDepartmentName(depName);
                listOfProfessorsByName.add(professor);
            }
        } catch (SQLException e) {
            System.out.println("selectListProfessorsByName: " + e.getMessage());
        }
        return listOfProfessorsByName;

    }

    //select full class infor that Professor is teaching by professorId
    public boolean isExit(String id) {
        String sql = "Select * from Professors where professor_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("isExit: " + e.getMessage());
        }
        return false;
    }

    public List<Professors> getGroupOfProfessor() {
        String sql = "select * from Professors";
        List<Professors> list = new ArrayList();
        try {
            st = cnn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Professors professor = new Professors();
                professor.setProfessorId(rs.getInt(1));
                professor.setNickname(rs.getString(2));
                professor.setName(rs.getString(3));
                professor.setEmail(rs.getString(4));
                int depId = rs.getInt(5);
                professor.setDepartmentId(depId);
                String depName = dd.getDepartmentById(depId).getDepartmentName();
                professor.setDepartmentName(depName);
                list.add(professor);
            }
        } catch (SQLException e) {
            System.out.println("getGroupOfProfessor: " + e.getMessage());
        }
        return list;
    }

    private boolean checkProfessorNickName(String nickname) {
        String sql = "Select nickname from Professors where nickname = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, nickname);
            rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("isExit: " + e.getMessage());
        }
        return false;
    }

    public String getProfessorNickNameAuto(String name) {
        ProfessorDao pd = new ProfessorDao();
        String nickname = Validation.convertNameEmail(name);
        int i = 1;
        while (pd.checkProfessorNickName(nickname)) {
            nickname = Validation.convertNameEmail(name);
            if (i < 10) {
                nickname = nickname + "0" + String.valueOf(i);
            } else {
                nickname = nickname + String.valueOf(i);
            }
            i++;
        }
        return nickname;
    }

    public String createProfessorInfor(String name, int departmentId) {

        String sql = "insert into Professors values (?, ? ,? ,?)";
        String nickname = getProfessorNickNameAuto(name);
        String email = nickname + "@fpt.edu.vn";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, nickname);
            st.setString(2, name);
            st.setString(3, email);
            st.setInt(4, departmentId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("createProfessor" + e.getMessage());
        }
        return nickname;
    }

    public Professors getProfessorByNickName(String nickname) {
        String sql = "Select * from Professors where nickname = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, nickname);
            rs = st.executeQuery();
            while (rs.next()) {
                int depId = rs.getInt(5);
                Professors p = new Professors(rs.getInt(1), rs.getString(3), rs.getString(2), rs.getString(4), depId, dd.getDepartmentById(depId).getDepartmentName());
                return p;
            }
        } catch (SQLException e) {
            System.out.println("getProfessorByNickName: " + e.getMessage());
        }
        return null;
    }

    public Professors getProfessorByClassCourseId(int classCourseId) {
        String sql = "select Professors.* "
                + "from Professors join ProfessorClassesCourse on Professors.professor_id = ProfessorClassesCourse.professor_id "
                + "join ClassesCourse on ClassesCourse.classCourse_id = ProfessorClassesCourse.classCourse_id "
                + "where ClassesCourse.classCourse_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, classCourseId);
            rs = st.executeQuery();
            while (rs.next()) {
                int depId = rs.getInt(5);
                Professors p = new Professors(rs.getInt(1), rs.getString(3), rs.getString(2), rs.getString(4), depId, dd.getDepartmentById(depId).getDepartmentName());
                return p;
            }
        } catch (SQLException e) {
            System.out.println("getProfessorByNickName: " + e.getMessage());
        }
        return null;
    }

    public void updateProfessor(Professors update) {
        String sql = "update Professors set name = ?, nickname = ?, email = ?, department_id = ? where professor_id = ?";
        checkProfessor(update);
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, update.getName());
            st.setString(2, update.getNickname());
            st.setString(3, update.getEmail());
            st.setInt(4, update.getDepartmentId());
            st.setInt(5, update.getProfessorId());
            st.executeUpdate();
        } catch (SQLException e) {

        }

    }

    public void checkProfessor(Professors update) {

        Professors p = getProfessorInfor(update.getProfessorId());
        if (update.getName().isEmpty()) {
            update.setName(p.getName());
            update.setNickname(p.getNickname());
        } else {
            update.setNickname(getProfessorNickNameAuto(update.getName()));
        }

        if (update.getEmail().isEmpty()) {
            update.setEmail(p.getEmail());
        }
    }

}
