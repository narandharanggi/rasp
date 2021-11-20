package com.cun.rasp.model;

public class DetailPakan {
    public static final String TABLE_NAME = "detail_pakan";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SAPI = "sapi";
    public static final String COLUMN_BAHAN_PAKAN = "bahan_pakan";

    private int id;
    private int sapi;
    private int bahan_pakan;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_SAPI + " INTEGER,"
                    + COLUMN_BAHAN_PAKAN + " INTEGER,"
                    + "FOREIGN KEY (" + COLUMN_SAPI + ") REFERENCES " + "detail_sapi" + "(id),"
                    + "FOREIGN KEY (" + COLUMN_BAHAN_PAKAN + ") REFERENCES " + "bahan_pakan" + "(id),"
                    + ")";


    public DetailPakan() {
    }

    public DetailPakan(int id, int sapi, int bahan_pakan) {
        this.id = id;
        this.sapi = sapi;
        this.bahan_pakan = bahan_pakan;
    }

    //getter
    public int getId() {
        return id;
    }

    public int getSapi() {
        return sapi;
    }

    public int getBahanPakan() {
        return bahan_pakan;
    }

    //setter
    public void setId(int id) {
        this.id = id;
    }

    public void setSapi(int sapi) {
        this.sapi = sapi;
    }

    public void setBahanPakan(int bahan_pakan) {
        this.bahan_pakan = bahan_pakan;
    }

}
