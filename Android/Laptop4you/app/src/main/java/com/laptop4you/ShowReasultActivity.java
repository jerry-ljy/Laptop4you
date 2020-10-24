package com.laptop4you;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.laptop4you.utils.Laptop;
import com.laptop4you.utils.Utils;

import java.util.ArrayList;


public class ShowReasultActivity extends AppCompatActivity {
    private ArrayList<Laptop> laptopList;
    private Utils mUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_reasult);

        Intent intent = getIntent();
        String cluster = intent.getStringExtra("cluster");
        int minPrice = intent.getIntExtra("minPrice",-1);
        int maxPrice = intent.getIntExtra("maxPrice",-1);

        mUtils = new Utils(this);
        laptopList = mUtils.getLaptops(cluster,minPrice,maxPrice);
        for(Laptop laptop:laptopList){
            Log.d("laptop",laptop.uuid);
        }
    }
}