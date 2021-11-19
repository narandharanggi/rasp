/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritma;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author ggnryr
 */
public class Populasi {
    private Individu[] populasi;
    private double fitnessPopulasi=0;
    
    public Populasi(int jumlahPopulasi) {
        this.populasi = new Individu[jumlahPopulasi];
    }
    
    public Populasi insialisasi(double batas){      
        for (int i = 0; i < this.populasi.length; i++) {
            this.populasi[i] = new Individu().inisialisasi(batas);
        }
        return this;
    }
    
    public Individu[] getIndividuals(){
        return this.populasi;
    }
    
    public void sortKromosom(){
        Arrays.sort(this.populasi, new Comparator<Individu>() {
            @Override
            public int compare(Individu o1, Individu o2) {
                if(o1.getFitness()>o2.getFitness()){
                    return -1;
                } else if(o1.getFitness()<o2.getFitness()){
                    return 1;
                }
                return 0;
            }
        });
    }
    
 
    
    public void setFitnessPopulasi(double fitness){
        this.fitnessPopulasi=fitness;
    }
    
    public double getFitnessPopulasi(){
        return this.fitnessPopulasi;
    }
    
    public int size(){
        return this.populasi.length;
    }
    
    public Individu setIndividu(int batas, Individu individu){
        return this.populasi[batas] = individu;
    }
    
    public Individu getIndividu(int batas){
        return this.populasi[batas];
    }
    
}
