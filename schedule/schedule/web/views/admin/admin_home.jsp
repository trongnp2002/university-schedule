<%-- 
    Document   : admin_home
    Created on : Mar 4, 2023, 1:46:37 PM
    Author     : nguyn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/views/assets/style.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/views/assets/icon/themify-icons-font/themify-icons/themify-icons.css"/>
        <style>
            .content1{
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
                margin-top: 46px;
                width: 600px;
            }
            header{
                margin-bottom: 46px;
            }
        </style>
        <script src="${pageContext.request.contextPath}/views/assets/myjs.js"></script>
    </head>

    <body onclick="">
        <header >
            <div class="fptIcon"></div>
            <ul id="nav">
                <li><a href="/schedule/admin/admin_home">HOME</a></li>
                <li><a href="/schedule/admin/admin_student">STUDENTS</a></li>
                <li><a href="">TEACHERS</a></li>
                <li><a href="">SUBJECTS</a></li>
                <li>
                    <a>
                        QUICK ACTION
                        <i class ="nav-arrow-down ti-widget-alt"></i>
                    </a>
                    <ul class="subnav">
                        <li><a href="">Figure</a></li>
                        <li><a href="">Wallpaper</a></li>
                        <li><a href="">Clothes</a></li>
                    </ul>
                </li>
            </ul>

            <div class="profile">
                <i class="userIcon ti-user" onclick="displayProfile()">
                    <ul id="profileUser" class="subnav">
                        <li>Name: <strong>Administrator</strong></li>
                        <li>Email: <strong>admin@fpt.com.vn</strong></li>
                        <li>User's role: <strong> administrator</strong></li>
                        <li><a href="" style="text-decoration: none; color: rgb(0, 123, 255);">Sign out</a></li>
                    </ul>                    
                </i>



            </div>
        </header>
        <div id="slide" onclick="eventOff()">
        </div>
        <div id="container" onclick="">
            <div id="sidebar"  >
                <div class="openMenuButton" onclick="displayMenu()">
                    <i class="ti-menu"></i>
                </div>
                <div id="theMenu">
                    <h3>HOME   <i style="float: right;"  class="ti-menu"onclick="displayMenu()"></i></h3>
                    <h4>SETUP</h4>
                    <ul class="list">
                        <li><a href="url">School Account</a></li>
                        <li><a href="url">Classrooms</a></li>
                        <li><a href="url">Professors</a></li>
                        <li><a href="url">Students</a></li>
                    </ul>
                    <h4>Academic disciplines</h4>
                    <ul class="list">
                        <li><a href="url">Course</a></li>
                        <li><a href="url">Department</a></li>
                    </ul>
                    <h4>Report</h4>
                    <ul class="list">
                        <li><a href="url">Schedule</a></li>
                        <li><a href="url">Slot</a></li>
                        <li><a href="url">Attendance</a></li>
                    </ul>
                    <h4>School architecture</h4>
                    <ul class="list">
                        <li><a href="url">Room</a></li>
                        <li><a href="url">Building</a></li>
                        <li><a href="url">Attendance</a></li>
                    </ul>
                </div>

            </div>
            <div id="content" onclick="eventOff()">
                <div class="contentLeft"></div>
            </div>
        </div>


        <footer>
            <p>Degin by <a href="https://www.facebook.com/profile.php?id=100007885478015">HE163170&trade;</a></p>
        </footer>
    </body>


</html>
