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
import thangdh.entities.Cart;
import thangdh.entities.CartItem;
import thangdh.dtos.CategoryDTO;
import thangdh.dtos.DrinkDTO;
import thangdh.utils.DBUtil;

/**
 *
 * @author dhtha
 */
public class DrinkDAO {

    private Connection conn = null;
    private PreparedStatement pStm = null;
    private ResultSet rs = null;

    public DrinkDAO() {
    }

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

    public ArrayList<DrinkDTO> getAllDrink() {
        ArrayList<DrinkDTO> result = new ArrayList<>();
        try {
            String sql = "SELECT D.ID ,D.Name, D.Image, D.Description,D.Price,D.CreateDate,D.CategoryID,D.Quantity ,D.Status, C.Name AS CategoryName "
                    + "         FROM [dbo].[Drink] AS D, Category AS C"
                    + "		WHERE D.ID=C.ID"
                    + "         ORDER BY D.CreateDate DESC";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            rs = pStm.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String name = rs.getString("Name");
                String image = rs.getString("Image");
                String description = rs.getString("Description");
                float price = rs.getFloat("Price");
                Date createDate = rs.getDate("CreateDate");
                int categoryID = rs.getInt("CategoryID");
                String categoryName = rs.getString("CategoryName");
                int quantity = rs.getInt("Quantity");
                String status = rs.getString("Status");
                DrinkDTO dto = new DrinkDTO(ID, name, image, description, price, createDate, new CategoryDTO(categoryID, categoryName), quantity, status);
                result.add(dto);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public DrinkDTO getDrinkByID(int ID) {
        DrinkDTO result = null;
        try {
            String sql = "SELECT D.ID ,D.Name, D.Image, D.Description,D.Price,D.CreateDate,D.CategoryID,D.Quantity ,D.Status, C.Name AS CategoryName "
                    + "         FROM [dbo].[Drink] AS D, Category AS C"
                    + "		WHERE D.CategoryID=C.ID AND D.ID=?";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            pStm.setInt(1, ID);
            rs = pStm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Name");
                String image = rs.getString("Image");
                String description = rs.getString("Description");
                float price = rs.getFloat("Price");
                Date createDate = rs.getDate("CreateDate");
                int cateID = rs.getInt("CategoryID");
                String categoryName = rs.getString("CategoryName");
                int quantity = rs.getInt("Quantity");
                String status = rs.getString("Status");
                result = new DrinkDTO(ID, name, image, description, price, createDate, new CategoryDTO(cateID, categoryName), quantity, status);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public ArrayList<DrinkDTO> searchDrink(String name, String categoryID, int priceFrom, int priceTo) {
        ArrayList<DrinkDTO> result = new ArrayList<>();
        try {
            String sql = "SELECT D.ID ,D.Name, D.Image, D.Description,D.Price,D.CreateDate,D.CategoryID,D.Quantity ,D.Status, C.Name AS CategoryName "
                    + "         FROM [dbo].[Drink] AS D, Category AS C "
                    + "		WHERE D.CategoryID=C.ID AND D.Name LIKE ? AND C.ID LIKE ? "
                    + "         AND D.Price>=? AND D.Price <=? AND D.Status=? AND D.Quantity>? "
                    + "         ORDER BY D.CreateDate DESC";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            pStm.setString(1, "%" + name + "%");
            pStm.setString(2, "%" + categoryID + "%");
            pStm.setInt(3, priceFrom);
            pStm.setInt(4, priceTo);
            pStm.setString(5, "Active");
            pStm.setInt(6, 0);
            rs = pStm.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                name = rs.getString("Name");
                String image = rs.getString("Image");
                String description = rs.getString("Description");
                float price = rs.getFloat("Price");
                Date createDate = rs.getDate("CreateDate");
                int cateID = rs.getInt("CategoryID");
                String categoryName = rs.getString("CategoryName");
                int quantity = rs.getInt("Quantity");
                String status = rs.getString("Status");
                DrinkDTO dto = new DrinkDTO(ID, name, image, description, price, createDate, new CategoryDTO(cateID, categoryName), quantity, status);
                result.add(dto);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public ArrayList<DrinkDTO> searchDrink(String name, String categoryID, int priceFrom, int priceTo, String status) {
        ArrayList<DrinkDTO> result = new ArrayList<>();
        try {
            String sql = "SELECT D.ID ,D.Name, D.Image, D.Description,D.Price,D.CreateDate,D.CategoryID,D.Quantity ,D.Status, C.Name AS CategoryName "
                    + "         FROM [dbo].[Drink] AS D, Category AS C "
                    + "		WHERE D.CategoryID=C.ID AND D.Name LIKE ? AND C.ID LIKE ? "
                    + "         AND D.Price>=? AND D.Price <=? AND D.Status LIKE ? "
                    + "         ORDER BY D.CreateDate DESC";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            pStm.setString(1, "%" + name + "%");
            pStm.setString(2, "%" + categoryID + "%");
            pStm.setInt(3, priceFrom);
            pStm.setInt(4, priceTo);
            if (status.isEmpty()) {
                pStm.setString(5, "%" + status + "%");
            } else {
                pStm.setString(5, status);
            }

            rs = pStm.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                name = rs.getString("Name");
                String image = rs.getString("Image");
                String description = rs.getString("Description");
                float price = rs.getFloat("Price");
                Date createDate = rs.getDate("CreateDate");
                int cateID = rs.getInt("CategoryID");
                String categoryName = rs.getString("CategoryName");
                int quantity = rs.getInt("Quantity");
                status = rs.getString("Status");
                DrinkDTO dto = new DrinkDTO(ID, name, image, description, price, createDate, new CategoryDTO(cateID, categoryName), quantity, status);
                result.add(dto);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean createDrink(DrinkDTO dto) {
        boolean result = false;
        try {
            String sql = "INSERT INTO [dbo].[Drink]"
                    + "           ([Name]"
                    + "           ,[Image]"
                    + "           ,[Description]"
                    + "           ,[Price]"
                    + "           ,[CreateDate]"
                    + "           ,[CategoryID]"
                    + "           ,[Quantity]"
                    + "           ,[Status])"
                    + "     VALUES(?,?,?,?,?,?,?,?)";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            pStm.setString(1, dto.getName());
            pStm.setString(2, dto.getImage());
            pStm.setString(3, dto.getDescription());
            pStm.setFloat(4, dto.getPrice());
            pStm.setDate(5, dto.getCreateDate());
            pStm.setInt(6, dto.getCategory().getID());
            pStm.setInt(7, dto.getQuantity());
            pStm.setString(8, dto.getStatus());
            result = pStm.executeUpdate() > 0;
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateDrink(DrinkDTO dto) {
        boolean result = false;
        try {
            String sql = "UPDATE [dbo].[Drink]"
                    + "   SET [Name] = ?"
                    + "      ,[Image] = ?"
                    + "      ,[Description] = ?"
                    + "      ,[Price] = ?"
                    + "      ,[CreateDate] = ?"
                    + "      ,[CategoryID] = ?"
                    + "      ,[Quantity] = ?"
                    + "      ,[Status] = ?"
                    + " WHERE ID=?";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            pStm.setString(1, dto.getName());
            pStm.setString(2, dto.getImage());
            pStm.setString(3, dto.getDescription());
            pStm.setFloat(4, dto.getPrice());
            pStm.setDate(5, dto.getCreateDate());
            pStm.setInt(6, dto.getCategory().getID());
            pStm.setInt(7, dto.getQuantity());
            pStm.setString(8, dto.getStatus());
            pStm.setInt(9, dto.getID());
            result = pStm.executeUpdate() > 0;
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean deleteDrinkByID(int ID) {
        boolean result = false;
        try {
            String sql = "UPDATE Drink "
                    + "SET Status=?, CreateDate=? "
                    + "WHERE ID=?";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            pStm.setString(1, "Inactive");
            pStm.setDate(2, new Date(System.currentTimeMillis()));
            pStm.setInt(3, ID);
            result = pStm.executeUpdate() > 0;
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean isOutOfStock(Cart cart) {
        boolean result = false;
        try {
            String sql = "SELECT ID, Quantity FROM Drink "
                    + "WHERE ID=? AND Quantity<?";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            result = false;
            for (Object object : cart.values()) {
                CartItem dto = (CartItem) object;
                int ID = dto.getDrink().getID();
                pStm.setInt(1, ID);
                pStm.setInt(2, dto.getQuantity());
                rs = pStm.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            }

        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public int getQuantityByID(int ID) {
        int result = 0;
        try {
            String sql = "SELECT Quantity FROM Drink "
                    + "WHERE ID=?";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            pStm.setInt(1, ID);
            rs = pStm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("Quantity");
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateQuantittyByID(int id, int buyQuantity) {
        boolean result = false;
        try {
            String sql = "UPDATE Drink "
                    + "SET Quantity=Quantity-? "
                    + "WHERE ID=?";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            pStm.setInt(1, buyQuantity);
            pStm.setInt(2, id);
            result = pStm.executeUpdate() > 0;
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public ArrayList<DrinkDTO> getDrinkRecommendation(String userEmail) {
        ArrayList<DrinkDTO> result = new ArrayList<>();
        try {
            String sql = "SELECT TOP(3)  D.ID ,D.Name, D.Image, D.Description,D.Price,D.CreateDate,D.CategoryID,D.Quantity ,D.Status, C.Name AS CategoryName"
                    + " FROM Drink AS  D, "
                    + "         (SELECT CategoryID, count(CategoryID) AS Num FROM Drink "
                    + "         WHERE ID IN  "
                    + "             (SELECT DrinkID FROM [Order] AS O, [Transaction] AS T "
                    + "             WHERE T.ID=O.TransactionID AND T.Email=?)  "
                    + "         GROUP BY CategoryID "
                    + "         HAVING count(CategoryID)>=All "
                    + "                                 (SELECT count(CategoryID) AS Num FROM Drink "
                    + "					WHERE ID IN  "
                    + "						(SELECT DrinkID FROM [Order] AS O, [Transaction] AS T "
                    + "						WHERE T.ID=O.TransactionID AND T.Email=?)  "
                    + "                                 GROUP BY CategoryID) "
                    + ") AS tblSum, Category AS C "
                    + "WHERE  D.CategoryID = tblSum.CategoryID AND C.ID= D.CategoryID AND Status=? "
                    + "ORDER BY CreateDate DESC";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            pStm.setString(1, userEmail);
            pStm.setString(2, userEmail);
           
            pStm.setString(3, "Active");
         
            rs = pStm.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String name = rs.getString("Name");
                String image = rs.getString("Image");
                String description = rs.getString("Description");
                float price = rs.getFloat("Price");
                Date createDate = rs.getDate("CreateDate");
                int cateID = rs.getInt("CategoryID");
                String categoryName = rs.getString("CategoryName");
                int quantity = rs.getInt("Quantity");
                String status = rs.getString("Status");
                DrinkDTO dto = new DrinkDTO(ID, name, image, description, price, createDate, new CategoryDTO(cateID, categoryName), quantity, status);
                result.add(dto);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }

}
