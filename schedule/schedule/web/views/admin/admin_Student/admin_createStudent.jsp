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
<html>

    <body >

        <form id="form1" action="/schedule/admin_student/create" method="post" >

            <table border="0" id="createTable">
                <tr><td colspan="2"> <h3 class="h1Cr" style="margin-bottom: 16px;">CREATE STUDENT INFORMATION</h3></td></tr>
                <tr>
                    <td style="width: 30%">Student name: </td>
                    <td style="width: 70%"><input type="text" name="studentName" required value="${studentCre.getName()}"></td>
                </tr>
            <tr>
                <td>Phone number: </td>
                <td><input type="text" name="studentPhone" minlength="10" value="${studentCre.getPhoneNumber()}"></td>
            </tr>
                <tr>
                    <td>Student Address: </td>
                    <td><input type="text" name="studentAddress" required></td>
                </tr>
                <tr>
                    <td>Student major: </td>
                    <td>
                        <select class="year" name ="studentMajor">
                            <option value="Software Engineer">Software Engineer</option>
                            <option value="Graphic Design">Graphic Design</option>
                            <option value="Artificial Intelligence">Artificial Intelligence</option>
                            <option value="English Studies">English Studies</option>
                            <option value="Japanese Studies">Japanese Studies</option>
                            <option value="Korean Studies">Korean Studies</option>
                            <option value="Business Administration">Business Administration</option>
                        </select></td>
                </tr>
                <tr>
                    <td>Start year: </td>
                    <td>
                        <select class="year" name ="studentYear">
                            <option value="K13">K13</option>
                            <option value="K14">K14</option>
                            <option value="K15">K15</option>
                            <option value="K16">K16</option>
                            <option value="K17">K17</option>
                            <option value="K18">K18</option>
                        </select>
                </tr>
                <tr>
                    <td  class="subhov" style="text-align: center" colspan="2"> <input type="submit" class="submit"  name="sub" value="CREATE" ></td>
                </tr>

            </table>
        </form>            

    </body>
</html>
