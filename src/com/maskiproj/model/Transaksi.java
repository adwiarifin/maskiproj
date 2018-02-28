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
    
    private final Connection conn;

    public Transaksi(Connection conn) {
        this.conn = conn;
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
                double panjang = Double.parseDouble(column[1]);
                double lebar = Double.parseDouble(column[2]);
                double harga = Double.parseDouble(column[3]);
                
                String sqlDetailTransaksi = "INSERT INTO detail_transaksi(id_transaksi, material, panjang, lebar, harga) VALUES(?, ?, ?, ?, ?)";
                PreparedStatement stmtDetailTransaksi = conn.prepareStatement(sqlDetailTransaksi);
                
                stmtDetailTransaksi.setInt(1, transaksiId);
                stmtDetailTransaksi.setString(2, material);
                stmtDetailTransaksi.setDouble(3, panjang);
                stmtDetailTransaksi.setDouble(4, lebar);
                stmtDetailTransaksi.setDouble(5, harga);
                
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
