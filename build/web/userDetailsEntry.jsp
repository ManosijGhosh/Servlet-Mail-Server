<%-- 
    Document   : userDetailsEntry
    Created on : 16 Sep, 2018, 7:50:29 PM
    Author     : MANOSIJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New user registration</title>
    </head>
    <body>
        <h1>Welcome</h1>
        <form action="NewUserRegistration" method="post">
            User name: <input type ="text" name = "userid" id="userid"><br>
            Password: <input type ="password" name ="password" id="password"><br>
            Name: <input type ="text" name ="name" id="name"><br>
            <button type="submit">Register</button>
        </form>
    </body>
</html>
