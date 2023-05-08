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
<form class="form3" action="/schedule/admin_account/select" method="post">
    <div class="forInput" >
        <input class="inputSearch" id="searchText" type="text" name="search" value="${input}">
        <select id="selectCondition" class="sel" name="option">
            <option value="all">All</option>
            <option value="id">Id</option>
            <option value="email">Email</option>
            <option value="position">Position</option>

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
                <th>Account id</th>
                <th>Email</th>
                <th>Password</th>
                <th>User id</th>
                <th>User Position</th>
                <th>Action</th>

                </thead>
                <c:forEach  items="${data}" var="account">
                    <tr>
                        <td style="text-align: center">${account.getAccountId()}</td>
                        <td>${account.getEmail()}</td>.
                        <td>${account.getPassword()}</td>
                        <c:if test="${account.getRoll() == 1}">
                            <td>${account.getStudentId()}</td>
                        </c:if>
                        <c:if test="${account.getRoll() == 2}">
                            <td>${account.getProfessorId()}</td>
                        </c:if>
                        <c:if test="${account.getRoll() == 1}">
                            <td style="text-align: center">Student</td>
                        </c:if>
                        <c:if test="${account.getRoll() == 2}">
                            <td style="text-align: center">Professor</td>
                        </c:if>
                        <td style="text-align: center">
                            <a onclick="checkDeleteAgree('${account.getAccountId()}', 'account')" style="cursor: pointer">Delete account</a>
                            <br>
                            <br>
                            <a onclick="openPop('${account.getAccountId()}')" style="cursor: pointer"">Change password</a>
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
            <div class="formPop">

                <table border="0" >
                    <tr><td colspan="2"><h3>CHANGE PASSWORD</h3></td></tr>
                    <tr>
                        <td style="width: 30%">Account ID: </td>
                        <td style="width: 70%"><input type="text" id="theIdPop" name="accId"  readonly></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><input type="text" name="password" id='pass'></td>
                    </tr>
                    <tr id="reportPassRowInPop" style="display:none">
                        <td></td>
                        <td><p id="textReportPassInPop"></p></td>
                    </tr>
                    <tr>
                        <td>Re-password: </td>
                        <td>
                            <input type="text" name="re_pass" minlength="10" id="re_pass" onchange="checkRepass('pass', 're_pass', 'textReportPassInPop', 'reportPassRowInPop')">
                        </td>
                    </tr>


                    </tr>
                    <tr>
                        <td class="subhov"  style="text-align: center" colspan="2">  <input class="submit"  type="submit" name="sub" value="ENTER" ></td>
                    </tr>
                </table>
                
                
            </div>            
        </div>
    </div>
</form>
