package com.cun.rasp.ga;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Individu {
    private double[] kromosom;
    private double fitness=0;
    private double penalti=0;
    private int harga=0;

    public Individu(){
        this.kromosom = new double[4];
    }

    public Individu inisialisasi(double batas){
        this.kromosom = new double[4];
        for (int i = 0; i < 4; i++) {
            Random r = new Random();
            double gene = r.nextDouble()*batas;
            this.setGene(i, gene);
            System.out.println("batas "+batas);
        }
        return this;
    }

    public double[] getKromosom(){
        return this.kromosom;
    }

    public int getPanjangKromosom(){
        return this.kromosom.length;
    }

    public void setGene(int batas, double gene){
        this.kromosom[batas] = gene;
    }

    public double getGene(int batas){
        return this.kromosom[batas];
    }

    public void setFitness (double fitness){
        this.fitness = fitness;
    }

    public double getFitness(){
        return this.fitness;
    }

    public double getPenalti() {
        return penalti;
    }

    public void setPenalti(double penalti) {
        this.penalti = penalti;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public static double roundFormat(double value) {
        int places = 4;
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public String toString(){
        String output="";
        for (int i = 0; i < this.kromosom.length; i++) {
            switch(i){
                case 0:
                    output+="Rumput Gajah : " + roundFormat(this.kromosom[i]) + " kg" + "\n";
                    break;
                case 1:
                    output+="Jagung : " + roundFormat(this.kromosom[i]) + " kg" + "\n";
                    break;
                case 2:
                    output+="Ampas Tahu : " + roundFormat(this.kromosom[i]) + " kg" + "\n";
                    break;
                case 3:
                    output+="Konsentrat : " + roundFormat(this.kromosom[i]) + " kg" + "\n";
                    break;
            }

        }
        return output;
    }
}