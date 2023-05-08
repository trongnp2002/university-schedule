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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.StudentSchedule;

/**
 *
 * @author nguyn
 */
public class StudentScheduleDao extends DBContext {

    Connection cnn;
    PreparedStatement st;
    ResultSet rs;

    public StudentScheduleDao() {
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

    public List<StudentSchedule> getStudentScheduleByStudentIdInWeek(int studentId, LocalDate startDate, LocalDate endDate) {
        List<StudentSchedule> schedule = new ArrayList();
        String sql = "select Enrollments.classCourse_id, class_name, Courses.course_code ,Rooms.room_name ,TimeSlots.slot_id ,TimeSlots.date, TimeSlots.day_of_week ,Attendance.status "
                + "from Enrollments join ClassesCourse on Enrollments.classCourse_id = ClassesCourse.classCourse_id "
                + "join Rooms on Rooms.room_id = ClassesCourse.room_id join Classes on ClassesCourse.class_id = Classes.class_id "
                + "join Courses on Courses.course_id = ClassesCourse.course_id  "
                + "join TimeSlotsClassesCourse on TimeSlotsClassesCourse.classCourse_id = ClassesCourse.classCourse_id "
                + "join TimeSlots on TimeSlots.timeslot_id = TimeSlotsClassesCourse.timeslot_id "
                + "join Attendance on Enrollments.student_id = Attendance.student_id and Attendance.timeslot_id = TimeSlots.timeslot_id "
                + "where Enrollments.student_id = ?  and TimeSlots.date between ? and ? "
                + "order by TimeSlots.date asc";

        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, studentId);
            st.setDate(2, java.sql.Date.valueOf(startDate));
            st.setDate(3, java.sql.Date.valueOf(endDate));
            rs = st.executeQuery();
            while (rs.next()) {
                int classCourseId = rs.getInt(1);
                String className = rs.getString(2);
                String courseCode = rs.getString(3);
                String room = rs.getString(4);
                int slotId = rs.getInt(5);
                String date = rs.getString(6);
                String dayOfWeek = rs.getString(7);
                String status = rs.getString(8);

                StudentSchedule studentSchedule = new StudentSchedule(classCourseId, className, courseCode, room, slotId, date, dayOfWeek, status);
                schedule.add(studentSchedule);
            }
        } catch (SQLException e) {
            System.out.println("getStudentScheduleByStudentId: " + e.getMessage());
        }
        return schedule;
    }

    public static void main(String[] args) {
        StudentScheduleDao ssd = new StudentScheduleDao();
        LocalDate dat = LocalDate.now();
        LocalDate start = dat.minusDays(4);
        LocalDate end = dat.plusDays(3);

        List<StudentSchedule> list = ssd.getStudentScheduleByStudentIdInWeek(130001, start, dat);
        for (StudentSchedule studentSchedule : list) {
            System.out.println(studentSchedule.getSlotId() + studentSchedule.getDayOfWeek());
        }
                }
//    public void getStudentScheduleByStudentIdInWeek(int i, LocalDate startDate, LocalDate end) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}
