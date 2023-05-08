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
<form class="form3" action="/schedule/admin_professor/select" method="post">
    <div class="forInput" >
        <input id="searchText" class="inputSearch" type="text" name="search" value="${input}">
        <select id="selectCondition" class="sel" name="option">
            <option value="all">All</option>
            <option value="nickname">Nick name</option>
            <option value="name">Name</option>
            <option value="department">Department</option>

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
                <th>Nick name</th>
                <th>Name</th>
                <th>Email</th>                
                <th>Department</th>
                <th>Action</th>
                </thead>
                <c:forEach  items="${data}" var="professor">
                    <tr>
                        <td style="text-align: center" >${professor.getNickname()}</td>.
                        <td style="text-align: center" >${professor.getName()}</td>
                        <td style="text-align: center">${professor.getEmail()}</td>
                        <td style="text-align:  center">${professor.getDepartmentName()}</td>
                        <td style="text-align: center">
                            <a onclick="openPop('${professor.getProfessorId()}')" style="cursor: pointer">update</a><br>
                            <br>
                            <a onclick="checkDeleteAgree('${professor.getProfessorId()}', 'professor')" style="cursor: pointer">delete</a><br>
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
                    <tr><td colspan="2"><h3>UPDATE PROFESSOR INFORMATION</h3></td></tr>
                    <tr>
                        <td style="width: 30%">Professor ID: </td>
                        <td style="width: 70%"><input type="text" id="theIdPop" name="professorId"  readonly></td>
                    </tr>

                    <tr>
                        <td>Professor name: </td>
                        <td><input type="text" name="professorName"  ></td>
                    </tr>
                    <tr>
                        <td>Professor email: </td>
                        <td><input type="text" name="professorEmail" ></td>
                    </tr>

                    <tr>
                        <td>Department: </td>
                        <td>
                            <select class="userOption" name ="department">
                                <c:forEach items="${department}" var="dep">
                                    <option value ="${dep.getDepartmentsId()}">${dep.getDepartmentName()}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="subhov"  style="text-align: center" colspan="2">  <input class="submit"  type="submit" name="sub" value="UPDATE" ></td>
                    </tr>
                </table>
            </div>            
        </div>
    </div>
</form>

