package com.sangnv3.nhom2_duan1_f_food.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sangnv3.nhom2_duan1_f_food.R;
import com.sangnv3.nhom2_duan1_f_food.activity.DangNhapActivity;

public class WelCome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_come);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelCome.this, DangNhapActivity.class));
            }
        },2000);
    }
}