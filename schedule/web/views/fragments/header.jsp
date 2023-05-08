<%-- 
    Document   : head
    Created on : Mar 10, 2023, 7:56:36 PM
    Author     : nguyn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<header >
    <div class="fptIcon"></div>
    <ul id="nav">
        <li><a href="/schedule/admin/admin_home">HOME</a></li>
        <li><a href="/schedule/admin/admin_student">STUDENTS</a></li>
        <li><a href="/schedule/admin/admin_professor">PROFESSOR</a></li>
        <li><a href="/schedule/admin/admin_account">ACCOUNTS</a></li>
       <li><a href="/schedule/admin/admin_class">CLASSES MANAGER</a></li>
    </ul>
    <div class="profile" onclick="displayProfile()">
        <i class="userIcon ti-user" >
            <ul id="profileUser" class="subnav">
                <li>Name: <strong>Administrator</strong></li>
                <li>Email: <strong>admin@fpt.com.vn</strong></li>
                <li>User's role: <strong> administrator</strong></li>
                <li><a href="/schedule/logout" style="text-decoration: none; color: rgb(0, 123, 255);">Sign out</a></li>
            </ul>                    
        </i>
    </div>
</header>
<div id="slide" onclick="eventOff()">
</div>

