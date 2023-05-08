<%-- 
    Document   : admin_professor
    Created on : Mar 10, 2023, 9:03:19 PM
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

        <body onload="checkReportA('${report}', '${select}')">
        <jsp:include page="/views/fragments/header.jsp"></jsp:include>
            <div id="container" onclick="">
            <jsp:include page="/views/fragments/slidebar.jsp"></jsp:include>
                <div id="content" onclick="eventOff()">
                    <h2>Professor setup: </h2>
                    <main>
                        <div class="selection">
                            <h3>Professor's feature: </h3>
                            <input type="button" name="sub" value="Search and view Professor " onclick="displayByIdAcc('viewAcc')">      
                            <input type="button" name="sub" value="Create Professor " onclick ="displayByIdAcc('createAcc')">       
                            <input type="button" name="sub" value="Delete Professor " onclick="displayByIdAcc('deleteAcc')">   
                        </div>
                        <br>
                        <hr>
                        <br>
                        <div id="result">
                            <div class="option" id="viewAcc">
                            <jsp:include page="/views/admin/admin_Professor/admin_selectProfessor.jsp" flush="true"></jsp:include>
                            </div>
                            <div class="option" id="deleteAcc">
                            <jsp:include page="/views/admin/admin_Professor/admin_deleteProfessor.jsp" flush="true"></jsp:include>
                            </div>
                            <div class="option" id="createAcc">
                            <jsp:include page="/views/admin/admin_Professor/admin_createProfessor.jsp" flush="true"></jsp:include>
                            </div>
                    </main>

                </div>
            </div>
        <jsp:include page="/views/fragments/footer.jsp" flush="true"></jsp:include>
    </body>


</html>
