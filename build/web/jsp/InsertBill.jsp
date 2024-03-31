<%-- 
    Document   : InsertBill
    Created on : 7 Mar 2024, 20:52:14
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
       <% Vector<Employee> result = (Vector<Employee>)request.getAttribute("data");
          Employee emp=result.get(0);
          DAODepartment daoDepart = new DAODepartment();
          Vector<Department> depVec = daoDepart.getAll("select * from department");
          DAOEmployee daoEmp = new DAOEmployee();
          Vector<Employee> empVec = daoEmp.getAll("select * from employee");
                                   
        %>
    </body>
</html>
