package com.cun.rasp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(MainActivity.this);
            try {
                db.createDataBase();
            } catch (Exception e){
                e.printStackTrace();
            }
        laktasiSpinner();
        pbbSpinner();
        movePakanLayout();
    }

    void laktasiSpinner(){
        Spinner spinner = findViewById(R.id.laktasi_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.laktasi, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    void pbbSpinner(){
        Spinner spinner = findViewById(R.id.pbb_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pbb, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    void movePakanLayout(){
        Button btn_pindah = findViewById(R.id.moveToFeed);
        btn_pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FoodActivity.class);
                startActivity(i);
            }
        });
    }

}