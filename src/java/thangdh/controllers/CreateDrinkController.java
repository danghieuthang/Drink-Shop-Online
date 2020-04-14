/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thangdh.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thangdh.daos.CategoryDAO;
import thangdh.daos.DrinkDAO;
import thangdh.dtos.CategoryDTO;
import thangdh.dtos.DrinkDTO;
import thangdh.dtos.DrinkErrorDTO;

/**
 *
 * @author dhtha
 */
public class CreateDrinkController extends HttpServlet {

    private final String SUCCESS = "AdminSearchDrinkController";
    private final String FAIL = "create_drink.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FAIL;
        try {
            String action = request.getParameter("action");
            if (action.equalsIgnoreCase("GetCategory")) { // 
                CategoryDAO categoryDAO = new CategoryDAO();
                ArrayList<CategoryDTO> categoryList = categoryDAO.getAllCategory();
                request.setAttribute("CATEGORY_LIST", categoryList);
            } else {
                DrinkErrorDTO drinkError = null;
                String name = request.getParameter("name");
                if (name == null || name.trim().isEmpty()) {
                    if (drinkError == null) {
                        drinkError = new DrinkErrorDTO();
                    }
                    drinkError.setNameErr("Drink name must be required!");
                } else {
                    name = name.trim();
                }
                String image = request.getParameter("image");
                if (image == null || image.trim().isEmpty()) {
                    if (drinkError == null) {
                        drinkError = new DrinkErrorDTO();
                    }
                    drinkError.setImageErr("Drink image must be required!");
                } else {
                    File f = new File(image);
                    if (!f.isFile()) {
                        if (drinkError == null) {
                            drinkError = new DrinkErrorDTO();
                        }
                        drinkError.setImageErr("Image not exists!");
                    } else if (!image.contains(".jpg") && !image.contains(".png")) {
                        if (drinkError == null) {
                            drinkError = new DrinkErrorDTO();
                        }
                        drinkError.setImageErr("Image only jpg or png");
                    } else {

                        int index = image.indexOf("images\\");
                        image = image.substring(index);
                    }
                }
                String description = request.getParameter("description");
                if (description == null || description.trim().isEmpty()) {
                    if (drinkError == null) {
                        drinkError = new DrinkErrorDTO();
                    }
                    drinkError.setDescriptionErr("Drink description must be required!");
                } else {
                    description = description.trim();
                }
                float price = 0;
                try {
                    price = Float.parseFloat(request.getParameter("price"));
                } catch (Exception e) {
                    price = -1;
                }
                if (price < 0) {
                    if (drinkError == null) {
                        drinkError = new DrinkErrorDTO();
                    }
                    drinkError.setPriceErr("Drink price must be valid!");
                }
                int categoryID = 0;
                try {
                    categoryID = Integer.parseInt(request.getParameter("categoryID"));
                } catch (Exception e) {
                }

                CategoryDAO categoryDAO = new CategoryDAO();
                CategoryDTO category = categoryDAO.getCategoryByID(categoryID);
                if (category == null) {
                    if (drinkError == null) {
                        drinkError = new DrinkErrorDTO();
                    }
                    drinkError.setCategoryErr("Drink category not exist!");
                }
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                } catch (Exception e) {
                    quantity = -1;
                }
                if (quantity <= 0) {
                    if (drinkError == null) {
                        drinkError = new DrinkErrorDTO();
                    }
                    drinkError.setQuantityErr("Drink quantity must be required!");
                } else {
                    if (quantity > 1000) {
                        if (drinkError == null) {
                            drinkError = new DrinkErrorDTO();
                        }
                        drinkError.setQuantityErr("Drink quantity must be smaller than 1000!");
                    }
                }

                if (drinkError == null) {
                    DrinkDAO drinkDAO = new DrinkDAO();
                    Date creatDate = new Date(System.currentTimeMillis());
                    String status = "Active";
                    DrinkDTO dto = new DrinkDTO(name, image, description, price, creatDate, category, quantity, status);
                    if (drinkDAO.createDrink(dto)) {
                        request.setAttribute("MESSAGE", "Insert was succesfull!");
                        url = SUCCESS;
                    } else {
                        request.setAttribute("MESSAGE", "Insert was fail1");
                        request.setAttribute("DRINK", dto);
                        ArrayList<CategoryDTO> categoryList = categoryDAO.getAllCategory();
                        request.setAttribute("CATEGORY_LIST", categoryList);
                    }
                } else {
                    Date creatDate = new Date(System.currentTimeMillis());
                    String status = "Active";
                    DrinkDTO dto = new DrinkDTO(name, image, description, price, creatDate, category, quantity, status);
                    request.setAttribute("DRINK", dto);
                    request.setAttribute("DRINK_ERR", drinkError);
                    request.setAttribute("MESSAGE", "Create Drink was fail!");
                    ArrayList<CategoryDTO> categoryList = categoryDAO.getAllCategory();
                    request.setAttribute("CATEGORY_LIST", categoryList);
                }
            }

        } catch (Exception e) {
            log("Error at CreateDrinkController: " + e.toString());
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
