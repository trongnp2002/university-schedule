<%-- 
    Document   : admin_updateCourseInClass
    Created on : Mar 14, 2023, 9:58:01 PM
    Author     : nguyn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="popupForm" id="popf">
    <div class="popupContent">
        <div class="popupHeader">
            <i class="ti-close popClose" onclick="closePop('popf')"></i>
        </div>
        <div class="formPop">

            <table border="0">
                <tr><td colspan="2"><h3>UPDATE COURSE</h3></td></tr>
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