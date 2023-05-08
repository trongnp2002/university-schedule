<%-- 
    Document   : studentClassFullInfor
    Created on : Mar 15, 2023, 11:05:47 PM
    Author     : nguyn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import = "model.Students" %>
<%@page  import = "java.util.List" %>
<%@page  import = "java.util.ArrayList" %>
<%@page  import = "util.Validation" %>

<!DOCTYPE html>
<html>

    <jsp:include page="../fragments/head.jsp"></jsp:include>
        <body>
        <jsp:include page="../fragments/header.jsp" flush="true"></jsp:include>


            <div id="content">
                <div id="classInfor">
                    Class name : ${classinfor.getClassName()}
                <br>
                Course : ${course.getCourseCode()}
                <br>
                Number of student: ${numOfStudent}

            </div>
            <form action="/schedule/professor/attend" method="post">
                <div class="studentInfor">
                    <div style="display: none"> <input type="text" name="numOfStudent" value="${numOfStudent}">
                        <input type="text" name="timeSlotId" value="${timeSlotId}">
                        <input type="text" name="classCourseId" value="${classCourseId}">
                    </div>
                    <table border="1" style="border-collapse: collapse">
                        <thead>
                            <tr>
                                <th>Roll Number</th>
                                <th>Student Name</th>
                                <th>Email</th>
                                <th>Major</th>
                                <th>Year of study</th>
                                <th>Check attend</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%int i = 1;
                            int j = 0;
                            String name = "stu"+ String.valueOf(i);
                            String id = "id"+i;
                            List<String> attendStatus = new ArrayList();
                            if(request.getAttribute("attendStatus")!= null){
                                attendStatus = (List<String>)request.getAttribute("attendStatus");
                                }
                            %>
                            <c:forEach items="${students}" var="student" >
                                <%
                             name = "stu"+ String.valueOf(i);
                             id = "id"+i;
                                %>

                                <tr>
                                    <td style="text-align: center">${Validation.getStudentRollNum(student.getMajor(),student.getStudentID(),false)}</td>
                                    <td>${student.getName()}</td>
                                    <td>${student.getEmail()}</td>.
                                    <td style="text-align: center">${student.getMajor()}</td>
                                    <td style="text-align:  center">${student.getYearOfStudy()}</td>
                                    <td style="text-align: center">
                                        <%if(        attendStatus.get(j).equalsIgnoreCase("not yet")){%>
                                        <input type="radio" name="<%=name%>" value="absent">Absent &nbsp;&nbsp; &nbsp; &nbsp; 
                                        <input type="radio" name="<%=name%>" value="present">Present
                                        <div style="display: none"><input type="type" name="<%=id%>" value="${student.getStudentID()}"></div>
                                            <%}%>
                                            <%if(        attendStatus.get(j).equalsIgnoreCase("absent")){%>
                                        <input type="radio" name="<%=name%>"  value="absent" checked="">Absent &nbsp;&nbsp; &nbsp; &nbsp; 
                                        <input type="radio" name="<%=name%>" value="present">Present
                                        <div style="display: none"><input type="type" name="<%=id%>" value="${student.getStudentID()}"></div>
                                            <%}%>
                                            <%if(        attendStatus.get(j).equalsIgnoreCase("attended")){%>
                                        <input type="radio" name="<%=name%>"  value="absent">Absent &nbsp;&nbsp; &nbsp; &nbsp; 
                                        <input type="radio" name="<%=name%>" checked value="present">Present
                                        <div style="display: none"><input type="type" name="<%=id%>" value="${student.getStudentID()}"></div>
                                            <%}%>
                                            <%i = i + 1 ;
                                            j = j+1 ;%>
                                    </td> 
                                </tr>                        

                            </c:forEach>

                            <tr >
                                <td colspan="6" ><input type="submit" value="Save" name="sub" style="float: right; font-size: 20px; padding: 12px;"></td>
                            </tr>
                        </tbody>
                    </table>                
                </div>                    
            </form>



        </div>

    <jsp:include page="../fragments/footer.jsp" flush="true"></jsp:include>
    </body>
</html>
