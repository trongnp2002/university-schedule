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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    public void addTimeSlot(TimeSlots timeSlot) {
        String sql = "insert into TimeSlots values(? , ? , ?)";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, timeSlot.getDayOfWeek());
            st.setDate(2, java.sql.Date.valueOf(timeSlot.getDate()));
            st.setInt(3, timeSlot.getSlot_id());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("addTimeSlot: " + e.getMessage());
        }
    }



    public List<TimeSlots> getAllSlotsOfClassWithCourse(int classId, int courseId) {
        List<TimeSlots> allSlotOfClassCourse = new ArrayList();
        String sql = "select TimeSlots.* from TimeSlots join TimeSlotsClassesCourse on TimeSlots.timeslot_id = TimeSlotsClassesCourse.timeslot_id "
                + "join ClassesCourse on ClassesCourse.classCourse_id = TimeSlotsClassesCourse.classCourse_id "
                + "where ClassesCourse.course_id = ?  and ClassesCourse.class_id = ? order by TimeSlots.date asc";

        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, courseId);
            st.setInt(2, classId);
            rs = st.executeQuery();
            while (rs.next()) {
                int timeSlotId = rs.getInt(1);
                String dayOfWeek = rs.getString(2);
                LocalDate date = rs.getDate(3).toLocalDate();
                int slotId = rs.getInt(4);
                TimeSlots timeslots = new TimeSlots(timeSlotId, dayOfWeek, date, slotId);
                allSlotOfClassCourse.add(timeslots);
            }
        } catch (SQLException e) {
            System.out.println("getAllSlotsOfClassWithCourse: " + e.getMessage());
        }
        return allSlotOfClassCourse;
    }

    public List<Integer> getArrayOfTimeSlotIdInDayOfWeek(String dayOfWeek, int slotId) {
        List<Integer> listSlotId = new ArrayList();
        String sql = "select timeslot_id from TimeSlots where day_of_week = ? and slot_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, dayOfWeek);
            st.setInt(2, slotId);
            rs = st.executeQuery();
            while (rs.next()) {
                listSlotId.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("getArrayOfTimeSlotId: " + e.getMessage());
        }
        return listSlotId;
    }

    public List<Integer> getArrayOfTimeSlotIdInDayOfWeekBetween2day(String dayOfWeek, String startDate, String endDate, int slotId) {
        String sql = "select timeslot_id from TimeSlots "
                + "where day_of_week = ? and slot_id = ?  and [date] between ? and ? ";
        List<Integer> list = new ArrayList();
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, dayOfWeek);
            st.setInt(2, slotId);
            st.setDate(3, java.sql.Date.valueOf(startDate));
            st.setDate(4, java.sql.Date.valueOf(endDate));
            rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("getArrayOfTimeSlotIdInDayOfWeekBetween2day: " + e.getMessage());
        }
        return list;
    }

    public List<TimeSlots> getTimeSlotDayOfWeakAndSlotId(int classId, int courseId) {
        List<TimeSlots> allSlotOfClassCourse = new ArrayList();

        String sql = "select distinct TimeSlots.day_of_week, TimeSlots.slot_id from TimeSlots join TimeSlotsClassesCourse on TimeSlots.timeslot_id = TimeSlotsClassesCourse.timeslot_id "
                + "join ClassesCourse on ClassesCourse.classCourse_id = TimeSlotsClassesCourse.classCourse_id "
                + "where ClassesCourse.course_id = ?  and ClassesCourse.class_id = ? order by TimeSlots.day_of_week asc";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, courseId);
            st.setInt(2, classId);
            rs = st.executeQuery();
            while (rs.next()) {
                String dayOfWeek = rs.getString(1);
                int slotId = rs.getInt(2);
                TimeSlots timeslots = new TimeSlots(0, dayOfWeek, null, slotId);
                allSlotOfClassCourse.add(timeslots);
            }
        } catch (SQLException e) {
            System.out.println("getAllSlotsOfClassWithCourse: " + e.getMessage());
        }
        return allSlotOfClassCourse;
    }

    public List<Integer> getArrayOfTimeSlotIdByCourseClassId(int courseClassId) {
        List<Integer> list = new ArrayList();
        String sql = "select TimeSlotsClassesCourse.timeslot_id from "
                + "ClassesCourse join TimeSlotsClassesCourse on TimeSlotsClassesCourse.classCourse_id = ClassesCourse.classCourse_id "
                + "where ClassesCourse.classCourse_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, courseClassId);
            rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("getArrayOfTimeSlotIdByCourseClassId: " + e.getMessage());
        }
        return list;
    }

    public boolean checkTimeSlotInClass(String dayOfWeek, int slotId, int classId) {
        String sql = "select * from "
                + "Classes join ClassesCourse on Classes.class_id = ClassesCourse.class_id "
                + "join TimeSlotsClassesCourse on TimeSlotsClassesCourse.classCourse_id= ClassesCourse.classCourse_id "
                + "join TimeSlots on TimeSlots.timeslot_id =TimeSlotsClassesCourse.timeslot_id "
                + "where slot_id = ? and ClassesCourse.class_id = ? and day_of_week = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, slotId);
            st.setInt(2, classId);
            st.setString(3, dayOfWeek);

            rs = st.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("checkTimeSlotInClass: " + e.getMessage());
        }
        return false;
    }

    public void removeClassTimeSlot(int classCourseId) {
        String sql = "delete from TimeSlotsClassesCourse where classCourse_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, classCourseId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("removeClassTimeSlot: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TimeSlotDao tsd = new TimeSlotDao();
//        List<TimeSlots> list = tsd.getTimeSlotDayOfWeakAndSlotId(1301, 2);
//        for (TimeSlots timeSlots : list) {
//            System.out.println(timeSlots.getDayOfWeek() + "|" + timeSlots.getSlot_id());
//        }
//
//        List<Integer> list1 = tsd.getArrayOfTimeSlotIdInDayOfWeekBetween2day("MONDAY", "2023-01-01", "2023-04-01");
//        for (Integer integer : list1) {
//            System.out.println(integer);
//        }
        System.out.println(tsd.checkTimeSlotInClass("MONDAY", 1, 1701));
    }

}
