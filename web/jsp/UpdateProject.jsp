<%-- 
    Document   : UpdateProject
    Created on : Feb 22, 2024, 9:15:42 PM
    Author     : quyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Project, java.util.Vector,entity.Department,model.DAODepartment" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Project</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <h1>UPDATE PROJECT</h1>
        <%Vector<Project> result = (Vector<Project>)request.getAttribute("data");
          Project pro=result.get(0);
          DAODepartment daoDepart = new DAODepartment();
          Vector<Department> depVec = daoDepart.getAll("select * from department");
        %>
        <form action="ProjectController" method="post">
            <table>
                <tr>
                    <td>ProNo</td>
                    <td><input type="text" name="ProNo" value="<%=pro.getProNo()%>" required></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="Name" value="<%=pro.getName()%>" required></td>
                </tr>
                <tr>
                    <td>StartDate</td>
                    <td><input type="date" name="StartDate" value="<%=pro.getStartDate()%>" required></td>
                </tr>
                <tr>
                    <td>EndDate</td>
                    <td><input type="date" name="EndDate" value="<%=pro.getEndDate()%>" required></td>
                </tr>
                <tr>
                    <td>DeptNo</td>
                    <td>
                        <select name="DeptNo">
                            <% for(Department dep : depVec) {%>
                            <option value="<%=dep.getDeptNo()%>" <%=(pro.getDeptNo().equals(dep.getDeptNo())? "selected":"")%>><%=dep.getName()%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="submit"></td>
                    <td><input type="reset" value="clear"></td>
                <input type="hidden" name="service" value="update">
                </tr>
            </table>
        </form>
    </body>
</html>
