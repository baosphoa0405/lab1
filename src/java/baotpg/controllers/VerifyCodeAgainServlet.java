/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.controllers;

import baotpg.users.UserDTO;
import baotpg.users.UsersDAO;
import baotpg.utils.SendEmail;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "VerifyCodeAgainServlet", urlPatterns = {"/VerifyCodeAgainServlet"})
public class VerifyCodeAgainServlet extends HttpServlet {
    private String SUCCESS = "Verify.jsp";
    private String FAIL = "Login.jsp";
    
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
        String url = SUCCESS;
        try {
            response.setContentType("text/html;charset=UTF-8");
            SendEmail sm = new SendEmail();
            String codeRandom = sm.getRandom();
            HttpSession session = request.getSession();
            String email = request.getParameter("email").trim();
            String password = request.getParameter("password").trim();
            UsersDAO userDAO = new UsersDAO();
            UserDTO user = userDAO.checkLogin(email, password);
            if (user != null) {
                boolean isEmail = sm.sendEmail(user, codeRandom);
                if (isEmail) {
                    request.setAttribute("emailUser", user.getEmail());
                    session.setAttribute("codeRandom", codeRandom);
                } else {
                    System.out.println("failed to send verification email");
                }
            } else {
                url = FAIL; 
                request.setAttribute("error", "user or password wrong");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VerifyCodeAgainServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(VerifyCodeAgainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
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
        processRequest(request, response);
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
