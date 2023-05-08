<%-- 
    Document   : admin_selectStudent
    Created on : Mar 4, 2023, 11:08:47 PM
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
<form class="form3" action="/schedule/admin_student/select" method="post">
    <div class="forInput" >
        <input id="searchText" class="inputSearch" type="text" name="search" value="${input}">
        <select id="selectCondition" class="sel" name="option">
            <option value="all">All</option>
            <option value="id">Id</option>
            <option value="name">Name</option>
            <option value="major">Major</option>
            <option value="year">Year of study</option>
            <option value="addr">Address</option>
        </select>
        <br>
        <div class="subhovdel" >
            <input class="subdel " type="submit" name="sub" value="SELECT">
        </div>
    </div>

    <c:if test="${data != null && data.size() > 0}">
        <div class="forData" style="overflow: scroll">
            <table border ="1"  style= "margin-left: 8px; border-collapse: collapse; border: 1px solid black ;font-size: 14px; width: 98%" >
                <thead>
                <th>Roll number</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone number</th>
                <th>Major</th>
                <th>Year of study</th>                    
                <th>Address</th>
                <th>Action</th>
                </thead>
                <c:forEach  items="${data}" var="student">
                    <tr>
                        <td style="text-align: center">${Validation.getStudentRollNum(student.getMajor(),student.getStudentID(),false)}</td>
                        <td>${student.getName()}</td>
                        <td>${student.getEmail()}</td>.
                        <td style="text-align: center" >${student.getPhoneNumber()}</td>
                        <td style="text-align: center">${student.getMajor()}</td>
                        <td style="text-align:  center">${student.getYearOfStudy()}</td>
                        <td>${student.getAddress()}</td>
                        <td style="text-align: center">
                            <a onclick="openPop('${student.getStudentID()}')" style="cursor: pointer">update</a><br>
                            <br>
                            <a onclick="checkDeleteAgree('${student.getStudentID()}', 'student')" style="cursor: pointer">delete</a><br>
                        </td>
                    </tr>
                </c:forEach>
            </table> 
        </div>                    
    </c:if>
    <c:if test="${data  ==  null || data.size() == 0}">     
        <div class="dataNotFound">
        </div>                 
    </c:if>
    <div class="popupForm" id="popf">
        <div class="popupContent">
            <div class="popupHeader">
                <i class="ti-close popClose" onclick="closePop('popf')"></i>
            </div>

            <div class="formPop"  >
                <%
                String id = "";
                id = id + request.getParameter("id");
                %>
                <table border="0" >
                    <tr><td colspan="2"><h3>UPDATE STUDENT INFORMATION</h3></td></tr>
                    <tr>
                        <td style="width: 30%">Student ID: </td>
                        <td style="width: 70%"><input type="text" id="theIdPop" name="studentID"  readonly></td>
                    </tr>

                    <tr>
                        <td>Student name: </td>
                        <td><input type="text" name="studentName"  ></td>
                    </tr>
                    <tr>
                        <td>Student email: </td>
                        <td><input type="text" name="studentEmail" ></td>
                    </tr>
                    <tr>
                        <td>Phone number: </td>
                        <td><input type="text" name="studentPhone" minlength="10"></td>
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
                            </select>
                        </td>
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
                        <td class="subhov"  style="text-align: center" colspan="2">  <input class="submit"  type="submit" name="sub" value="ENTER" ></td>
                    </tr>
                </table>
            </div>            
        </div>
    </div>
</form>

