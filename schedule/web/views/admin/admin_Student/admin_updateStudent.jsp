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

<form class="form4" action="/schedule/admin_student/update" method="post" >
    <table border="0" >
        <tr><td colspan="2"><h3>UPDATE STUDENT INFORMATION</h3></td></tr>
        <tr>
            <td style="width: 30%">Student ID: </td>
            <td style="width: 70%"><input type="number" name="studentID" required></td>
        </tr>

        <tr>
            <td>Student name: </td>
            <td><input type="text" name="studentName" ></td>
        </tr>
        <tr>
            <td>Student email: </td>
            <td><input type="email" name="studentEmail" ></td>
        </tr>
        <tr>
            <td>Phone number: </td>
            <td><input type="number" name="studentPhone" minlength="10"></td>
        </tr>

        <tr>
            <td>Student Address: </td>
            <td><input type="text" name="studentAddress" ></td>
        </tr>
        <tr>
            <td>Student major: </td>
            <td>
                <select class="userOption" name ="studentMajor">
                    <c:forEach items="${data2}" var="major">
                        <option value="${major.getMajorId()}">${major.getMajorName()}</option>
                    </c:forEach>
                </select></td>
        </tr>

        <tr>
            <td>Start year: </td>
            <td>
                <select class="userOption" name ="studentYear">
                    <option value="K13">K13</option>
                    <option value="K14">K14</option>
                    <option value="K15">K15</option>
                    <option value="K16">K16</option>
                    <option value="K17">K17</option>
                    <option value="K18">K18</option>
                </select>

        </tr>
        <tr>
            <td class="subhov"  style="text-align: center" colspan="2">  <input class="submit"  type="submit" name="sub" value="UPDATE"></td>
        </tr>

    </table>
</form>            
