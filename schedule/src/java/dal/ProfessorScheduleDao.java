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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.ProfessorSchedule;

/**
 *
 * @author nguyn
 */
public class ProfessorScheduleDao extends DBContext {

    Connection cnn;
    PreparedStatement st;
    ResultSet rs;
    private BuildingDao bd = new BuildingDao();

    public ProfessorScheduleDao() {
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

    public List<ProfessorSchedule> getFullProfessorScheduleInDateRange(int professorId, LocalDate startDate, LocalDate endDate) {
        List<ProfessorSchedule> list = new ArrayList();
        String sql = "select ClassesCourse.classCourse_id , TimeSlots.*,  Classes.class_name,Rooms.room_name from ProfessorClassesCourse join ClassesCourse on ClassesCourse.classCourse_id = ProfessorClassesCourse.classCourse_id "
                + "join TimeSlotsClassesCourse on TimeSlotsClassesCourse.classCourse_id = ClassesCourse.classCourse_id "
                + "join TimeSlots on TimeSlots.timeslot_id = TimeSlotsClassesCourse.timeslot_id join Classes on Classes.class_id = ClassesCourse.class_id "
                + "join Professors on Professors.professor_id = ProfessorClassesCourse.professor_id join Rooms on Rooms.room_id = ClassesCourse.room_id "
                + "where ProfessorClassesCourse.professor_id = ? and date between ? and ? "
                + "order by TimeSlots.date asc ";
        try {
            st = cnn.prepareCall(sql);
            st.setInt(1, professorId);
            st.setDate(2, java.sql.Date.valueOf(startDate));
            st.setDate(3, java.sql.Date.valueOf(endDate));
            rs = st.executeQuery();
            while (rs.next()) {
                int classCourseId = rs.getInt(1);
                int timeSlotId = rs.getInt(2);
                String dayOfWeek = rs.getString(3);
                String date = rs.getString(4);
                int slotId = rs.getInt(5);
                String className = rs.getString(6);
                String room = rs.getString(7);
                ProfessorSchedule ps = new ProfessorSchedule(classCourseId, timeSlotId, dayOfWeek, date, slotId, className, room);
                list.add(ps);
//                ProfessorScheduleo
            }
        } catch (SQLException e) {
            System.out.println("getFullProfessorScheduleInDateRange: " + e.getMessage());
        }
        return list;
    }
    
    

    public static void main(String[] args) {
        ProfessorScheduleDao psd = new ProfessorScheduleDao();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String start = "2023-01-02";
        String end = "2023-01-08";
        LocalDate startDate = LocalDate.parse(start, formatter);
        LocalDate endDate = LocalDate.parse(end, formatter);
        List<ProfessorSchedule> list = psd.getFullProfessorScheduleInDateRange(5, startDate, endDate);
        for (ProfessorSchedule professorSchedule : list) {
            System.out.println(professorSchedule.getRoom());
        }
    }
}
