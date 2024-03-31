<%-- 
    Document   : InsertEmployee
    Created on : Feb 23, 2024, 9:00:26 AM
    Author     : quyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Employee, model.DAOEmployee, entity.Department, model.DAODepartment, java.util.Vector" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <h1>INSERT NEW EMPLOYEE</h1>
        <form action="" method="post">
            <table>
                <tr>
                    <td>SSN</td>
                    <td><input type="text" name="SSN" required></td>
                </tr>
                <tr>
                    <td>FName</td>
                    <td><input type="text" name="FName" required></td>
                </tr>
                <tr>
                    <td>LName</td>
                    <td><input type="text" name="LName" required></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><input type="text" name="Address" required></td>
                </tr>
                <tr>
                    <td>Salary</td>
                    <td><input type="number" name="Salary" required></td>
                </tr>
                <tr>
                    <td>Sex</td>
                    <td><input type="radio" name="Sex" checked value="1">Male
                        <input type="radio" name="Sex" value="0">Female</td>
                </tr>
                <tr>
                    <td>DeptNo</td>
                    <td>
                        <select name="DeptNo" >
                            <% DAODepartment daoDepart = new DAODepartment(); 
                               Vector<Department> vDepart = daoDepart.getAll("select * from Department"); %>
                            <c:forEach items="<%=vDepart%>" var="dep">
                                <option value="${dep.getDeptNo()}">${dep.getName()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>SSNSupervisor</td>
                    <td>
                        <select name="SSNSupervisor" >
                            <% DAOEmployee daoEmp = new DAOEmployee(); 
                               Vector<Employee> vEmp = daoEmp.getAll("select * from Employee");  %>
                            <c:forEach items="<%=vEmp%>" var="emp">
                                <option value="${emp.getSSN()}">${emp.getFName()} ${emp.getLName()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="submit"></td>
                    <td><input type="reset" value="clear"></td>
                </tr>
            </table>
        </form>                  
    </body>
</html>
