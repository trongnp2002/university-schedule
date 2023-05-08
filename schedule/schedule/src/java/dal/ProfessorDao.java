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
import model.Professors;

/**
 *
 * @author nguyn
 */
public class ProfessorDao extends DBContext {

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
    public Professors createProfessorInfor(Professors professors) {
        if (isExit(professors.getProfessorId())) {
            return null;
        }
        String sql = "insert into Professors values (?, ? ,? ,?)";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, professors.getProfessorId());
            st.setString(2, professors.getName());
            st.setString(3, professors.getEmail());
            st.setString(4, professors.getOffice());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("createProfessor" + e.getMessage());
        }
        return professors;
    }

    //get professor infor
    public Professors getProfessorInfor(String id) {
        String sql = "Select * from Professors where professor_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Professors professor = new Professors();
                professor.setProfessorId(rs.getString(1));
                professor.setName(rs.getString(2));
                professor.setEmail(rs.getString(3));
                professor.setOffice(rs.getString(4));
                return professor;
            }
        } catch (SQLException e) {
            System.out.println("getProfessorInfor: " + e.getMessage());
        }
        return null;
    }

    //update
    public Professors updateProfessorInfor(Professors professor) {
        if (!isExit(professor.getProfessorId())) {
            return null;
        }
        String sql = "update Professors set name = ?, email = ?, office = ? where professor_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, professor.getName());
            st.setString(2, professor.getEmail());
            st.setString(3, professor.getOffice());
            st.setString(4, professor.getProfessorId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateProfessorInfor: " + e.getMessage());
        }
        return professor;
    }

    //delete professor information
    public Professors deleteProfessorInfor(String id) {
        if (!isExit(id)) {
            return null;
        }
        Professors professors = getProfessorInfor(id);
        String sql = "Delete from Professors where professor_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("deleteProfessorInfor" + e.getMessage());
        }
        return professors;
    }

    // select group of Professors with condition
    public List<Professors> selectListProfessorsesById(String id) {
        List<Professors> listOfProfessorsById = new ArrayList();
        String sql = "Select * from Professors where professor_id like ?";
        id = "%" + id + "%";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Professors professor = new Professors();
                professor.setProfessorId(rs.getString(1));
                professor.setName(rs.getString(2));
                professor.setEmail(rs.getString(3));
                professor.setOffice(rs.getString(4));
                listOfProfessorsById.add(professor);
            }
        } catch (SQLException e) {
            System.out.println("selectListProfessorsesById: " + e.getMessage());
        }
        return listOfProfessorsById;

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
                professor.setProfessorId(rs.getString(1));
                professor.setName(rs.getString(2));
                professor.setEmail(rs.getString(3));
                professor.setOffice(rs.getString(4));
                listOfProfessorsByName.add(professor);
            }
        } catch (SQLException e) {
            System.out.println("selectListProfessorsByName: " + e.getMessage());
        }
        return listOfProfessorsByName;

    }

    public List<Professors> selectListProfessorsByOffice(String office) {
        List<Professors> listOfProfessorsByOffice = new ArrayList();
        String sql = "Select * from Professors where office like ?";
        office = "%" + office + "%";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, office);
            rs = st.executeQuery();
            while (rs.next()) {
                Professors professor = new Professors();
                professor.setProfessorId(rs.getString(1));
                professor.setName(rs.getString(2));
                professor.setEmail(rs.getString(3));
                professor.setOffice(rs.getString(4));
                listOfProfessorsByOffice.add(professor);
            }
        } catch (SQLException e) {
            System.out.println("selectListProfessorsByOffice: " + e.getMessage());
        }
        return listOfProfessorsByOffice;
    }

    public List<Professors> selectListProfessorsByString(String str) {
        List<Professors> listOfProfessorsByString = new ArrayList();
        String sql = "Select * from Professors where professor_id like ? or name like ? "
                + "or email like ? or office like ?";
        str = "%" + str + "%";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, str);
            st.setString(2, str);
            st.setString(3, str);
            st.setString(4, str);
            rs = st.executeQuery();
            while (rs.next()) {
                Professors professor = new Professors();
                professor.setProfessorId(rs.getString(1));
                professor.setName(rs.getString(2));
                professor.setEmail(rs.getString(3));
                professor.setOffice(rs.getString(4));
                listOfProfessorsByString.add(professor);
            }
        } catch (SQLException e) {
            System.out.println("selectListProfessorsByString: " + e.getMessage());
        }
        return listOfProfessorsByString;
    }

    //select full class infor that Professor is teaching by professorId
    public List<Classes> selectFullOfProfessorClasses(String professorId) {
        if (!isExit(professorId)) {
            return null;
        }
        List<Classes> professorClass = new ArrayList();
        String sql = "select * from Classes "
                + "where Classes.professor_id = ?";

        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, professorId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Classes theClass = new Classes();
                theClass.setClassID(rs.getString(1));
                theClass.setClassName(rs.getString(2));
                theClass.setCourseId(rs.getString(3));
                theClass.setRoomId(rs.getString(4));
                theClass.setProfessorID(rs.getString(5));
                professorClass.add(theClass);
            }
        } catch (SQLException e) {
            System.out.println("selectFullOfProfessorClasses: " + e.getMessage());
        }

        return professorClass;
    }

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

    public static void main(String[] args) {
        ProfessorDao dpa = new ProfessorDao();
        List<Professors> list = dpa.selectListProfessorsByString("r");
        for (Professors professors : list) {
            System.out.println(professors.toString());
        }

    }
}
