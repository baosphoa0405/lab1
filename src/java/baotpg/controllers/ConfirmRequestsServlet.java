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
import baotpg.utils.MyConstants;
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

/**
 *
 * @author Admin
 */
@WebServlet(name = "ConfirmRequestsServlet", urlPatterns = {"/ConfirmRequestsServlet"})
public class ConfirmRequestsServlet extends HttpServlet {

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
            String requestID = request.getParameter("requestID");
            String isConfirm = request.getParameter("flag");
            String productID = request.getParameter("productID");
            RequestsDAO requestDAO = new RequestsDAO();
            ResourcesDAO resourceDAO = new ResourcesDAO();
            ResourceDTO resource = resourceDAO.getDetailResource(productID);
            if (Boolean.parseBoolean(isConfirm)) {
                // kiểm tra số lượng
                if (resource.getQuanlity() > 0) {
                    boolean isStatusActive = requestDAO.updateStatusRequest(Integer.parseInt(requestID), MyConstants.STATUS_REQUEST_ACTIVE);
                    boolean updateQuanity = resourceDAO.updateQuanityResource(productID, resource.getQuanlity() - 1);
                    if (updateQuanity && isStatusActive) {
                        request.setAttribute("successConfirm", "Confirm successfully rquestID " + requestID);
                    }
                }else{
                    request.setAttribute("errorConfirm", "Sorry quanity resource " + requestID + " = " + resource.getQuanlity());
                }
                // giam so luong resource, change status thanh Active
            } else {
                System.out.println("deny");
                // change status thanh Delete
                boolean isStatusDelete = requestDAO.updateStatusRequest(Integer.parseInt(requestID), MyConstants.STATUS_REQUEST_DELETE);
                 boolean updateQuanity = resourceDAO.updateQuanityResource(productID, resource.getQuanlity() + 1);
                if (isStatusDelete && updateQuanity) {
                    request.setAttribute("deleteConfirm", "Deny successfully requestID " + requestID);
                }
            }
            request.setAttribute("requestID", requestID);
        } catch (NamingException ex) {
            Logger.getLogger(ConfirmRequestsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmRequestsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.getRequestDispatcher("LoadRequestServlet").forward(request, response);
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
