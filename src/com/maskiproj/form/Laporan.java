package com.maskiproj.form;

import com.maskiproj.main.Main;
import com.maskiproj.model.Transaksi;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * @author fauzi
 * afnan13579@gmail.com
 */
public class Laporan extends javax.swing.JFrame {

    private final Main main;
    private final Transaksi transaksi;
    
    DefaultTableCellRenderer rightRenderer;
    DefaultTableCellRenderer centerRenderer;
    
    /**
     * Creates new form Laporan
     */
    public Laporan(Main main) {
        this.main = main;
        this.transaksi = main.getModelTransaksi();
        
        initComponents();
        initSelectionListener();
        
        rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    }
    
    public void loadLaporan() {
        String[][] dataSupplier = transaksi.getListTransaksiForSupplier();
        DefaultTableModel modelSupplier = new DefaultTableModel(dataSupplier, Transaksi.TRANSAKSI_COLUMN_TITLE);
        tbSupplier.setModel(modelSupplier);
        tbSupplier.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbSupplier.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        
        String[][] dataCustomer = transaksi.getListTransaksiForCustomer();
        DefaultTableModel modelCustomer = new DefaultTableModel(dataCustomer, Transaksi.TRANSAKSI_COLUMN_TITLE);
        tbCustomer.setModel(modelCustomer);
        tbCustomer.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbCustomer.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
    }
    
    private void loadSupplierDetail(String id) {
        String[][] data = transaksi.getListDetailTransaksiForSupplier(id);
        DefaultTableModel model = new DefaultTableModel(data, Transaksi.TRANSAKSI_DETAIL_COLUMN_TITLE);
        tbSupplierDetail.setModel(model);
        tbSupplierDetail.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        tbSupplierDetail.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        tbSupplierDetail.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
    }
    
    private void loadCustomerDetail(String id) {
        String[][] data = transaksi.getListDetailTransaksiForCustomer(id);
        DefaultTableModel model = new DefaultTableModel(data, Transaksi.TRANSAKSI_DETAIL_COLUMN_TITLE);
        tbCustomerDetail.setModel(model);
        tbCustomerDetail.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        tbCustomerDetail.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        tbCustomerDetail.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
    }
    
    private void initSelectionListener() {
        tbSupplier.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int rowNumber = tbSupplier.getSelectedRow();
                if (rowNumber >= 0) {
                    String id = tbSupplier.getValueAt(rowNumber, 0).toString();
                    loadSupplierDetail(id);
                }
            }
        });
        
        tbCustomer.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int rowNumber = tbCustomer.getSelectedRow();
                if (rowNumber >= 0) {
                    String id = tbCustomer.getValueAt(rowNumber, 0).toString();
                    loadCustomerDetail(id);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSupplierDetail = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSupplier = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCustomer = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbCustomerDetail = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miKalkulator = new javax.swing.JMenuItem();
        miFormula = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        miKeluar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("High Tower Text", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 255, 51));
        jLabel1.setText("MASKI MAGIC TOOLS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(tbSupplierDetail);

        jScrollPane1.setViewportView(tbSupplier);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Supplier", jPanel2);

        jScrollPane2.setViewportView(tbCustomer);

        jScrollPane4.setViewportView(tbCustomerDetail);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Customer", jPanel3);

        jMenu1.setText("Form");

        miKalkulator.setText("Kalkulator");
        miKalkulator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miKalkulatorActionPerformed(evt);
            }
        });
        jMenu1.add(miKalkulator);

        miFormula.setText("Formula");
        miFormula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miFormulaActionPerformed(evt);
            }
        });
        jMenu1.add(miFormula);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Menu");

        miKeluar.setText("Keluar");
        miKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miKeluarActionPerformed(evt);
            }
        });
        jMenu2.add(miKeluar);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void miKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miKeluarActionPerformed
        main.exit();
    }//GEN-LAST:event_miKeluarActionPerformed

    private void miKalkulatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miKalkulatorActionPerformed
        main.hideLaporan();
        main.showKalkulator();
    }//GEN-LAST:event_miKalkulatorActionPerformed

    private void miFormulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miFormulaActionPerformed
        main.hideLaporan();
        main.showFormula();
    }//GEN-LAST:event_miFormulaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem miFormula;
    private javax.swing.JMenuItem miKalkulator;
    private javax.swing.JMenuItem miKeluar;
    private javax.swing.JTable tbCustomer;
    private javax.swing.JTable tbCustomerDetail;
    private javax.swing.JTable tbSupplier;
    private javax.swing.JTable tbSupplierDetail;
    // End of variables declaration//GEN-END:variables
}
