package com.maskiproj.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adwi Arifin <adwiarifin@gmail.com>
 */
public class Material {

    public static final String[] MATERIAL_COLUMN_TITLE = {"Material", "Formula"};
    private final Connection conn;

    public Material(Connection conn) {
        this.conn = conn;
    }

    public String[][] getListMaterial() {
        String[][] result = null;
        try {
            String sql = "SELECT m.name, m.formula FROM material m ORDER BY m.name";
            PreparedStatement stmt = conn.prepareStatement(sql);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.last();
                int row = rs.getRow();
                result = new String[row][2];

                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    result[i][0] = rs.getString("name");
                    result[i][1] = rs.getString("formula");
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Material.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int getMaterialId(String namaMaterial, String formulaMaterial) {
        int result = 0;
        try {
            String sql = "SELECT id FROM material WHERE name = ? AND formula = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, namaMaterial);
            stmt.setString(2, formulaMaterial);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.last();
                int row = rs.getRow();
                if (row > 0) {
                    result = rs.getInt("id");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Material.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean insertMaterial(String name, int formula) {
        boolean result = false;
        try {
            // insert to material
            String sql = "INSERT INTO material(name, formula) VALUES(?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, name);
            stmt.setInt(2, formula);

            stmt.executeUpdate();

            // change result value
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(Material.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean updateMaterial(int id, String name, int formula) {
        boolean result = false;
        try {
            String sql = "UPDATE material SET name = ?, formula = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, name);
            stmt.setInt(2, formula);
            stmt.setInt(3, id);
            
            stmt.executeUpdate();
            
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(Material.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
