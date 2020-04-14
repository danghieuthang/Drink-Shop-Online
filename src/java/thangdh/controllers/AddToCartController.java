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
import thangdh.daos.DrinkDAO;
import thangdh.entities.Cart;
import thangdh.entities.CartItem;
import thangdh.dtos.DrinkDTO;

/**
 *
 * @author dhtha
 */
public class AddToCartController extends HttpServlet {

    private final String SUCCESS = "cart.jsp";
    private final String FAIL = "shop.jsp";
    private final String LOGIN = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FAIL;
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null) {
                cart = new Cart();
            }
            String from = request.getParameter("from");
            int id = Integer.parseInt(request.getParameter("ID"));
            DrinkDAO drinkDAO = new DrinkDAO();
            DrinkDTO drink = drinkDAO.getDrinkByID(id);
            if (from != null && from.equalsIgnoreCase("single_shop")) {
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                CartItem dto = new CartItem(drink, quantity);
                CartItem oldItem = (CartItem) cart.get(id);
                if (oldItem == null) {
                    if (quantity <= drinkDAO.getQuantityByID(id)) {
                        cart.addDrinkWithQuantity(dto);
                    } else {
                        request.setAttribute("MESSAGE", "Select drink is out of stocks!");
                    }
                } else {
                    if (quantity + oldItem.getQuantity() <= drinkDAO.getQuantityByID(id)) {
                        cart.addDrinkWithQuantity(dto);
                    } else {
                        request.setAttribute("MESSAGE", "Select drink is out of stocks!");
                    }
                }

            } else {
                CartItem item = new CartItem(drink);
                CartItem oldItem = (CartItem) cart.get(id);
                if (oldItem != null && (oldItem.getQuantity() + 1) <= drinkDAO.getQuantityByID(id)) {
                    cart.addDrink(item);
                } else if (oldItem == null) {
                    cart.addDrink(item);;
                } else {
                    request.setAttribute("MESSAGE", "Select drink is out of stocks!");
                }
            }
            session.setAttribute("CART", cart);
            request.setAttribute("TOTAL", cart.getTotalPrice());
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at AddToCartController: " + e.toString());
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
