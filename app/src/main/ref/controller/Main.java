/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import algoritma.AlgoritmaGenetika;
import algoritma.Individu;
import algoritma.Populasi;
import com.opencsv.CSVWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import model.BahanPakan;
import model.SapiPerah;
import view.FrameAlgen;

/**
 *
 * @author ggnryr
 */
public class Main {
    FrameAlgen view;
    SapiPerah sapi;
    BahanPakan pakan;
    AlgoritmaGenetika ag;
    double kebutuhan[];
    List<BahanPakan> bp;
    List<Populasi> listPopulasi;
    int ukuranPopulasi;
    int generasi;
    double crossoverRate;
    double mutationRate;
    
    
    public Main(FrameAlgen view, SapiPerah sapi, BahanPakan pakan ){
        this.view = view;
        this.sapi = sapi;
        this.pakan = pakan;
        
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.view.setDefaultLookAndFeelDecorated(true);
        this.view.setVisible(true);
        
        setBahanPakan();
        view.tabelAlgoritma(dtm());
        view.inputSapiPerah(new klikInput());
        view.inputAlgen(new klikAlgen());
        
    } 
    
    public void setBahanPakan(){
        view.tabelBahanPakan(pakan.bacaTabelBahanPakan());
    }
    
    public void hitungKebutuhan(int laktasi, double bobot, double produksi, double ls, double pbb){
        boolean cekbbBk=false;
        boolean cekbb=false;
        boolean cekps=false;
        int fcm=0;
        
        //cek bb & ls
        int convProduksi = (int)Math.round(produksi);
        int convBobot = (int)Math.round(bobot);
        cekbbBk = sapi.bobot(convBobot,"bk");
        cekbb = sapi.bobot(convBobot,"kh");
        
        
        kebutuhan = new double[5];
        
        //hitung 4%FCM
        if (ls!=4.0) {
            fcm = (int)Math.round((0.4*convProduksi)+(15*ls/100*convProduksi));
        } else {
            fcm = convProduksi;
        }
        cekps = sapi.produksi(fcm);
        
        //kebutuhan bk
        kebutuhan[0] = roundFormat(hitungBk(cekbbBk, cekps, convBobot, fcm, ls)*convBobot/100);

        //kebutuhan hidup
        double[] kh = kebutuhanHidup(cekbb, convBobot);
        
        //periode laktasi
        double[] lak = laktasi(kh, laktasi);
        
        //kebutuhan lemak
        double[] kl = kebutuhanLemak(ls,convProduksi);
        
        //perubahan Bb
        double[] perBb = perubahanBb(pbb);  
        
        //total kebutuhan
        kebutuhan[1] = roundFormat(kh[0] + lak[0] + kl[0] + perBb[0]);
        System.out.println("kebutuhan 1"+kh[0]+" "+lak[0]+" "+kl[0]+" "+perBb[0]);
        kebutuhan[2] = roundFormat(kh[1] + lak[1] + kl[1] + perBb[1]);
        System.out.println("kebutuhan 2"+kh[1]+" "+lak[1]+" "+kl[1]+" "+perBb[1]);
        kebutuhan[3] = roundFormat(kh[2] + lak[2] + kl[2]);
        System.out.println("kebutuhan 3"+kh[2]+" "+lak[2]+" "+kl[2]);
        kebutuhan[4] = roundFormat(kh[3] + lak[3] + kl[3]); 
        System.out.println("kebutuhan 4"+kh[3]+" "+lak[3]+" "+kl[3]);
    }
    
    public double[] kebutuhanHidup(boolean cekbb, int bobot){
        double[] kebHidup = new double[4];
        List<Integer> bb=null;
        int min=0;
        int max=0;
       
        if (cekbb == false) {
            bb = sapi.getAllBobot();
            //mengambil nilai min & max
            for (int i = 0; i < bb.size(); i++) {
                if (bb.get(i)<bobot) {
                    min = bb.get(i);
                } else if (bb.get(i)>bobot){
                    max = bb.get(i);
                    break;
                }
            }
            
            //interpolasi
            double[] x1 = sapi.getKebutuhanHidup(min);
            double[] x2 = sapi.getKebutuhanHidup(max);
            kebHidup[0] = x1[0]+(((double)(bobot-min)/(max-min))*(x2[0]-x1[0]));
            kebHidup[1] = x1[1]+(((double)(bobot-min)/(max-min))*(x2[1]-x1[1]));
            kebHidup[2] = x1[2]+(((double)(bobot-min)/(max-min))*(x2[2]-x1[2]));
            kebHidup[3] = x1[3]+(((double)(bobot-min)/(max-min))*(x2[3]-x1[3]));
        } else {
            kebHidup = sapi.getKebutuhanHidup(bobot);
        }
        
        return kebHidup;
    }
    
