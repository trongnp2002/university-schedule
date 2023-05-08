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
import model.Slot;
import model.TimeSlots;
import util.Validation;

/**
 *
 * @author nguyn
 */
public class SlotDao extends DBContext {
    
    Connection cnn;
    PreparedStatement st;
    ResultSet rs;
    
    public SlotDao() {
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
    
    public void createSlot(Slot slot) throws Exception {
        String sql = "insert into Slots values (?, ?)";
        if (!Validation.checkTimeValid(slot.getStartTime(), slot.getEndTime())) {
            throw new Exception("End time invalid!");
        }
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, slot.getStartTime());
            st.setString(2, slot.getEndTime());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("createSlot: " + e);
        }
        
    }
    
    public void updateSlot(Slot slot) throws Exception {
        String sql = "update Slots set start_time = ?, end_time = ? where slot_id = ?";
        checkSlotInfor(slot);
        if (!Validation.checkTimeValid(slot.getStartTime(), slot.getEndTime())) {
            throw new Exception("End time invalid!");
        }
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, slot.getStartTime());
            st.setString(2, slot.getEndTime());
            st.setInt(3, slot.getSlotId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateSlot: " + e.getMessage());
        }
    }
    
    private void checkSlotInfor(Slot slot) {
        Slot originSlot = getSlotById(slot.getSlotId());
        if (slot.getStartTime().isEmpty()) {
            slot.setStartTime(originSlot.getStartTime());
        }
        if (slot.getEndTime().isEmpty()) {
            slot.setEndTime(originSlot.getEndTime());
        }
    }
    
    public Slot getSlotById(int slotId) {
        String sql = "select * from Slots where slot_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, slotId);
            rs = st.executeQuery();
            while (rs.next()) {
                Slot slot = new Slot(rs.getInt(1), rs.getString(2), rs.getString(3));
                return slot;
            }
              
        } catch (SQLException e) {
            System.out.println("getSlotById: " + e.getMessage());
        }
      return null;
    }
    
    public List<Slot> getGroupOfSlot() {
        String sql = "select * from Slots";
        List<Slot> list = new ArrayList();
        try {
            st = cnn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Slot slot = new Slot(rs.getByte(1), rs.getString(2), rs.getString(3));
                list.add(slot);
            }
        } catch (SQLException e) {
            System.out.println("getGroupOfSlot: " + e.getMessage());
        }
        return list;
    }

}
