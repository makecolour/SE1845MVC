<%-- 
    Document   : BillManage
    Created on : 7 Mar 2024, 22:06:01
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
        <table border="1">
            <caption>${requestScope.titleTable}</caption>
            <tr>
                <th>Bill ID</th>
                <th>Customer Name</th>
                <th>Date</th>
                <th>Total</th>
                <th>Status</th>
                <c:if test="${sessionScope.UserLogin.isAdmin}">
                    <th>Detail</th>
                </c:if>
                <th>Cart</th>
            </tr>
            <c:forEach items="${requestScope.data}" var="bill">
                <tr>
                    <td>${bill.getBillId()}</td>
                    <td>${bill.getCustomerName()}</td>
                    <td>${bill.getDate()}</td>
                    <td>${bill.getTotal()}</td>
                    <td>${bill.getStatus()}</td>
                    <c:if test="${sessionScope.UserLogin.isAdmin}">
                        <td><a href="BillController?service=update&Bill_ID=${bill.getBillId()}">Detail</td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        
    </body>
</html>
