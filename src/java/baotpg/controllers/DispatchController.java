/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DispatchController", urlPatterns = {"/DispatchController"})
public class DispatchController extends HttpServlet {

    private String LOGIN_ACTION = "login";
    private String LOGIN_SERVLET = "LoginServlet";
    private String ERROR_JSP = "error.jsp";
    private String LOGIN_JSP = "Login.jsp";
    private String LOGOUT_ACTION = "logout";
    private String LOGOUT_SERVLET = "LogoutServlet";
    private String REGISTER_ACTION = "Create";
    private String REGISTER_SERVLET = "RegisterServlet";
    private String VERIFY_ACTION = "Submit Code";
    private String VERIFY_SERVLET = "VerifyCodeServlet";
    private String SELECT_ACTION = "search";
    private String SEARCH_SERVLET = "SearchServlet";
    private String SEARCH_NAME_ACTION = "Search Name";
    private String RESET_ACTION = "Reset";
    private String LOAD_PRODUCT_SERVLET = "LoadProductServlet";
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
        String btnAction = request.getParameter("btnAction");
        System.out.println("action " + btnAction);
        String url = ERROR_JSP;
        try {
            if (btnAction == null) {
//                url = LOGIN_JSP;
                  url = "LoadProductServlet";
            }
            if (LOGIN_ACTION.equals(btnAction)) {
                url = LOGIN_SERVLET;
            }
            if (LOGOUT_ACTION.equals(btnAction)) {
                url = LOGOUT_SERVLET;
            }
            if (REGISTER_ACTION.equals(btnAction)) {
                url = REGISTER_SERVLET;
            }
            if (VERIFY_ACTION.equals(btnAction)) {
                url = VERIFY_SERVLET;
            }
            if (SELECT_ACTION.equals(btnAction)) {
                url = SEARCH_SERVLET;
            }
            if (SEARCH_NAME_ACTION.equals(btnAction)) {
                url = SEARCH_SERVLET;
            }
            if (RESET_ACTION.equals(btnAction)) {
                url = LOAD_PRODUCT_SERVLET;
            }
        } catch (Exception e) {

        } finally {
            System.out.println("url" + url);
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
