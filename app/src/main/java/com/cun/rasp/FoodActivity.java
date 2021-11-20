package com.cun.rasp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cun.rasp.ga.AlgoritmaGenetika;
import com.cun.rasp.ga.Populasi;
import com.cun.rasp.model.Food;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FoodActivity extends AppCompatActivity {
    protected Cursor cursor;
    private RecyclerView recyclerView;
    private ArrayList<Food> foods = new ArrayList<>();
    private FoodAdapter adapter;
    private Button btnGetSelected;
    private AlgoritmaGenetika ga;
    private DatabaseHandler db;
    private int ukuranPopulasi;
    private int generasi;
    private double crossoverRate;
    private double mutationRate;
    List<Populasi> listPopulasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        db = new DatabaseHandler(FoodActivity.this);
        try {
            db.createDataBase();
        } catch (Exception e){
            e.printStackTrace();
        }

        this.btnGetSelected = findViewById(R.id.btnGetSelected);
        this.recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new FoodAdapter(this, foods);
        recyclerView.setAdapter(adapter);

        createList();

        btnGetSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getSelected().size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < adapter.getSelected().size(); i++) {
                        stringBuilder.append(adapter.getSelected().get(i).getName());
                        stringBuilder.append("\n");
                    }
//                    showToast(stringBuilder.toString().trim());
                    startAlgen();
                } else {
                    showToast("No Selection");
                }
            }
        });
    }

    private void createList() {
        String query = "SELECT nama_pakan, bk, tdn, pk, ca, p, harga FROM bahan_pakan";
        foods = new ArrayList<>();
        db.openDataBase();
        cursor = db.getReadableDatabase().rawQuery(query,null);
        cursor.moveToFirst();

        if(cursor.getCount()>0){
            for(int cc=0; cc<cursor.getCount(); cc++){
                Food food = new Food();
                cursor.moveToPosition(cc);
                food.setName(cursor.getString(0).toString());
                food.setBk(cursor.getDouble(1));
                food.setTdn(cursor.getDouble(2));
                food.setPk(cursor.getDouble(3));
                food.setCa(cursor.getDouble(4));
                food.setP(cursor.getDouble(5));
                food.setHarga(cursor.getInt(6));
                foods.add(food);
            }
        }
//        foodNames = new String[4];
//        foodNames = new String[]{"Rumput Gajah", "Jagung", "Ampas Tahu", "Konsentrat"};


//        for (String i : foodNames) {
//
//
//            // for example to show at least one selection
//            if (i.equals("Rumput Gajah")) {
//                food.setChecked(true);
//
//            }
//            //
//            foods.add(food);
//        }

//        for (int i = 0; i < 20; i++) {
//
//            Food food = new Food();
//            food.setName("Food " + (i + 1));
//            // for example to show at least one selection
//            if (i == 0) {
//                food.setChecked(true);
//            }
//            //
//            foods.add(food);
//        }
        adapter.setFoods(foods);
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void printPopulasi(Populasi populasi){
        System.out.println("=====================================================");
        for (int i = 0; i < populasi.size(); i++) {
            System.out.println("Kromosom "+i+" : "+ Arrays.toString(populasi.getIndividu(i).getKromosom())+" | Fitness "+populasi.getIndividu(i).getFitness());
        }
    }

    private void startAlgen (){
        ukuranPopulasi = 50;
        generasi = 50;
        crossoverRate = 0.1;
        mutationRate = 0.2;
        listPopulasi = new ArrayList<>();
        String[] tmpOpt = new String[7];
        ga = new AlgoritmaGenetika(ukuranPopulasi, generasi, mutationRate, crossoverRate, 2, 100, foods);
        Populasi pop = ga.initPopulasi();
        ga.evalPopulasi(pop);
        pop.sortKromosom();
        int generasiProc=0;
        while (ga.isTerminate(generasiProc)==false) {
//           printPopulasi(pop);

//
//            //cek pop awal
//            for (int i = 0; i < pop.size(); i++) {
//                for (int j = 0; j < pop.getIndividu(0).getPanjangKromosom(); j++) {
//                    System.out.println("cek pop awal "+pop.getIndividu(i).getGene(j));
//                }
//            }

            pop = ga.crossover(pop);
//            printPopulasi(pop);
            pop = ga.mutasi(pop);
//            printPopulasi(pop);
            ga.evalPopulasi(pop);
            pop.sortKromosom();
            printPopulasi(pop);

            Populasi tmp = new Populasi(pop.size());
            for (int i = 0; i < pop.size(); i++) {
                tmp.setIndividu(i, pop.getIndividu(i));
            }

            listPopulasi.add(tmp);
            generasiProc++;
        }

        tmpOpt[0] = String.valueOf(pop.getIndividu(0).getGene(0));
        tmpOpt[1] = String.valueOf(pop.getIndividu(0).getGene(1));
        tmpOpt[2] = String.valueOf(pop.getIndividu(0).getGene(2));
        tmpOpt[3] = String.valueOf(pop.getIndividu(0).getGene(3));
        tmpOpt[4] = String.valueOf(pop.getIndividu(0).getPenalti());
        tmpOpt[5] = String.valueOf(pop.getIndividu(0).getHarga());
        tmpOpt[6] = String.valueOf(pop.getIndividu(0).getFitness());

        int x=0;
        for(String i : tmpOpt){
            System.out.println("Data "+x+" "+i);
            x++;
        }

    }
}