<%-- 
    Document   : header
    Created on : Mar 16, 2023, 5:49:02 AM
    Author     : nguyn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<header style="padding: 20px; box-sizing: border-box;">
    <div class="fptIcon" onclick="chuyenVeHome('${sessionScope.account.roll}')"></div>
    <h1>FPT University-HÒA LẠC</h1>

    <div style="padding: 20px; ; background-color: #dee2e6; color: black; font-size: 16px; border-radius: 8px;">
        <a href="/schedule/logout" style="text-decoration: none; color: rgb(0, 123, 255);">Sign out</a>
    </div>
    <script src=""></script>
  
</header>
