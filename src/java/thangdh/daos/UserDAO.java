/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thangdh.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import thangdh.dtos.UserDTO;
import thangdh.utils.DBUtil;

/**
 *
 * @author dhtha
 */
public class UserDAO {

    private Connection conn = null;
    private PreparedStatement pStm = null;
    private ResultSet rs = null;

    public UserDAO() {
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

    public boolean isExistsEmail(String email) {
        boolean result = false;
        try {
            String sql = "SELECT Name FROM [User] "
                    + "WHERE Email=?";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            pStm.setString(1, email);
            rs = pStm.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public UserDTO checkLogin(String email, String password) {
        UserDTO result = null;
        try {
            String sql = "SELECT Email, Name, Password, RoleID, Status FROM [User] WHERE Email=? AND Password=?";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            pStm.setString(1, email);
            pStm.setString(2, password);
            rs = pStm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Name");
                String roleID = rs.getString("RoleID");
                String Status = rs.getString("Status");
                result = new UserDTO(email, name, password, roleID, Status);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }
     public UserDTO getUserByEmail(String email) {
        UserDTO result = null;
        try {
            String sql = "SELECT Email, Name, Password, RoleID, Status FROM [User] WHERE Email=?";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            pStm.setString(1, email);
            rs = pStm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Name");
                String roleID = rs.getString("RoleID");
                String Status = rs.getString("Status");
                result = new UserDTO(email, name, "****", roleID, Status);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean createUser(UserDTO dto) {
        boolean result = false;
        try {
            String sql = "INSERT INTO [dbo].[User]"
                    + "           ([Email]"
                    + "           ,[Name]"
                    + "           ,[Password]"
                    + "           ,[RoleID]"
                    + "           ,[Status])"
                    + "     VALUES(?,?,?,?,?)";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            pStm.setString(1, dto.getEmail());
            pStm.setString(2, dto.getName());
            pStm.setString(3, dto.getPassword());
            pStm.setString(4, dto.getRoleID());
            pStm.setString(5, dto.getStatus());
            result = pStm.executeUpdate() > 0;
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }

}
