package com.cun.rasp.model;

public class ProduksiSusu {
    public static final String TABLE_NAME = "produksi_susu";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PRODUKSI_SUSU = "produksi_susu";


    private int id;
    private int produksi_susu;


    // Create table SQL query
    public static final String CREATE_TABLE_PRODUKSI_SUSU =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PRODUKSI_SUSU + " INTEGER,"
                    + ")";

    public ProduksiSusu() {
    }

    public ProduksiSusu(int id, int produksi_susu) {
        this.id = id;
        this.produksi_susu = produksi_susu;
    }

    //getter
    public int getId() {
        return id;
    }

    public int getProduksiSusu() {
        return produksi_susu;
    }

    //setter
    public void setId(int id) {
        this.id = id;
    }

    public void setProduksiSusu(int produksi_susu) {
        this.produksi_susu = produksi_susu;
    }

}
