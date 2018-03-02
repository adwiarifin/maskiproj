package com.maskiproj.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adwi Arifin <adwiarifin@gmail.com>
 */
public class Transaksi {

    public static final String[] TRANSAKSI_COLUMN_TITLE = {"ID", "Tanggal", "Pemesan", "Jenis Pesanan", "Total"};
    public static final String[] TRANSAKSI_DETAIL_COLUMN_TITLE = {"Material", "Luas", "Hasil", "Subtotal"};
    private final Connection conn;

    public Transaksi(Connection conn) {
        this.conn = conn;
    }

    public String[][] getListTransaksiForSupplier() {
        String[][] result = null;
        try {
            String sql = "SELECT id, tanggal, pemesan, jenis_pesanan, total FROM transaksi ORDER BY id DESC";
            PreparedStatement stmt = conn.prepareStatement(sql);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.last();
                int row = rs.getRow();
                result = new String[row][TRANSAKSI_COLUMN_TITLE.length];

                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    result[i][0] = rs.getString("id");
                    result[i][1] = rs.getString("tanggal");
                    result[i][2] = rs.getString("pemesan");
                    result[i][3] = rs.getString("jenis_pesanan");
                    result[i][4] = rs.getString("total");
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public String[][] getListTransaksiForCustomer() {
        String[][] result = null;
        try {
            String sql = "SELECT id, tanggal, pemesan, jenis_pesanan, ROUND(total*1.15,2) as total FROM transaksi  ORDER BY id DESC";
            PreparedStatement stmt = conn.prepareStatement(sql);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.last();
                int row = rs.getRow();
                result = new String[row][TRANSAKSI_COLUMN_TITLE.length];

                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    result[i][0] = rs.getString("id");
                    result[i][1] = rs.getString("tanggal");
                    result[i][2] = rs.getString("pemesan");
                    result[i][3] = rs.getString("jenis_pesanan");
                    result[i][4] = rs.getString("total");
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public String[][] getListDetailTransaksiForSupplier(String id) {
        String[][] result = null;
        try {
            String sql = "SELECT material, luas, hasil, total FROM detail_transaksi WHERE id_transaksi = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.last();
                int row = rs.getRow();
                result = new String[row][TRANSAKSI_DETAIL_COLUMN_TITLE.length];

                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    result[i][0] = rs.getString("material");
                    result[i][1] = rs.getString("luas");
                    result[i][2] = rs.getString("hasil");
                    result[i][3] = rs.getString("total");
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public String[][] getListDetailTransaksiForCustomer(String id) {
        String[][] result = null;
        try {
            String sql = "SELECT material, luas, ROUND(hasil*1.15,4) as hasil, ROUND(total*1.15,2) as total FROM detail_transaksi WHERE id_transaksi = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.last();
                int row = rs.getRow();
                result = new String[row][TRANSAKSI_DETAIL_COLUMN_TITLE.length];

                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    result[i][0] = rs.getString("material");
                    result[i][1] = rs.getString("luas");
                    result[i][2] = rs.getString("hasil");
                    result[i][3] = rs.getString("total");
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean insertTransaksi(String pemesan, String jenisPesanan, double total, String[][] detailTransaksi) {
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
                
                // get double value
                NumberFormat format = NumberFormat.getInstance();
                double luas = 0;
                double hasil = 0;
                double subtotal = 0;
                try {
                    luas = format.parse(column[1]).doubleValue();
                    hasil = format.parse(column[2]).doubleValue();
                    subtotal = format.parse(column[3]).doubleValue();
                } catch (ParseException ex) {
                    conn.rollback();
                }

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
