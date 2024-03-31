<%-- 
    Document   : InsertProject
    Created on : Feb 23, 2024, 9:11:35 AM
    Author     : quyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Department, model.DAODepartment, java.util.Vector" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <h1>INSERT NEW PROJECT</h1>
        <form action="" method="post">
            <table>
                <tr>
                    <td>ProNo</td>
                    <td><input type="text" name="ProNo" required></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="Name" required></td>
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
                    <td><input type="submit" name="submit" value="submit"></td>
                    <td><input type="reset" value="clear"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
