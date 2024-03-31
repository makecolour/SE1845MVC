<%-- 
    Document   : Login.jsp
    Created on : Feb 18, 2024, 3:03:43 PM
    Author     : quyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body style="display: flex">
        <form action="LoginController" method="post" style="margin: auto">
            <h1>Login</h1>
            <p style="color: red;">${requestScope.error}</p>
            <table>
                <tr><td><input type="text" placeholder="Username" name="user" required></td></tr>
                <tr><td><input type="password" placeholder="Password" name="pass" required></td></tr>
            </table>
            <input type="submit" value="submit">
        </form>
    </body>
</html>
