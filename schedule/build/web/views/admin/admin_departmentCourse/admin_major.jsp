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
<form class="form3" action="/schedule/admin_major/major" method="post">
        <div style="width: 99%">
            <div class="subhovdel" style="margin-bottom: 12px; float: left">
                <input class="subdel " type="button" onclick="openPopA('popm')" value="Add major">
            </div>            
        </div>
    <br>

    <c:if test="${data2 != null && data2.size() > 0}">
        <div class="forData" style="overflow: scroll">
            <table border ="1"  style= "margin-left: 8px; border-collapse: collapse; border: 1px solid black ;font-size: 14px; width: 98%" >
                <thead>
                <th>Major id</th>
                <th>Major name</th>
                <th>Major group</th>
                <th>Action</th>
                </thead>
                <c:forEach  items="${data2}" var="major">
                    <tr>
                        <td style="text-align: center">${major.getMajorId()}</td>
                        <td>${major.getMajorName()}</td>
                        <td>${major.getMajorGroup()}</td>
                        <td style="text-align: center">
                            <a onclick="checkDeleteAgree('${major.getMajorId()}', 'major')" style="cursor: pointer">delete</a><br>
                        </td>
                    </tr>
                </c:forEach>
            </table> 
        </div>                    
    </c:if>
    <c:if test="${data2  ==  null || data2.size() == 0}">     
        <div class="dataNotFound">
        </div>                 
    </c:if>
    <div class="popupForm" id="popm">
        <div class="popupContent">
            <div class="popupHeader">
                <i class="ti-close popClose" onclick="closePop('popm')"></i>
            </div>
            <div class="formPop">
                <table border="0">
                    <tr><td colspan="2"><h3>ADD MAJOR</h3></td></tr>
                    <tr>
                        <td>Major name: </td>
                        <td><input type="text" name="majorName" id='endTimeCreate' ></td>
                    </tr>
                    <tr>
                        <td>Major group: </td>
                        <td><input type="text" name="majorGroup" id='endTimeCreate' ></td>
                    </tr>
                    <tr>
                        <td  class="subhov"  style="text-align: center" colspan="2" >  <input class="submit"  type="submit" name="sub" value="ADD" ></td>
                    </tr>

                </table>
            </div>            
        </div>
    </div>
</form>

