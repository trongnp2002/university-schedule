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
<form class="form3" action="/schedule/admin_architecture/building" method="post">
    <div class="subhovdel" style="margin-bottom: 12px; width: 99%; float: left">
        <input class="subdel " type="button" onclick="openPopA('popa')" value="Add department">
    </div>
    <br>

    <c:if test="${data != null && data.size() > 0}">
        <div class="forData" style="overflow: scroll">
            <table border ="1"  style= "margin-left: 8px; border-collapse: collapse; border: 1px solid black ;font-size: 14px; width: 98%" >
                <thead>
                <th>Bulding id</th>
                <th>Bulding name</th>
                <th>Action</th>
                </thead>
                <c:forEach  items="${data}" var="building">
                    <tr>
                        <td style="text-align: center">${building.getBuildingId()}</td>
                        <td style="text-align: center">${building.getBuildingName()}</td>
                        <td style="text-align: center">
                            <a onclick="checkDeleteAgree('${building.getBuildingId()}', 'building')" style="cursor: pointer">delete</a><br>
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
    <div class="popupForm" id="popa">
        <div class="popupContent">
            <div class="popupHeader">
                <i class="ti-close popClose" onclick="closePop('popa')"></i>
            </div>
            <div class="formPop">
                <table border="0">
                    <tr><td colspan="2"><h3>ADD BUILDING</h3></td></tr>
                    <tr>
                        <td >Bulding name: </td>
                        <td ><input type="text" name="building" id='endTimeCreate' ></td>
                    </tr>
                    <tr>
                        <td  class="subhov"  style="text-align: center" colspan="2" >  <input class="submit"  type="submit" name="sub" value="ADD" ></td>
                    </tr>

                </table>
            </div>            
        </div>
    </div>
</form>

