<%-- 
    Document   : sintag
    Created on : Jan 31, 2024, 1:12:24 PM
    Author     : Duc Minh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% int max = 300;
            out.print("<h1>max = "+max+"</h1>");
        %>
        <h1>Hello World!</h1>
        <% 
        for(int i = 0; i<=max; i+=10) {%>
        <hr width="<%=i%>" />
        <%}%>

        <%! int MIN=1;//global %>   
        <%! int getValue() {return MIN;} %>
        <p>Value =<%=getValue()%></p>
    </body>
</html>
