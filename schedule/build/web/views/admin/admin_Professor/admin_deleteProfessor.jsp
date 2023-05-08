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
<form class="form2" action="/schedule/admin_professor/delete" method="post">
    <h3 style="text-align: center; margin-right: 44px;">Delete professor by Nickname</h1>
        <div class="forInput">
            <input class="inputSearch" type="text" name="nickname" value="${inputDel}">
            <div class="subhovdel">
                <input class="subdel" type="submit" name="sub" value="DELETE">
            </div>
        </div>
        <br>
        <div class="delResult">

            <c:if test="${professorDel == null }">     
                <div class="dataNotFound">
                </div>
            </c:if>

            <c:if test="${professorDel != null }">     
                <h3>Professor information has been deleted: </h3>
                ${professorDel.toString()}
                <br>
                ${accountDel.toString()}
            </c:if>              
        </div>
</form>

