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
import java.io.IOException;
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

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoadRequestServlet", urlPatterns = {"/LoadRequestServlet"})
public class LoadRequestServlet extends HttpServlet {

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
            String keySearch = request.getParameter("key");
            String indexPage = request.getParameter("index");
            RequestsDAO requestDaos = new RequestsDAO();
            int countsListRequest = requestDaos.getCountRequests(keySearch);
            int countItemPageSize = 20;
            int countPageSize = countsListRequest / countItemPageSize;
            if (countsListRequest % countItemPageSize != 0) {
                countPageSize += 1;
            }
            StatusRequestsDAO statusRequestDAO = new StatusRequestsDAO();
            ResourcesDAO reourceDAO = new ResourcesDAO();
            ArrayList<ResourceDTO> listResources = reourceDAO.getAllListReources();
            ArrayList<StatusRequestDTO> listStatusRequest = statusRequestDAO.getAllListStatusRequest();
            ArrayList<RequestDTO> arrayListRequest = new ArrayList<>();
            if (indexPage == null) {
                arrayListRequest = requestDaos.getAllRequest(1, keySearch);
                request.setAttribute("index", 1);
            } else {
                arrayListRequest = requestDaos.getAllRequest(Integer.parseInt(indexPage), keySearch);
                request.setAttribute("index", indexPage);
            }
            request.setAttribute("arrayListRequest", arrayListRequest);
            request.setAttribute("countListRequest", countPageSize);
            request.setAttribute("listStatusRequest", listStatusRequest);
            request.setAttribute("listResource", listResources);
            request.setAttribute("key", keySearch);
        } catch (SQLException ex) {
            Logger.getLogger(LoadRequestServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(LoadRequestServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.getRequestDispatcher("ManagementRequest.jsp").forward(request, response);
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
