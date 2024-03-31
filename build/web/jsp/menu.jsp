<%-- 
    Document   : menu
    Created on : Feb 23, 2024, 8:45:11 AM
    Author     : Duc Minh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table style="width: 100%">
            <tr>
                <td>
                    <p align="center">
                        <a href="HomeController">Home</a> -            
                        <c:if test="${sessionScope.UserLogin != null}">
                            <a href="LogoutController">Logout</a></p>
                        </c:if>
                        <c:if test="${sessionScope.UserLogin == null}">
                            <a href="LoginController">Login</a></p>
                        </c:if>
                    <p align="end">Welcome ${sessionScope.UserLogin.getUserName()}</p>
                </td>
            </tr>
        </table>

    </body>
</html>
