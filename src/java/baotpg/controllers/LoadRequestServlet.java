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
import baotpg.statusRequest.StatusRequestDTO;
import baotpg.statusRequest.StatusRequestsDAO;
import baotpg.users.UserDTO;
import baotpg.utils.MyConstants;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "LoadRequestServlet", urlPatterns = {"/LoadRequestServlet"})
public class LoadRequestServlet extends HttpServlet {
    private String SUCCESS = "ManagementRequest.jsp";
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
                String keySearch = request.getParameter("key"); // vua2 search name product , email
                String indexPage = request.getParameter("index");
                String statusRequest = request.getParameter("StatusRequest");
                String date = request.getParameter("date");
                Date dateSearch = null;
                if (date != null && !date.isEmpty()) {
                    dateSearch = Date.valueOf(date);
                }

                if (keySearch == null) {
                    keySearch = "";
                }
                if (statusRequest == null) {
                    statusRequest = "";
                }

                RequestsDAO requestDaos = new RequestsDAO();
                StatusRequestsDAO statusRequestDAO = new StatusRequestsDAO();
                ResourcesDAO reourceDAO = new ResourcesDAO();

                int countsListRequest = requestDaos.getCountRequests(keySearch, statusRequest, dateSearch);
                int countPageSize = countsListRequest / MyConstants.QUANITY_ITEM_IN_PAGE;
                if (countsListRequest % MyConstants.QUANITY_ITEM_IN_PAGE != 0) {
                    countPageSize += 1;
                }

                ArrayList<ResourceDTO> listResources = reourceDAO.getAllListReources();
                ArrayList<StatusRequestDTO> listStatusRequest = statusRequestDAO.getAllListStatusRequest();
                ArrayList<RequestDTO> arrayListRequest = new ArrayList<>();
                if (indexPage == null) {
                    arrayListRequest = requestDaos.getAllRequest(1, keySearch, statusRequest, dateSearch);
                    request.setAttribute("index", 1);
                } else {
                    arrayListRequest = requestDaos.getAllRequest(Integer.parseInt(indexPage), keySearch, statusRequest, dateSearch);
                    request.setAttribute("index", indexPage);
                }

                for (int i = 0; i < listStatusRequest.size(); i++) {
                    if (listStatusRequest.get(i).getStatusReqName().equalsIgnoreCase("inactive")) {
                        listStatusRequest.remove(i);
                    }
                }
                for (int i = 0; i < arrayListRequest.size(); i++) {
                    if (arrayListRequest.get(i).getStatusReqID() == 2) {
                        arrayListRequest.remove(i);
                    }
                }
                request.setAttribute("arrayListRequest", arrayListRequest);
                request.setAttribute("countPageSize", countPageSize);
                request.setAttribute("StatusRequest", statusRequest);
                request.setAttribute("listStatusRequest", listStatusRequest);
                request.setAttribute("listResource", listResources);
                request.setAttribute("key", keySearch);
                request.setAttribute("date", date);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoadRequestServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(LoadRequestServlet.class.getName()).log(Level.SEVERE, null, ex);
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
