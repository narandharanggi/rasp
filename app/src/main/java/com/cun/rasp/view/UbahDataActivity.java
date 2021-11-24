package com.cun.rasp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cun.rasp.R;
import com.cun.rasp.view.bahanpakan.BahanPakanActivity;

public class UbahDataActivity extends AppCompatActivity {
    TextView bahan_pakan_textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_menu);

        //navigate to ubah data page
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
    }
}