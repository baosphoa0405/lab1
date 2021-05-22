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
import java.io.PrintWriter;
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
@WebServlet(name = "LoadListBookingServlet", urlPatterns = {"/LoadListBookingServlet"})
public class LoadListBookingServlet extends HttpServlet {

    private String ERROR = "Login.jsp";
    private String SUCCESS = "ViewListBooking.jsp";

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
            String nameProduct = request.getParameter("nameProduct");
            String date = request.getParameter("dateSearch");
            String index = request.getParameter("index");
            RequestsDAO requestDAO = new RequestsDAO();
            ResourcesDAO resourceDAO = new ResourcesDAO();
            StatusRequestsDAO statusRequestDAO = new StatusRequestsDAO();
            Date dateSearch = null;
            if (date != null && !date.isEmpty()) {
                dateSearch = Date.valueOf(date);
            }
            if (nameProduct == null) {
                nameProduct = "";
            }
            ArrayList<RequestDTO> listRequestBooking = new ArrayList<>();
            ArrayList<ResourceDTO> listResources = new ArrayList<>();
            ArrayList<StatusRequestDTO> listStatusRequest = new ArrayList<>();
            int quanityPageSize = 0;
            int quanityListRequestBooking = 0;
            // cho user normal
            if (user == null && emailUserLoginGG == null) {
                url = ERROR;
            } else {
                if (user != null) {
                    quanityListRequestBooking = requestDAO.getCountListBooking(user.getEmail(), dateSearch, nameProduct);
                    if (quanityListRequestBooking > 0) {
                        listResources = resourceDAO.getAllListReources();
                        listStatusRequest = statusRequestDAO.getAllListStatusRequest();
                        quanityPageSize = quanityListRequestBooking / MyConstants.QUANITY_ITEM_IN_PAGE;
                        if (quanityPageSize % quanityListRequestBooking != 0) {
                            quanityPageSize += 1;
                        }
                        if (index == null) {
                            listRequestBooking = requestDAO.getAllListRequestBooking(user.getEmail(), dateSearch, nameProduct, 1);
                        } else {
                            listRequestBooking = requestDAO.getAllListRequestBooking(user.getEmail(), dateSearch, nameProduct, Integer.parseInt(index));
                        }
                    } else {
                        request.setAttribute("emptyListRequestBooking", "Empty List Request Booking");
                    }
                }
                // cho email
                if (emailUserLoginGG != null) {
                    quanityListRequestBooking = requestDAO.getCountListBooking(user.getEmail(), dateSearch, nameProduct);
                    if (quanityListRequestBooking > 0) {
                        listResources = resourceDAO.getAllListReources();
                        quanityPageSize = quanityListRequestBooking / MyConstants.QUANITY_ITEM_IN_PAGE;
                        if (quanityPageSize % quanityListRequestBooking != 0) {
                            quanityPageSize += 1;
                        }
                        if (index == null) {
                            listRequestBooking = requestDAO.getAllListRequestBooking(user.getEmail(), dateSearch, nameProduct, 1);
                        } else {
                            listRequestBooking = requestDAO.getAllListRequestBooking(user.getEmail(), dateSearch, nameProduct, Integer.parseInt(index));
                        }
                    } else {
                        request.setAttribute("emptyListRequestBooking", "Empty List Request Booking");
                    }
                }

            }
            request.setAttribute("listStatusRequest", listStatusRequest);
            request.setAttribute("listResouces", listResources);
            request.setAttribute("quanityPageSize", quanityPageSize);
            request.setAttribute("listRequestBooking", listRequestBooking);
            request.setAttribute("index", index);
            request.setAttribute("nameProduct", nameProduct);
            request.setAttribute("dateSearch", date);
            System.out.println("dada" + url);
        } catch (NamingException ex) {
            Logger.getLogger(LoadListBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoadListBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
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
