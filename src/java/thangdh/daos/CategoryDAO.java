/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thangdh.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import thangdh.dtos.CategoryDTO;
import thangdh.utils.DBUtil;

/**
 *
 * @author dhtha
 */
public class CategoryDAO {

    private Connection conn = null;
    private PreparedStatement pStm = null;
    private ResultSet rs = null;

    public CategoryDAO() {
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

    public ArrayList<CategoryDTO> getAllCategory() {
        ArrayList<CategoryDTO> result = new ArrayList<>();
        try {
            String sql = "SELECT ID, Name FROM Category";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            rs = pStm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                CategoryDTO dto = new CategoryDTO(id, name);
                result.add(dto);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }

    public CategoryDTO getCategoryByID(int id) {
        CategoryDTO result = null;
        try {
            String sql = "SELECT Name FROM Category WHERE ID=?";
            conn = DBUtil.getConnection();
            pStm = conn.prepareStatement(sql);
            pStm.setInt(1, id);
            rs = pStm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Name");
                result = new CategoryDTO(id, name);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }
}
