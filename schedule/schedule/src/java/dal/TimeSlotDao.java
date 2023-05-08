/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.TimeSlots;

/**
 *
 * @author nguyn
 */
public class TimeSlotDao extends DBContext {

    Connection cnn;
    PreparedStatement st;
    ResultSet rs;

    public TimeSlotDao() {
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

    public void createTimeSlot(TimeSlots time) {
        String sql = "insert into TimeSlots values(? ,? ,? ,?, ?)";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, time.getDayOfWeek());
            st.setString(2, time.getTimeSlotsId());
            st.setTime(3, java.sql.Time.valueOf(time.getStartTime()));
            st.setTime(4, java.sql.Time.valueOf(time.getEndTime()));
            st.setDate(5, java.sql.Date.valueOf(time.getDate()));
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("createTimeSlot: " + e.getMessage());
        }
    }
}
