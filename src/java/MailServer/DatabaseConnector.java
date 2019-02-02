package MailServer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MANOSIJ
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DatabaseConnector implements ServletContextListener {
    
    //Run this before web application is started
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/keyvaluepairs?autoReconnect=true&useSSL=false", "root", "manosij1996");
            event.getServletContext().setAttribute("dbConnection",connection);
            System.out.println("The attribute stored in  - "+event.getServletContext().getAttribute("dbConnection"));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        try {
            Connection connection = (Connection) event.getServletContext().getAttribute("dbConnection");
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
