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

/**
 *
 * @author dhtha
 */
public class UpdateCartController extends HttpServlet {

    private final String SUCCESS="cart.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       String url=SUCCESS;
        try {
            String[] IDs=request.getParameterValues("ID");
            HttpSession session=request.getSession();
            Cart cart=(Cart) session.getAttribute("CART");
            for (int i = 0; i < IDs.length; i++) {
                String ID = IDs[i];
                try {
                    int numID=Integer.parseInt(ID);
                    String name="quantity_"+ID;
                    int quantity=Integer.parseInt(request.getParameter(name));
                    DrinkDAO drinkDAO=new DrinkDAO();
                    if(drinkDAO.getQuantityByID(numID)>=quantity){
                        cart.updateDrink(numID, quantity);
                    }
                    else{
                        request.setAttribute("MESSAGE", "There are some drink out of stock!");
                    }
                   
                    
                } catch (Exception e) {
                }
            }
            request.setAttribute("TOTAL", cart.getTotalPrice());
        } catch (Exception e) {
        }finally{
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
