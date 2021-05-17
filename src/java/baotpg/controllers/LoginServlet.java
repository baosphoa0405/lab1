/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.controllers;


import baotpg.utils.VerifyUtils;
import baotpg.users.UserDTO;
import baotpg.users.UsersDAO;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private String SUCCESS = "LoadProductServlet";
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
        String url = FAIL;
        try {
            response.setContentType("text/html;charset=UTF-8");
            String email = request.getParameter("email").trim();
            String password = request.getParameter("password").trim();
            UsersDAO userDAO = new UsersDAO();
            UserDTO user = userDAO.checkLogin(email, password);
            HttpSession session = request.getSession();
            boolean valid = false;
            boolean checkCapCha = false;
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            System.out.println("gRecaptchaResponse=" + gRecaptchaResponse);
            request.setAttribute("valueEmail", email);
            request.setAttribute("valuePW", password);
            if (email.isEmpty()) {
                valid = true;
                request.setAttribute("email", "email is empty");
            }
            if (password.isEmpty()) {
                valid = true;
                request.setAttribute("password", "password is empty");
            }
            // Verify CAPTCHA.
//            checkCapCha = VerifyUtils.verify(gRecaptchaResponse);
//            if (!checkCapCha) {
//                valid = true;
//                request.setAttribute("errorCapCha", "Please choosen capCha");
//            }
            if (!valid) {
                if (user != null) {
                    session.setAttribute("user", user);
                    url = SUCCESS;
                } else {
                    request.setAttribute("error", "user or password wrong");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
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
