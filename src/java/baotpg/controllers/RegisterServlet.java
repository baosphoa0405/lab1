/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.controllers;

import baotpg.users.UserDTO;
import baotpg.users.UsersDAO;
import baotpg.users.UsersError;
import baotpg.utils.SendEmail;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
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
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String SUCCESS = "Verify.jsp";
    private String FAIL = "Register.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FAIL;
        UsersError userErrors = new UsersError();
        try {
            String email = request.getParameter("email").trim();
            String phone = request.getParameter("phone").trim();
            String password = request.getParameter("password").trim();
            String name = request.getParameter("name").trim();
            String address = request.getParameter("address").trim();
            boolean checkError = false;
            UsersDAO usersDAO = new UsersDAO();
            if (email.isEmpty()) {
                checkError = true;
                userErrors.setEmailError("Email is empty");
            }
            if (!email.isEmpty() && !email.matches("^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$")) {
                checkError = true;
                userErrors.setEmailErrorFormat("Email invalid Format abc@gmail.com");
            }
            if (phone.isEmpty()) {
                checkError = true;
                userErrors.setPhoneError("Phone is empty");
            }
            if (!phone.isEmpty() && !phone.matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")) {
                checkError = true;
                userErrors.setPhoneErrorFormat("Phone can only number 09XXXXXX");
            }
            if (password.isEmpty()) {
                checkError = true;
                userErrors.setPasswordError("password is empty");
            }
            if (name.isEmpty()) {
                checkError = true;
                userErrors.setNameError("name is empty");
            }
            if (address.isEmpty()) {
                checkError = true;
                userErrors.setAddressError("address is empty");
            }
            if (checkError) {
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("password", password);
                request.setAttribute("name", name);
                request.setAttribute("address", address);
                request.setAttribute("errorRegister", userErrors);
            } else {
                UserDTO user = new UserDTO(name, phone, password, address, email, 1, 1, Date.valueOf(java.time.LocalDate.now()));
                SendEmail sm = new SendEmail();
                String codeRandom = sm.getRandom();
                HttpSession session = request.getSession();
                boolean isAdd = usersDAO.insertUser(user);
                if (isAdd) {
                    boolean isEmail = sm.sendEmail(user, codeRandom);
                    if (isEmail) {
                        url = SUCCESS;
                        session.setAttribute("codeRandom", codeRandom);
                    } else {
                        System.out.println("failed to send verification email");
                    }
                }
            }
        } catch (SQLException e) {
            userErrors.setEmailDuplicate("Email duplicate");
            request.setAttribute("errorRegister", userErrors);
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
