/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.AttendanceDao;
import dal.ClassDao;
import dal.CourseDao;
import dal.DepartmentDao;
import dal.MajorDao;
import dal.ProfessorDao;
import dal.RoomDao;
import dal.SlotDao;
import dal.StudentDao;
import dal.TimeSlotDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ClassFullInfor;
import model.Classes;
import model.Courses;
import model.Departments;
import model.Major;
import model.Professors;
import model.Slot;
import model.Students;
import model.TimeSlots;

/**
 *
 * @author nguyn
 */
public class ClassService {

    private ClassDao cd = new ClassDao();
    private MajorDao md = new MajorDao();
    private StudentDao sd = new StudentDao();
    private DepartmentDao dd = new DepartmentDao();
    private CourseDao courseDao = new CourseDao();
    private ProfessorDao pd = new ProfessorDao();
    private SlotDao slotDao = new SlotDao();
    private RoomDao rd = new RoomDao();
    private TimeSlotDao tsd = new TimeSlotDao();
    private AttendanceDao ad = new AttendanceDao();

    public void classHomeStart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Classes> listFullClasses = cd.getListFullOfClass();
        List<Major> groupMajor = md.getFullGroupOfMajor();
        req.setAttribute("data2", groupMajor);
        req.setAttribute("data", listFullClasses);
        req.getRequestDispatcher("/views/admin/admin_class.jsp").forward(req, resp);

    }

    public void createClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String majorId = req.getParameter("classMajor").trim();
        String startYear = req.getParameter("startYear").trim();
        int mId = Integer.parseInt(majorId);
        String report = "Process success!";
        Classes classes = new Classes(0, "", startYear, mId);
        int newClassId = 0;
        try {
            newClassId = cd.createClass(classes);
        } catch (Exception e) {
            report = "Process fail!";
        }
        List<Classes> newClass = new ArrayList();
        List<Major> groupMajor = md.getFullGroupOfMajor();
        newClass.add(cd.getClassById(newClassId));
        req.setAttribute("data2", groupMajor);
        req.setAttribute("data", newClass);
        req.getRequestDispatcher("/views/admin/admin_class.jsp").forward(req, resp);
    }

    public void deleteClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id").trim();
        int classId = Integer.parseInt(id);
        String report = "Procces sucessful";
        List<Integer> listOfClassCourseId = cd.getAllClassCourseIdByClassId(classId);

        for (Integer integer : listOfClassCourseId) {
            removeAllStudentAttendedInClass(classId, integer);
            tsd.removeClassTimeSlot(integer);
            cd.removeAllStudentInClass(integer);
            cd.removeProfessorFromClass(integer);
            cd.removeCourseFromClass(integer);
        }
        Classes classDel = cd.deleteClassById(classId);
        if (classDel == null) {
            report = "delete fail!";
        } else {
            report = "Delete class success: id: " + classDel.getClassId() + " name: " + classDel.getClassName();
        }
        List<Classes> listFullClasses = cd.getListFullOfClass();
        List<Major> groupMajor = md.getFullGroupOfMajor();
        req.setAttribute("report", report);
        req.setAttribute("data2", groupMajor);
        req.setAttribute("data", listFullClasses);
        req.getRequestDispatcher("/views/admin/admin_class.jsp").forward(req, resp);
    }

    public void selectClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option");
        String search = req.getParameter("search");
        List<Classes> listOfClass = new ArrayList();
        if (option.equalsIgnoreCase("all")) {
            listOfClass = getClassByString(search.toUpperCase());
        }
        if (option.equalsIgnoreCase("id")) {
            listOfClass = cd.getListOfClassByPartOfId(search);
        }
        if (option.equalsIgnoreCase("name")) {
            listOfClass = cd.getListOfClassByPartOfName(search);
        }
        if (option.equalsIgnoreCase("mainMajor")) {
            listOfClass = getClassByMajor(search.toUpperCase());
        }
        List<Major> groupMajor = md.getFullGroupOfMajor();
        req.setAttribute("report", "Process success!!!");
        req.setAttribute("data2", groupMajor);
        req.setAttribute("data", listOfClass);
        req.getRequestDispatcher("/views/admin/admin_class.jsp").forward(req, resp);
    }

    public void getClassFullInfor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id").trim();
        int classId = Integer.parseInt(id);
        Classes thisClass = cd.getClassById(classId);
        List<ClassFullInfor> listFullInforOfClass = cd.getClassFullInforByClassId(classId);
        List<Departments> listDepartment = dd.getGroupOfDepartment();
        req.setAttribute("data", listFullInforOfClass);
        req.setAttribute("department", listDepartment);
        req.setAttribute("thisClass", thisClass);
        req.getRequestDispatcher("/views/admin/admin_class/admin_classInfor.jsp").forward(req, resp);
    }

    public void getGroupOfStudentInClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String classId = req.getParameter("classId");
        String courseId = req.getParameter("courseId");
        Classes thisClass = cd.getClassById(Integer.parseInt(classId));
        Courses courses = courseDao.getCoursesById(Integer.parseInt(courseId));
        List<Students> studentInClass = sd.getFullStudentFromClassWithCourse(Integer.parseInt(classId), Integer.parseInt(courseId));
        req.setAttribute("course", courses);
        req.setAttribute("data", studentInClass);
        req.setAttribute("thisClass", thisClass);
        req.getRequestDispatcher("/views/admin/admin_class/admin_classStudentInClass.jsp").forward(req, resp);
    }

    public void getFullAddInforAfterChooseDepartment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String depId = req.getParameter("department");
        String cId = req.getParameter("classId");
        int classId = Integer.parseInt(cId);
        int departmetnId = Integer.parseInt(depId);
        List<Courses> listCourseFindByDepartmentId = courseDao.getListCourseByDepartmentId(departmetnId);
        List<Professors> listProfessorFindByDepartmentId = pd.selectListProfessorByDepartmentId(departmetnId);
        List<Slot> listSlot = slotDao.getGroupOfSlot();
        Classes thisClass = cd.getClassById(classId);
        List<ClassFullInfor> listFullInforOfClass = cd.getClassFullInforByClassId(classId);
        List<Departments> listDepartment = dd.getGroupOfDepartment();
        req.setAttribute("data", listFullInforOfClass);
        req.setAttribute("department", listDepartment);
        req.setAttribute("listCourse", listCourseFindByDepartmentId);
        req.setAttribute("listSlot", listSlot);
        req.setAttribute("listProfessor", listProfessorFindByDepartmentId);
        req.setAttribute("thisClass", thisClass);
        req.setAttribute("selected", departmetnId);
        req.setAttribute("isOpenPop", "true");
        req.getRequestDispatcher("/views/admin/admin_class/admin_classInfor.jsp").forward(req, resp);
    }

    public void addClassCourseMethod(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String depId = req.getParameter("department");
        String cId = req.getParameter("classId");
        String report = "Process success!!!";
        int classId = Integer.parseInt(cId);
        if (depId == null || depId.equalsIgnoreCase("select")) {
            report = "You must select department!!!";
        } else {
            String csId = req.getParameter("course");
            String sId = req.getParameter("slot");
            String dayOfWeek[] = req.getParameterValues("dow");
            String professor = req.getParameter("professor");
            String room = req.getParameter("room");
            String startDate = req.getParameter("startDate");
            String endDate = req.getParameter("endDate");
            int courseId = Integer.parseInt(csId);
            int slotId = Integer.parseInt(sId);
            boolean isDuplicate = false;
            int professorId = pd.getProfessorIdByNickName(professor);
            boolean professorIsBussy = false;
            try {
                for (int i = 0; i < dayOfWeek.length; i++) {
                    isDuplicate = tsd.checkTimeSlotInClass(dayOfWeek[i], slotId, classId);
                    if (pd.checkProfessorSlot(professorId, dayOfWeek[i], slotId)) {
                        professorIsBussy = true;
                        continue;
                    }
                    if (isDuplicate == true) {
                        break;
                    }
                }
                if (startDate.isEmpty() || startDate == null || endDate.isEmpty() || endDate == null) {
                    report = "Start date and end date cannot be empty!!!";
                } else {
                    if (professorIsBussy == true) {
                        report = "Sorry professor already have class in the same Slot";
                    } else {
                        if (isDuplicate == false) {
                            addClassCourse(room, classId, courseId);
                            for (int i = 0; i < dayOfWeek.length; i++) {
                                addSlotForClassWithCourse(dayOfWeek[i], startDate, endDate, slotId, classId, courseId);
                            }
                            addProfessorToClass(classId, courseId, professor);
                        } else {
                            report = "This class already study in the same slot";
                        }
                    }
                }
            } catch (Exception e) {
                report = e.getMessage();
            }
            List<ClassFullInfor> listFullInforOfClass = cd.getClassFullInforByClassId(classId);
            req.setAttribute("data", listFullInforOfClass);

        }

        List<Departments> listDepartment = dd.getGroupOfDepartment();
        List<ClassFullInfor> listFullInforOfClass = cd.getClassFullInforByClassId(classId);
        Classes thisClass = cd.getClassById(classId);
        req.setAttribute("report", report);
        req.setAttribute("data", listFullInforOfClass);
        req.setAttribute("thisClass", thisClass);
        req.setAttribute("department", listDepartment);
        req.getRequestDispatcher("/views/admin/admin_class/admin_classInfor.jsp").forward(req, resp);
    }

    public void addStudentToClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String classId = req.getParameter("classId");
        String courseId = req.getParameter("courseId");
        int couId = Integer.parseInt(courseId);
        String studentId = req.getParameter("studentID");
        int classCourseId = cd.getClassCourseId(Integer.parseInt(classId), Integer.parseInt(courseId));
        int stId = Integer.parseInt(studentId);
        String report = "Process success !!!";
        boolean isHaveSlot = false;
         List<Integer> timeSlotID = tsd.getArrayOfTimeSlotIdByCourseClassId(classCourseId);
        for(int i =0 ; i <timeSlotID.size(); i++){
            isHaveSlot = ad.checkAttendExit(stId, timeSlotID.get(i));
            if(isHaveSlot == true){
                break;
            }
        }
        
        if (courseDao.checkStudentIsHaveSameCourse(stId, couId)) {
            report = "Sorry, this student already have this course";
        }else if(isHaveSlot){
            report = "Sorry, this student already have study in this slot";
        }else {
            
            try {
                cd.addStudentToClass(stId, classCourseId);
               
                for (Integer integer : timeSlotID) {
                    ad.createAttendance(stId, integer);
                }
            } catch (Exception ex) {
                report = ex.getMessage();
            }
        }
        Classes thisClass = cd.getClassById(Integer.parseInt(classId));
        Courses courses = courseDao.getCoursesById(Integer.parseInt(courseId));
        List<Students> studentInClass = sd.getFullStudentFromClassWithCourse(Integer.parseInt(classId), Integer.parseInt(courseId));
        req.setAttribute("report", report);
        req.setAttribute("course", courses);
        req.setAttribute("data", studentInClass);
        req.setAttribute("thisClass", thisClass);
        req.getRequestDispatcher("/views/admin/admin_class/admin_classStudentInClass.jsp").forward(req, resp);

    }

    public void removeStuFromClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stuId = req.getParameter("stuId");
        String claId = req.getParameter("claId");
        String couId = req.getParameter("couId");
        int classId = Integer.parseInt(claId);
        int courseId = Integer.parseInt(couId);
        int studentId = Integer.parseInt(stuId);
        int classCourseId = cd.getClassCourseId(Integer.parseInt(claId), Integer.parseInt(couId));
        List<Integer> listOfSlotId = tsd.getArrayOfTimeSlotIdByCourseClassId(classCourseId);
        for (Integer integer : listOfSlotId) {
            ad.removeAttendance(studentId, integer);
        }
        cd.removeStudentFromClass(studentId, classCourseId);
        Classes thisClass = cd.getClassById(classId);
        Courses courses = courseDao.getCoursesById(courseId);
        List<Students> studentInClass = sd.getFullStudentFromClassWithCourse(classId, courseId);
        req.setAttribute("report", "Process successful");
        req.setAttribute("course", courses);
        req.setAttribute("data", studentInClass);
        req.setAttribute("thisClass", thisClass);
        req.getRequestDispatcher("/views/admin/admin_class/admin_classStudentInClass.jsp").forward(req, resp);
    }

    public void removeCourseFromClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String classId = req.getParameter("classId");
        String courseId = req.getParameter("courseId");
        int classCourseId = cd.getClassCourseId(Integer.parseInt(classId), Integer.parseInt(courseId));
        List<Students> list = sd.getFullStudentFromClassWithCourse(classCourseId);
        List<Integer> list2 = tsd.getArrayOfTimeSlotIdByCourseClassId(classCourseId);
        try {
            for(int i = 0; i < list.size(); i++){
                for(int j = 0; j < list2.size(); j++){
                    ad.removeAttendance(list.get(i).getStudentID(), list2.get(j));
                }
            }
            
            cd.removeAllStudentInClass(classCourseId);
            cd.removeProfessorFromClass(classCourseId);
            tsd.removeClassTimeSlot(classCourseId);
            cd.removeCourseFromClass(classCourseId);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Classes thisClass = cd.getClassById(Integer.parseInt(classId));
        List<ClassFullInfor> listFullInforOfClass = cd.getClassFullInforByClassId(Integer.parseInt(classId));
        List<Departments> listDepartment = dd.getGroupOfDepartment();
        req.setAttribute("data", listFullInforOfClass);
        req.setAttribute("department", listDepartment);
        req.setAttribute("thisClass", thisClass);
        req.getRequestDispatcher("/views/admin/admin_class/admin_classInfor.jsp").forward(req, resp);
    }

    private List<Classes> getClassByString(String str) {
        List<Classes> fullClass = cd.getListFullOfClass();
        List<Classes> getClassByString = new ArrayList();
        for (Classes fullClas : fullClass) {
            if (fullClas.getClassName().toUpperCase().contains(str) || fullClas.getMajorName().toUpperCase().contains(str)
                    || fullClas.getStudentYear().contains(str) || String.valueOf(fullClas.getClassId()).contains(str)) {
                getClassByString.add(fullClas);
            }
        }
        return getClassByString;
    }

    private List<Classes> getClassByMajor(String str) {
        List<Classes> fullClass = cd.getListFullOfClass();
        List<Classes> getClassByString = new ArrayList();
        for (Classes fullClas : fullClass) {
            if (fullClas.getMajorName().toUpperCase().contains(str)) {
                getClassByString.add(fullClas);
            }
        }
        return getClassByString;
    }

    private void addClassCourse(String roomName, int classId, int courseId) throws Exception {
        if (roomName.isEmpty() || roomName == null) {
            throw new Exception("room cannot be empty!");
        }
        int roomId = rd.getRoomIdByName(roomName);
        if (roomId == 0) {
            throw new Exception("this room not exit!");
        }
        int classCourseId = cd.getClassCourseId(classId, courseId);
        if (classCourseId != 0) {
            throw new Exception("This class already have this course!!");
        }
        cd.addClassCourse(courseId, classId, roomId);

    }

    private void addSlotForClassWithCourse(String dayOfWeek, String startDate, String endDate, int slotId, int classId, int courseId) {
        List<Integer> listOfTimeSlotsId = tsd.getArrayOfTimeSlotIdInDayOfWeekBetween2day(dayOfWeek, startDate, endDate, slotId);
        int classCourseId = cd.getClassCourseId(classId, courseId);
        for (Integer integer : listOfTimeSlotsId) {
            cd.addClassTimeSlots(integer, classCourseId);
        }
    }

    private void addProfessorToClass(int classId, int courseId, String professorNickName) throws Exception {
        int professorId = pd.getProfessorIdByNickName(professorNickName);
        if (professorId == 0) {
            throw new Exception("This professor data not exits");
        }
        int classCourseId = cd.getClassCourseId(classId, courseId);
        cd.addProfessorToClassCourse(professorId, classCourseId);

    }

    private void removeAllStudentAttendedInClass(int classId, int classCourseId) {
        List<Students> allStudentOfClass = cd.getAllStudentInClass(classId);
        List<Integer> listOfAllTimeSlotId = tsd.getArrayOfTimeSlotIdByCourseClassId(classCourseId);
        for (Students allStudentOfClas : allStudentOfClass) {
            for (Integer integer : listOfAllTimeSlotId) {
                ad.removeAttendance(allStudentOfClas.getStudentID(), integer);
            }
        }
    }

}
