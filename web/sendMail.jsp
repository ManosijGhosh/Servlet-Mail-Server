<%-- 
    Document   : sendMail
    Created on : 10 Nov, 2018, 4:15:45 PM
    Author     : MANOSIJ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Compose mail</title>
    </head>
    <body>
        <h1>Write your mail here</h1>
        <form action ="MailOperations" method="post">
            Receiver: <input type ="text" name = "receiver" id="receiver"><br>
            Mail: <textarea rows="5" cols="80" name = "mail" id="mail"></textarea>
            <br>
            <button type="submit">Send</button>
        </form>
        <br>
        <%
            if(session.getAttribute("mailState")!=null){
                session.setAttribute("mailState",null);
                out.println("Error in sending mail");
            }
        %>
    </body>
</html>
