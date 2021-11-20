package com.cun.rasp.model;

public class Food {
    private boolean isChecked = false;
    private String name;
    double bk;
    double tdn;
    double pk;
    double ca;
    double p;
    int harga;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