    public double[] kebutuhanLemak(double lemakSusu, double produksi){
        double[] kebLemak = new double[4];
                
        kebLemak[0] = sapi.getKebutuhanLemak(lemakSusu)[0]*produksi;
        kebLemak[1] = sapi.getKebutuhanLemak(lemakSusu)[1]*produksi;
        kebLemak[2] = sapi.getKebutuhanLemak(lemakSusu)[2]*produksi;
        kebLemak[3] = sapi.getKebutuhanLemak(lemakSusu)[3]*produksi;
        
        return kebLemak;
    }
    
    public double hitungBk(boolean cekbb, boolean cekps, int bobot, int produksi, double lemakSusu){
        double bk=0;
        List<Integer> ps=null;
        List<Integer> bb=null;
        int minBb=0;
        int minPs=0;
        int maxBb=0;
        int maxPs=0;
        
        if (cekbb==true&&cekps==true) {
            bk = sapi.getBk(bobot, produksi);
            System.out.println("cekkk");
        } else {
            if (cekbb==true&&cekps==false) {
                ps = sapi.getAllPs();
                //mengambil min & max
                for (int i = 0; i < ps.size(); i++) {
                    if (ps.get(i)<produksi) {
                        minPs = ps.get(i);
                        System.out.println("min ps "+minPs);
                    } else if(ps.get(i)>produksi){
                        maxPs = ps.get(i);
                        System.out.println("max ps "+maxPs);
                        break;
                    }
                }
                
                //interpolasi
                bk = sapi.getBk(bobot, minPs)+(((double)(produksi-minPs)/(maxPs-minPs))*(sapi.getBk(bobot, maxPs)-sapi.getBk(bobot, minPs)));
                System.out.println("1 "+sapi.getBk(bobot, minPs));
                System.out.println("2 "+(produksi-minPs));
                System.out.println("3 "+(maxPs-minPs));
                System.out.println("3 "+(sapi.getBk(bobot, maxPs)-sapi.getBk(bobot, minPs)));
                System.out.println("bk "+bk);
            } else if(cekbb==false&&cekps==true){
                bb = sapi.getBobotBk();
                //mengambil min & max
                for (int i = 0; i < bb.size(); i++) {
                    if (bb.get(i)<bobot) {
                        minBb = bb.get(i);
                        System.out.println("min bb "+minBb);
                    } else if(bb.get(i)>bobot){
                        maxBb = bb.get(i);
                        System.out.println("max bb "+maxBb);
                        break;
                    }
                }
                
                //interpolasi
                bk = sapi.getBk(minBb, produksi)+(((double)(bobot-minBb)/(maxBb-minBb))*(sapi.getBk(maxBb, produksi)-sapi.getBk(minBb, produksi)));
                System.out.println("bobot "+bobot);
                System.out.println("1 "+sapi.getBk(minBb, produksi));
                System.out.println("2 "+((double)(bobot-minBb)/(maxBb-minBb)));
                System.out.println("3 "+(sapi.getBk(maxBb, produksi)-sapi.getBk(minBb, produksi)));
                System.out.println("bk "+bk);
            } else {
                bb = sapi.getBobotBk();
                //mengambil min & max
                for (int i = 0; i < bb.size(); i++) {
                    if (bb.get(i)<bobot) {
                        minBb = bb.get(i);
                        System.out.println("min BB "+minBb);
                    } else if(bb.get(i)>bobot){
                        maxBb = bb.get(i);
                        System.out.println("max BB "+maxBb);
                        break;
                    }
                }
                
                ps = sapi.getAllPs();
                //mengambil min & max
                for (int i = 0; i < ps.size(); i++) {
                    if (ps.get(i)<produksi) {
                        minPs = ps.get(i);
                        System.out.println("min Ps "+minPs);
                    } else if(ps.get(i)>produksi){
                        maxPs = ps.get(i);
                        System.out.println("max Ps "+maxPs);
                        break;
                    }
                }
                
                double[] tmp = new double[2];
                tmp[0] = sapi.getBk(minBb, minPs)+(((double)(bobot-minBb)/(maxBb-minBb))*(sapi.getBk(maxBb, minPs)-sapi.getBk(minBb, minPs)));
                System.out.println("tmp 0 "+tmp[0]);
                System.out.println("1 "+sapi.getBk(minBb, minPs));
                System.out.println("2 "+(bobot-minBb));
                System.out.println("2-1 "+(maxBb-minBb));
                System.out.println("3 "+(sapi.getBk(minBb, minPs)-sapi.getBk(maxBb, minPs)));
                tmp[1] = sapi.getBk(minBb, maxPs)+(((double)(bobot-minBb)/(maxBb-minBb))*(sapi.getBk(maxBb, maxPs)-sapi.getBk(minBb, maxPs)));
                System.out.println("tmp 1 "+tmp[1]);
                bk = tmp[0]+(((double)(produksi-minPs)/(maxPs-minPs))*(tmp[1]-tmp[0]));
                System.out.println("bk "+bk);
            }
        }
        
        return bk;
    }
    
