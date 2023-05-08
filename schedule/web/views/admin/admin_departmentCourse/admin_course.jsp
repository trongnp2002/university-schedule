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



<form class="form3" action="/schedule/admin_course/course" method="post">
    <div class="forInput" >
        <input id="searchText" class="inputSearch" type="text" name="search" value="${input}">
        <select id="selectCondition" class="sel" name="option">
            <option value="all">All</option>
            <option value="name">Name</option>
            <option value="code">Course code</option>
            <option value="semester">Semester</option>
            <option value="depart">Department</option>
        </select>
        <br>
        <div class="subhovdel" >
            <input class="subdel " type="submit" name="sub" value="SELECT">
        </div>
    </div>

    <br>
    <br>

    <div style="width: 99%">
        <div class="subhovdel" style="margin-bottom: 12px; float: left">
            <input class="subdel " type="button" onclick="openPopA('popf')" value="Add course">
        </div>            
    </div>

    <br>

    <c:if test="${data1 != null && data1.size() > 0}">
        <div class="forData" style="overflow: scroll">
            <table border ="1"  style= "margin-left: 8px; border-collapse: collapse; border: 1px solid black ;font-size: 14px; width: 98%" >
                <thead>
                <th>Course id</th>
                <th>Course code</th>
                <th>Course name</th>
                <th>Department</th>
                <th>Semester</th>
                <th>Action</th>
                </thead>
                <c:forEach  items="${data1}" var="course">
                    <tr>
                        <td style="text-align: center">${course.getCourseId()}</td>
                        <td style="text-align: center">${course.getCourseCode()}</td>
                        <td style="text-align: center">${course.getCourseName()}</td>
                        <td style="text-align: center">${course.getDepartmentName()}</td>
                        <td style="text-align: center">${course.getSemester()}</td>
                        <td style="text-align: center">
                            <a onclick="checkDeleteAgree('${course.getCourseId()}', 'course')" style="cursor: pointer">delete</a><br>
                        </td>
                    </tr>
                </c:forEach>
            </table> 
        </div>                    
    </c:if>
    <c:if test="${data1  ==  null || data1.size() == 0}">     
        <div class="dataNotFound">
        </div>                 
    </c:if>
    <div class="popupForm" id="popf">
        <div class="popupContent">
            <div class="popupHeader">
                <i class="ti-close popClose" onclick="closePop('popf')"></i>
            </div>
            <div class="formPop">
                <table border="0">
                    <tr><td colspan="2"><h3>ADD COURSE</h3></td></tr>
                    <tr>
                        <td>Course code: </td>
                        <td><input type="text" name="courseCode" ></td>
                    </tr>
                    <tr>
                        <td>Course name: </td>
                        <td><input type="text" name="courseName" ></td>
                    </tr>
                    <tr>
                        <td>Department: </td>
                        <td>
                            <select class="userOption" name ="department1">
                                <c:forEach items="${data}" var="dep">
                                    <option value ="${dep.getDepartmentsId()}">${dep.getDepartmentName()}</option>
                                </c:forEach>
                            </select>
                    </tr>
                    <tr>
                        <td>Semester: </td>
                        <td>
                            <select class="userOption" name ="semester">
                                <option value ="1">1</option>
                                <option value ="2">2</option>
                                <option value ="3">3</option>
                                <option value ="4">4</option>
                                <option value ="5">5</option>
                                <option value ="6">6</option>
                                <option value ="7">7</option>
                                <option value ="8">8</option>
                                <option value ="9">9</option>
                            </select>
                        </td>

                    </tr>
                    <tr>
                        <td  class="subhov"  style="text-align: center" colspan="2" >  <input class="submit"  type="submit" name="sub" value="ADD" ></td>
                    </tr>

                </table>
            </div>            
        </div>
    </div>
</form>

