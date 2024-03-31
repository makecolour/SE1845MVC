<%-- 
    Document   : Emp_Manage_DeptManage
    Created on : Feb 1, 2024, 3:39:29 PM
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
        <p><a href="Emp_Manage_DeptController?service=listAll">Show All Emp_Manage_Depts</a> ||
            <c:if test="${sessionScope.UserLogin.isAdmin}">
                <a href="Emp_Manage_DeptController?service=insert">Insert New Emp_Manage_Depts</a></p>
            </c:if>
        <table border="1">
            <caption>${requestScope.titleTable}</caption>
            <tr>
                <th>SSN</th>
                <th>DeptNo</th>
                <th>StartDate</th>
                <th>EndDate</th>
                <c:if test="${sessionScope.UserLogin.isAdmin}">
                    <th>Update</th>
                    <th>Delete</th>
                </c:if>
                
            </tr>
            <c:forEach items="${requestScope.data}" var="emp">
                <tr>
                    <td>${emp.getSSN()}</td>
                    <td>${emp.getDeptNo()}</td>
                    <td>${emp.getStartDate()}</td>
                    <td>${emp.getEndDate()}</td>
                    <c:if test="${sessionScope.UserLogin.isAdmin}">
                        <td><a href="Emp_Manage_DeptController?service=update&ssn=${emp.getSSN()}&deptno=${emp.getDeptNo()}&startdate=${emp.getStartDate()}">update</td>
                        <td><a href="Emp_Manage_DeptController?service=delete&ssn=${emp.getSSN()}&deptno=${emp.getDeptNo()}&startdate=${emp.getStartDate()}">delete</td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <form action="Emp_Manage_DeptController?service=listAll" method="post">
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
