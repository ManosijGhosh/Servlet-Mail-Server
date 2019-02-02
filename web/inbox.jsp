<%-- 
    Document   : productListDisplay
    Created on : 15 Sep, 2018, 3:59:05 PM
    Author     : MANOSIJ
--%>

<%@page import="Elements.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MailServer.DatabaseOperations"%>
<%@page import="Elements.Mail"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inbox</title>
    </head>
    <body>
        <%@ page import="java.sql.ResultSet" %>
        <h1>Welcome 
            <%
                DatabaseOperations obj = new DatabaseOperations();
                ArrayList<Mail> list = obj.getAllMail(request);
                String notification, name;
                name = ((User) session.getAttribute("user")).getName();
                notification = obj.getNotifcation(request);
                out.println(name);
            %>
        </h1>
        <br>
        <form action ="sendMail.jsp">
            <button type ="submit">Compose</button>
        </form>
        <br>
        Notification : <%= notification%>
        <br>
        <form action="MailOperations" method="get">
            <%
                for (Mail mail : list) {
                    //Mail mail = list.get(i);
                    int len = mail.getMessage().length();
                    if (len > 6) {
                        len = 6;
                    }
            %>
            <br>
            <input type="checkbox" name="mark" value="<%= mail.getId()%>"/>
            <%= mail.getId() + ". " + mail.getSender() + " " + mail.getMessage().substring(0, len) + "..."%>
            <%
                if (!mail.isRead()) {
                    out.println("  (UnRead)  ");
                }
            %>
            <button type="submit" name="mailIndex" value = "<%= mail.getId()%>" formaction="displayMail.jsp">View</button>
            <%
                }
            %>
            <br>
            <button type="submit">Delete</button>
        </form>
    </body>
</html>
