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
<form class="form3" action="/schedule/admin_architecture/room" method="post">
    <div class="forInput" >
        <input id="searchText" class="inputSearch" type="text" name="search" value="${input}">
        <select id="selectCondition" class="sel" name="option">
            <option value="select" disabled selected>Select</option>
            <option value="buldingNameOption">Bulding name</option>
            <option value="floorOption">Floor</option>
            <option value="roomName">Room Name</option>
        </select>
        <br>
        <div class="subhovdel" >
            <input class="subdel " type="submit" name="sub" value="SELECT">
        </div>
    </div>
    <div class="subhovdel" style="margin-bottom: 12px; width: 99%; float: left">
        <input class="subdel " type="button" onclick="openPopF()" value="Add room">
    </div>
    <c:if test="${rooms != null && rooms.size() > 0}">
        <div class="forData" style="overflow: scroll">
            <table border ="1"  style= "margin-left: 8px; border-collapse: collapse; border: 1px solid black ;font-size: 14px; width: 98%" >
                <thead>
                <th>Building</th>
                <th>Room name</th>
                <th>Floor</th>
                <th>Action</th>
                </thead>
                <c:forEach  items="${rooms}" var="room">
                    <tr>
                        <td style="text-align: center">${room.getBuildingName()}</td>
                        <td style="text-align: center">${room.getName()}</td>
                        <td style="text-align: center">${room.getFloor()}</td>
                        <td style="text-align: center">
                            <a onclick="checkDeleteAgree('${room.getRoomId()}', 'room')" style="cursor: pointer">delete</a><br>
                        </td>
                    </tr>
                </c:forEach>
            </table> 
        </div>                
    </c:if>
    <c:if test="${rooms  ==  null || rooms.size() == 0}">     
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
                    <tr><td colspan="2"><h3>ADD ROOM</h3></td></tr>
                    <tr>
                        <td>Building name: </td>
                        <td>
                            <select id="buildingName" class ="userOption" name="selectBulding">
                                <c:forEach items="${data}" var="building">
                                    <option value="${building.getBuildingName()}">${building.getBuildingName()}</option>
                                </c:forEach>
                            </select>

                        </td>
                    </tr>
                    <tr>
                        <td>Floor: </td>
                        <td>
                            <select id="floor" class = "userOption" name="floor">
                                <option value="1">Floor 1</option>
                                <option value="2">Floor 2</option>
                                <option value="3">Floor 3</option>
                                <option value="4">Floor 4</option>
                                <option value="5">Floor 5</option>
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

