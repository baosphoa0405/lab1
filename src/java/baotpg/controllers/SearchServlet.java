/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.controllers;

import baotpg.categories.CategoriesDAO;
import baotpg.resources.ResourceDTO;
import baotpg.resources.ResourcesDAO;
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
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private String SUCCESS = "ListProduct.jsp";
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
            // search category
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("user");
            String emailUserLoginGG = (String) session.getAttribute("loginGG");
            if (user == null && emailUserLoginGG == null) {
                url = FAIL;
            } else {
                String categorySelect = request.getParameter("category");
                String index = request.getParameter("index");
                String nameSearch = request.getParameter("nameSearch");
                String date = request.getParameter("date");
                if (nameSearch == null) {
                    nameSearch = "";
                }
                if (categorySelect == null) {
                    categorySelect = "";
                }
                if (categorySelect != null) {
                    if (categorySelect.equalsIgnoreCase("default")) {
                        categorySelect = "";
                    }
                }
                ResourcesDAO productDao = new ResourcesDAO();
                CategoriesDAO categoriesDAO = new CategoriesDAO();
                Date dateSearch = null;
                if (date != null && !date.isEmpty()) {
                    dateSearch = Date.valueOf(date);
                }
                int countListProducts = 0;
                int pageSize = 0;
                countListProducts = productDao.getQuanityResouces(nameSearch, categorySelect, dateSearch);
                if (countListProducts > 0) {
                    pageSize = countListProducts / MyConstants.QUANITY_ITEM_IN_PAGE;
                    if (countListProducts % MyConstants.QUANITY_ITEM_IN_PAGE != 0) {
                        pageSize += 1;
                    }
                    if (index == null) {
                        ArrayList<ResourceDTO> listReourcesPagnination = productDao.getListResourcePagination(nameSearch, 1, categorySelect, dateSearch);
                        request.setAttribute("listReourcesPagnination", listReourcesPagnination);
                        request.setAttribute("index", 1);
                    } else {
                        ArrayList<ResourceDTO> listReourcesPagnination = productDao.getListResourcePagination(nameSearch, Integer.parseInt(index), categorySelect, dateSearch);
                        request.setAttribute("listReourcesPagnination", listReourcesPagnination);
                        request.setAttribute("index", index);
                    }
                } else {
                    request.setAttribute("noValue", "No resource");
                }
                request.setAttribute("date", dateSearch);
                request.setAttribute("pageSize", pageSize);
                request.setAttribute("categorySelect", categorySelect);
                request.setAttribute("category", categorySelect);
                request.setAttribute("nameSearch", nameSearch);
                request.setAttribute("listCategories", categoriesDAO.getAllListCategories());
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
