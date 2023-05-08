<%-- 
    Document   : admin_createStudentStudy
    Created on : Mar 2, 2023, 11:37:25 PM
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

<form class="form1" action="/schedule/admin_professor/create" method="post" >
    <table border="0" id="createTable">
        <tr><td colspan="2"> <h3 class="h1Cr" style="margin-bottom: 16px;">CREATE PROFESSOR INFORMATION</h3></td></tr>
        <tr>
            <td style="width: 30%">Professor name: </td>
            <td style="width: 70%"><input type="text" name="professorName" required></td>
        </tr>
        <tr>
            <td>Department: </td>
            <td>
                <select class="userOption" name ="department">
                    <c:forEach items="${department}" var="dep">
                        <option value ="${dep.getDepartmentsId()}">${dep.getDepartmentName()}</option>
                    </c:forEach>
                </select>
        </tr>
        <tr>
            <td  class="subhov" style="text-align: center" colspan="2"> <input type="submit" class="submit"  name="sub" value="CREATE" ></td>
        </tr>
    </table>
</form>            

