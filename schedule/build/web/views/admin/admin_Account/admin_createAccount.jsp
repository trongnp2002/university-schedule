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

<form class="form1" action="/schedule/admin_account/create" method="post" >
    <table border="0" >
        <tr><td colspan="2"><h3>CREATE ACCOUNT</h3></td></tr>
        <tr>
            <td style="width: 30%">Account ID: </td>
            <td style="width: 70%"><input type="text"  name="accId" ></td>
        </tr>
        <tr>
            <td>Email: </td>
            <td><input type="text" name="email"  ></td>
        </tr>
        <tr>
            <td>Password: </td>
            <td><input type="text" name="password" id='passCreateAcc'></td>
        </tr>
        <tr id="reportPassRow" style="display: none">
            <td></td>
            <td><p id="textReportPass"></p></td>
        </tr>
        <tr>
            <td>Re-password: </td>
            <td>
                <input type="text" name="re_pass" minlength="10" id="re_passCreateAcc" onchange="checkRepass('passCreateAcc', 're_passCreateAcc', 'textReportPass', 'reportPassRow')">
            </td>
        </tr>

        <tr>
            <td>Roll: </td>
            <td>
                <select class="userOption" id="roll" name="roll" onchange="displayRollInput()">
                    <option value="0">Select</option>
                    <option value="1">Student</option>
                    <option value="2">Professor</option>
                </select>

            </td>
        </tr>

        <tr id="studentRollInput" style="display:none">
            <td>Student Id: </td>
            <td>
                <input type="text" name="studentId">          
            </td>
        </tr>

        <tr id="professorRollInput"  style="display:none"> 
            <td>Professor Id: </td>
            <td>
                <input type="text" name="professorId">         
            </td>

        </tr>
        <tr>
            <td class="subhov"  style="text-align: center" colspan="2">  <input class="submit"  type="submit" name="sub" value="CREATE" ></td>
        </tr>

    </table>
</form>          
<script >
    function displayRollInput() {
        var userRoll = document.getElementById("roll").value;
        if (userRoll === "1") {
            document.getElementById("studentRollInput").style.display = "table-row";
            document.getElementById("professorRollInput").style.display = "none";

        }
        if (userRoll === "2") {
            document.getElementById("professorRollInput").style.display = "table-row";
            document.getElementById("studentRollInput").style.display = "none";
        }
    }
</script>

