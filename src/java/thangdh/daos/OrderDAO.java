/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thangdh.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import thangdh.dtos.DrinkDTO;
import thangdh.dtos.OrderDTO;
import thangdh.dtos.TransactionDTO;
import thangdh.utils.DBUtil;

/**
 *
 * @author dhtha
 */
public class OrderDAO {

    private Connection conn = null;
    private PreparedStatement pStm = null;
    private ResultSet rs = null;

    private void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pStm != null) {
                pStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {

        }
    }

    public OrderDAO() {
    }

    public boolean createOrder(OrderDTO dto) {
        boolean result = false;
        try {
            String sql = "INSERT INTO [Order](TransactionID, DrinkID, Quantity,Amount) "
                    + "VALUES(?,?,?,?)";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            pStm.setInt(1, dto.getTransaction().getID());
            pStm.setInt(2, dto.getDrink().getID());
            pStm.setInt(3, dto.getQuantity());
            pStm.setFloat(4, dto.getAmount());
            result = pStm.executeUpdate() > 0;
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public ArrayList<OrderDTO> getOrderByTransactionID(TransactionDTO tran, String name) {
        ArrayList<OrderDTO> resutt = new ArrayList<>();
        try {
            String sql = "SELECT O.ID, O.Quantity, O.Amount, D.ID AS DrinkID, D.Name, D.Image, D.Description,D.Price,D.CreateDate,D.CategoryID,D.Quantity AS DrinkQuantity,D.Status "
                    + " FROM [Order] AS O, Drink AS D "
                    + "WHERE O.DrinkID=D.ID AND O.TransactionID=? AND D.Name LIKE ? ";
            conn=DBUtil.getConnection();
            pStm=conn.prepareStatement(sql);
            pStm.setInt(1, tran.getID());
            pStm.setString(2, "%"+name+"%");
            rs=pStm.executeQuery();
            while(rs.next()){
                int id=rs.getInt("ID");
                int quantity=rs.getInt("Quantity");
                float amount=rs.getFloat("Amount");
                int drinkID = rs.getInt("DrinkID");
                 name = rs.getString("Name");
                String image = rs.getString("Image");
                String description = rs.getString("Description");
                float price = rs.getFloat("Price");
                Date createDate = rs.getDate("CreateDate");
                int categoryID = rs.getInt("CategoryID");
          
                int drinkQuantity = rs.getInt("DrinkQuantity");
                String status = rs.getString("Status");
                DrinkDTO drink=new DrinkDTO(drinkID, name, image, description, price, createDate, drinkQuantity, status);
                OrderDTO dto=new OrderDTO(tran, drink, quantity, amount);
                resutt.add(dto);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return resutt;
    }
    
}
