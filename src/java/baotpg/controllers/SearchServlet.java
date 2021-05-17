/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.controllers;

import baotpg.resources.ResourceDTO;
import baotpg.resources.ResourcesDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

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
        try {
            // search category
            String categorySelect = request.getParameter("category");
            String index = request.getParameter("index");
            // search name
            ResourcesDAO productDao = new ResourcesDAO();
            String nameSearch = request.getParameter("nameSearch");
            String btnRest = request.getParameter("Reset");
            System.out.println("btnRest" + btnRest);
            int countListProducts = 0;
            int countItemInPageSize = 3;
            int pageSize = 0;
            if (nameSearch != null && categorySelect != null) {
                request.setAttribute("categorySelect", categorySelect);
                request.setAttribute("category", categorySelect);
                request.setAttribute("nameSearch", nameSearch);
                countListProducts = productDao.getCountResoucesByName(nameSearch, categorySelect);
                pageSize = countListProducts / countItemInPageSize;
                if (countListProducts % countItemInPageSize != 0) {
                    pageSize += 1;
                }
                request.setAttribute("pageSize", pageSize);
                if (index == null) {
                    ArrayList<ResourceDTO> listReourcesPagnination = productDao.getListResourcePagination(nameSearch, countItemInPageSize, 1, categorySelect);
                    System.out.println(listReourcesPagnination);
                    request.setAttribute("listReourcesPagnination", listReourcesPagnination);
                    request.setAttribute("index", 1);
                } else {
                    ArrayList<ResourceDTO> listReourcesPagnination = productDao.getListResourcePagination(nameSearch, countItemInPageSize, Integer.parseInt(index), categorySelect);
                    System.out.println(listReourcesPagnination);
                    request.setAttribute("listReourcesPagnination", listReourcesPagnination);
                    request.setAttribute("index", index);
                }
            }
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
            request.getRequestDispatcher("LoadProductServlet").forward(request, response);
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
