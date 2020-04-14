/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thangdh.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thangdh.daos.DrinkDAO;
import thangdh.daos.OrderDAO;
import thangdh.daos.TransactionDAO;
import thangdh.entities.Cart;
import thangdh.entities.CartItem;
import thangdh.dtos.OrderDTO;
import thangdh.dtos.TransactionDTO;
import thangdh.dtos.UserDTO;
import thangdh.entities.PaymentServices;

/**
 *
 * @author dhtha
 */
public class AuthorizePaymentController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final String SUCCESS = "ViewHistoryController";
    private final String FAIL = "ViewCartController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FAIL;
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            UserDTO user = (UserDTO) session.getAttribute("USER");
            DrinkDAO drinkDAO = new DrinkDAO();
            if (!drinkDAO.isOutOfStock(cart)) {
                PaymentServices paymentServices = new PaymentServices();
                String approvalLink = paymentServices.authorizePayment(cart);
                url = approvalLink;
            } else {
                url = FAIL;
                request.setAttribute("ERROR_MESSAGE", "There are some drink out of stock!");
            }
        } catch (Exception e) {
            log("Message at PaymentPaypalController: " + e.toString());
        } finally {
            if (url.equals(FAIL)) {
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
