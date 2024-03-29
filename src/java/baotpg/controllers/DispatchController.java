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
    private String BOOKING_SERVLET = "BookingServlet";
    private String BOOK_ACTION = "Booking";
    private String LOAD_REQUEST_ACTION = "loadRequets";
    private String LOAD_REQUEST_SERVLET = "LoadRequestServlet";
    private String SEARCH_REQUEST_ACTION = "Search Request";
    private String SEARCH_SERVLET_REQUEST = "LoadRequestServlet";
    private String DENY_ACTION = "Delete";
    private String ACCEPT_ACTION = "Accept";
    private String CONFIRM_REQUEST_SERVLET = "ConfirmRequestsServlet";
    private String VIEW_LIST_BOOKING_ACTION = "View List Booking";
    private String LOAD_LIST_BOOKING_SERVLET = "LoadListBookingServlet";
    private String SEARCH_LIST_BOOKING = "Search Booking";
    private String DELETE_REQUEST_FROM_USER = "Delete Request";
    private String DELETE_REQUEST_SERVLET = "DeleteRequestFromUser";
    private String VERIFY_EMAIL_ACTION = "verifyEmail";
    private String VERIFY_SERVLET_AGAIN = "VerifyCodeAgainServlet";
    private String VIEW_RESOURCE_ACTION = "View Course";
    private String VIEW_RESOURCE_SERVLET = "ViewResourceServlet";
    private String BACK_TO_LIST_BOOKING = "backToListBooking";
    private String DELETE_VIEW_COURSE_ACTION = "Delete View Course";
    private String DELETE_VIEW_COURSE = "DeleteViewCourse";

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
                url = LOGIN_JSP;
            } else if (LOGIN_ACTION.equals(btnAction)) {
                url = LOGIN_SERVLET;
            } else if (LOGOUT_ACTION.equals(btnAction)) {
                url = LOGOUT_SERVLET;
            } else if (REGISTER_ACTION.equals(btnAction)) {
                url = REGISTER_SERVLET;
            } else if (VERIFY_ACTION.equals(btnAction)) {
                url = VERIFY_SERVLET;
            } else if (SELECT_ACTION.equals(btnAction)) {
                url = SEARCH_SERVLET;
            } else if (SEARCH_NAME_ACTION.equals(btnAction)) {
                url = SEARCH_SERVLET;
            } else if (RESET_ACTION.equals(btnAction)) {
                url = SEARCH_SERVLET;
            } else if (BOOK_ACTION.equals(btnAction)) {
                url = BOOKING_SERVLET;
            } else if (LOAD_REQUEST_ACTION.equals(btnAction)) {
                url = LOAD_REQUEST_SERVLET;
            } else if (SEARCH_REQUEST_ACTION.equals(btnAction)) {
                url = SEARCH_SERVLET_REQUEST;
            } else if (DENY_ACTION.equals(btnAction)) {
                url = CONFIRM_REQUEST_SERVLET;
            } else if (ACCEPT_ACTION.equals(btnAction)) {
                url = CONFIRM_REQUEST_SERVLET;
            } else if (VIEW_LIST_BOOKING_ACTION.equals(btnAction)) {
                url = LOAD_LIST_BOOKING_SERVLET;
            } else if (SEARCH_LIST_BOOKING.equals(btnAction)) {
                url = LOAD_LIST_BOOKING_SERVLET;
            } else if (DELETE_REQUEST_FROM_USER.equals(btnAction)) {
                url = DELETE_REQUEST_SERVLET;
            } else if (VERIFY_EMAIL_ACTION.equals(btnAction)) {
                url = VERIFY_SERVLET_AGAIN;
            } else if (VIEW_RESOURCE_ACTION.equals(btnAction)) {
                url = VIEW_RESOURCE_SERVLET;
            } else if (BACK_TO_LIST_BOOKING.equals(btnAction)) {
                url = LOAD_LIST_BOOKING_SERVLET;
            }else if (DELETE_VIEW_COURSE_ACTION.equals(btnAction)) {
               url = DELETE_VIEW_COURSE;
            }
        } catch (Exception e) {
            e.printStackTrace();
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