    public double[] perubahanBb(double perBb){
        double[] nutrisi = new double[2];
        String keterangan = "";
        
        if(perBb==0){
            perBb=0;
        }
        else if (Double.compare(perBb, 0.0)<0) {
            keterangan="Kehilangan BB";
            perBb*=-1;
        } 
        else {
            keterangan="Pertambahan BB";
        }
        nutrisi = sapi.perBb(keterangan);
        nutrisi[0] *= perBb;
        nutrisi[1] *= perBb;
        
        return nutrisi;
    }
    
    public double[] laktasi(double[] kebutuhanHidup, int periode){
        double[] nutrisi = new double[4];
        int konstanta=0;
        
        if (periode==0) {
            konstanta=20;
        } else if(periode==1){
            konstanta=10;
        } else {
            konstanta=0;
        }
        
        nutrisi[0] = konstanta*kebutuhanHidup[0]/100;
        nutrisi[1] = konstanta*kebutuhanHidup[1]/100;
        nutrisi[2] = konstanta*kebutuhanHidup[2]/100;
        nutrisi[3] = konstanta*kebutuhanHidup[3]/100;
        
        return nutrisi;
    }
    
    public void tampilDataSapi(double bk, double tdn, double pk, double ca, double p){
        view.setBK(String.valueOf(bk)+" kg");
        view.setTDN(String.valueOf(tdn)+" kg");
        view.setPK(String.valueOf(pk)+" kg");
        view.setCa(String.valueOf(ca)+" kg");
        view.setP(String.valueOf(p)+" kg");
    }
    
