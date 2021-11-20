package com.cun.rasp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SapiPerah {
//    private Connection koneksi;
//
//    public SapiPerah() throws SQLException{
//        koneksi = new Koneksi().getConnection();
//    }
//
//    public boolean bobot(int bobot, String keterangan){
//        PreparedStatement ps = null;
//        String query = "SELECT bobot FROM bobot_sapi WHERE bobot=?";
//        String query2 = "SELECT bobot_sapi.bobot FROM bobot_sapi INNER JOIN sapi ON bobot_sapi.kode = sapi.bobot WHERE bobot_sapi.bobot =?";
//        boolean hasil = false;
//
//        try {
//            if (keterangan.equals("bk")) {
//                ps = koneksi.prepareStatement(query2);
//            } else {
//                ps = koneksi.prepareStatement(query);
//            }
//
//            ps.setInt(1, bobot);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                hasil = true;
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            ex.getMessage();
//        }
//
//        return hasil;
//    }
//
//    public boolean produksi (int produksi){
//        PreparedStatement ps = null;
//        String query = "SELECT produksi_susu FROM produksi_susu WHERE produksi_susu=?";
//        boolean hasil = false;
//
//        try {
//            ps = koneksi.prepareStatement(query);
//            ps.setDouble(1, produksi);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                hasil = true;
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            ex.getMessage();
//        }
//
//        return hasil;
//    }
//
//    public List<Integer> getAllBobot(){
//        Statement stm = null;
//        String query = "SELECT bobot FROM bobot_sapi";
//        List<Integer> bb = new ArrayList<>();
//
//        try {
//            stm = koneksi.createStatement();
//            ResultSet rs = stm.executeQuery(query);
//
//            while (rs.next()) {
//                int bobot = rs.getInt(1);
//                bb.add(bobot);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            ex.getMessage();
//        }
//
//        return bb;
//    }
//
//    public List<Integer> getBobotBk(){
//        Statement stm = null;
//        String query = "SELECT bobot_sapi.bobot FROM bobot_sapi INNER JOIN sapi ON bobot_sapi.kode = sapi.bobot GROUP BY bobot_sapi.bobot";
//        List<Integer> bb = new ArrayList<>();
//
//        try {
//            stm = koneksi.createStatement();
//            ResultSet rs = stm.executeQuery(query);
//
//            while (rs.next()) {
//                int bobot = rs.getInt(1);
//                bb.add(bobot);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            ex.getMessage();
//        }
//
//        return bb;
//    }
//
//    public List<Integer> getAllPs(){
//        Statement stm = null;
//        String query = "SELECT produksi_susu FROM produksi_susu";
//        List<Integer> ps = new ArrayList<>();
//
//        try {
//            stm = koneksi.createStatement();
//            ResultSet rs = stm.executeQuery(query);
//
//            while (rs.next()) {
//                int produksi = rs.getInt(1);
//                ps.add(produksi);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            ex.getMessage();
//        }
//
//        return ps;
//    }
//
//    public double[] getKebutuhanHidup(int bobot){
//        PreparedStatement ps = null;
//        String query = "SELECT tdn, pk, ca, p FROM bobot_sapi WHERE bobot=?";
//        double[] kh = new double[4];
//
//        try {
//            ps = koneksi.prepareStatement(query);
//            ps.setDouble(1, bobot);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                kh[0] = rs.getDouble(1);
//                kh[1] = rs.getDouble(2);
//                kh[2] = rs.getDouble(3);
//                kh[3] = rs.getDouble(4);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            ex.getMessage();
//        }
//
//        return kh;
//    }
//
//    public double[] getKebutuhanLemak(double lemak){
//        PreparedStatement ps = null;
//        String query = "SELECT tdn, pk, ca, p FROM lemak_susu WHERE persen_lemak=?";
//        double[] kebLemak = new double[4];
//
//        try {
//            ps = koneksi.prepareStatement(query);
//            ps.setDouble(1, lemak);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                kebLemak[0] = rs.getDouble(1);
//                kebLemak[1] = rs.getDouble(2);
//                kebLemak[2] = rs.getDouble(3);
//                kebLemak[3] = rs.getDouble(4);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            ex.getMessage();
//        }
//
//        return kebLemak;
//    }
//
//    public double getBk(int bobot, int produksi){
//        PreparedStatement ps = null;
//        String query = "SELECT bk FROM sapi "
//                    + "JOIN bobot_sapi ON sapi.bobot = bobot_sapi.kode "
//                    + "JOIN produksi_susu ON sapi.produksi_susu = produksi_susu.kode "
//                    + "WHERE bobot_sapi.bobot=? AND produksi_susu.produksi_susu=? ";
//        double bk = 0;
//
//        try {
//            ps = koneksi.prepareStatement(query);
//            ps.setInt(1, bobot);
//            ps.setInt(2, produksi);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                bk = rs.getDouble(1);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            ex.getMessage();
//        }
//
//        return bk;
//    }
//
//    public double[] perBb(String keterangan){
//        PreparedStatement ps = null;
//        String query = "SELECT tdn,pk FROM perBB WHERE keterangan=?";
//        double[] perBb = new double[2];
//
//        try {
//            ps = koneksi.prepareStatement(query);
//            ps.setString(1, keterangan);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                perBb[0] = rs.getDouble(1);
//                perBb[1] = rs.getDouble(2);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            ex.getMessage();
//        }
//
//        return perBb;
//    }
}
