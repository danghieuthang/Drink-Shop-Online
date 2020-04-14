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
import thangdh.dtos.TransactionDTO;
import thangdh.dtos.UserDTO;
import thangdh.utils.DBUtil;

/**
 *
 * @author dhtha
 */
public class TransactionDAO {

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

    public TransactionDAO() {
    }

    public boolean createTransaction(TransactionDTO dto) {
        boolean result = false;
        try {
            String sql = "INSERT INTO [Transaction](Email, CreateDate, Amount) "
                    + "VALUES(?,?,?)";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            pStm.setString(1, dto.getUser().getEmail());
            pStm.setDate(2, new Date(System.currentTimeMillis()));
            pStm.setFloat(3, dto.getAmount());
            result = pStm.executeUpdate() > 0;
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public TransactionDTO getTransactionByID(int id) {
        TransactionDTO result = null;
        try {
            String sql = "SELECT U.Email, U.Name, T.CreateDate, T.Amount "
                    + "FROM [Tranasction] AS T, [User] AS U "
                    + "WHERE ID=? AND T.Email=U.Email";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            pStm.setInt(1, id);
            rs = pStm.executeQuery();
            if (rs.next()) {
                String email = rs.getString("Email");
                String name = rs.getString("Name");
                Date createDate = rs.getDate("CreateDate");
                float amount = rs.getFloat("Amount");
                result = new TransactionDTO(id, new UserDTO(email, name), createDate, amount);
            }
        } catch (Exception e) {
        }
        return result;
    }

    public ArrayList<TransactionDTO> getTransactionByEmail(String email) {
        ArrayList<TransactionDTO> result = new ArrayList<>();
        try {
            String sql = "SELECT ID, CreateDate, Amount "
                    + "FROM [Transaction] "
                    + "WHERE Email=? "
                    + "ORDER BY CreateDate DESC";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            pStm.setString(1, email);
            rs = pStm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                Date createDate = rs.getDate("CreateDate");
                float amount = rs.getFloat("Amount");
                TransactionDTO dto = new TransactionDTO(id, createDate, amount);
                result.add(dto);
            }
        } catch (Exception e) {
        }
        return result;
    }
     public ArrayList<TransactionDTO> getTransactionByEmail(String email, Date dateFrom, Date dateTo) {
        ArrayList<TransactionDTO> result = new ArrayList<>();
        try {
            String sql = "SELECT ID, CreateDate, Amount "
                    + "FROM [Transaction] "
                    + "WHERE Email=? AND CreateDate>=? AND CreateDate<=? "
                    + "ORDER BY CreateDate DESC";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            pStm.setString(1, email);
            pStm.setDate(2, dateFrom);
            pStm.setDate(3, dateTo);
            rs = pStm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                Date createDate = rs.getDate("CreateDate");
                float amount = rs.getFloat("Amount");
                TransactionDTO dto = new TransactionDTO(id, createDate, amount);
                result.add(dto);
            }
        } catch (Exception e) {
        }
        return result;
    }

    public TransactionDTO getLastTransaction(String email) {
        TransactionDTO result = null;
        try {
            String sql = "SELECT TOP(1) T.ID, U.Email, U.Name, T.CreateDate, T.Amount "
                    + "FROM [Transaction] AS T, [User] AS U "
                    + "WHERE T.Email=U.Email AND T.Email=? "
                    + "ORDER BY ID DESC";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            pStm.setString(1, email);
            rs = pStm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("ID");
                email = rs.getString("Email");
                String name = rs.getString("Name");
                Date createDate = rs.getDate("CreateDate");
                float amount = rs.getFloat("Amount");
                result = new TransactionDTO(id, new UserDTO(email, name), createDate, amount);
            }
        } catch (Exception e) {
        }
        return result;
    }

}