    public String[] startAlgen(int index){
        String[] tmpOpt = new String[8];
        ag = new AlgoritmaGenetika(ukuranPopulasi, generasi, mutationRate, crossoverRate, 2, 100, kebutuhan, bp);
        Populasi pop = ag.initPopulasi();
        printPopulasi(pop);
        ag.evalPopulasi(pop);
        pop.sortKromosom();
        
        Object[] item = new Object[generasi];
        int generasiProc=0;
        listPopulasi = new ArrayList<>();
        
        while (ag.isTerminate(generasiProc)==false) {
//           printPopulasi(pop);

            System.out.println("Generasi ke "+generasi);
            item[generasiProc] = generasiProc+1;
//            
//            //cek pop awal
//            for (int i = 0; i < pop.size(); i++) {
//                for (int j = 0; j < pop.getIndividu(0).getPanjangKromosom(); j++) {
//                    System.out.println("cek pop awal "+pop.getIndividu(i).getGene(j));
//                }
//            }
            
            pop = ag.crossover(pop);
//            printPopulasi(pop);
            pop = ag.mutasi(pop);
//            printPopulasi(pop);
            ag.evalPopulasi(pop);
            pop.sortKromosom();
            printPopulasi(pop);
            
            Populasi tmp = new Populasi(pop.size());
            for (int i = 0; i < pop.size(); i++) {
                tmp.setIndividu(i, pop.getIndividu(i));
            }
            
            listPopulasi.add(tmp);
            generasiProc++;
        }
               
//        for (int j = 0; j < listPopulasi.size(); j++) {
//            System.out.println("generasi ke "+j);
//            printPopulasi(listPopulasi.get(j));
//        }
        

        view.modelCombo(item);

        ambilProsesAlgen(generasiProc-1,dtm());
        view.setGenerasi(new pilihGenerasi());

        ag.calcFitness(pop.getIndividu(0));
        view.setRansum("============== Ransum =============="+"\n"+pop.getIndividu(0).toString()+
                "============== Total Nutrisi yang Tersedia =============="+
                "\n"+"Total Kandungan BK "+roundFormat(ag.getKandunganNutrisi()[0])+"\n"+"Total Kandungan TDN "+roundFormat(ag.getKandunganNutrisi()[1])+
                "\n"+"Total Kandungan PK "+roundFormat(ag.getKandunganNutrisi()[2])+"\n"+"Total Kandungan Ca "+roundFormat(ag.getKandunganNutrisi()[3])+
                "\n"+"Total Kandungan P "+roundFormat(ag.getKandunganNutrisi()[4])+"\n"+"============== Selisih Nutrisi dengan Kebutuhan =============="+
                "\n"+getSelisih()+"\n"+"============== Hasil Algoritma Genetika =============="+
                "\n"+"Generasi ke "+generasi+"\n"+"Penalti "+roundFormat(pop.getIndividu(0).getPenalti())+"\n"+"Harga "+pop.getIndividu(0).getHarga()+"\n"+"Fitness "+roundFormat(pop.getIndividu(0).getFitness()));
        view.falseEdit();
        
        tmpOpt[0] = String.valueOf(index+1);
        tmpOpt[1] = String.valueOf(roundFormat(pop.getIndividu(0).getGene(0)));
        tmpOpt[2] = String.valueOf(roundFormat(pop.getIndividu(0).getGene(1)));
        tmpOpt[3] = String.valueOf(roundFormat(pop.getIndividu(0).getGene(2)));
        tmpOpt[4] = String.valueOf(roundFormat(pop.getIndividu(0).getGene(3)));
        tmpOpt[5] = String.valueOf(roundFormat(pop.getIndividu(0).getPenalti()));
        tmpOpt[6] = String.valueOf(pop.getIndividu(0).getHarga());
        tmpOpt[7] = String.valueOf(roundFormat(pop.getIndividu(0).getFitness()));
        
        return tmpOpt;
    }
    
    public void printPopulasi(Populasi populasi){
        System.out.println("=====================================================");
        for (int i = 0; i < populasi.size(); i++) {
            System.out.println("Kromosom "+i+" : "+Arrays.toString(populasi.getIndividu(i).getKromosom())+" | Fitness "+populasi.getIndividu(i).getFitness());
        }
    }
    
    public String getSelisih(){
        String output="";
        String keterangan[]=new String[5];
        double nutrisi[]=new double[5];
        
        nutrisi[0]=roundFormat(ag.getKandunganNutrisi()[0]-kebutuhan[0]);
        nutrisi[1]=roundFormat(ag.getKandunganNutrisi()[1]-kebutuhan[1]);
        nutrisi[2]=roundFormat(ag.getKandunganNutrisi()[2]-kebutuhan[2]);
        nutrisi[3]=roundFormat(ag.getKandunganNutrisi()[3]-kebutuhan[3]);
        nutrisi[4]=roundFormat(ag.getKandunganNutrisi()[4]-kebutuhan[4]);
        
        for (int i = 0; i < nutrisi.length; i++) {
            if (Double.doubleToRawLongBits(nutrisi[i])<0) {
                keterangan[i]="kurang";
            } else {
                keterangan[i]="terpenuhi";
            }
        }
        
        for (int i = 0; i < nutrisi.length; i++) {
            switch(i){
                case 0:
                  output+="BK : " + nutrisi[i] + " kg " + "(" + "Nutrisi "+keterangan[i] + ")" + "\n";
                  break;
                case 1:
                  output+="TDN : " + nutrisi[i] + " kg " + "(" + "Nutrisi "+keterangan[i] + ")" + "\n"; 
                  break;
                case 2:
                  output+="PK : " + nutrisi[i] + " kg " + "(" + "Nutrisi "+keterangan[i] + ")" + "\n";
                  break;
                case 3:
                  output+="Ca : " + nutrisi[i] + " kg " + "(" + "Nutrisi "+keterangan[i] + ")" + "\n";
                  break;
                case 4:
                  output+="P : " + nutrisi[i] + " kg " + "(" + "Nutrisi "+keterangan[i] + ")";
                  break;
            }
                        
        }
        
        return output;
    }
    
