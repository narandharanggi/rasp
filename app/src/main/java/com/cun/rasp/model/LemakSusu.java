package com.cun.rasp.model;

public class LemakSusu {
    public static final String TABLE_NAME = "lemak_susu";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PERSEN_LEMAK = "persen_lemak";
    public static final String COLUMN_TDN = "tdn";
    public static final String COLUMN_PK = "pk";
    public static final String COLUMN_CA = "ca";
    public static final String COLUMN_P = "p";

    private int id;
    private Double persen_lemak;
    private Double tdn;
    private Double pk;
    private Double ca;
    private Double p;

    // Create table SQL query
    public static final String CREATE_TABLE_LEMAK_SUSU =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PERSEN_LEMAK + " REAL,"
                    + COLUMN_TDN + " REAL,"
                    + COLUMN_PK + " REAL,"
                    + COLUMN_CA + " REAL,"
                    + COLUMN_P + " REAL,"
                    + ")";

    public LemakSusu() {
    }

    public LemakSusu(int id, Double persen_lemak, Double tdn, Double pk, Double ca, Double p) {
        this.id = id;
        this.persen_lemak = persen_lemak;
        this.tdn = tdn;
        this.pk = pk;
        this.ca = ca;
        this.p = p;
    }

    //getter
    public int getId() {
        return id;
    }

    public Double getPersenLemak() {
        return persen_lemak;
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
    public void setId(int id) {
        this.id = id;
    }

    public void setPersenLemak(Double persen_lemak) {
        this.persen_lemak = persen_lemak;
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
