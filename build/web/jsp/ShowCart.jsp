<%-- 
    Document   : ShowCart
    Created on : Feb 23, 2024, 4:13:18 PM
    Author     : quyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.EmployeeCart, java.util.Enumeration, entity.Project, java.util.Vector" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <%
          Vector<Project> proVec = (Vector<Project>) request.getAttribute("data");
        %>
        <form action="CartController" method="post">
            <table border="1">
                <caption>SHOW EMPLOYEE CART</caption>
                <tr>
                    <th>SSN</th>
                    <th>FName</th>
                    <th>LName</th>
                    <th>quantity</th>
                    <th>Salary</th>
                    <th>Position</th>
                    <th>SubTotal</th>
                    <th>remove</th>
                </tr>
                <% Enumeration em = session.getAttributeNames();
                   double total = 0;
                   EmployeeCart emp = new EmployeeCart();
                   while(em.hasMoreElements()) {
                         String key = em.nextElement().toString();
                         if(!key.equals("UserLogin")) {
                             emp = (EmployeeCart) session.getAttribute(key);
                             double subTotal = emp.getSalary() * emp.getQuantity();
                             total += subTotal;
                %>
                <tr>
                    <td><%=emp.getSSN()%></td>
                    <td><%=emp.getFName()%></td>
                    <td><%=emp.getLName()%></td>
                    <td><input type="number" name="<%=emp.getSSN()%>quantity" value="<%=emp.getQuantity()%>" required></td>
                    <td><%=emp.getSalary()%></td>
                    <td><input type="text" name="<%=emp.getSSN()%>position" value="<%=emp.getPosition()%>" required></td>
                    <td><%=subTotal%></td>
                    <td><a href="CartController?service=remove&ssn=<%=emp.getSSN()%>" />remove</td>
                </tr>
                <%}%>
                <%}%>
            </table>
            Choose project:
            <select name="ProNo">
                <%for(Project pro : proVec) {
                 if(emp.getProNo() != null) {%>
                <option value="<%=pro.getProNo()%>" <%=(emp.getProNo().equals(pro.getProNo())? "selected":"")%>><%=pro.getName()%></option>
                <%} else {%>
                <option value="<%=pro.getProNo()%>"><%=pro.getName()%></option>
                <%}
                }%>
            </select>
            <input type="hidden" name="service" value="update">
            <input type="submit" name="submit" value="Save">
        </form>
        <p><a href="CartController?service=removeall">Remove All</a></p>
        <p>Total = <%=total%></p>
        <p><a href="EmployeeController">Display Employee</a> ||
            <a href="CartController?service=checkout">Check Out</a>||
            <a href="Emp_WorkOn_ProController?service=listAll&option=bill">Bill</a></p> 
        
    </body>
</html>
