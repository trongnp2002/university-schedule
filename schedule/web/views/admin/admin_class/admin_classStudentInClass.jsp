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

        <body onload="checkReportD('${report}', '${select}')">
        <jsp:include page="/views/fragments/header.jsp" flush="true"></jsp:include>
            <div id="container" onclick="">
            <jsp:include page="/views/fragments/slidebar.jsp" flush="true"></jsp:include>
                <div id="content" onclick="eventOff()">
                    <h2>Class all students information: </h2>
                    <main>
                        <h3 ><h3><a href="/schedule/admin/admin_class" style="color: black">Classes's feature</a>
                                /<a href="/schedule/admin_class/classinfor?id=${thisClass.getClassId()}" style="color: black">Class ${thisClass.getClassName()}</a>/
                            ${course.getCourseName()}</h3>
                        <br>
                        <hr>
                        <br>

                        <div id="result">
                            <div class="option" id="building" style="display: block"> 
                                <div class="forData" style="overflow: scroll">
                                    <form class="form3" action="/schedule/admin_class/addStudent" method="post">
                                        <div style="width: 99%">
                                            <div class="subhovdel" style="margin-bottom: 12px; float: left">
                                                <input class="subdel " type="button" onclick="openPopA('popa')" value="Add Student">
                                            </div>            
                                        </div>
                                        <br>
                                        <c:if test="${data != null && data.size() > 0}">
                                            <div class="forData" style="overflow: scroll">
                                                <table border ="1"  style= "margin-left: 8px; border-collapse: collapse; border: 1px solid black ;font-size: 14px; width: 98%" >
                                                    <thead>
                                                    <th>Roll number</th>
                                                    <th>Name</th>
                                                    <th>Email</th>
                                                    <th>Phone number</th>
                                                    <th>Major</th>
                                                    <th>Year of study</th>                    
                                                    <th>Address</th>
                                                    <th>Action</th>
                                                    </thead>
                                                    <c:forEach  items="${data}" var="student">
                                                        <tr>
                                                            <td style="text-align: center">${Validation.getStudentRollNum(student.getMajor(),student.getStudentID(),false)}</td>
                                                            <td>${student.getName()}</td>
                                                            <td>${student.getEmail()}</td>.
                                                            <td style="text-align: center" >${student.getPhoneNumber()}</td>
                                                            <td style="text-align: center">${student.getMajor()}</td>
                                                            <td style="text-align:  center">${student.getYearOfStudy()}</td>
                                                            <td>${student.getAddress()}</td>
                                                            <td style="text-align: center">
                                                                <a onclick="checkRemoveAgree('${student.getStudentID()}', '${thisClass.getClassId()}', '${course.getCourseId()}', 'removeStudent')" style="cursor: pointer">remove</a><br>
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

                                                <div class="formPop"  >
                                                    <table border="0" >
                                                        <tr><td colspan="2"> <label><input style= "padding: 8px; width: 52px;"   type="text" name="classId" readonly value="${thisClass.getClassId()}"></label>
                                                                <label><input style=" padding: 8px; text-align: center ; width: 40px"  type="text" readonly name="courseId" value="${course.getCourseId()}"></label><h3>ADD STUDENT TO CLASS</h3></td></tr>
                                                        <tr>
                                                            <td style="width: 30%">Class Name: </td>
                                                            <td style="width: 70%"><input type="text" id="theIdPop" name="className"  value="${thisClass.getClassName()}" readonly></td>
                                                        </tr>
                                                        <tr>
                                                            <td style="width: 30%">Course Name: </td>
                                                            <td style="width: 70%"><input type="text" id="theIdPop" name="courseName"  value="${course.getCourseName()}" readonly></td>    
                                                        </tr>
                                                        <tr>
                                                            <td style="width: 30%">Student ID: </td>
                                                            <td style="width: 70%"><input type="text" id="theIdPop" name="studentID"  ></td>
                                                        </tr>

                                                        <tr>
                                                            <td class="subhov"  style="text-align: center" colspan="2">  <input class="submit"  type="submit" name="sub" value="ADD" ></td>
                                                        </tr>
                                                    </table>
                                                </div>            
                                            </div>
                                        </div>
                                    </form>
                                </div>   
                            </div>                 
                        </div>
                </main>

            </div>
        </div>

        <jsp:include page="/views/fragments/footer.jsp" flush="true"></jsp:include>
        <script>
            function checkRemoveAgree(sid, cid, csid, service) {
                if (confirm('Do you want to remove: ' + sid)) {
                    if (service === 'removeStudent') {
                        window.location = "/schedule/admin_class/removeStu?stuId=" + sid + "&claId=" + cid + "&couId=" + csid;
                    }
                }
            }
        </script>
    </body>


</html>
