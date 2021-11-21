package com.cun.rasp.model;

public class perBB {
    public static final String TABLE_NAME = "perBB";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_KETERANGAN = "keterangan";
    public static final String COLUMN_TDN = "tdn";
    public static final String COLUMN_PK = "pk";

    private int id;
    private String keterangan;
    private Double tdn;
    private Double pk;

    // Create table SQL query
    public static final String CREATE_TABLE_PERBB =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_KETERANGAN + " TEXT,"
                    + COLUMN_TDN + " REAL,"
                    + COLUMN_PK + " REAL,"
                    + ")";

    public perBB() {
    }

    public perBB(int id, String keterangan, Double tdn, Double pk) {
        this.id = id;
        this.keterangan = keterangan;
        this.tdn = tdn;
        this.pk = pk;
    }

    //getter
    public int getId() {
        return id;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public Double getTdn() {
        return tdn;
    }

    public Double getPk() {
        return pk;
    }

    //setter
    public void setId(int id) {
        this.id = id;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public void setTdn(Double tdn) {
        this.tdn = tdn;
    }

    public void setPk(Double pk) {
        this.pk = pk;
    }

}
