/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thangdh.controllers;

import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
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
import thangdh.dtos.OrderDTO;
import thangdh.dtos.TransactionDTO;
import thangdh.dtos.UserDTO;
import thangdh.entities.Cart;
import thangdh.entities.CartItem;
import thangdh.entities.PaymentServices;

/**
 *
 * @author dhtha
 */
public class ExecutePaymentController extends HttpServlet {

    private final String SUCCESS = "ViewHistoryController";
    private final String FAIL = "ViewCartController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");
        String url = FAIL;
        try {
//            PaymentServices paymentServices = new PaymentServices();
//            Payment payment = paymentServices.executePayment(paymentId, payerId);
//
//            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
//            Transaction transaction = payment.getTransactions().get(0);

//            request.setAttribute("payer", payerInfo);
//            request.setAttribute("transaction", transaction);
            //
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            UserDTO user = (UserDTO) session.getAttribute("USER");
            TransactionDTO transactionDTO = new TransactionDTO(user, new Date(System.currentTimeMillis()), cart.getTotalPrice());
            TransactionDAO transactionDAO = new TransactionDAO();
            DrinkDAO drinkDAO = new DrinkDAO();
            if (!drinkDAO.isOutOfStock(cart)) {
                PaymentServices paymentServices = new PaymentServices();
                Payment payment = paymentServices.executePayment(paymentId, payerId);
                PayerInfo payerInfo = payment.getPayer().getPayerInfo();
                Transaction transaction = payment.getTransactions().get(0);

                if (transactionDAO.createTransaction(transactionDTO)) {
                    transactionDTO = transactionDAO.getLastTransaction(user.getEmail());
                    OrderDAO orderDAO = new OrderDAO();
                    for (Object dto : cart.values()) {
                        OrderDTO orderDTO = new OrderDTO(transactionDTO, ((CartItem) dto).getDrink(), ((CartItem) dto).getQuantity(), ((CartItem) dto).getTotalPrice());
                        orderDAO.createOrder(orderDTO);
                        drinkDAO.updateQuantittyByID(orderDTO.getDrink().getID(), orderDTO.getQuantity());
                    }
                }
                url = SUCCESS;
                cart = null;
                session.setAttribute("CART", cart);
                request.setAttribute("MESSAGE", "Checkout was successfull!");
            } else {
                request.setAttribute("ERROR_MESSAGE", "Select drink out of stock!");
            }
        } catch (PayPalRESTException ex) {
            log("Error at ExecutePaymentController: " + ex.toString());
            url = FAIL;
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
