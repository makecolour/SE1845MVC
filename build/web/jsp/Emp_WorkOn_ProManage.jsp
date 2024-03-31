<%-- 
    Document   : Emp_WorkOn_ProManage
    Created on : Feb 1, 2024, 2:57:08 PM
    Author     : quyen
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
        <jsp:include page="menu.jsp"/>
        <p><a href="Emp_WorkOn_ProController?service=listAll">Show All Emp_WorkOn_Pro</a> ||
            <c:if test="${sessionScope.UserLogin.isAdmin}">
                <a href="Emp_WorkOn_ProController?service=insert">Insert New Emp_WorkOn_Pro</a></p>
            </c:if>
        <table border="1">
            <caption>${requestScope.titleTable}</caption>
            <tr>
                <th>SSN</th>
                <th>ProNo</th>
                <th>Position</th>
                <th>HourPerday</th>
                <c:if test="${sessionScope.UserLogin.isAdmin}">
                    <th>Update</th>
                    <th>Delete</th>
                </c:if>
                
            </tr>
            <c:forEach items="${requestScope.data}" var="emp">
                <tr>
                    <td>${emp.getSSN()}</td>
                    <td>${emp.getProNo()}</td>
                    <td>${emp.getPosition()}</td>
                    <td>${emp.getHourPerDay()}</td>
                    <c:if test="${sessionScope.UserLogin.isAdmin}">
                        <td><a href="Emp_WorkOn_ProController?service=update&ssn=${emp.getSSN()}&prono=${emp.getProNo()}">update</td>
                        <td><a href="Emp_WorkOn_ProController?service=delete&ssn=${emp.getSSN()}&prono=${emp.getProNo()}">delete</td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <form action="Emp_WorkOn_ProController?service=listAll" method="post">
            <table>
                <tr>
                    <td>SEARCH SSN: </td>
                    <td><input type="text" name="ssn" required></td>
                    <td><input type="submit" name="submit" value="submit"></td>
                    <td><input type="reset" value="clear"></td>
                    <td><input type="hidden" name="option" value="searchssn"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
