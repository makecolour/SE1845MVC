<%-- 
    Document   : UpdateEmployee
    Created on : Feb 21, 2024, 1:13:38 PM
    Author     : quyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Employee, java.util.Vector, entity.Department,model.DAODepartment,model.DAOEmployee"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Employee</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <% Vector<Employee> result = (Vector<Employee>)request.getAttribute("data");
          Employee emp=result.get(0);
          DAODepartment daoDepart = new DAODepartment();
          Vector<Department> depVec = daoDepart.getAll("select * from department");
          DAOEmployee daoEmp = new DAOEmployee();
          Vector<Employee> empVec = daoEmp.getAll("select * from employee");
                                   
        %>
        <h1>UPDATE EMPLOYEE</h1>
        <form action="EmployeeController" method="post">
            <table>
                <tr>
                    <td>SSN</td>
                    <td><input type="text" name="SSN" value="<%=emp.getSSN()%>" required></td>
                </tr>
                <tr>
                    <td>FName</td>
                    <td><input type="text" name="FName" value="<%=emp.getFName()%>" required></td>
                </tr>
                <tr>
                    <td>LName</td>
                    <td><input type="text" name="LName" value="<%=emp.getLName()%>" required></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><input type="text" name="Address" value="<%=emp.getAddress()%>" required></td>
                </tr>
                <tr>
                    <td>Salary</td>
                    <td><input type="text" name="Salary" value="<%=emp.getSalary()%>" required></td>
                </tr>
                <tr>
                    <td>Sex</td>
                    <td><input type="radio" name="Sex"  value="1" <%=(emp.isSex()==true? "checked":"")%>>Male
                        <input type="radio" name="Sex" value="0" <%=(emp.isSex()==false? "checked":"")%>>Female</td>
                </tr>
                <tr>
                    <td>DeptNo</td>
                    <td>
                        <select name="DeptNo" >
                            <% for(Department dep : depVec) {%>
                            <option value="<%=dep.getDeptNo()%>" <%=(emp.getDeptNo().equals(dep.getDeptNo())? "selected":"")%>><%=dep.getName()%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>SSNSupervisor</td>
                    <td>
                        <select name="SSNSupervisor">
                            <% for(Employee allEmp : empVec) {
                                if(!emp.getSSN().equals(allEmp.getSSN())) {
                                    if(emp.getSSNSupervisor() == null) {%>
                            <option value="<%=allEmp.getSSN()%>"><%=allEmp.getFName()%> <%=allEmp.getLName()%></option>
                            <%} else {%>
                            <option value="<%=allEmp.getSSN()%>" <%=(emp.getSSNSupervisor().equals(allEmp.getSSN()) ? "selected":"")%>><%=allEmp.getFName()%> <%=allEmp.getLName()%></option>
                            <%}%>
                            <%}%>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="submit"></td>
                    <td><input type="reset" value="clear"></td>
                </tr>
                <td>
                    <input type="hidden" name="service" value="update">
                </td>
            </table>
        </form>
    </body>
</html>
