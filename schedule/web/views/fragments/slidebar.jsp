<%-- 
    Document   : slidebar
    Created on : Mar 10, 2023, 8:00:27 PM
    Author     : nguyn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div id="sidebar"  >
    <div class="openMenuButton" onclick="displayMenu()">
        <i class="ti-menu"></i>
    </div>
    <div id="theMenu">
        <h3>HOME   <i style="float: right;"  class="ti-menu"onclick="displayMenu()"></i></h3>
        <h4>SETUP</h4>
        <ul class="list">
            <li><a href="/schedule/admin/admin_account">School Account</a></li>
            <li><a href="/schedule/admin/admin_class">Classrooms</a></li>
            <li><a href="/schedule/admin/admin_professor">Professors</a></li>
            <li><a href="/schedule/admin/admin_student">Students</a></li>
        </ul>
        <h4>Academic disciplines</h4>
        <ul class="list">
            <li><a href="/schedule/admin/admin_department">Course <br> Department <br> Major</a></li>
        </ul>
        <h4>Report</h4>
        <ul class="list">
            <li><a href="/schedule/admin/admin_slot">Slot</a></li>
        </ul>
        <h4>School architecture</h4>
        <ul class="list">
            <li><a href="/schedule/admin/admin_architecture">Building & Room</a></li>
        </ul>
    </div>
</div>

