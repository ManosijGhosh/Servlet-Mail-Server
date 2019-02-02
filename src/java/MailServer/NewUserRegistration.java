/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MailServer;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MANOSIJ
 */
public class NewUserRegistration extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewUserRegistration</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewUserRegistration at " + request.getAttribute("message") + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // INSERT INTO `mailservice`.`users` (`id`, `username`, `password`, `name`) VALUES ('1', 'mano', 'mano', 'manosij');
        String username,query;
        username = request.getParameter("userid");
        query = "insert into `mailservice`.`users` (`username`, `password`, `name`) values(";
        query = query + "'"+username+"', '"+request.getParameter("password")+"', '"+request.getParameter("name")+"')";
        System.out.println(query);
        DatabaseOperations obj = new DatabaseOperations();
        if(!obj.executeUpdate(request, query)){
            request.setAttribute("message","user registration unsuccessfull");
            processRequest(request, response);
        }
        query = "CREATE TABLE `mailservice`.`"+username+"_mail` (`idmail` INT NOT NULL, `sender` VARCHAR(45) NOT NULL, `isRead` TINYINT NOT NULL DEFAULT 0, `data` VARCHAR(200) NULL, PRIMARY KEY (`idmail`))";
        if(!obj.executeUpdate(request, query)){
            request.setAttribute("message","user mail table creation unsuccessfull");
            processRequest(request, response);
        }
        query = "INSERT INTO `mailservice`.`user_notification` (`userId`) VALUES ('"+username+"')";
        if(!obj.executeUpdate(request, query)){
            request.setAttribute("message","user insertion in notification unsuccessfull");
            processRequest(request, response);
        }
        request.setAttribute("message","user successfully registered");
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "registers new user";
    }// </editor-fold>

}
