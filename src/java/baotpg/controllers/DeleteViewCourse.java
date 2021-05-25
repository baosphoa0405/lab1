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
@WebServlet(name = "DeleteViewCourse", urlPatterns = {"/DeleteViewCourse"})
public class DeleteViewCourse extends HttpServlet {

    private String SUCCESS = "LoadRequestServlet";
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
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        try {
            HttpSession session = request.getSession();
            UserDTO admin = (UserDTO) session.getAttribute("admin");
            if (admin == null) {
                url = FAIL;
            } else {
                ResourcesDAO resourcesDAO = new ResourcesDAO();
                String keySearch = request.getParameter("key"); // vua2 search name product , email
                String indexPage = request.getParameter("index");
                String statusRequest = request.getParameter("StatusRequest");
                String date = request.getParameter("date");
                String productID = request.getParameter("productID");
                String requestID = request.getParameter("requestID");
                RequestsDAO requestsDAO = new RequestsDAO();

                if (requestID != null) {
                    RequestDTO requestDetail = requestsDAO.getDetailRequest(Integer.parseInt(requestID));
                    if (requestDetail.getStatusReqID() == MyConstants.STATUS_REQUEST_ACTIVE) {
                        boolean isStatusActive = requestsDAO.updateStatusRequest(Integer.parseInt(requestID), MyConstants.STATUS_REQUEST_DELETE);
                        ResourceDTO resource = resourcesDAO.getDetailResource(productID);
                        boolean updateQuanity = resourcesDAO.updateQuanityResource(productID, resource.getQuanlity() + 1);
                        if (updateQuanity && isStatusActive) {
                            request.setAttribute("successDeleteView", "success delete view resource");
                        }
                    } else {
                        request.setAttribute("errorDeleteView", "error Delete view resource");
                    }

                }
                request.setAttribute("index", indexPage);
                request.setAttribute("StatusRequest", statusRequest);
                request.setAttribute("key", keySearch);
                request.setAttribute("date", date);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
