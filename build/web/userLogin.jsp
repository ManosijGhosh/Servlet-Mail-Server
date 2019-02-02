<%-- 
    Document   : userLogin
    Created on : 9 Nov, 2018, 8:43:06 PM
    Author     : MANOSIJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manosij's Email Service</title>
    </head>
    <body>
        <h1>User Login</h1>
        <form action="UserLogin" method="post">
            User name: <input type ="text" name = "userid" id="userid"><br>
            Password: <input type ="password" name ="password" id="password"><br>
            <button type="submit">Login</button>
        </form>
        <br>
        <form action="userDetailsEntry.jsp">
            <button type="submit">User Registration</button>
        </form>
        <%
            if (session.getAttribute("loginError") != null) {
                session.setAttribute("loginError", null);
                out.println("User does not exist");
            }
        %>
    </body>
</html>