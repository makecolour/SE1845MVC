<%-- 
    Document   : InsertDepartment
    Created on : Feb 23, 2024, 9:18:38 AM
    Author     : quyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <h1>INSERT NEW DEPARTMENT</h1>
        <form action="" method="post">
            <table>
                <tr>
                    <td>DeptNo</td>
                    <td><input type="text" name="DeptNo" required></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="Name" required></td>
                </tr>
                <tr>
                    <td>Location</td>
                    <td><input type="text" name="Location" required></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="Submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
