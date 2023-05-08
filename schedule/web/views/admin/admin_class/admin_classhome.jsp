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

<form class="form3" action="/schedule/admin_class/classaction" method="post">
    <div class="forInput" >
        <input id="searchText" class="inputSearch" type="text" name="search" value="${input}">
        <select id="selectCondition" class="sel" name="option">
            <option value="all" >All</option>
            <option value="id">Class id</option>
            <option value="className">Class Name</option>
            <option value="mainMajor">Main major</option>
        </select>
        <br>
        <div class="subhovdel" >
            <input class="subdel " type="submit" name="sub" value="SELECT">
        </div>
    </div>
    <div style="width: 99%">
        <div class="subhovdel" style="margin-bottom: 12px; float: left">
            <input class="subdel " type="button" onclick="openPopF()" value="Add classes">
        </div>            
    </div>

    <c:if test="${data != null && data.size() > 0}">
        <div class="forData" style="overflow: scroll">
            <table border ="1"  style= "margin-left: 8px; border-collapse: collapse; border: 1px solid black ;font-size: 14px; width: 98%" >
                <thead>
                <th>Class id</th>
                <th>Class name</th>
                <th>Star year</th>
                <th>Class main major</th>
                <th>Action</th>
                </thead>
                <c:forEach  items="${data}" var="classes">
                    <tr>
                        <td style="text-align: center">${classes.getClassId()}</td>
                        <td style="text-align: center">${classes.getClassName()}</td>
                        <td style="text-align: center">${classes.getStudentYear()}</td>
                        <td style="text-align: center">${classes.getMajorName()}</td>
                        <td style="text-align: center">
                            <a onclick="checkDeleteAgree('${classes.getClassId()}', 'classes')" style="cursor: pointer">delete</a><br>
                            <a href="/schedule/admin_class/classinfor?id=${classes.getClassId()} " style="cursor: pointer">Class infor</a><br>

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
                <table border="0">
                    <tr><td colspan="2"><h3>CREATE CLASS</h3></td></tr>
                    <tr>
                        <td>Class main major: </td>
                        <td>
                            <select class="userOption" name ="classMajor">
                                <c:forEach items="${data2}" var="major">
                                    <option value="${major.getMajorId()}">${major.getMajorName()}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Start year: </td>
                        <td>
                            <select class="userOption" name ="startYear">
                                <option value="K13">K13</option>
                                <option value="K14">K14</option>
                                <option value="K15">K15</option>
                                <option value="K16">K16</option>
                                <option value="K17">K17</option>
                                <option value="K18">K18</option>
                            </select>
                    </tr>
                    <tr>
                        <td  class="subhov"  style="text-align: center" colspan="2" >  <input class="submit"  type="submit" name="sub" value="ADD" ></td>
                    </tr>

                </table>
            </div>            
        </div>
    </div>
</form>

