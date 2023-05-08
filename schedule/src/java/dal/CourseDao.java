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
import model.Courses;
import model.Departments;

/**
 *
 * @author nguyn
 */
public class CourseDao extends DBContext {

    Connection cnn;
    PreparedStatement st;
    ResultSet rs;
    DepartmentDao dd = new DepartmentDao();

    public CourseDao() {
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

    public Courses getCourseByClassCourseId(int classCourseId) {
        String sql = "select Courses.* from ClassesCourse join Courses on Courses.course_id = ClassesCourse.course_id "
                + "where ClassesCourse.classCourse_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, classCourseId);
            rs = st.executeQuery();
            while (rs.next()) {
                int departmentId = rs.getInt(4);
                Courses course = new Courses(rs.getInt(1), rs.getString(2), rs.getString(3), departmentId, rs.getInt(5), dd.getDepartmentById(departmentId).getDepartmentName());
                return course;
            }
        } catch (SQLException e) {
            System.out.println("getCoursesById" + e.getMessage());

        }
        return null;
    }

    public List<Courses> getFullGroupOfCourse() {
        String sql = "select * from Courses";
        List<Courses> groupCourse = new ArrayList();
        try {
            st = cnn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                int departmentId = rs.getInt(4);
                Courses course = new Courses(rs.getInt(1), rs.getString(2), rs.getString(3), departmentId, rs.getInt(5), dd.getDepartmentById(departmentId).getDepartmentName());
                groupCourse.add(course);
            }
        } catch (SQLException e) {
            System.out.println("getFullGroupOfCourse: " + e.getMessage());
        }
        return groupCourse;
    }

    public void addCourse(Courses course) {
        String sql = "insert into Courses values (?, ?, ?, ?)";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, course.getCourseCode());
            st.setString(2, course.getCourseName());
            st.setInt(3, course.getDepartmentId());
            st.setInt(4, course.getSemester());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("addCourse" + e.getMessage());
        }
    }

    public List<Courses> getListCourseByDepartmentId(int depId) {
        List<Courses> groupCourse = new ArrayList();
        String sql = "select * from Courses where department_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, depId);
            rs = st.executeQuery();
            while (rs.next()) {
                int departmentId = rs.getInt(4);
                Courses course = new Courses(rs.getInt(1), rs.getString(2), rs.getString(3), departmentId, rs.getInt(5), dd.getDepartmentById(departmentId).getDepartmentName());
                groupCourse.add(course);
            }
        } catch (SQLException e) {
            System.out.println("getFullGroupOfCourse: " + e.getMessage());
        }
        return groupCourse;

    }

    public Courses getCoursesById(int id) {
        String sql = "Select * from Courses where course_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                int departmentId = rs.getInt(4);
                Courses course = new Courses(rs.getInt(1), rs.getString(2), rs.getString(3), departmentId, rs.getInt(5), dd.getDepartmentById(departmentId).getDepartmentName());
                return course;
            }
        } catch (SQLException e) {
            System.out.println("getCoursesById" + e.getMessage());

        }
        return null;
    }

    public Courses deleteCourses(int id) {
        Courses coursesDel = getCoursesById(id);
        String sql = "Delete from Courses where course_id = ?";

        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            return coursesDel;
        } catch (SQLException e) {
            System.out.println("getCoursesById" + e.getMessage());

        }
        return null;

    }

    public boolean checkStudentCourseExit(int studentId, int courseId) {
        String sql = "select * from Students join Enrollments on Students.student_id = Enrollments.student_id "
                + "join ClassesCourse on ClassesCourse.classCourse_id = Enrollments.classCourse_id "
                + "where Students.student_id = ? and course_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, studentId);
            st.setInt(2, courseId);
            rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("checkStudentCourseExit: " + e.getMessage());
        }
        return false;
    }

    public boolean checkStudentIsHaveSameCourse(int studentId, int courseId) {
        String sql = "select * from Enrollments join ClassesCourse on Enrollments.classCourse_id = ClassesCourse.classCourse_id "
                + "join Courses on Courses.course_id = ClassesCourse.course_id "
                + "where Courses.course_id = ? and Enrollments.student_id = ?";
        try{
            st = cnn.prepareStatement(sql);
            st.setInt(1, courseId);
            st.setInt(2, studentId);
            rs = st.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch(SQLException e){
            System.out.println("checkStudentIsHaveSameCourse" + e.getMessage());
        }
        return false;
    }
    
    public static void main(String[] args) {
        CourseDao cd = new CourseDao();
        System.out.println(cd.checkStudentIsHaveSameCourse(130001, 5));
    }
}
