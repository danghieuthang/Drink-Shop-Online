/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thangdh.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thangdh.daos.CategoryDAO;
import thangdh.daos.DrinkDAO;
import thangdh.dtos.CategoryDTO;
import thangdh.dtos.DrinkDTO;

/**
 *
 * @author dhtha
 */
public class AdminSearchDrinkController extends HttpServlet {

    private final String SUCCESS = "manager.jsp";
    private final String FAIL = "index.jsp";
    private final int PRICE_MIN = 0;
    private final int PRICE_MAX = 1000;
private final int PAGE_SIZE=6;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FAIL;
        try {

            String name = request.getParameter("nameSearch");
            String categoryID = request.getParameter("categoryIDSearch");
            int priceFrom = PRICE_MIN;
            int priceTo = PRICE_MAX;
            try {
                priceFrom = Integer.parseInt(request.getParameter("priceFrom"));
            } catch (NumberFormatException e) {
            }
            try {
                priceTo = Integer.parseInt(request.getParameter("priceTo"));
            } catch (NumberFormatException e) {
            }
            if (name == null) {
                name = "";
            }
            if (categoryID == null) {
                categoryID = "";
            }
            String status = request.getParameter("status");
            if (status == null) {
                status = "";
            }
            // get Drinks search 
            DrinkDAO drinkDAO = new DrinkDAO();
            ArrayList<DrinkDTO> drinkList = drinkDAO.searchDrink(name, categoryID, priceFrom, priceTo, status);
            int pageList = (int) Math.ceil(drinkList.size() * 1.0 / PAGE_SIZE);
            request.setAttribute("NUMBER_PAGE", pageList);
            int begin = 1;
            int page = 1;
            try {
                page = Integer.parseInt(request.getParameter("page"));
                if (page > pageList) {
                    page = 1;
                }
            } catch (Exception e) {
            }
            begin = (page - 1) * PAGE_SIZE;
            int end = Math.min(page * PAGE_SIZE, drinkList.size());
            request.setAttribute("PAGE", page);
            request.setAttribute("DRINK_LIST", drinkList.subList(begin, end));
            /// request.setAttribute("DRINK_LIST", drinkList);
            //get category
            CategoryDAO categoryDAO = new CategoryDAO();
            ArrayList<CategoryDTO> categoryList = categoryDAO.getAllCategory();
            request.setAttribute("CATEGORY_LIST", categoryList);
            // 
            request.setAttribute("NAME", name);
            request.setAttribute("CATEGORY_ID", categoryID);
            request.setAttribute("PRICE_FROM", priceFrom);
            request.setAttribute("PRICE_TO", priceTo);
            request.setAttribute("STATUS", status);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at SearchDrinkController: " + e.toString());
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
