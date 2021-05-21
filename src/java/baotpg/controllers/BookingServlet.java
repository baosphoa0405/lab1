/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.controllers;

import baotpg.requests.RequestDTO;
import baotpg.requests.RequestsDAO;
import baotpg.resources.ResourceDTO;
import baotpg.resources.ResourcesDAO;
import baotpg.users.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
@WebServlet(name = "BookingServlet", urlPatterns = {"/BookingServlet"})
public class BookingServlet extends HttpServlet {

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
        try {
            response.setContentType("text/html;charset=UTF-8");
            String productID = request.getParameter("productID");
            RequestsDAO requestDAO = new RequestsDAO();
            ResourcesDAO resourceDAO = new ResourcesDAO();
            HttpSession sesion = request.getSession();
            UserDTO user = (UserDTO) sesion.getAttribute("user");
            String emailLoginGG = (String) sesion.getAttribute("loginGG");
            RequestDTO requestBooking = null;
            ResourceDTO resource = resourceDAO.getDetailResource(productID); 
            if (resource.getQuanlity() >= 1) {
                if (user != null) {
                    requestBooking = new RequestDTO(0, 1, Date.valueOf(java.time.LocalDate.now()), user.getEmail(), productID);
                }
                if (emailLoginGG != null) {
                    requestBooking = new RequestDTO(0, 1, Date.valueOf(java.time.LocalDate.now()), emailLoginGG, productID);
                }
                boolean isBooking = requestDAO.bookingResource(requestBooking);
                boolean updateQuanity = resourceDAO.updateQuanityResource(productID, resource.getQuanlity() -1 );
                if (isBooking && updateQuanity) {
                    request.setAttribute("bookingSuccess", "Booking SUCCESS");
                } else {
                    request.setAttribute("bookingFail", "Booking FAIL");
                }
            } else {
                request.setAttribute("errBooking", "Please booking another resource");
                request.setAttribute("idProductOutOfNumber", resource.getProductID());
            }

        } catch (NamingException ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.getRequestDispatcher("SearchServlet").forward(request, response);
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
