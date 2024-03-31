<%-- 
    Document   : UpdateEmp_Manage_Dept
    Created on : Mar 2, 2024, 9:52:16 AM
    Author     : quyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Employee, model.DAOEmployee, entity.Emp_Manage_Dept, entity.Department, model.DAODepartment" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <%
            Vector<Emp_Manage_Dept> result = (Vector<Emp_Manage_Dept>)request.getAttribute("data");
            Emp_Manage_Dept emd = result.get(0);
            DAOEmployee daoEmp = new DAOEmployee();
            Vector<Employee> vEmp = daoEmp.getAll("select * from Employee"); 
            DAODepartment daoDepart = new DAODepartment();
            Vector<Department> depVec = daoDepart.getAll("select * from department");
        %>
        <h1>UPDATE EMP_MANAGE_DEPT</h1>
        <form action="" method="post">
            <table>
                <tr>
                    <td>SSN</td>
                    <td>
                        <select name="SSN">
                            <%for(Employee emp : vEmp) {%>
                            <option value="<%=emp.getSSN()%>" <%=(emp.getSSN().equals(emd.getSSN())? "selected":"")%>><%=emp.getFName()%> <%=emp.getLName()%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>DeptNo</td>
                    <td>
                        <select name="DeptNo">
                            <% for(Department dep : depVec) {%>
                            <option value="<%=dep.getDeptNo()%>" <%=(emd.getDeptNo().equals(dep.getDeptNo())? "selected":"")%>><%=dep.getName()%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>StartDate</td>
                    <td><input type="date" name="StartDate" value="<%=emd.getStartDate()%>" required></td>
                </tr>
                <tr>
                    <td>EndDate</td>
                    <td><input type="date" name="EndDate" value="<%=emd.getEndDate()%>" required></td>
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
