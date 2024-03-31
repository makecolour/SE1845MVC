<%-- 
    Document   : employeeManage
    Created on : Jan 31, 2024, 1:31:26 PM
    Author     : quyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <p><c:if test="${sessionScope.UserLogin != null}">
                <a href="CartController?service=showcart">
            </c:if>
            <c:if test="${sessionScope.UserLogin == null}">
                <a href="LoginController">
            </c:if> Show Cart</a> ||
        <a href="EmployeeController?service=listAll">Show All Employee</a> ||
        <c:if test="${sessionScope.UserLogin.isAdmin}">
            <a href="EmployeeController?service=insert">Insert New Employee</a>
        </c:if>
        </p>
        <table border="1">
            <caption>${requestScope.titleTable}</caption>
            <tr>
                <th>SSN</th>
                <th>FName</th>
                <th>LName</th>
                <th>Address</th>
                <th>Salary</th>
                <th>Sex</th>
                <th>DeptNo</th>
                <th>SSNSupervisor</th>
                <c:if test="${sessionScope.UserLogin.isAdmin}">
                    <th>update</th>
                    <th>delete</th>
                </c:if>
                <th>Cart</th>
            </tr>
            <c:forEach items="${requestScope.data}" var="emp">
                <tr>
                    <td>${emp.getSSN()}</td>
                    <td>${emp.getFName()}</td>
                    <td>${emp.getLName()}</td>
                    <td>${emp.getAddress()}</td>
                    <td>${emp.getSalary()}</td>
                    <td>${emp.isSex() ? "Female":"Male"}</td>
                    <td>${emp.getDeptNo()}</td>
                    <td>${emp.getSSNSupervisor()}</td>
                    <c:if test="${sessionScope.UserLogin.isAdmin}">
                        <td><a href="EmployeeController?service=update&ssn=${emp.getSSN()}">update</td>
                        <td><a href="EmployeeController?service=delete&ssn=${emp.getSSN()}">delete</td>
                    </c:if>
                    <td>
                        <c:if test="${sessionScope.UserLogin != null}"><a href="CartController?service=addtocart&ssn=${emp.getSSN()}"></c:if>
                        <c:if test="${sessionScope.UserLogin == null}"><a href="LoginController"></c:if>
                        Add To Cart
                    </td>
                </tr>
            </c:forEach>
        </table>
        <form action="EmployeeController?service=listAll" method="post">
            <table>
                <tr>
                    <td>SEARCH FNAME: </td>
                    <td><input type="text" name="fname" required></td>
                    <td><input type="submit" name="submit" value="submit"></td>
                    <td><input type="reset" value="clear"></td>
                    <td><input type="hidden" name="option" value="searchfname"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
