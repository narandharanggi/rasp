package com.cun.rasp.model;

public class BobotSapi {
    public static final String TABLE_NAME = "bobot_sapi";

    public static final String COLUMN_ID = "kode";
    public static final String COLUMN_BOBOT = "bobot";
    public static final String COLUMN_TDN = "tdn";
    public static final String COLUMN_PK = "pk";
    public static final String COLUMN_CA = "ca";
    public static final String COLUMN_P = "p";

    private int kode;
    private Double bobot;
    private Double tdn;
    private Double pk;
    private Double ca;
    private Double p;


    // Create table SQL query
    public static final String CREATE_TABLE_BOBOT_SAPI =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_BOBOT + " REAL,"
                    + COLUMN_TDN + " REAL,"
                    + COLUMN_PK + " REAL,"
                    + COLUMN_CA + " REAL,"
                    + COLUMN_P + " REAL"
                    + ")";

    public BobotSapi() {
    }

    public BobotSapi(int kode, Double bobot, Double tdn, Double pk, Double ca, Double p) {
        this.kode = kode;
        this.bobot = bobot;
        this.tdn = tdn;
        this.pk = pk;
        this.ca = ca;
        this.p = p;
    }

    //getter
    public int getId() {
        return kode;
    }

    public Double getBobot() {
        return bobot;
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


    //setter
    public void setId(int kode) {
        this.kode = kode;
    }

    public void setBobot(Double bobot) {
        this.bobot = bobot;
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

}
