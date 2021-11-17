/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ggnryr
 */
public class FrameAlgen extends javax.swing.JFrame {

    /**
     * Creates new form FrameAlgen
     */
    CardLayout cl;
    
    public FrameAlgen() {
        initComponents();
        
        this.setSize(1152, 700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        cl = (CardLayout) jPanel5.getLayout();
    }
    
    public void tabelBahanPakan(DefaultTableModel dm){
        jTable1.setModel(dm);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
    }
    
    public void tabelAlgoritma(DefaultTableModel dm){
        jTable2.setModel(dm);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(100);
    }
    
    public int getLaktasi(){
        return jComboBox1.getSelectedIndex();        
    }
    
    
    public double getBobot(){
        double bobot = Double.parseDouble(jTextField1.getText());
        return bobot;
    }
    
    public double getProduksi(){
        double produksi = Double.parseDouble(jTextField2.getText());
        return produksi;
    }
    
    public double getLemakSusu(){
        double ls = Double.parseDouble(jComboBox2.getSelectedItem().toString());
        return ls;
    }
    
    public double getPerubahanBB(){
        double bb = Double.parseDouble(jTextField3.getText());
        return bb;
    }
    
    public void setBK(String bk){
        jLabel13.setText(bk);
    }
    
    public void setTDN(String bk){
        jLabel14.setText(bk);
    }
    
    public void setPK(String bk){
        jLabel15.setText(bk);
    }
   
    public void setCa(String bk){
        jLabel16.setText(bk);
    }
    
    public void setP(String bk){
        jLabel17.setText(bk);
    }
    
    public void falseEdit(){
//        jTextField7.setEditable(false);
//        jTextField8.setEditable(false);
//        jTextField9.setEditable(false);
//        jTextField10.setEditable(false);
//        jButton2.setEnabled(false);
    }
    
    public void falseDataSapi(){
        jTextField1.setEditable(false);
        jTextField2.setEditable(false);
        jTextField3.setEditable(false);
        jComboBox1.setEnabled(false);
        jComboBox2.setEnabled(false);
        jButton1.setEnabled(false);
    }
    
    public void inputSapiPerah (ActionListener a){
        jButton1.addActionListener(a);
    }
    
    public int getUkuranPopulasi(){
        int populasi = Integer.parseInt(jTextField7.getText());
        return populasi;
    }
    
    public int getGenerasi(){
        int generasi = Integer.parseInt(jTextField8.getText());
        return generasi;
    }
    
    public double getCrossoverRate(){
        double cr = Double.parseDouble(jTextField9.getText());
        return cr;
    }
    
    public double getMutationRate(){
        double mr = Double.parseDouble(jTextField10.getText());
        return mr;
    }
    
    public void inputAlgen(ActionListener a){
        jButton2.addActionListener(a);
    }
    
    public void setGenerasi(ActionListener a){
        jComboBox5.addActionListener(a);
    }
    
//    public void setIndexGenerasi(String index){
//        jComboBox5.addItem(index);
//    }
    
    public int getIndexGenerasi(){
        return jComboBox5.getSelectedIndex();
    }
    
    public void modelCombo(Object model[]){
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(model));
    }
    
    public void setRansum(String isi){
        jTextArea1.setText(isi);
        jTextArea1.setEditable(false);
    }
    
