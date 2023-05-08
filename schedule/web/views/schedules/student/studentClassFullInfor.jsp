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
                Professor : ${professor.getName()}
                <br>
                Professor email: ${professor.getEmail()}

            </div>
            <div class="studentInfor">
                <table border="1" style="border-collapse: collapse">
                    <thead>
                        <tr>
                            <th>Roll Number</th>
                            <th>Student Name</th>
                            <th>Email</th>
                            <th>Major</th>
                            <th>Year of study</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${students}" var="student" >
                            <tr>
                                <td style="text-align: center;">${Validation.getStudentRollNum(student.getMajor(),student.getStudentID(),false)}</td>
                                <td>${student.getName()}</td>
                                <td>${student.getEmail()}</td>.
                                <td style="text-align: center">${student.getMajor()}</td>
                                <td style="text-align:  center">${student.getYearOfStudy()}</td>
                            </tr>                        
                        </c:forEach>


                    </tbody>
                </table>                
            </div>


        </div>
    <jsp:include page="../fragments/footer.jsp" flush="true"></jsp:include>
    </body>
</html>
