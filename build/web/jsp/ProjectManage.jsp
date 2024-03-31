<%-- 
    Document   : ProjectManage
    Created on : Feb 1, 2024, 2:17:09 PM
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
        <p><a href="ProjectController?service=listAll">Show All Projects</a> ||
            <c:if test="${sessionScope.UserLogin.isAdmin}">
                <a href="ProjectController?service=insert">Insert New Project</a></p>
            </c:if>

        <table border="1">
            <caption>${requestScope.titleTable}</caption>
            <tr>
                <th>ProNo</th>
                <th>Name</th>
                <th>StartDate</th>
                <th>EndDate</th>
                <th>DeptNo</th>
                <c:if test="${sessionScope.UserLogin.isAdmin}">
                    <th>Update</th>
                    <th>Delete</th>
                </c:if>

            </tr>
            <c:forEach items="${requestScope.data}" var="pro">
                <tr>
                    <td><a href="Emp_WorkOn_ProController?service=listAll&option=project&id=${pro.getProNo()}">${pro.getProNo()}</a></td>
                    <td>${pro.getName()}</td>
                    <td>${pro.getStartDate()}</td>
                    <td>${pro.getEndDate()}</td>
                    <td>${pro.getDeptNo()}</td>
                    <c:if test="${sessionScope.UserLogin.isAdmin}">
                        <td><a href="ProjectController?service=update&prono=${pro.getProNo()}">update</td>
                        <td><a href="ProjectController?service=delete&prono=${pro.getProNo()}">delete</td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <form action="ProjectController?service=listAll" method="post">
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

