<%-- 
    Document   : admin_home
    Created on : Mar 4, 2023, 1:46:37 PM
    Author     : nguyn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/views/fragments/head.jsp" flush="true"></jsp:include>

    <body onclick="">
        <jsp:include page="/views/fragments/header.jsp"></jsp:include>
            <div id="container" onclick="">
            <jsp:include page="/views/fragments/slidebar.jsp"></jsp:include>

                <div id="content" onclick="eventOff()">
                    <div class="contentLeft"></div>
                </div>
            </div>
        <jsp:include page="/views/fragments/footer.jsp" flush="true"></jsp:include>
    </body>


</html>
