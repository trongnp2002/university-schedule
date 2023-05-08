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
    <body onload="checkReportA('${report}', '${select}')">

        <jsp:include page="/views/fragments/header.jsp" flush="true"></jsp:include>
            <div id="container" onclick="">
            <jsp:include page="/views/fragments/slidebar.jsp" flush="true"></jsp:include>
                <div id="content" onclick="eventOff()">
                    <h2>Account setup: </h2>
                    <main>
                        <div class="selection">
                            <h3>Account's feature: </h3>
                            <input type="button" name="sub" value="Search and view Account " onclick="displayByIdAcc('viewAcc')">      
                            <input type="button" name="sub" value="Delete Account " onclick="displayByIdAcc('deleteAcc')">   
                            <input type="button" name="sub" value="Create Account " onclick ="displayByIdAcc('createAcc')">     
                        </div>
                        <br>
                        <hr>
                        <br>
                        <div id="result">
                            <div class="option" id="viewAcc">
                            <jsp:include page="/views/admin/admin_Account/admin_selectAccount.jsp" flush="true"></jsp:include>
                            </div>
                            <div class="option" id="createAcc">
                            <jsp:include page="/views/admin/admin_Account/admin_createAccount.jsp" flush="true"></jsp:include>
                            </div>
                            <div class="option" id="deleteAcc">
                            <jsp:include page="/views/admin/admin_Account/admin_deleteAccount.jsp" flush="true"></jsp:include>
                            </div>
                        </div>
                    </main>

                </div>
            </div>
        <jsp:include page="/views/fragments/footer.jsp" flush="true"></jsp:include>
    </body>


</html>
