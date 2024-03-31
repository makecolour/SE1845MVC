<%-- 
    Document   : UpdateEmp_WorkOn_Pro
    Created on : Mar 2, 2024, 9:19:54 AM
    Author     : quyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, model.DAOEmployee, entity.Employee, entity.Emp_WorkOn_Pro, model.DAOProject, entity.Project" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <%
            Vector<Emp_WorkOn_Pro> result = (Vector<Emp_WorkOn_Pro>)request.getAttribute("data");
            Emp_WorkOn_Pro ewp = result.get(0);
            DAOEmployee daoEmp = new DAOEmployee();
            Vector<Employee> vEmp = daoEmp.getAll("select * from Employee"); 
            DAOProject daoPro = new DAOProject();
            Vector<Project> vPro = daoPro.getAll("select * from Project"); 
        %>
        <h1>UPDATE EMP_WORKON_PRO</h1>
        <form action="" method="post">
            <table>
                <tr>
                    <td>SSN</td>
                    <td>
                        <select name="SSN" >
                            <%for(Employee emp : vEmp) {%>
                            <option value="<%=emp.getSSN()%>" <%=(emp.getSSN().equals(ewp.getSSN())? "selected":"")%>><%=emp.getFName()%> <%=emp.getLName()%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>ProNo</td>
                    <td>
                        <select name="ProNo">
                            <%for(Project pro : vPro) {%>
                            <option value="<%=pro.getProNo()%>" <%=(ewp.getProNo().equals(pro.getProNo())? "selected":"")%>><%=pro.getName()%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Position</td>
                    <td><input type="text" name="Position" value="<%=ewp.getPosition()%>" required></td>
                </tr>
                <tr>
                    <td>HourPerDay</td>
                    <td><input type="number" name="HourPerDay" value ="<%=ewp.getHourPerDay()%>" required></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="submit"></td>
                    <td><input type="reset" value="clear"></td>
                </tr>
            </table>
            <input type="hidden" name="service" value="update">
        </form>
    </body>
</html>
