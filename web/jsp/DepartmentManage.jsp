<%-- 
    Document   : DepartmentManage
    Created on : Feb 1, 2024, 2:03:43 PM
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
        <p><a href="DepartmentController?service=listAll">Show All Departments</a> ||
            <c:if test="${sessionScope.UserLogin.isAdmin}">
                <a href="DepartmentController?service=insert">Insert New Department</a></p>
            </c:if>
        <table border="1">
            <caption>${requestScope.titleTable}</caption>
            <tr>
                <th>DeptNo</th>
                <th>Name</th>
                <th>Location</th>
                <c:if test="${sessionScope.UserLogin.isAdmin}">
                    <th>Update</th>
                    <th>Delete</th>
                </c:if>  
            </tr>
            <c:forEach items="${requestScope.data}" var="dep">
                <tr>
                    <td>${dep.getDeptNo()}</td>
                    <td>${dep.getName()}</td>
                    <td>${dep.getLocation()}</td>
                    <c:if test="${sessionScope.UserLogin.isAdmin}">
                        <td><a href="DepartmentController?service=update&deptno=${dep.getDeptNo()}">update</td>
                        <td><a href="DepartmentController?service=delete&deptno=${dep.getDeptNo()}">delete</td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <form action="DepartmentController?service=listAll" method="post">
            <table>
                <tr>
                    <td>SEARCH NAME: </td>
                    <td><input type="text" name="name" required></td>
                    <td><input type="submit" name="submit" value="submit"></td>
                    <td><input type="reset" value="clear"></td>
                    <td><input type="hidden" name="option" value="searchname"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
