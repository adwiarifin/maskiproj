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
public class Transaksi {
    
    public static final String[] TRANSAKSI_COLUMN_TITLE = {"Tanggal", "Pemesan", "Jenis Pesanan", "Total"};
    private final Connection conn;

    public Transaksi(Connection conn) {
        this.conn = conn;
    }
    
    public String[][] getListTransaksi() {
        String[][] result = null;
        try {
            String sql = "SELECT * FROM transaksi";
            PreparedStatement stmt = conn.prepareStatement(sql);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.last();
                int row = rs.getRow();
                result = new String[row][TRANSAKSI_COLUMN_TITLE.length];

                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    result[i][0] = rs.getString("tanggal");
                    result[i][1] = rs.getString("pemesan");
                    result[i][2] = rs.getString("jenis_pesanan");
                    result[i][3] = rs.getString("total");
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean insertTransaksi(String pemesan, String jenisPesanan, double total, String[][] detailTransaksi){
        boolean result = false;
        try {
            // start transaction
            conn.setAutoCommit(false);
            
            // insert to transaksi
            String sqlTransaksi = "INSERT INTO transaksi(pemesan, jenis_pesanan, total, tanggal) VALUES(?, ?, ?, NOW())";
            PreparedStatement stmtTransaksi = conn.prepareStatement(sqlTransaksi, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtTransaksi.setString(1, pemesan);
            stmtTransaksi.setString(2, jenisPesanan);
            stmtTransaksi.setDouble(3, total);
            stmtTransaksi.executeUpdate();
            
            // get transaksi insert id
            int transaksiId = 0;
            ResultSet rs = stmtTransaksi.getGeneratedKeys();
            if (rs.next()) {
                transaksiId = rs.getInt(1);
            }
            
            // insert to detail_transaksi
            for (String[] column : detailTransaksi) {
                String material = column[0];
                double luas = Double.parseDouble(column[1]);
                double hasil = Double.parseDouble(column[2]);
                double subtotal = Double.parseDouble(column[3]);
                
                String sqlDetailTransaksi = "INSERT INTO detail_transaksi(id_transaksi, material, luas, hasil, total) VALUES(?, ?, ?, ?, ?)";
                PreparedStatement stmtDetailTransaksi = conn.prepareStatement(sqlDetailTransaksi);
                
                stmtDetailTransaksi.setInt(1, transaksiId);
                stmtDetailTransaksi.setString(2, material);
                stmtDetailTransaksi.setDouble(3, luas);
                stmtDetailTransaksi.setDouble(4, hasil);
                stmtDetailTransaksi.setDouble(5, subtotal);
                
                stmtDetailTransaksi.executeUpdate();
            }
            
            // end transaction
            conn.commit();
            conn.setAutoCommit(true);
            
            // change return value
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return result;
    }
}
