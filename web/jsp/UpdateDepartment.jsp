<%-- 
    Document   : UpdateDepartment
    Created on : Feb 22, 2024, 8:13:44 PM
    Author     : quyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Department, java.util.Vector" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Department</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <%Vector<Department> result = (Vector<Department>)request.getAttribute("data");
          Department depart=result.get(0);%>
        <h1>UPDATE DEPARTMENT</h1>
        <form action="DepartmentController" method="post">
            <table>
                <tr>
                    <td>DeptNo</td>
                    <td><input type="text" name="DeptNo" value="<%=depart.getDeptNo()%>" required></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="Name" value="<%=depart.getName()%>" required></td>
                </tr>
                <tr>
                    <td>Location</td>
                    <td><input type="text" name="Location" value="<%=depart.getLocation()%>" required></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="Submit"></td>
                    <td><input type="reset" value="Clear"></td>
                <input type="hidden" name="service" value="update">
                </tr>
            </table>
        </form>
    </body>
</html>
