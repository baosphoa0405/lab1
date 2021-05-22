/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.controllers;

import baotpg.requests.RequestDTO;
import baotpg.requests.RequestsDAO;
import baotpg.users.UserDTO;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DeleteRequestFromUser", urlPatterns = {"/DeleteRequestFromUser"})
public class DeleteRequestFromUser extends HttpServlet {

    private String SUCCESS = "LoadListBookingServlet";
    private String ERROR = "Login.jsp";
    private String ERROR_UPDATE = "LoadListBookingServlet";

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
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("user");
            String emailUserLoginGG = (String) session.getAttribute("loginGG");
            String requestID = request.getParameter("requestID");
            RequestsDAO requestDAO = new RequestsDAO();
            if (user == null && emailUserLoginGG == null) {
                url = ERROR;
            } else {
                // kiem tra xm request ID co1 trong ban3 request ko neu61 co1 thi2 cho user setStaus con2 neu61
                // ko co1 thi2 phai3 show err
                RequestDTO requestDetail = requestDAO.getDetailRequest(Integer.parseInt(requestID));
                if (requestDetail.getStatusReqID() == MyConstants.STATUS_REQUEST_NEW) {
                    boolean updateStatus = requestDAO.updateStatusRequest(requestDetail.getRequestID(), MyConstants.STATUS_REQUEST_IN_ACTIVE);
                    if (updateStatus) {
                        request.setAttribute("successDelete", "request of user deleted successfully");
                    }
                } else {
                    url = ERROR_UPDATE;
                    request.setAttribute("errorDelete", "please load list request");
                }
            }
            System.out.println("dasdas" + url);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteRequestFromUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(DeleteRequestFromUser.class.getName()).log(Level.SEVERE, null, ex);
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
