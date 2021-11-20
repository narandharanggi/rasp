package com.cun.rasp.model;

public class BahanPakan {
    public static final String TABLE_NAME = "bahan_pakan";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMA_PAKAN = "nama_pakan";
    public static final String COLUMN_BK = "bk";
    public static final String COLUMN_TDN = "tdn";
    public static final String COLUMN_PK = "pk";
    public static final String COLUMN_CA = "ca";
    public static final String COLUMN_P = "p";
    public static final String COLUMN_HARGA = "harga";

    private int id;
    private String nama_pakan;
    private Double tdn;
    private Double bk;
    private Double pk;
    private Double ca;
    private Double p;
    private int harga;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAMA_PAKAN + " TEXT,"
                    + COLUMN_TDN + " REAL,"
                    + COLUMN_BK + " REAL,"
                    + COLUMN_PK + " REAL,"
                    + COLUMN_CA + " REAL,"
                    + COLUMN_P + " REAL,"
                    + COLUMN_HARGA + " INTEGER,"
                    + ")";

    public BahanPakan() {
    }

    public BahanPakan(int id, String nama_pakan, Double bk ,Double tdn, Double pk, Double ca, Double p,int harga) {
        this.id = id;
        this.nama_pakan = nama_pakan;
        this.bk = bk;
        this.tdn = tdn;
        this.pk = pk;
        this.ca = ca;
        this.p = p;
        this.harga = harga;
    }

    //getter
    public int getId() {
        return id;
    }

    public String getNamaPakan() {
        return nama_pakan;
    }

    public Double getBk() {
        return bk;
    }
    public Double getTdn() {
        return tdn;
    }

    public Double getPk() {
        return pk;
    }

    public Double getCa() {
        return ca;
    }

    public Double getP() {
        return p;
    }

    public int getHarga() {
        return harga;
    }


    //setter
    public void setId(int id) {
        this.id = id;
    }

    public void setNamaPakan(String nama_pakan) {
        this.nama_pakan = nama_pakan;
    }

    public void setBk(Double bk) {
        this.bk = bk;
    }

    public void setTdn(Double tdn) {
        this.tdn = tdn;
    }

    public void setPk(Double pk) {
        this.pk = pk;
    }

    public void setCa(Double ca) {
        this.ca = ca;
    }

    public void setP(Double p) {
        this.p = p;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
