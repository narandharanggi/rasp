/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritma;

import controller.Main;
import java.util.Random;

/**
 *
 * @author ggnryr
 */
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
    
    public String toString(){
        String output="";
        for (int i = 0; i < this.kromosom.length; i++) {
            switch(i){
                case 0:
                  output+="Rumput Gajah : " + Main.roundFormat(this.kromosom[i]) + " kg" + "\n";
                  break;
                case 1:
                  output+="Jagung : " + Main.roundFormat(this.kromosom[i]) + " kg" + "\n"; 
                  break;
                case 2:
                  output+="Ampas Tahu : " + Main.roundFormat(this.kromosom[i]) + " kg" + "\n";
                  break;
                case 3:
                  output+="Konsentrat : " + Main.roundFormat(this.kromosom[i]) + " kg" + "\n";
                  break;
            }
            
        }
        return output;
    }
}
