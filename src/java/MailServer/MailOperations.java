/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MailServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MANOSIJ
 */
public class MailOperations extends HttpServlet {

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
            out.println("<title>Servlet MailDeletions</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MailDeletions at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method for mail deletions.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DatabaseOperations db = new DatabaseOperations();
        String list[] = request.getParameterValues("mark");
        if (list != null) {
            System.out.println(Arrays.toString(list));
            for (String list1 : list) {
                int index = Integer.parseInt(list1);
                db.deleteMail(request, index);
            }
        }
        RequestDispatcher view = getServletContext().getRequestDispatcher("/inbox.jsp");
        view.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method for mail sending.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher view;
        DatabaseOperations db = new DatabaseOperations();
        String receiver, data;
        receiver = request.getParameter("receiver");
        data = request.getParameter("mail");
        if (receiver != null) {
            if(db.sendMail(request, receiver, data)){
                view = getServletContext().getRequestDispatcher("/inbox.jsp");
            } else {
                request.getSession().setAttribute("mailState", "error");
                view = getServletContext().getRequestDispatcher("/sendMail.jsp");
            }
        } else {
            view = getServletContext().getRequestDispatcher("/sendMail.jsp");
        }
        view.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
