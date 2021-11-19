/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import ransum.Koneksi;

/**
 *
 * @author ggnryr
 */
public class BahanPakan {
    Connection koneksi;
    double bk;
    double tdn;
    double pk;
    double ca;
    double p;
    int harga;
    
    public BahanPakan() throws SQLException{
        koneksi = new Koneksi().getConnection();
    }

    public double getBk() {
        return bk;
    }

    public void setBk(double bk) {
        this.bk = bk;
    }

    public double getTdn() {
        return tdn;
    }

    public void setTdn(double tdn) {
        this.tdn = tdn;
    }

    public double getPk() {
        return pk;
    }

    public void setPk(double pk) {
        this.pk = pk;
    }

    public double getCa() {
        return ca;
    }

    public void setCa(double ca) {
        this.ca = ca;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
    
        public DefaultTableModel bacaTabelBahanPakan(){
        Statement stm = null;
        String query = "SELECT * FROM bahan_pakan";
        String namaKolom[] = {"Nama Pakan","BK","TDN","PK","Ca","P","Harga"};
        DefaultTableModel dm = new DefaultTableModel(null, namaKolom);
        
        try {
            stm = koneksi.createStatement();
            ResultSet rs = stm.executeQuery(query);
            
            while (rs.next()) {                
                Object data[] = new Object[7];
                
                data[0] = rs.getString("nama_pakan");
                data[1] = rs.getDouble("bk");
                data[2] = rs.getDouble("tdn");
                data[3] = rs.getDouble("pk");
                data[4] = rs.getDouble("ca");
                data[5] = rs.getDouble("p");
                data[6] = rs.getInt("harga");
                
                dm.addRow(data);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getMessage();
        }
        
        return dm;
    }
    
    public List<BahanPakan> getAll(){
        Statement stm = null;
        String query = "SELECT bk, tdn, pk, ca, p, harga FROM bahan_pakan";
        List<BahanPakan> bp = new ArrayList<>();
        
        try {
            stm = koneksi.createStatement();
            ResultSet rs = stm.executeQuery(query);
            
            while (rs.next()) {                
                BahanPakan bahanPakan = new BahanPakan();
                bahanPakan.setBk(rs.getDouble(1));
                bahanPakan.setTdn(rs.getDouble(2));
                bahanPakan.setPk(rs.getDouble(3));
                bahanPakan.setCa(rs.getDouble(4));
                bahanPakan.setP(rs.getDouble(5));
                bahanPakan.setHarga(rs.getInt(6));
                
                bp.add(bahanPakan);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getMessage();
        }
        
        return bp;
    }
    
}
