<%-- 
    Document   : InsertEmp_WorkOn_Pro
    Created on : Feb 23, 2024, 9:43:22 AM
    Author     : quyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Employee, model.DAOEmployee, java.util.Vector, entity.Project, model.DAOProject" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <h1>INSERT NEW EMP_WORKON_PRO</h1>
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
                    <td>ProNo</td>
                    <td>
                        <select name="ProNo">
                            <% DAOProject daoPro = new DAOProject(); 
                               Vector<Project> vPro = daoPro.getAll("select * from Project");  %>
                            <c:forEach items="<%=vPro%>" var="pro">
                                <option value="${pro.getProNo()}">${pro.getName()}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Position</td>
                    <td><input type="text" name="Position" required></td>
                </tr>
                <tr>
                    <td>HourPerDay</td>
                    <td><input type="number" name="HourPerDay" required></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="submit"></td>
                    <td><input type="reset" value="clear"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
