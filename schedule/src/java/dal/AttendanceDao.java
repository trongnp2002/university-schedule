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

/**
 *
 * @author nguyn
 */
public class AttendanceDao extends DBContext {

    Connection cnn;
    PreparedStatement st;
    ResultSet rs;

    public AttendanceDao() {
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

    public void createAttendance(int studentId, int time_slotId) {
        String sql = "insert into Attendance values(?, ?, ?)";
        try {
            st = cnn.prepareCall(sql);
            st.setInt(1, time_slotId);
            st.setInt(2, studentId);
            st.setString(3, "not yet");
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("createAttendance: " + e.getMessage());
        }
    }

    public void removeAttendance(int studentId, int time_slotId) {
        try {
            String sql = "delete from Attendance where student_id = ? and timeslot_id = ?";
            st = cnn.prepareStatement(sql);
            st.setInt(1, studentId);
            st.setInt(2, time_slotId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("removeAttendance: " + e.getMessage());
        }
    }

    public void removeAllAttendanceOfStudent(int studentId) {
        try {
            String sql = "delete from Attendance where Attendance.student_id =  ? ";
            st = cnn.prepareStatement(sql);
            st.setInt(1, studentId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("removeAllAttendanceOfStudent: " + e.getMessage());
        }
    }

    public void UpdateAttendance(int timeSlotId, int studentId, String status) {
        String sql = "update Attendance set status = ? "
                + "where timeslot_id = ? and student_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, status);
            st.setInt(2, timeSlotId);
            st.setInt(3, studentId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("UpdateAttendance: " + e.getMessage());
        }
    }

    public String getAttendStatus(int studentId, int timeSlotId) {
        String sql = "select Attendance.status from Attendance "
                + "where Attendance.student_id = ? "
                + "and timeslot_id = ?";
        String status = "";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, studentId);
            st.setInt(2, timeSlotId);
            rs = st.executeQuery();
            while (rs.next()) {
                status = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("getAttendStatus: " + e.getMessage());
        }
        return status;
    }

    public boolean checkAttendExit(int studentId, int timeSlotId) {
        String sql = "select * "
                + "from ClassesCourse join TimeSlotsClassesCourse on TimeSlotsClassesCourse.classCourse_id = ClassesCourse.classCourse_id\n"
                + "join TimeSlots on TimeSlots.timeslot_id = TimeSlotsClassesCourse.timeslot_id \n"
                + "where ClassesCourse.class_id = ? and ClassesCourse.course_id = ?";

        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, studentId);
            st.setInt(2, timeSlotId);
            rs = st.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("getAttendStatus: " + e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        AttendanceDao ad = new AttendanceDao();
        System.out.println(ad.getAttendStatus(130001, 2));
    }
}
