<%-- 
    Document   : InsertEmp_Manage_Dept
    Created on : Feb 23, 2024, 9:50:42 AM
    Author     : quyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Employee, model.DAOEmployee, java.util.Vector, entity.Department, model.DAODepartment" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <h1>INSERT NEW EMP_MANAGE_DEPT</h1>
        <form action="" method="post">
            <table>
                <tr>
                    <td>SSN</td>
                    <td>
                        <select name="SSN">
                            <% DAOEmployee daoEmp = new DAOEmployee(); 
                               Vector<Employee> vEmp = daoEmp.getAll("select * from Employee");  %>
                            <c:forEach items="<%=vEmp%>" var="emp">
                                <option value="${emp.getSSN()}">${emp.getFName()} ${emp.getLName()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>DeptNo</td>
                    <td>
                        <select name="DeptNo">
                            <% DAODepartment daoDepart = new DAODepartment(); 
                               Vector<Department> vDepart = daoDepart.getAll("select * from Department"); %>
                            <c:forEach items="<%=vDepart%>" var="dep">
                                <option value="${dep.getDeptNo()}">${dep.getName()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>StartDate</td>
                    <td><input type="date" name="StartDate" required></td>
                </tr>
                <tr>
                    <td>EndDate</td>
                    <td><input type="date" name="EndDate" required></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="submit"></td>
                    <td><input type="reset" value="clear"></td>
                </tr>
            </table>
        </form>  
    </body>
</html>
