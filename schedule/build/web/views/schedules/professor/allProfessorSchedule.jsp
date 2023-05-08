<%-- 
    Document   : allProfessorSchedule
    Created on : Mar 16, 2023, 4:50:25 PM
    Author     : nguyn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.ProfessorSchedule"%>
<%@page import="java.time.DayOfWeek"%>
<%@page import="model.Slot"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ScheduleOfAllProfessor"%>

<!DOCTYPE html>
<html>
    <jsp:include page="../fragments/head.jsp"></jsp:include>
        <body>
        <jsp:include page="../fragments/header.jsp" flush="true"></jsp:include>
            <div class="container">
                <form action="/schedule/professor/allProfessrSchedule"  style="margin-bottom: 32px;" method="post">
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
                        <tr  class="bg-light-gray" >
                            <th class="text-uppercase">Professor id</th>
                            <th class="text-uppercase">Nickname</th>
                            <th class="text-uppercase">Full name</th>
                            <th class="text-uppercase">Email</th>
                            <th class = "text-uppercase">Slot teaching</th>

                        </tr>
                    </thead>
                    <tbody>
                        <%
                        
                        %>
                        <%
                            List<ScheduleOfAllProfessor> listOfAllProfessorSchedule = new ArrayList();
                            if(request.getAttribute("scheduleOfFullProfessor") != null){
                                listOfAllProfessorSchedule = (List<ScheduleOfAllProfessor> )request.getAttribute("scheduleOfFullProfessor");
                                for(int i = 0; i < listOfAllProfessorSchedule.size(); i++){
                                 List<ProfessorSchedule> professorSchedule = listOfAllProfessorSchedule.get(i).getList();
                                 if(professorSchedule.size() >0){
                            
                        %>
                        <tr>
                            <td><%=listOfAllProfessorSchedule.get(i).getProfessor().getProfessorId()%></td>
                            <td><%=listOfAllProfessorSchedule.get(i).getProfessor().getNickname()%></td>
                            <td><%=listOfAllProfessorSchedule.get(i).getProfessor().getName()%></td>
                            <td><%=listOfAllProfessorSchedule.get(i).getProfessor().getEmail()%></td>
                            <td style="text-align: left;" ><%
                               
                                for(int j = 0; j < professorSchedule.size(); j++){
                                %>
                                <p> &nbsp;&nbsp;&nbsp;Slot: <%=professorSchedule.get(j).getSlotId()%>&nbsp;|&nbsp;Class: <%=professorSchedule.get(j).getClassName()%>
                                    &nbsp;| Day Of Week: <%=professorSchedule.get(j).getDayOfWeek()%></p>
                                <%}%>

                            </td>

                        <tr>
                            <%}}
                                }%>


                    </tbody>
                </table>
            </div>
        </div>

        <jsp:include page="../fragments/footer.jsp" flush="true"></jsp:include>
    </body>
</html>
