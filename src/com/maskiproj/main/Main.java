package com.maskiproj.main;

import com.maskiproj.form.Formula;
import com.maskiproj.form.Kalkulator;
import com.maskiproj.form.Laporan;
import com.maskiproj.model.Material;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Sasuke
 */
public class Main {
    
    private final Formula fFormula;
    private final Kalkulator fKalkulator;
    private final Laporan fLaporan;
    
    private final Material mMaterial;
    
    private final Connection conn;

    public Main() {
        conn = getConnection();
        
        mMaterial = new Material(conn);
        
        fFormula = new Formula(this);
        fKalkulator = new Kalkulator(this);
        fLaporan = new Laporan(this);
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            Main main = new Main();
            
            main.showFormula();
        });
    }
    
    public void showFormula() {
        fFormula.setVisible(true);
    }
    
    public void hideFormula() {
        fFormula.setVisible(false);
    }
    
    public void showKalkulator() {
        fKalkulator.setVisible(true);
    }
    
    public void hideKalkulator() {
        fKalkulator.setVisible(false);
    }
    
    public void showLaporan() {
        fLaporan.setVisible(true);
    }
    
    public void hideLaporan() {
        fLaporan.setVisible(false);
    }
    
    public Material getModelMaterial() {
        return mMaterial;
    }
    
    private Connection getConnection() {
        String server = "localhost";
        String database = "maskiproj";
        String username = "root";
        String password = "";
        String host = "jdbc:mysql://" + server + "/" + database;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection(host, username, password);
            return koneksi;
        } catch (ClassNotFoundException | SQLException ex) {
            // show error
            JOptionPane.showMessageDialog(null, "Tidak dapat menyambung ke database", "Koneksi gagal", JOptionPane.ERROR_MESSAGE);
            exit();
        }

        return null;
    }
    
    public void exit() {
        System.exit(0);
    }
    
}
