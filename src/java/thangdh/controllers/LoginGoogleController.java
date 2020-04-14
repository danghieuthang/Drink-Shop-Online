/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thangdh.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thangdh.daos.UserDAO;
import thangdh.dtos.UserDTO;
import thangdh.entities.GooglePojo;
import thangdh.utils.GoogleUtils;

/**
 *
 * @author dhtha
 */

public class LoginGoogleController extends HttpServlet {

    private final String SUCCESS = "shop.jsp";
    private final String FAIL = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FAIL;
        String code = request.getParameter("code");
        try {
//            if (code == null || code.isEmpty()) {
//                request.setAttribute("MESSAGE", "Login Was Fail!");
//                url = FAIL;
//            } else {
//                String accessToken = GoogleUtils.getToken(code);
//                GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
//                String email = googlePojo.getEmail();
//                UserDTO user = new UserDAO().getUserByEmail(email);
//                if (user != null) {
//                    HttpSession session = request.getSession();
//                    session.setAttribute("USER", user);
//                    url = SUCCESS;
//                } else {
//                    request.setAttribute("MESSAGE", "This account not found!");
//                }
//
//            }
                String email=request.getParameter("Email");
                UserDTO user = new UserDAO().getUserByEmail(email);
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", user);
                    url = SUCCESS;
                } else {
                    request.setAttribute("MESSAGE", "This account not found!");
                }
        } catch (Exception e) {
            log("Error at LoginGoogleController: " + e.toString());
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
