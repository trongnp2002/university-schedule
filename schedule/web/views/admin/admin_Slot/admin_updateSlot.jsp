<%-- 
    Document   : admin_Slot
    Created on : Mar 10, 2023, 10:38:03 PM
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


<form id="form3" action="/schedule/admin_slot/select" method="post">
    <div class="subhovdel" style="margin-bottom: 12px; width: 99%; float: left">
        <input class="subdel " type="button" onclick="openPopA('popa')" value="Add slot">
    </div>
    <br>
    <c:if test="${data != null && data.size() > 0}">
        <div class="forData" style="overflow: scroll">
            <table border ="1"  style= "margin-left: 8px; border-collapse: collapse; border: 1px solid black ;font-size: 14px; width: 98%" >
                <thead>
                <th>Slot</th>
                <th>Start time</th>
                <th>End time</th>
                <th>Action</th>
                </thead>
                <c:forEach  items="${data}" var="slot">
                    <tr>
                        <td style="text-align: center">Slot ${slot.getSlotId()}</td>
                        <td  style="text-align: center">${slot.getStartTime()}</td>.
                        <td style="text-align: center">${slot.getEndTime()}</td>
                        <td style="text-align: center"><a  onclick="openPop('${slot.getSlotId()}')">Update</a></td>
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
                    <tr><td colspan="2"><h3>UPDATE SLOT</h3></td></tr>
                    <tr>
                        <td>Slot Id: </td>
                        <td><input type="text" id="theIdPop" name="slotId" readonly=""></td>
                    </tr>
                    <tr>
                        <td style="width: 30%">Start time: </td>
                        <td style="width: 70%"><input type="time" id='startTimeUpdate' name="startTimeUpdate" ></td>
                    </tr>
                    <tr>
                        <td>End Time: </td>
                        <td><input type="time" name="endTimeUpdate" id='endTimeUpdate'></td>
                    </tr>
                    <tr>
                        <td  class="subhov"  style="text-align: center" colspan="2" >  <input class="submit"  type="submit" name="sub" value="UPDATE" ></td>
                    </tr>

                </table>
            </div>            
        </div>
    </div>
    <div class="popupForm" id="popa">
        <div class="popupContent">
            <div class="popupHeader">
                <i class="ti-close popClose" onclick="closePop('popa')"></i>
            </div>
            <div class="formPop">
                <table border="0">
                    <tr><td colspan="2"><h3>ADD SLOT</h3></td></tr>
                    <tr>
                        <td style="width: 30%">Start time: </td>
                        <td style="width: 70%"><input type="time" id='startTimeCreate' name="startTimeCreate" ></td>
                    </tr>
                    <tr>
                        <td>End Time: </td>
                        <td><input type="time" name="endTimeCreate" id='endTimeCreate'></td>
                    </tr>
                    <tr>
                        <td  class="subhov"  style="text-align: center" colspan="2" >  <input class="submit"  type="submit" name="sub" value="ADD" ></td>
                    </tr>

                </table>
            </div>            
        </div>
    </div>
</form>



