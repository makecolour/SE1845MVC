<%-- 
    Document   : UpdateDependence
    Created on : Feb 22, 2024, 8:54:02 PM
    Author     : quyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.DAOEmployee, java.util.Vector, entity.Employee, entity.Dependence" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <%
            Vector<Dependence> result = (Vector<Dependence>)request.getAttribute("data");
            Dependence dep=result.get(0);
            DAOEmployee daoEmp = new DAOEmployee();
            Vector<Employee> vEmp = daoEmp.getAll("select * from Employee"); 
        %>
        <h1>UPDATE DEPENDENCE</h1>
        <form action="" method="post">
            <table>
                <tr>
                    <td>SSN</td>
                    <td>
                        <select name="SSN" >
                            <%for(Employee emp : vEmp) {%>
                            <option value="<%=emp.getSSN()%>" <%=(emp.getSSN().equals(dep.getSSN())? "selected":"")%>><%=emp.getFName()%> <%=emp.getLName()%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="Name" value="<%=dep.getName()%>" required></td>
                </tr>
                <tr>
                    <td>DOB</td>
                    <td><input type="date" name="DOB" value="<%=dep.getDOB()%>" required></td>
                </tr>
                <tr>
                    <td>Relationship</td>
                    <td><input type="text" name="Relationship" value="<%=dep.getRelationship()%>" required></td>
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
