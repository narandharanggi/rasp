package com.cun.rasp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cun.rasp.R;
import com.cun.rasp.view.bahanpakan.BahanPakanActivity;
import com.cun.rasp.view.bobotsapi.BobotSapiActivity;
import com.cun.rasp.view.detailpakan.DetailPakanActivity;

public class UbahDataActivity extends AppCompatActivity {
    TextView bahan_pakan_textview, bobot_sapi_textview, detail_pakan_textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_menu);

        //navigate to bahan pakan
        bahan_pakan_textview = findViewById(R.id.bahan_pakan_list_menu);
        bahan_pakan_textview.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(UbahDataActivity.this, BahanPakanActivity.class);
                        startActivity(i);
                    }
                }
        );
     
        //navigate to bobot sapi
        bobot_sapi_textview = findViewById(R.id.bobot_sapi_list_menu);
        bobot_sapi_textview.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(UbahDataActivity.this, BobotSapiActivity.class);
                        startActivity(i);
                    }
                }
        );
       
        //navigate to bobot sapi
        detail_pakan_textview = findViewById(R.id.detail_pakan_list_menu);
        detail_pakan_textview.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(UbahDataActivity.this, DetailPakanActivity.class);
                        startActivity(i);
                    }
                }
        );
    }
}