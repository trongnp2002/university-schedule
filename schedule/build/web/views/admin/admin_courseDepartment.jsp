<%-- 
    Document   : admin_studentHome
    Created on : Mar 4, 2023, 3:24:15 PM
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
    <jsp:include page="/views/fragments/head.jsp" flush="true"></jsp:include>

        <body onload="checkReportD('${report}', '${select}')">
        <jsp:include page="/views/fragments/header.jsp" flush="true"></jsp:include>
            <div id="container" onclick="">
            <jsp:include page="/views/fragments/slidebar.jsp" flush="true"></jsp:include>
                <div id="content" onclick="eventOff()">
                    <h2>Department & course setup: </h2>
                    <main>
                        <div class="selection">
                            <h3>All feature: </h3>
                            <input type="button" name="sub" value="Department information" onclick="displayByIdDC('department')">
                            <input type="button" name="sub" value="Course information" onclick="displayByIdDC('course')">                    
                            <input type="button" name="sub" value="Major information" onclick="displayByIdDC('major')">                    

                        </div>
                        <br>
                        <hr>
                        <br>

                        <div id="result">
                            <div class="option" id="department"> 
                            <jsp:include page="/views/admin/admin_departmentCourse/admin_department.jsp" flush="true"></jsp:include>
                            </div>
                            <div class="option" id="course"> 
                            <jsp:include page="/views/admin/admin_departmentCourse/admin_course.jsp" flush="true"></jsp:include>
                            </div>                 
                            <div class="option" id="major"> 
                            <jsp:include page="/views/admin/admin_departmentCourse/admin_major.jsp" flush="true"></jsp:include>
                            </div>         
                        </div>
                    </main>

                </div>
            </div>

        <jsp:include page="/views/fragments/footer.jsp" flush="true"></jsp:include>
    </body>


</html>