    public String getJudul(){
        return jTextField11.getText();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        sapiPanel = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        background1 = new javax.swing.JLabel();
        algenPanel = new javax.swing.JPanel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        background2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 245, 236));
        setMaximumSize(new java.awt.Dimension(1152, 700));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1152, 700));

        jSplitPane1.setBackground(new java.awt.Color(255, 245, 236));
        jSplitPane1.setBorder(null);
        jSplitPane1.setDividerSize(0);

        jPanel4.setBackground(new java.awt.Color(255, 245, 236));
        jPanel4.setPreferredSize(new java.awt.Dimension(213, 700));

        jPanel1.setLayout(null);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/exit.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });
        jPanel1.add(jLabel5);
        jLabel5.setBounds(40, 20, 70, 60);

        jPanel3.setOpaque(false);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/button1.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });
        jPanel3.add(jLabel2);

        jLabel4.setPreferredSize(new java.awt.Dimension(64, 48));
        jPanel3.add(jLabel4);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/button2.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });
        jPanel3.add(jLabel3);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(50, 260, 110, 180);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/leftside.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 213, 700);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 245, 236));
        jPanel5.setLayout(new java.awt.CardLayout());

        sapiPanel.setBackground(new java.awt.Color(255, 245, 236));
        sapiPanel.setPreferredSize(new java.awt.Dimension(939, 700));
        sapiPanel.setLayout(null);

        jComboBox1.setBackground(new java.awt.Color(250, 223, 200));
        jComboBox1.setForeground(new java.awt.Color(67, 51, 37));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", ">=3" }));
        sapiPanel.add(jComboBox1);
        jComboBox1.setBounds(200, 100, 130, 30);

        jTextField3.setBackground(new java.awt.Color(250, 223, 200));
        jTextField3.setForeground(new java.awt.Color(67, 51, 37));
        jTextField3.setText("0.258");
        jTextField3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField3.setSelectionColor(new java.awt.Color(255, 255, 255));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        sapiPanel.add(jTextField3);
        jTextField3.setBounds(200, 310, 130, 30);

        jTextField1.setBackground(new java.awt.Color(250, 223, 200));
        jTextField1.setForeground(new java.awt.Color(67, 51, 37));
        jTextField1.setText("450");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField1.setSelectionColor(new java.awt.Color(255, 255, 255));
        sapiPanel.add(jTextField1);
        jTextField1.setBounds(200, 150, 130, 30);

        jTextField2.setBackground(new java.awt.Color(250, 223, 200));
        jTextField2.setForeground(new java.awt.Color(67, 51, 37));
        jTextField2.setText("13");
        jTextField2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField2.setSelectionColor(new java.awt.Color(255, 255, 255));
        sapiPanel.add(jTextField2);
        jTextField2.setBounds(200, 200, 130, 30);

        jComboBox2.setBackground(new java.awt.Color(250, 223, 200));
        jComboBox2.setForeground(new java.awt.Color(67, 51, 37));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3.0", "3.5", "4.0", "4.5" }));
        jComboBox2.setSelectedIndex(1);
        sapiPanel.add(jComboBox2);
        jComboBox2.setBounds(200, 260, 130, 30);

        jButton1.setBackground(new java.awt.Color(67, 51, 37));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Input");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        sapiPanel.add(jButton1);
        jButton1.setBounds(240, 360, 90, 30);

        jLabel13.setPreferredSize(new java.awt.Dimension(143, 39));
        sapiPanel.add(jLabel13);
        jLabel13.setBounds(560, 149, 80, 30);

        jLabel14.setPreferredSize(new java.awt.Dimension(100, 39));
        sapiPanel.add(jLabel14);
        jLabel14.setBounds(560, 200, 80, 39);

        jLabel15.setPreferredSize(new java.awt.Dimension(100, 39));
        sapiPanel.add(jLabel15);
        jLabel15.setBounds(560, 260, 80, 30);

        jLabel16.setPreferredSize(new java.awt.Dimension(100, 39));
        sapiPanel.add(jLabel16);
        jLabel16.setBounds(800, 149, 80, 30);

        jLabel17.setPreferredSize(new java.awt.Dimension(100, 39));
        sapiPanel.add(jLabel17);
        jLabel17.setBounds(800, 200, 80, 39);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setOpaque(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setSelectionBackground(new java.awt.Color(250, 223, 200));
        jScrollPane1.setViewportView(jTable1);

        sapiPanel.add(jScrollPane1);
        jScrollPane1.setBounds(20, 540, 870, 90);

        background1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/layout1.png"))); // NOI18N
        sapiPanel.add(background1);
        background1.setBounds(0, 0, 939, 700);

        jPanel5.add(sapiPanel, "sapiPanel");

        algenPanel.setBackground(new java.awt.Color(255, 245, 236));
        algenPanel.setPreferredSize(new java.awt.Dimension(939, 700));
        algenPanel.setLayout(null);

        jComboBox5.setBackground(new java.awt.Color(67, 51, 37));
        jComboBox5.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox5.setToolTipText("");
        jComboBox5.setPreferredSize(new java.awt.Dimension(28, 30));
        algenPanel.add(jComboBox5);
        jComboBox5.setBounds(798, 30, 90, 30);

        jTextArea1.setBackground(new java.awt.Color(255, 245, 236));
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(67, 51, 37));
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextArea1.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextArea1.setSelectionColor(new java.awt.Color(250, 223, 200));
        jScrollPane3.setViewportView(jTextArea1);

        algenPanel.add(jScrollPane3);
        jScrollPane3.setBounds(20, 460, 430, 210);

        jScrollPane2.setBorder(null);
        jScrollPane2.setOpaque(false);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.setGridColor(new java.awt.Color(255, 255, 255));
        jTable2.setSelectionBackground(new java.awt.Color(250, 223, 200));
        jScrollPane2.setViewportView(jTable2);

        algenPanel.add(jScrollPane2);
        jScrollPane2.setBounds(360, 100, 530, 280);

        jButton2.setBackground(new java.awt.Color(67, 51, 37));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Input");
        jButton2.setPreferredSize(new java.awt.Dimension(90, 30));
        algenPanel.add(jButton2);
        jButton2.setBounds(210, 330, 90, 30);

        jTextField7.setBackground(new java.awt.Color(250, 223, 200));
        jTextField7.setForeground(new java.awt.Color(67, 51, 37));
        jTextField7.setText("150");
        jTextField7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField7.setDisabledTextColor(new java.awt.Color(250, 223, 200));
        jTextField7.setPreferredSize(new java.awt.Dimension(130, 30));
        algenPanel.add(jTextField7);
        jTextField7.setBounds(170, 110, 130, 30);

        jTextField8.setBackground(new java.awt.Color(250, 223, 200));
        jTextField8.setForeground(new java.awt.Color(67, 51, 37));
        jTextField8.setText("200");
        jTextField8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField8.setDisabledTextColor(new java.awt.Color(250, 223, 200));
        jTextField8.setPreferredSize(new java.awt.Dimension(130, 30));
        algenPanel.add(jTextField8);
        jTextField8.setBounds(170, 170, 130, 30);

        jTextField9.setBackground(new java.awt.Color(250, 223, 200));
        jTextField9.setForeground(new java.awt.Color(67, 51, 37));
        jTextField9.setText("0.2");
        jTextField9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField9.setDisabledTextColor(new java.awt.Color(250, 223, 200));
        jTextField9.setPreferredSize(new java.awt.Dimension(130, 30));
        algenPanel.add(jTextField9);
        jTextField9.setBounds(170, 230, 130, 30);

        jTextField11.setBackground(new java.awt.Color(250, 223, 200));
        jTextField11.setForeground(new java.awt.Color(67, 51, 37));
        jTextField11.setText("0.2");
        jTextField11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField11.setDisabledTextColor(new java.awt.Color(250, 223, 200));
        jTextField11.setPreferredSize(new java.awt.Dimension(130, 30));
        algenPanel.add(jTextField11);
        jTextField11.setBounds(30, 330, 130, 30);

        jTextField10.setBackground(new java.awt.Color(250, 223, 200));
        jTextField10.setForeground(new java.awt.Color(67, 51, 37));
        jTextField10.setText("0.2");
        jTextField10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField10.setDisabledTextColor(new java.awt.Color(250, 223, 200));
        jTextField10.setPreferredSize(new java.awt.Dimension(130, 30));
        algenPanel.add(jTextField10);
        jTextField10.setBounds(170, 280, 130, 30);

        background2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/layout2.png"))); // NOI18N
        background2.setText("jLabel1");
        background2.setPreferredSize(new java.awt.Dimension(939, 700));
        algenPanel.add(background2);
        background2.setBounds(0, 0, 940, 700);

        jPanel5.add(algenPanel, "algenPanel");

        jSplitPane1.setRightComponent(jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        cl.show(jPanel5,"sapiPanel");
    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        cl.show(jPanel5,"algenPanel");
    }//GEN-LAST:event_jLabel3MousePressed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        System.exit(0);
    }//GEN-LAST:event_jLabel5MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameAlgen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameAlgen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameAlgen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameAlgen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameAlgen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel algenPanel;
    private javax.swing.JLabel background1;
    private javax.swing.JLabel background2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel sapiPanel;
    // End of variables declaration//GEN-END:variables
}
