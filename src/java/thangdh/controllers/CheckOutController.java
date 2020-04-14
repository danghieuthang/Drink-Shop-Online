/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thangdh.controllers;

import java.io.IOException;
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

/**
 *
 * @author dhtha
 */
public class CheckOutController extends HttpServlet {

    private final String FAIL = "checkout.jsp";
    private final String SUCCESS = "ViewCartController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FAIL;
        try {
            String action = request.getParameter("action");
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if (action != null && !action.equalsIgnoreCase("GetDataCheckOut")) {
                if (cart != null) {
                    request.setAttribute("TOTAL", cart.getTotalPrice());
                } else {
                    url = SUCCESS;
                }
            } else {
                if (cart != null && !cart.values().isEmpty()) {
                    UserDTO user = (UserDTO) session.getAttribute("USER");
                    TransactionDTO transaction = new TransactionDTO(user, new Date(System.currentTimeMillis()), cart.getTotalPrice());
                    TransactionDAO transactionDAO = new TransactionDAO();
                    DrinkDAO drinkDAO=new DrinkDAO();
                    if (!drinkDAO.isOutOfStock(cart)) {
                        if (transactionDAO.createTransaction(transaction)) {
                            transaction = transactionDAO.getLastTransaction(user.getEmail());
                            OrderDAO orderDAO = new OrderDAO();
                            for (Object dto : cart.values()) {
                                OrderDTO orderDTO = new OrderDTO(transaction, ((CartItem) dto).getDrink(), ((CartItem) dto).getQuantity(), ((CartItem) dto).getTotalPrice());
                                orderDAO.createOrder(orderDTO);
                                drinkDAO.updateQuantittyByID(orderDTO.getDrink().getID(), orderDTO.getQuantity());
                            }
                        }
                        url = SUCCESS;
                        cart = null;
                        session.setAttribute("CART", cart);
                        request.setAttribute("MESSAGE", "Checkout was successfull!");
                    }else{
                        url=SUCCESS;
                        request.setAttribute("ERROR_MESSAGE", "Select drink out of stock!");
                    }

                }
            }
        } catch (Exception e) {
            log("Error at CheckOutController: ");
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
