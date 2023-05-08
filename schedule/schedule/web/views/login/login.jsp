<%-- 
    Document   : login
    Created on : Feb 28, 2023, 2:47:57 PM
    Author     : nguyn
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <link rel="stylesheet" href="style.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/views/login/style.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FPTU_Schedule</title>
    </head>

    <body onload ="check1('${message})">
        <header>
            <h1>FPT University-HÒA LẠC</h1>
        </header>
        <main>
            <form id="login_form" class="form_class" action="/schedule/login" method="post">
                <div class="form_div">
                    <label>Login:</label>
                    <input class="field_class" name="account" type="text" value="${account}" placeholder="username" autofocus required>
                    <label>Password:</label>
                    <input id="pass" class="field_class" name="password" type="password" placeholder="password" required>
                    <button class="submit_class" type="submit" form="login_form" >Enter</button>
                </div>
                <div class="info_div">
                    <p>Forgot your password? <a href="https://chgpwd.fpt.edu.vn.">Click here</a></p>
                </div>
            </form>
        </main>
        <footer>
            <p>Degin by <a href="https://www.facebook.com/profile.php?id=100007885478015">HE163170&trade;</a></p>
        </footer>

        <script lang="text/javascript">
            function check1(report) {
                if (report !== '') {
                    alert('account or password is wrong!');
                    console.log(report);
                }
            }
        </script>
    </body>


</html>
