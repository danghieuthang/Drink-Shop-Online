/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thangdh.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thangdh.daos.OrderDAO;
import thangdh.daos.TransactionDAO;
import thangdh.dtos.OrderDTO;
import thangdh.dtos.TransactionDTO;
import thangdh.dtos.UserDTO;

/**
 *
 * @author dhtha
 */
public class ViewHistoryController extends HttpServlet {

    private final String SUCCESS = "history.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            String name = request.getParameter("name");
            if (name == null) {
                name = "";
            } else {
                name = name.trim();
            }
            String strDateFrom = request.getParameter("dateFrom");
            request.setAttribute("DATE_FROM", strDateFrom);
            Date dateFrom = null;
            if (strDateFrom != null) {
                try {
                    dateFrom = Date.valueOf(strDateFrom);
                } catch (Exception e) {
                }

            }
            String strDateTo = request.getParameter("dateTo");
            request.setAttribute("DATE_TO", strDateTo);
            Date dateTo = null;
            if (strDateTo != null) {
                try {
                    dateTo = Date.valueOf(strDateTo);
                } catch (Exception e) {
                }

            }

            ArrayList<TransactionDTO> transactionList;
            if (dateFrom != null && dateTo != null) {
                transactionList = new TransactionDAO().getTransactionByEmail(user.getEmail(), dateFrom, dateTo);
            } else {
                transactionList = new TransactionDAO().getTransactionByEmail(user.getEmail());
            }

            if (!transactionList.isEmpty()) {
                OrderDAO orderDAO = new OrderDAO();
                ArrayList<OrderDTO> orderList = new ArrayList<OrderDTO>();
                for (TransactionDTO transactionDTO : transactionList) {
                    orderList.addAll(orderDAO.getOrderByTransactionID(transactionDTO, name));
                }
                request.setAttribute("ORDER_LIST", orderList);
            }
            request.setAttribute("NAME_SEARCH", name);
        } catch (Exception e) {
            log("Error at ViewHistoryController: " + e.toString());
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
