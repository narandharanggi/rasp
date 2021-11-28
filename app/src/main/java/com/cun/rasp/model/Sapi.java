package com.cun.rasp.model;

public class Sapi {
    public static final String TABLE_NAME = "sapi";
    public static final String COLUMN_ID = "kode";
    public static final String COLUMN_BOBOT = "bobot";
    public static final String COLUMN_PRODUKSI_SUSU = "produksi_susu";
    public static final String COLUMN_BK = "bk";

    private int kode;
    private int bobot;
    private int produksi_susu;
    private Double bk;

    // Create table SQL query
    public static final String CREATE_TABLE_SAPI =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_BOBOT + " INTEGER,"
                    + COLUMN_PRODUKSI_SUSU + " INTEGER,"
                    + COLUMN_BK + " REAL,"
                    + "FOREIGN KEY (" + COLUMN_BOBOT + ") REFERENCES " + "bobot_sapi" + "(kode),"
                    + "FOREIGN KEY (" + COLUMN_PRODUKSI_SUSU + ") REFERENCES " + "produksi_susu" + "(kode)"
                    + ")";


    public Sapi() {
    }

    public Sapi(int kode, int bobot, int produksi_susu, Double bk) {
        this.kode = kode;
        this.bobot = bobot;
        this.produksi_susu = produksi_susu;
        this.bk = bk;
    }

    //getter
    public int getId() {
        return kode;
    }

    public int getBobot() {
        return bobot;
    }

    public int getProduksiSusu() {
        return produksi_susu;
    }

    public Double getBk() {
        return bk;
    }

    //setter
    public void setId(int kode) {
        this.kode = kode;
    }

    public void setBobot(int bobot) {
        this.bobot = bobot;
    }

    public void setProduksiSusu(int produksi_susu) {
        this.produksi_susu = produksi_susu;
    }

    public void setBk(Double bk) {
        this.bk = bk;
    }

}
