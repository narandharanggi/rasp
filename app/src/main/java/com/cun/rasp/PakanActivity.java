package com.cun.rasp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PakanActivity extends AppCompatActivity implements PakanListener{

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pakan);

        RecyclerView pakanRv = findViewById(R.id.tvShowsRv);
        btn = findViewById(R.id.btnAddWatchlist);

        List<Pakan> pakan = new ArrayList<>();

        Pakan pakan1 = new Pakan();
        pakan1.name = "Rumput Gajah";
        pakan.add(pakan1);

        Pakan pakan2 = new Pakan();
        pakan2.name = "Jagung";
        pakan.add(pakan2);

        Pakan pakan3 = new Pakan();
        pakan3.name = "Ampas Tahu";
        pakan.add(pakan3);

        Pakan pakan4 = new Pakan();
        pakan4.name = "Konsentrat";
        pakan.add(pakan4);

        final PakanAdapter pakanAdapter = new PakanAdapter(pakan,this);
        pakanRv.setAdapter(pakanAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Pakan> selectedPakan = pakanAdapter.getSelectedPakan();
                StringBuilder pakanNames = new StringBuilder();
                for(int i=0; i<selectedPakan.size(); i++){
                    if(i==0){
                        pakanNames.append(selectedPakan.get(i).name);
                    } else{
                        pakanNames.append("\n").append(selectedPakan.get(i).name);
                    }
                }
                Toast toast = Toast.makeText(getApplicationContext(), pakanNames.toString(), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
//        pakan1Spinner();
//        pakan2Spinner();
//        pakan3Spinner();
//        pakan4Spinner();
    }


    private void ShowToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPakan(Boolean isSelected) {
        if (isSelected) {
            btn.setVisibility(View.VISIBLE);
        } else {
            btn.setVisibility(View.GONE);
        }
    }

//    void pakan1Spinner(){
//        Spinner spinner = (Spinner) findViewById(R.id.pakan1);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.pakan, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//    }
//
//    void pakan2Spinner(){
//        Spinner spinner = (Spinner) findViewById(R.id.pakan2);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.pakan, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//    }
//
//    void pakan3Spinner(){
//        Spinner spinner = (Spinner) findViewById(R.id.pakan3);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.pakan, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//    }
//
//    void pakan4Spinner(){
//        Spinner spinner = (Spinner) findViewById(R.id.pakan4);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.pakan, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//    }
}