    public DefaultTableModel dtm(){
        String[] kolom = {"Rumput Gajah","Jagung","Ampas Tahu","Konsentrat","Penalti","Harga","Fitness"};
        DefaultTableModel dm = new DefaultTableModel(null, kolom){
            public boolean isCellEditable(int row, int column) {                
                return false;               
            };
        };
        
        return dm;
    }
    
    
    public void ambilProsesAlgen(int index, DefaultTableModel tabelModel){
        DefaultTableModel dm = tabelModel;
        
        for (Individu individu:listPopulasi.get(index).getIndividuals()) {
            Object[] data = new Object[7];
            data[0] = roundFormat(individu.getGene(0));
            data[1] = roundFormat(individu.getGene(1));
            data[2] = roundFormat(individu.getGene(2));
            data[3] = roundFormat(individu.getGene(3));
            data[4] = roundFormat(individu.getPenalti());
            data[5] = individu.getHarga();
            data[6] = roundFormat(individu.getFitness());
            dm.addRow(data);
            
            for (int i = 0; i < listPopulasi.get(index).size(); i++) {
                for (int j = 0; j < data.length; j++) {
                    dm.isCellEditable(i, j);
                }
            }
        }
        
        view.tabelAlgoritma(dm);
    }

    private class pilihGenerasi implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = view.getIndexGenerasi();
            System.out.println("index ke "+index);
            
            for (int i = 0; i < listPopulasi.get(index).size(); i++) {
                for (int j = 0; j < 4; j++) {
                        System.out.println("pilih index "+j+listPopulasi.get(index).getIndividu(i).getGene(j));
                }
            }
            
            ambilProsesAlgen(index,dtm());
        }
    }

    private class klikInput implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           int laktasi = view.getLaktasi();
           double bobot = view.getBobot();
           double produksi = view.getProduksi();
           double ls = view.getLemakSusu();
           double pbb = view.getPerubahanBB();
           
           if(bobot == 0 || produksi == 0){
                JOptionPane.showMessageDialog(view, "Masukkan nilai yang valid", 
                        "ERROR",JOptionPane.ERROR_MESSAGE);
            } else {
                hitungKebutuhan(laktasi, bobot, produksi, ls, pbb);
                bp = pakan.getAll();
           
                tampilDataSapi(kebutuhan[0], kebutuhan[1], kebutuhan[2], 
                        kebutuhan[3], kebutuhan[4]);
                view.falseDataSapi();
           }

        }
    }
    
    private class klikAlgen implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ukuranPopulasi = view.getUkuranPopulasi();
            generasi = view.getGenerasi();
            crossoverRate = view.getCrossoverRate();
            mutationRate = view.getMutationRate();
            String judul = view.getJudul();
            
            if(ukuranPopulasi == 0 || generasi == 0 || crossoverRate < -0.1 || mutationRate < -0.1){
                JOptionPane.showMessageDialog(view, "Masukkan nilai yang valid", "ERROR",JOptionPane.ERROR_MESSAGE);
            } else {
                List<String[]> iterasi= new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    iterasi.add(startAlgen(i));          
                }
                writeDataLineByLine(judul,iterasi);
                JOptionPane.showMessageDialog(view,"Process completed", "Complete", JOptionPane.INFORMATION_MESSAGE);
            }           
        }
    }
    
    public static double roundFormat(double value) {
        int places = 4;
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    public static void writeDataLineByLine(String pathFile, List<String[]> tmpHasil) 
{ 
	// first create file object for file placed at location 
	// specified by filepath 
	File file = new File(pathFile+".csv"); 
	try { 
		// create FileWriter object with file as parameter 
		FileWriter outputfile = new FileWriter(file); 

		// create CSVWriter object filewriter object as parameter 
		CSVWriter writer = new CSVWriter(outputfile); 

		 // create a List which contains String array 
                List<String[]> data = tmpHasil; 
                writer.writeAll(data); 

		// closing writer connection 
		writer.close(); 
	} 
	catch (IOException e) { 
		// TODO Auto-generated catch block 
		e.printStackTrace(); 
	} 
} 
}
