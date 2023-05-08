<%-- 
    Document   : Student_Schedule.jsp
    Created on : Mar 4, 2023, 3:49:38 AM
    Author     : nguyn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.StudentSchedule"%>
<%@page import="java.time.DayOfWeek"%>
<%@page import="model.Slot"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <jsp:include page="../fragments/head.jsp"></jsp:include>
        <body>

        <jsp:include page="../fragments/header.jsp" flush="true"></jsp:include>
            <div class="container">
                <form action="/schedule/student/scheduleStudent"  style="margin-bottom: 32px;" method="post">
                    <div >
                        <h1 style="text-align: center">IN WEEK </h1>
                        <h3 style="text-align: center"><input type="text" readonly name="dateInSchedule" style="padding: 0 12px; min-width: 360px; cursor: pointer" value="${startDate} To ${endDate}"></h3>
                    <div style="display: flex; justify-content: center; width: 100%">
                        <input type="submit" name="submit" value="Previous" style="margin-right: 10px; min-width: 80px;">
                        <input type="submit" name="submit" value="Next"  style="min-width: 80px;" ></div>
                </div>

            </form>


            <div class="table-responsive">
                <table class="table table-bordered text-center">
                    <thead>
                        <tr class="bg-light-gray">
                            <th class="text-uppercase">Slot</th>
                            <th class="text-uppercase">Monday</th>
                            <th class="text-uppercase">Tuesday</th>
                            <th class="text-uppercase">Wednesday</th>
                            <th class="text-uppercase">Thursday</th>
                            <th class="text-uppercase">Friday</th>
                            <th class="text-uppercase">Saturday</th>
                            <th class="text-uppercase">Sunday</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        List<StudentSchedule> studentSchedule = new ArrayList();
                        if(request.getAttribute("schedule") != null){
                        studentSchedule = (List<StudentSchedule>)request.getAttribute("schedule");
                            }
                        List<Slot> groupOfAllSlot = new ArrayList();
                        if(request.getAttribute("slots") != null){
                          groupOfAllSlot = (List<Slot>)request.getAttribute("slots");
                            }
                        %>
                        <%
                               DayOfWeek dow[] = DayOfWeek.values();
                                for(int i =0; i < groupOfAllSlot.size(); i++){ 
                             
                        %>
                        <tr>
                            <td class="align-middle" >Slot <%=groupOfAllSlot.get(i).getSlotId()%>
                                <br> <%=groupOfAllSlot.get(i).getStartTime().substring(0, 5)%>-<%=groupOfAllSlot.get(i).getEndTime().substring(0, 5)%>
                            </td>
                            <%   for(int j = 0; j < dow.length ; j++){
                                        Boolean haveCourse = false;          
                                        for(int k = 0; k < studentSchedule.size(); k++){
                                            if(studentSchedule.get(k).getSlotId() == groupOfAllSlot.get(i).getSlotId() &&  studentSchedule.get(k).getDayOfWeek().equalsIgnoreCase(String.valueOf(dow[j]))){
                                                 haveCourse = true;
                            %>
                            <td style="height: 80px; width: 140px;">
                                <span style="cursor: pointer" class="bg-sky padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13" onclick="getClassCourseFullInfor('<%=studentSchedule.get(k).getClassCourseId()%>')"><%=studentSchedule.get(k).getCourseCode()%></span>
                                <div class="margin-10px-top font-size14">at&nbsp;<%=studentSchedule.get(k).getRoom()%></div>
                                <%
                                if(studentSchedule.get(k).getAttendanceStatus().equalsIgnoreCase("attended")){
                                %>
                                <div class="font-size13 " style="color: green">(<%=studentSchedule.get(k).getAttendanceStatus()%>)</div>
                                <%}else{%>
                                <div class="font-size13 " style="color: red">(<%=studentSchedule.get(k).getAttendanceStatus()%>)</div>
                                <%}%>
                            </td>
                            <%              }
                                          }
                            %>
                            <%
                                             if(haveCourse == false){ %>
                            <td style="height: 80px; width: 140px;"><div class=" padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13" ></div></td>
                                <%                 }
                                          }
                                %>
                        <tr>
                            <%}%>


                    </tbody>
                </table>
            </div>
        </div>

        <jsp:include page="../fragments/footer.jsp" flush="true"></jsp:include>
       
        <script>
                                    function getClassCourseFullInfor(classCourseId) {
                                        window.location = ('/schedule/student/classInfor?csId=' + classCourseId);
                                    }

        </script>
    </body>
</html>
