/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritma;

import java.util.Random;

/**
 *
 * @author ggnryr
 */
public class Seleksi {
    
    private double[] cumulativeProb;
    private double totalFitness;
    private int indexInduk;
    private double randomProb;
    
    public Seleksi(Populasi pop) {
        randomProb = 0.0;
        indexInduk = -1;
        totalFitness = 0.0;
        cumulativeProb = new double[pop.size()];
        
        for (Individu ind:pop.getIndividuals() ) {
            totalFitness = totalFitness + pop.getFitnessPopulasi();
        }
        
        int nextIndex=1;
        int prevIndex=0;
        cumulativeProb[0]=0.0;
        
        if (totalFitness==0.0) {
            for (Individu ind:pop.getIndividuals()) {
                cumulativeProb[nextIndex]=0.0;
                nextIndex++;
            }
        } else {
            for (Individu ind:pop.getIndividuals()) {
                double selectionProb=ind.getFitness()/totalFitness;
                cumulativeProb[nextIndex] = cumulativeProb[prevIndex]+selectionProb;
                prevIndex++;
                nextIndex++;
            }
        }
    }
    
    public void pilihIndividu(){
        Random doubleGenerator = new Random();
        randomProb = doubleGenerator.nextDouble();
        indexInduk = getLocationInduk(randomProb);
    }
    
    private int getLocationInduk(double randomDouble){
        int locationInduk=0;
        
        for (int index = 0; index < cumulativeProb.length-1; index++) {
            if (randomDouble>=cumulativeProb[index]&&randomDouble<=cumulativeProb[index+1]) {
                locationInduk=index;
                break;
            }
        }
        return locationInduk;
    }
    
    public int getIndexInduk(){
        return indexInduk;
    }
    
    public double[] getRouletteWheel(){
        return cumulativeProb;
    }
    
    public double getTotalFitness(){
        return totalFitness;
    }
    
    public double getRandomNo(){
        return randomProb;
    }
}
