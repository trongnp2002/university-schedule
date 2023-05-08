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
import model.ClassFullInfor;
import model.Classes;
import model.Students;
import model.TimeSlots;
import util.Validation;

/**
 *
 * @author nguyn
 */
public class ClassDao extends DBContext {

    Connection cnn;
    PreparedStatement st;
    ResultSet rs;
    private CourseDao csd = new CourseDao();
    private MajorDao md = new MajorDao();
    private StudentDao sd = new StudentDao();
    private TimeSlotDao tsd = new TimeSlotDao();

    public ClassDao() {
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

    public List<Classes> getListFullOfClass() {
        List<Classes> list = new ArrayList();
        String sql = "select * from Classes";
        try {
            st = cnn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                int classId = rs.getInt(1);
                String className = rs.getString(2);
                String starYear = rs.getString(3);
                int majorId = rs.getInt(4);
                String majorName = md.getMajorById(majorId).getMajorName();
                Classes classes = new Classes(classId, className, starYear, majorId, majorName);
                list.add(classes);
            }

        } catch (SQLException e) {
            System.out.println("getListFullOfClass: " + e.getMessage());
        }
        return list;
    }

    public int createClass(Classes classes) {
        String sql = "insert into Classes values(?, ?, ?, ?)";
        int id = getClassIdAuto(classes.getStudentYear());
        String name = getClassNameAuto(id, classes.getMajorId());
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, name);
            st.setString(3, classes.getStudentYear());
            st.setInt(4, classes.getMajorId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("createClass: " + e.getMessage());
        }
        return id;
    }

    public Classes deleteClassById(int id) {
        Classes classDel = getClassById(id);
        String sql = "Delete from Classes where class_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            return classDel;
        } catch (SQLException e) {
            System.out.println("deleteClassById: " + e.getMessage());
        }
        return null;
    }

    public int getClassIdAuto(String year) {
        int num = Validation.getYearStudyInt(year);
        int idSS = (num + 1) * 100;
        int idAuto = num * 100 + 1;
        String sql = "Select top 1 class_id from Classes where class_id < ? order by class_id desc";
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
            System.out.println("getClassIdAuto: " + e.getMessage());
        }
        return idAuto;
    }

    public Classes getClassById(int id) {
        String sql = "Select * from Classes where class_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                int classId = rs.getInt(1);
                String className = rs.getString(2);
                String starYear = rs.getString(3);
                int majorId = rs.getInt(4);
                String majorName = md.getMajorById(majorId).getMajorName();
                Classes classes = new Classes(classId, className, starYear, majorId, majorName);
                return classes;
            }

        } catch (SQLException e) {
            System.out.println("deleteDepartment: " + e.getMessage());
        }
        return null;
    }

    public List<Classes> getListOfClassByPartOfId(String id) {
        id = "%" + id + "%";
        List<Classes> list = new ArrayList();
        String sql = "select * from Classes where class_id like ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                int classId = rs.getInt(1);
                String className = rs.getString(2);
                String starYear = rs.getString(3);
                int majorId = rs.getInt(4);
                String majorName = md.getMajorById(majorId).getMajorName();
                Classes classes = new Classes(classId, className, starYear, majorId, majorName);
                list.add(classes);
            }
        } catch (SQLException e) {
            System.out.println("getListOfClassByPartOfId: " + e.getMessage());
        }
        return list;
    }

    public List<Classes> getListOfClassByPartOfName(String name) {
        name = "%" + name + "%";
        List<Classes> list = new ArrayList();
        String sql = "select * from Classes where class_name like ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setString(1, name);
            rs = st.executeQuery();
            while (rs.next()) {
                int classId = rs.getInt(1);
                String className = rs.getString(2);
                String starYear = rs.getString(3);
                int majorId = rs.getInt(4);
                String majorName = md.getMajorById(majorId).getMajorName();
                Classes classes = new Classes(classId, className, starYear, majorId, majorName);
                list.add(classes);
            }
        } catch (SQLException e) {
            System.out.println("getListOfClassByPartOfName: " + e.getMessage());
        }
        return list;
    }

    public String getClassNameAuto(int id, int majorId) {
        String majorGroup = md.getMajorById(majorId).getMajorGroup();
        String name = majorGroup + String.valueOf(id);
        return name;
    }

    public void addClassCourse(int courseId, int classId, int roomId) {
        String sql = "Insert into ClassesCourse values(?, ?, ?)";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, classId);
            st.setInt(2, courseId);
            st.setInt(3, roomId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("addClassCourse: " + e.getMessage());
        }
    }

    public void addStudentToClassWithCourse(int studentId, int classCourseId) throws Exception {
        if (csd.checkStudentCourseExit(studentId, classCourseId)) {
            throw new Exception("This student already learn this course!");
        }
        String sql = "Insert into Enrollments values (?, ?)";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, studentId);
            st.setInt(2, classCourseId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("addStudentToClassWithCourse: " + e.getMessage());
        }
    }

    public int getClassCourseId(int classId, int courseId) {
        String sql = "select classCourse_id from ClassesCourse "
                + "where class_id = ? and course_id = ?";
        int classCourseId = 0;
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, classId);
            st.setInt(2, courseId);
            rs = st.executeQuery();
            if (rs.next()) {
                classCourseId = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("getClassCourseId: " + e.getMessage());
        }
        return classCourseId;
    }

    public Classes getClassByClassCourseId(int classCourseId) {
        String sql = "select Classes.* from ClassesCourse join Classes on ClassesCourse.class_id = ClassesCourse.class_id "
                + "where ClassesCourse.classCourse_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, classCourseId);
            rs = st.executeQuery();
            while (rs.next()) {
                int classId = rs.getInt(1);
                String className = rs.getString(2);
                String starYear = rs.getString(3);
                int majorId = rs.getInt(4);
                String majorName = md.getMajorById(majorId).getMajorName();
                Classes classes = new Classes(classId, className, starYear, majorId, majorName);
                return classes;
            }

        } catch (SQLException e) {
            System.out.println("deleteDepartment: " + e.getMessage());
        }
        return null;

    }

    public void addClassTimeSlots(int timeSlotId, int classCourseId) {
        String sql = "Insert into TimeSlotsClassesCourse values(?, ?)";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, timeSlotId);
            st.setInt(2, classCourseId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("addClassTimeSlots: " + e.getMessage());
        }
    }

    public void addProfessorToClassCourse(int professorId, int classCourseId) {
        String sql = "Insert into ProfessorClassesCourse values(?, ?)";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, professorId);
            st.setInt(2, classCourseId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("addProfessorToClassCourse: " + e.getMessage());
        }
    }

    public List<ClassFullInfor> getClassFullInforByClassId(int classId) {
        List<ClassFullInfor> list = new ArrayList();
        String sql1 = "select  ClassesCourse.course_id, Courses.course_name, Courses.semester, Rooms.room_name "
                + "from ClassesCourse join Courses on Courses.course_id = ClassesCourse.course_id  "
                + "join Rooms on ClassesCourse.room_id = Rooms.room_id "
                + "where ClassesCourse.class_id = ?";
        try {
            st = cnn.prepareStatement(sql1);
            st.setInt(1, classId);
            rs = st.executeQuery();
            while (rs.next()) {
                int courseId = rs.getInt(1);
                String courseName = rs.getString(2);
                int semester = rs.getInt(3);
                String roomName = rs.getString(4);
                String professorNickName = getProfessorNickNameByCourseAndClass(classId, courseId);
                List<TimeSlots> lisOfTimeSlot = tsd.getTimeSlotDayOfWeakAndSlotId(classId, courseId);
                ClassFullInfor cfi = new ClassFullInfor(classId, courseId, courseName, semester, roomName, professorNickName, lisOfTimeSlot);
                list.add(cfi);
            }
        } catch (SQLException e) {
            System.out.println("getClassFullInforByClassId: " + e.getMessage());
        }
        return list;
    }

    private String getProfessorNickNameByCourseAndClass(int classId, int courseId) {
        String sql = "select  Professors.nickname from \n"
                + "ClassesCourse join ProfessorClassesCourse on ProfessorClassesCourse.classCourse_id = ClassesCourse.classCourse_id \n"
                + "join Professors on Professors.professor_id = ProfessorClassesCourse.professor_id \n"
                + "where ClassesCourse.class_id = ? and ClassesCourse.course_Id = ?";
        String professorNickName = "not yet";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, classId);
            st.setInt(2, courseId);
            rs = st.executeQuery();
            while (rs.next()) {
                if (rs.getString(1) != null && !rs.getString(1).isEmpty()) {
                    professorNickName = rs.getString(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("getProfessorNickNameByCourseAndClass: " + e.getMessage());
        }
        return professorNickName;
    }

    public void addStudentToClass(int studentId, int classCourse) throws Exception {
        String sql = "insert into Enrollments values(?, ?)";
        if (checkEnrollments(studentId, classCourse)) {
            throw new Exception("This student already in class");

        }
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, studentId);
            st.setInt(2, classCourse);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("addStudentToClass: " + e.getMessage());
        }
    }

    public void removeStudentFromClass(int studentId, int classCourseId) {
        String sql = "Delete from Enrollments where student_id = ? and classCourse_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, studentId);
            st.setInt(2, classCourseId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("removeStudentFromClass" + e.getMessage());
        }
    }

    public void removeStudentFromAllClass(int studentId) {
        String sql = "Delete from Enrollments where student_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, studentId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("removeStudentFromClass" + e.getMessage());
        }
    }

    public boolean checkEnrollments(int studentId, int classCourseId) {
        String sql = "select * from Enrollments where student_id = ? and classCourse_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, studentId);
            st.setInt(2, classCourseId);
            rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("checkEnrollments: " + e.getMessage());
        }
        return false;
    }

    public List<Students> getAllStudentInClass(int classId) {
        List<Students> myStudentList = new ArrayList();
        String sql = "select Students.* from ClassesCourse "
                + "join Enrollments on Enrollments.classCourse_id = ClassesCourse.classCourse_id "
                + "join Students on Students.student_id = Enrollments.student_id "
                + "where ClassesCourse.class_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, classId);
            rs = st.executeQuery();
            while (rs.next()) {
                Students student = new Students();
                student.setStudentID(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setEmail(rs.getString(3));
                student.setPhoneNumber(rs.getString(4));
                int majorId = rs.getInt(5);
                student.setMajorId(rs.getInt(5));
                student.setYearOfStudy(rs.getString(6));
                student.setAddress(rs.getString(7));
                student.setMajor(md.getMajorById(majorId).getMajorName());
                myStudentList.add(student);
            }
        } catch (SQLException e) {
            System.out.println("getAllStudentInClass:" + e.getMessage());
        }
        return myStudentList;
    }

    public void removeAllStudentInClass(int classCourseId) {
        String sql = "Delete from Enrollments where classCourse_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, classCourseId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("removeAllStudentInClass: " + e.getMessage());
        }
    }

    public void removeProfessorFromClass(int classCourseId) {
        String sql = "Delete from ProfessorClassesCourse where classCourse_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, classCourseId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("removeProfessorFromClass: " + e.getMessage());
        }
    }

    public void removeCourseFromClass(int classCourseId) {
        String sql = "Delete from ClassesCourse where classCourse_id = ?";
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, classCourseId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("removeCourseFromClass: " + e.getMessage());
        }
    }

    public List<Integer> getAllClassCourseIdByClassId(int classId) {
        String sql = "select classCourse_id from ClassesCourse where class_id = ?";
        List<Integer> list = new ArrayList();
        try {
            st = cnn.prepareStatement(sql);
            st.setInt(1, classId);
            rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("getAllClassCourseIdByClassId: " + e.getMessage());
        }

        return list;
    }

    public static void main(String[] args) {
        ClassDao cd = new ClassDao();
        TimeSlotDao tsd = new TimeSlotDao();
        System.out.println(cd.checkEnrollments(13001, 2));

        System.out.println(cd.getClassFullInforByClassId(1601).get(0).getProfesser());
//        List<Students> list = cd.getAllStudentInClass(1301);
//        for (Students students : list) {
//            System.out.println(students);
//        }
//        List<Integer> list = cd.getAllClassCourseIdByClassId(1303);
//        for (Integer integer : list) {
//            System.out.println(integer);
//        }
//        cd.addClassCourse(2, 1301, 1);
//        List<Integer> firstSlot = tsd.getArrayOfTimeSlotIdInDayOfWeek("MONDAY", 2);
//        List<Integer> secondSlot = tsd.getArrayOfTimeSlotIdInDayOfWeek("WEDNESDAY", 2);
//        for (int i = 0; i < firstSlot.size(); i++) {
//            cd.addClassTimeSlots(firstSlot.get(i), 2);
//        }
//        for (int i = 0; i < secondSlot.size(); i++) {
//            cd.addClassTimeSlots(secondSlot.get(i), 2);
////        }
//        cd.addProfessorToClassCourse(1, 2);
//        List<ClassFullInfor> list = cd.getClassFullInforByClassId(1302);
//        for (ClassFullInfor classFullInfor : list) {
//            System.out.print(classFullInfor.getClassId() + "||");
//            System.out.print(classFullInfor.getCourseId() + "||");
//            System.out.print(classFullInfor.getCourseName() + "||");
//            System.out.print(classFullInfor.getProfesser() + "||");
//            System.out.print("Semester: " + classFullInfor.getSemester() + "||");
//            System.out.print(classFullInfor.getRoom() + "||");
//            List<TimeSlots> list1 = classFullInfor.getSlot();
//            for (TimeSlots timeSlots : list1) {
//                System.out.print("Slot" + timeSlots.getSlot_id() + ":" + timeSlots.getDayOfWeek() + "| ");
//            }
//            System.out.print("||");
//            System.out.println("");
//        }
////        System.out.println(cd.getClassCourseId(1301, 1));
    }

//test by console
}
