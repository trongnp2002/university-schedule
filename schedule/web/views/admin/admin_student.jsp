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

    <body onload="checkReportS('${report}', '${select}')">
        <jsp:include page="/views/fragments/header.jsp" flush="true"></jsp:include>
            <div id="container" onclick="">
            <jsp:include page="/views/fragments/slidebar.jsp" flush="true"></jsp:include>
                <div id="content" onclick="eventOff()">
                    <h2>Students setup: </h2>
                    <main>
                        <div class="selection">
                            <h3>Student's feature: </h3>
                            <input type="button" name="sub" value="View Students information" onclick="displayByIdStu('viewStu')">
                            <input type="button" name="sub" value="Update Student information" onclick="displayByIdStu('updateStu')">                    
                            <input type="button" name="sub" value="Delete Student information" onclick="displayByIdStu('deleteStu')">   
                            <input type="button" name="sub" value="Create Student information" onclick ="displayByIdStu('createStu')">     
                        </div>
                        <br>
                        <hr>
                        <br>

                        <div id="result">
                            <div class="option" id="viewStu"> 
                            <jsp:include page="/views/admin/admin_Student/admin_selectStudent.jsp" flush="true"></jsp:include>
                            </div>
                            <div class="option" id="updateStu"> 
                            <jsp:include page="/views/admin/admin_Student/admin_updateStudent.jsp" flush="true"></jsp:include>
                            </div>                 
                            <div class="option" id="deleteStu"> 
                            <jsp:include page="/views/admin/admin_Student/admin_deleteStudent.jsp" flush="true"></jsp:include>
                            </div>                   
                            <div class="option" id="createStu"> 
                            <jsp:include page="/views/admin/admin_Student/admin_createStudent.jsp" flush="true"></jsp:include>
                            </div>
                        </div>
                    </main>

                </div>
            </div>

        <jsp:include page="/views/fragments/footer.jsp" flush="true"></jsp:include>
    </body>


</html>
