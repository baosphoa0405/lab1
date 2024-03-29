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
@WebServlet(name = "ViewResourceServlet", urlPatterns = {"/ViewResourceServlet"})
public class ViewResourceServlet extends HttpServlet {

    private String SUCCESS = "DetailResource.jsp";
    private String FAIL = "Login.jsp";
    private String FAIL_STATUS = "LoadListBookingServlet";

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
        HttpSession session = request.getSession();
        String url = SUCCESS;
        UserDTO user = (UserDTO) session.getAttribute("user");
        String emailUserLoginGG = (String) session.getAttribute("loginGG");
        String productID = request.getParameter("productID");
        String requestID = request.getParameter("requestID");
        RequestsDAO requestsDAO = new RequestsDAO();
        try {
            ResourcesDAO resourceDAO = new ResourcesDAO();
            if (user == null && emailUserLoginGG == null) {
                url = FAIL;
            } else {
                RequestDTO requestDetail = requestsDAO.getDetailRequest(Integer.parseInt(requestID));
                if (requestDetail.getStatusReqID()== MyConstants.STATUS_REQUEST_DELETE) {
                    url = FAIL_STATUS;
                    request.setAttribute("errorStatusViewCourse", "Sorry Resource of you request deleted");
                } else {
                    ResourceDTO reource = resourceDAO.getDetailResource(productID);
                    request.setAttribute("resource", reource);
                }
            }
        } catch (NamingException ex) {
            Logger.getLogger(ViewResourceServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ViewResourceServlet.class.getName()).log(Level.SEVERE, null, ex);
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
