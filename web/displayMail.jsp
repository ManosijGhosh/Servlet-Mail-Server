<%-- 
    Document   : checkOut
    Created on : 16 Sep, 2018, 6:34:22 PM
    Author     : MANOSIJ
--%>

<%@page import="Elements.Mail"%>
<%@page import="MailServer.DatabaseOperations"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mail received</title>
    </head>
    <body>
        <h1>List</h1>
        <%
            DatabaseOperations obj = new DatabaseOperations();
            ArrayList<Mail> list = obj.getAllMail(request);
            int index = Integer.parseInt(request.getParameter("mailIndex"));
            for (Mail mail : list) {
                if (mail.getId() == index) {
                    out.println("From: " + mail.getSender());
                    out.println("To: " + mail.getReceiver());
                    out.println("<br>");
                    out.println("Message:");
                    out.println(mail.getMessage());
                    obj.markMailRead(request, mail);
                }
            }
        %>
        <br>
        <form action ="inbox.jsp">
            <button type="submit">Back</button>
        </form>
    </body>
</html>
