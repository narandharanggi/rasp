package com.cun.rasp.ga;

import com.cun.rasp.model.Food;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class AlgoritmaGenetika {
    private int ukuranPopulasi;
    private int jumlahGenerasi;
    private double probMutasi;
    private double probCross;
    private int elitism;
    private int batas;
    double target[];
    List<Food> bp;
    double[] kandunganBk;
    double finalnutrisi[];
    double atribut[];
    double harga;

    public AlgoritmaGenetika(int ukuranPopulasi, int jumlahGenerasi, double probMutasi, double probCross, int elitism, int batas,List<Food> bp){
        this.ukuranPopulasi = ukuranPopulasi;
        this.jumlahGenerasi = jumlahGenerasi;
        this.probMutasi = probMutasi;
        this.probCross = probCross;
        this.elitism = elitism;
        this.batas = batas;
        this.bp = bp;
        target = new double[5];
    }

    public Populasi initPopulasi(){
        Populasi populasi = new Populasi(this.ukuranPopulasi).insialisasi(batas);
        return populasi;
    }

    public void calcFitness(Individu individu){
        atribut = new double[2];
        kandunganBk = new double[2];
        finalnutrisi = new double[5];

        target[0] = 12.285;
        target[1] = 8.6001;
        target[2] = 1.5838;
        target[3] = 0.0602;
        target[4] = 0.0394;

        for (int i = 0; i < 4; i++) {
            finalnutrisi[0] += bp.get(i).getBk()*individu.getGene(i)/100;
//               finalnutrisi[0] = Main.roundFormat(finalnutrisi[0]);
//               System.out.println("nutrisi bk "+finalnutrisi[0]);
        }

        for (int i = 0; i < 4; i++) {
            finalnutrisi[1] += (bp.get(i).getBk()*individu.getGene(i)/100)*bp.get(i).getTdn()/100;
//               finalnutrisi[1] = Main.roundFormat(finalnutrisi[1]);
//               System.out.println("nutrisi tdn "+finalnutrisi[1]);
        }

        for (int i = 0; i < 4; i++) {
            finalnutrisi[2] += (bp.get(i).getBk()*individu.getGene(i)/100)*bp.get(i).getPk()/100;
//               finalnutrisi[2] = Main.roundFormat(finalnutrisi[2]);
//               System.out.println("nutrisi pk "+finalnutrisi[2]);
        }

        for (int i = 0; i < 4; i++) {
            finalnutrisi[3] += (bp.get(i).getBk()*individu.getGene(i)/100)*bp.get(i).getCa()/100;
//               finalnutrisi[3] = Main.roundFormat(finalnutrisi[3]);
//               System.out.println("nutrisi ca "+finalnutrisi[3]);
        }

        for (int i = 0; i < 4; i++) {
            finalnutrisi[4] += (bp.get(i).getBk()*individu.getGene(i)/100)*bp.get(i).getP()/100;
//               finalnutrisi[4] = Main.roundFormat(finalnutrisi[4]);
//               System.out.println("nutrisi p "+finalnutrisi[4]);
        }

        for (int j = 0; j < 5; j++) {
            double hitung = target[j]-finalnutrisi[j];
            if (Double.doubleToRawLongBits(hitung)>0) {
                atribut[0]+=hitung;
//                System.out.println("target "+hitung);
//                System.out.println("cekk"+atribut[0]);
//                System.out.println("final "+finalnutrisi[j]);
            }
        }

//        atribut[0] = atribut[0];
//        System.out.println("pinaltyy "+atribut[0]);
        harga = harga(individu, bp);
//        System.out.println("harga "+harga);
        atribut[1] = 10000/(harga+(atribut[0]*100000));
//        System.out.println("asd "+(atribut[0]*100000));
//        System.out.println("fitness "+atribut[1]);

        individu.setPenalti(atribut[0]);
        individu.setHarga((int)individu.roundFormat(harga));
        individu.setFitness(atribut[1]);
    }

    public double harga(Individu individu, List<Food> bp){
        double total=0;
        for (int i = 0; i < 4; i++) {
            total += individu.getGene(i)*bp.get(i).getHarga();
//            System.out.println("harga ke "+i+total);
        }

        return total;
    }

    public double[] getKandunganNutrisi(){
        return finalnutrisi;
    }

    public void evalPopulasi(Populasi populasi){
        double fitnessPopulasi=0;

        for (Individu individu:populasi.getIndividuals()) {
            calcFitness(individu);
            fitnessPopulasi+=individu.getFitness();
        }

        populasi.setFitnessPopulasi(fitnessPopulasi);
    }

    public boolean isTerminate(int jumlahGenerasi){
        if (this.jumlahGenerasi==jumlahGenerasi) {
            return true;
        }

        return false;
    }

    public Individu selectInduk(Populasi populasi){
        Individu selectInd = new Individu();
        Individu individuals[] = populasi.getIndividuals();

        double fitnessPopulasi = populasi.getFitnessPopulasi();


        double prob=0;
        double probCum[] = new double[populasi.size()];

        for (int i = 0; i < populasi.size(); i++) {
            prob=populasi.getIndividu(i).getFitness()/fitnessPopulasi;
//            System.out.println("prob "+prob);
            probCum[i]+=prob;
//            System.out.println("prob cum"+probCum[i]);   
        }

        for (int i = 0; i < populasi.size(); i++) {
//            System.out.println("probb crumm"+probCum[probCum.length-1]);
            double rodaCakram = Math.random()*probCum[probCum.length-1];
//            System.out.println("random cros"+rodaCakram);
            if (probCum[i]>=rodaCakram) {
//                System.out.println("masuk cakram");
                selectInd = populasi.getIndividu(i);

//                for (int j = 0; j < 4; j++) {
//                    System.out.println("rodaaaa "+populasi.getIndividu(i).getGene(j));
//                }

                return selectInd;
            }
        }
        return individuals[populasi.size()-1];
    }

    public Populasi crossover (Populasi pop){
        double alpha[]=new double[4];
        double gen=0;
        Individu child1 = new Individu();
        Individu child2 = new Individu();
        Populasi newPop = new Populasi(pop.size());

        for (int i = 0; i < pop.size();i++) {
            Individu parent1 = pop.getIndividu(i);
//            System.out.println("parent1"+parent1.toString());
//            System.out.println(parent1.toString());

            if (this.probCross>Math.random()&&i>this.elitism&&i%2==0&&i!=pop.size()-1) {
                Individu parent2 = selectInduk(pop);
//                System.out.println("parent2"+parent2.toString());

                for (int j = 0; j < 4; j++) {
                    alpha[j] = Math.random();
//                alpha[j] *= Math.floor(Math.random()*2) == 1 ? 1 : -1;
//                System.out.println("alpah"+alpha[j]);
                }

                for (int x = 0; x < parent1.getPanjangKromosom(); x++) {
                    gen = parent1.getGene(x)+alpha[x]*(parent2.getGene(x)-parent1.getGene(x));
//                System.out.println("1"+parent1.getGene(x));
//                System.out.println("2"+alpha[x]);
//                System.out.println("3"+(parent2.getGene(x)-parent1.getGene(x)));
//                System.out.println("gen 1"+gen);
//                gen = (Double.doubleToRawLongBits(gen)) < 0 ? 0 : gen;
                    child1.setGene(x, gen);
//                System.out.println("gennn"+gen);
                }

                for (int y = 0; y < parent2.getPanjangKromosom(); y++) {
                    gen = parent2.getGene(y)+alpha[y]*(parent1.getGene(y)-parent2.getGene(y));
//                System.out.println("1"+parent1.getGene(y));
//                System.out.println("2"+alpha[y]);
//                System.out.println("3"+(parent2.getGene(y)-parent1.getGene(y)));
//                System.out.println("gen 2"+gen);
//                gen = (Double.doubleToRawLongBits(gen)) < 0 ? 0 : gen;
                    child2.setGene(y, gen);
//                System.out.println("gennn"+gen);
                }

//                System.out.println("cek child1"+i);
                newPop.setIndividu(i, child1);
//                System.out.println("cek child2"+(i+1));
                newPop.setIndividu(i+1, child2);
                i=i+1;
            } else {
                newPop.setIndividu(i, parent1);
            }


        }
        return newPop;
    }

    public Populasi mutasi(Populasi pop){
        Populasi newPop = new Populasi(this.ukuranPopulasi);

        for (int i = 0; i < pop.size(); i++) {
            Individu parent = pop.getIndividu(i);
            Individu child = new Individu();

            if (i>=this.elitism) {
//                System.out.println("iii"+i);
                for (int index = 0; index < pop.getIndividu(0).getPanjangKromosom(); index++) {
                    if (this.probMutasi>Math.random()) {
                        double gene = Math.random()*batas;
                        child.setGene(index, gene);
                    } else {
                        child.setGene(index, parent.getGene(index));
                    }
                }
                newPop.setIndividu(i, child);
            } else {
                newPop.setIndividu(i, pop.getIndividu(i));
            }
        }

        return newPop;
    }

}