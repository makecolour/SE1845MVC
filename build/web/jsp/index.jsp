<%-- 
    Document   : index
    Created on : Mar 1, 2024, 8:46:44 PM
    Author     : Duc Minh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <div>
        <p><a href="EmployeeController?service=listAll">list all employee</a>
        <p><a href="ProjectController?service=listAll">list all project</a>
        <p><a href="DepartmentController?service=listAll">list all department</a>
        <p><a href="DependenceController?service=listAll">list all dependence</a>
        <p><a href="Emp_WorkOn_ProController?service=listAll">list all emp_workon_pro</a>
        <p><a href="Emp_Manage_DeptController?service=listAll">list all emp_manage_dept</a>
        <c:if test="${sessionScope.UserLogin.isAdmin}">
            <a href="BillController?service=listAll">list all bill</a>
        </c:if>
        </div>
    </body>
</html>
