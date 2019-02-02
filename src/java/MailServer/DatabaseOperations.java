/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MailServer;

import Elements.Mail;
import Elements.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MANOSIJ
 */
public class DatabaseOperations {

    public ResultSet executeQuery(HttpServletRequest request, String query) {
        try {
            Connection connection = (Connection) request.getServletContext().getAttribute("dbConnection");
            PreparedStatement stmt;
            System.out.println(request.getServletContext().getAttributeNames());
            if (connection == null) {
                System.out.println("Connection attribute not retrieved");
            }
            stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;    // on error
    }

    public boolean executeUpdate(HttpServletRequest request, String query) {
        try {
            Connection connection = (Connection) request.getServletContext().getAttribute("dbConnection");
            PreparedStatement stmt;
            System.out.println(request.getServletContext().getAttributeNames());
            if (connection == null) {
                System.out.println("Connection attribute not retrieved");
            }
            stmt = connection.prepareStatement(query);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;    // on error
    }

    public ArrayList<Mail> getAllMail(HttpServletRequest request) {
        try {
            // select `name`,(((100-`discount`)*`cost`)/100) from `onlineapparel`.`products` order by `discount`;
            String query;
            ArrayList<Mail> list = new ArrayList();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            query = "select * from `mailservice`.`" + user.getUsername() + "_mail`";
            ResultSet rs = executeQuery(request, query);
            while (rs.next()) {
                Mail mail = new Mail(rs.getInt(1));
                mail.setSender(rs.getString(2));
                mail.setMessage(rs.getString(4));
                mail.setReceiver(user.getUsername());
                if (rs.getBoolean(3)) {
                    mail.markRead();
                }
                list.add(mail);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean getUserInformation(HttpServletRequest request, String id) {
        try {
            HttpSession session = request.getSession();
            String query = "select `name` from `mailservice`.`users` where ( `username` = '" + id + "')";
            ResultSet rs = executeQuery(request, query);
            if (rs.next()) {
                User user = new User(rs.getString(1), id);
                session.setAttribute("user", user);
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean deleteMail(HttpServletRequest request, int index) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String query;
        query = "DELETE FROM `mailservice`.`"+user.getUsername()+"_mail` WHERE (`idmail` = '"+index+"')";
        return executeUpdate(request, query);
    }
    
    public boolean sendMail(HttpServletRequest request, String receiver, String data) {
        try {
            String query;
            int id;
            ResultSet rs;
            query = "select max(`idmail`) from `mailservice`.`"+receiver+"_mail`";
            rs = executeQuery(request, query);
            if(rs.next()){
                id = rs.getInt(1)+1;
            } else {
                id = 1;
            }
            User user = (User)request.getSession().getAttribute("user");
            query = "INSERT INTO `mailservice`.`"+receiver+"_mail` (`idmail`, `sender`, `isRead`, `data`) VALUES ('"+id+"', '"+user.getUsername()+"', '0', '"+data+"')";
            return executeUpdate(request,query);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean markMailRead(HttpServletRequest request, Mail mail) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String query, update;
        query = "UPDATE `mailservice`.`"+user.getUsername()+"_mail` SET `isRead` = '1' WHERE (`idmail` = '"+mail.getId()+"')";
        update = "UPDATE `mailservice`.`user_notification` SET `notification` = 'mail to "+mail.getReceiver()+" read' WHERE (`userId` = '"+mail.getSender()+"')";
        if(executeUpdate(request, query)){
            return executeUpdate(request, update);
        }
        return false;
    }
    
    public String getNotifcation(HttpServletRequest request) {
        try {
            ResultSet rs;
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            String query;
            query = "select `notification` from `mailservice`.`user_notification` WHERE (`userId` = '"+user.getUsername()+"')";
            rs = executeQuery(request, query);
            String notification = "";
            if(rs.next()) {
                notification = rs.getString(1);
            }
            return notification;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "error in retrieval";
    }
}
