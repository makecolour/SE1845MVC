<%-- 
    Document   : DependenceManage
    Created on : Feb 1, 2024, 2:37:47 PM
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
        <p><a href="DependenceController?service=listAll">Show All Dependences</a> ||
            <c:if test="${sessionScope.UserLogin.isAdmin}">
                <a href="DependenceController?service=insert">Insert New Dependence</a></p>
            </c:if>
        <table border="1">
            <caption>${requestScope.titleTable}</caption>
            <tr>
                <th>SSN</th>
                <th>Name</th>
                <th>DOB</th>
                <th>Relationship</th>
                <c:if test="${sessionScope.UserLogin.isAdmin}">
                    <th>Update</th>
                    <th>Delete</th>
                </c:if>  
            </tr>
            <c:forEach items="${requestScope.data}" var="dep">
                <tr>
                    <td>${dep.getSSN()}</td>
                    <td>${dep.getName()}</td>
                    <td>${dep.getDOB()}</td>
                    <td>${dep.getRelationship()}</td>
                    <c:if test="${sessionScope.UserLogin.isAdmin}">
                        <td><a href="DependenceController?service=update&ssn=${dep.getSSN()}&name=${dep.getName()}">update</td>
                        <td><a href="DependenceController?service=delete&ssn=${dep.getSSN()}&name=${dep.getName()}">delete</td>
                    </c:if>  
                </tr>
            </c:forEach>
        </table>
        <form action="DependenceController?service=listAll" method="post">
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
