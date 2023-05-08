<%-- 
    Document   : admin_studentHome
    Created on : Mar 4, 2023, 3:24:15 PM
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
<html>
    <jsp:include page="/views/fragments/head.jsp" flush="true"></jsp:include>

        <body onload="checkReportClass('${report}', '${isOpenPop}')">
        <jsp:include page="/views/fragments/header.jsp" flush="true"></jsp:include>
            <div id="container" onclick="">
            <jsp:include page="/views/fragments/slidebar.jsp" flush="true"></jsp:include>
                <div id="content" onclick="eventOff()">
                    <h2>Class full information: </h2>
                    <main>
                        <div class="selection">
                            <h3><a href="/schedule/admin/admin_class" style="color: black">Classes's feature</a>/Class ${thisClass.getClassName()}</h3>
                    </div>
                    <br>
                    <hr>
                    <br>

                    <div id="result">
                        <div class="option" id="building" style="display: block"> 
                            <form class="form3" action="/schedule/admin_class/addCourse" method="post">
                                <div style="width: 99%">
                                    <div class="subhovdel" style="margin-bottom: 12px; float: left">
                                        <input class="subdel " type="button" onclick="openPopA('popa')" value="Add course">
                                    </div>            
                                </div>
                                <br>
                                <c:if test="${data  !=  null && data.size() != 0}">     
                                    <div class="forData" style="overflow: scroll">
                                        <table border ="1"  style= "margin-left: 8px; border-collapse: collapse; border: 1px solid black ;font-size: 14px; width: 98%" >
                                            <thead>
                                            <th>Course id</th>
                                            <th>Course name</th>
                                            <th>Semester</th>
                                            <th>Slot</th>
                                            <th>Room</th>
                                            <th>Professor</th>                    
                                            <th>Student in class</th>
                                            <th>Action</th>
                                            </thead>
                                            <c:forEach  items="${data}" var="classInfor">
                                                <tr>

                                                    <td style="text-align: center" >${classInfor.getCourseId()}</td>
                                                    <td style="text-align: center" >${classInfor.getCourseName()}</td>
                                                    <td style="text-align: center" >${classInfor.getSemester()}</td>
                                                    <td>
                                                        <c:forEach items="${classInfor.getSlot()}" var="slot">
                                                            <p>Slot: ${slot.getSlot_id()} : ${slot.getDayOfWeek()}</p>
                                                            <br>
                                                        </c:forEach>
                                                    </td>
                                                    <td style="text-align: center" >${classInfor.getRoom()}</td>
                                                    <td style="text-align: center" >${classInfor.getProfesser()}</td>
                                                    <td style="text-align: center" ><a href="/schedule/admin_class/studentInClass?classId=${thisClass.getClassId()}&courseId=${classInfor.getCourseId()}" style="cursor: pointer">Students</a></td>
                                                    <td style="text-align: center" ><a onclick="checkRemoveCourse('${thisClass.getClassId()}', '${classInfor.getCourseId()}')">Delete</a><br> 
                                                        <a href="/schedule/admin_class/updateClassCourse?classId=${thisClass.getClassId()}&courseId=${classInfor.getCourseId()}">Update</a></td>
                                                </tr>
                                            </c:forEach>
                                        </table> 
                                    </c:if>
                                    <c:if test="${data  ==  null || data.size() == 0}">     
                                        <div class="dataNotFound">
                                        </div>                 
                                    </c:if>
                                </div>

                                <div class="popupForm" id="popa">
                                    <div class="popupContent">
                                        <div class="popupHeader">
                                            <i class="ti-close popClose" onclick="closePop('popa')"></i>
                                        </div>
                                        <div class="formPop">
                                            <table border="0">
                                                <tr><td colspan="2"><h3>ADD COURSE</h3></td></tr>
                                                <tr>
                                                    <td>Class id</td>
                                                    <td><input type="text" name="classId" readonly value="${thisClass.getClassId()}"></td>
                                                </tr>
                                                <tr>
                                                    <td>Department: </td>
                                                    <td>
                                                        <select class="userOption" name ="department" onchange="this.form.submit()">
                                                            <option value="select" disabled selected>select</option>
                                                            <c:forEach items="${department}" var="dep">
                                                                <c:if test="${dep.getDepartmentsId() == selected}">
                                                                    <option value ="${dep.getDepartmentsId()}" selected="">${dep.getDepartmentName()}</option>                                                                    
                                                                </c:if>
                                                                <c:if test="${dep.getDepartmentsId() != selected}">
                                                                    <option value ="${dep.getDepartmentsId()}">${dep.getDepartmentName()}</option>
                                                                </c:if>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <c:if test="${listCourse != null}">
                                                    <tr>
                                                        <td>Course </td>
                                                        <td>
                                                            <select class="userOption" name ="course">
                                                                <c:forEach items="${listCourse}" var="course">
                                                                    <option value ="${course.getCourseId()}">${course.getCourseName()}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Slot</td>
                                                        <td>
                                                            <select class="userOption" name ="slot">
                                                                <c:forEach items="${listSlot}" var="slot">
                                                                    <option value ="${slot.getSlotId()}">Slot: ${slot.getSlotId()}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td>Day Of Week</td>
                                                        <td style="font-size: 16px; display: block">
                                                            <div style="width: 100%; display: flex;flex-wrap: wrap ;align-items: center;"><input style="width: 32px" type="checkbox" name="dow" value="MONDAY">&nbsp; MONDAY &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                <input style="width: 32px" type="checkbox" name="dow" value="TUESDAY">&nbsp; TUESDAY 
                                                            </div>

                                                            <div style="width: 100%; display: flex;flex-wrap: wrap ; align-items: center;"><input style="width: 32px" type="checkbox" name="dow" value="WEDNESDAY">&nbsp; WEDNESDAY &nbsp;&nbsp;&nbsp;
                                                                <input style="width: 32px" type="checkbox" name="dow" value="THURSDAY">&nbsp; THURSDAY </div>
                                                            <div style="width: 100%; display: flex;flex-wrap: wrap ; align-items: center;"><input style="width: 32px" type="checkbox" name="dow" value="FRIDAY">&nbsp; FRIDAY &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                <input style="width: 32px; margin-left: 1px;" type="checkbox" name="dow" value="SATURDAY">&nbsp; SATURDAY </div>
                                                            <div style="width: 40%; display: flex;flex-wrap: wrap ; align-items: center;"><input style="width: 32px" type="checkbox" name="dow" value="SUNDAY">&nbsp; SUNDAY </div>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td>Date</td>
                                                        <td style="font-size: 16px; display: block">
                                                            <label>From: &nbsp; <input type="date" name="startDate" max="2023-04-12" min="2023-01-01"></label>
                                                            <label>To: &nbsp; <input type="date" name="endDate" max="2023-04-12" min="2023-01-01"></label>

                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td>Professor </td>
                                                        <td>
                                                            <select class="userOption" name ="professor">
                                                                <c:forEach items="${listProfessor}" var="professor">
                                                                    <option value ="${professor.getNickname()}">${professor.getNickname()}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Room </td>
                                                        <td>
                                                            <input type="text" name="room">
                                                        </td>
                                                    </tr>
                                                </c:if>
                                                <tr>
                                                    <td  class="subhov"  style="text-align: center" colspan="2" >  <input class="submit"  type="submit" name="sub" value="ADD" ></td>
                                                </tr>

                                            </table>
                                        </div>            
                                    </div>
                                </div>
                                                
                                                

           
                                                
                            </form>
                        </div>                 
                    </div>
                </main>

            </div>
        </div>

        <script function>
            function checkRemoveCourse(classId, courseId) {
                if (confirm('Do you want to delete this course')) {
                    window.location = "/schedule/admin_class/removeCourse?classId=" + classId + "&courseId=" + courseId;
                }

            }
        </script>
        <jsp:include page="/views/fragments/footer.jsp" flush="true"></jsp:include>
    </body>


</html>
