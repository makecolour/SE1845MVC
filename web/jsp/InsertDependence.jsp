<%-- 
    Document   : InsertDependence
    Created on : Feb 23, 2024, 9:25:24 AM
    Author     : quyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Employee, model.DAOEmployee, java.util.Vector" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <h1>INSERT NEW DEPENDENCE</h1>
        <form action="" method="post">
            <table>
                <tr>
                    <td>SSN</td>
                    <td>
                        <select name="SSN" >
                            <% DAOEmployee daoEmp = new DAOEmployee(); 
                               Vector<Employee> vEmp = daoEmp.getAll("select * from Employee"); %>
                            <c:forEach items="<%=vEmp%>" var="emp">
                                <option value="${emp.getSSN()}">${emp.getFName()} ${emp.getLName()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="Name" required></td>
                </tr>
                <tr>
                    <td>DOB</td>
                    <td><input type="date" name="DOB" required></td>
                </tr>
                <tr>
                    <td>Relationship</td>
                    <td><input type="text" name="Relationship" required></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="submit"></td>
                    <td><input type="reset" value="clear"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
