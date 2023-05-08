<%-- 
    Document   : admin_deleteStudent
    Created on : Mar 5, 2023, 5:12:44 PM
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

    <body>
        <form id="form2" action="/schedule/admin_student/delete" method="post">
            <h3 style="text-align: center; margin-right: 44px;">Delete student by ID</h1>
                <div class="forInput">
                    <input class="theId" type="text" name="id" value="${inputDel}">
                    <div class="subhovdel">
                        <input class="subdel" type="submit" name="sub" value="DELETE">
                    </div>
                </div>
                <br>
                <div class="delResult">

                    <c:if test="${studentDel == null }">     
                        <div class="dataNotFound">
                        </div>
                    </c:if>

                    <c:if test="${studentDel != null }">     
                        <h3>Student information has been deleted: </h3>
                        ${studentDel.toString()}
                    </c:if>              
                </div>


        </form>
    </body>
</html>
