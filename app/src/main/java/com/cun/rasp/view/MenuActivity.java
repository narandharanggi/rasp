package com.cun.rasp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cun.rasp.MainActivity;
import com.cun.rasp.R;

public class MenuActivity extends AppCompatActivity {
    Button UbahData_btn, Main_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //navigate to ubah data page
        UbahData_btn = findViewById(R.id.UbahData_btn);
        UbahData_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MenuActivity.this,BahanPakanActivity.class);
                        startActivity(i);
                    }
                }
        );

        //navigate to main page
        Main_btn = findViewById(R.id.Main_btn);
        Main_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MenuActivity.this,MainActivity.class);
                        startActivity(i);
                    }
                }
        );
    }
}