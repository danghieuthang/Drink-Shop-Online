/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thangdh.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thangdh.daos.UserDAO;
import thangdh.dtos.UserDTO;
import thangdh.dtos.UserErrorDTO;

/**
 *
 * @author dhtha
 */
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String SUCCESS = "index.jsp";
    private final String FAIL = "login.jsp";

    private UserErrorDTO checkInputData(String email, String password) {
        UserErrorDTO result = null;
        if (email == null || email.trim().isEmpty()) {
            result = new UserErrorDTO();
            result.setEmailErr("Email must be required!");
        } else if (!email.matches("^[a-z][a-z0-9_\\.]{2,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$")) {
            result = new UserErrorDTO();
            result.setEmailErr("Email is not valid!");
        }

        if (password == null || password.trim().isEmpty()) {
            result = result == null ? new UserErrorDTO() : result;
            result.setPasswordErr("Password must be required!");
        }

        return result;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FAIL;
        boolean check = false;
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            UserErrorDTO userErr = checkInputData(email, password);
            if (userErr == null) {
                UserDAO dao = new UserDAO();
                UserDTO user = dao.checkLogin(email, password);
                if (user == null) {
                    userErr = new UserErrorDTO();
                    userErr.setPasswordErr("User is not found!");
                    request.setAttribute("USER_ERROR", userErr);
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", user);
                    url = SUCCESS;
                    check = true;
                }
            } else {
                request.setAttribute("USER_ERROR", userErr);
            }
        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
        } finally {
            if (!check) {
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                response.sendRedirect(url);
            }
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
