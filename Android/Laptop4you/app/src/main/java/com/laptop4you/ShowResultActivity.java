package com.laptop4you;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.laptop4you.utils.Laptop;
import com.laptop4you.utils.Utils;

import java.util.ArrayList;


public class ShowResultActivity extends AppCompatActivity {
    private ArrayList<Laptop> laptopList;
    private Utils mUtils;

    private RecyclerView mProductList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);

        Intent intent = getIntent();
        String cluster = intent.getStringExtra("cluster");
        int minPrice = intent.getIntExtra("minPrice",-1);
        int maxPrice = intent.getIntExtra("maxPrice",-1);

        mUtils = new Utils(this);
        laptopList = mUtils.getLaptops(cluster,minPrice,maxPrice);
        for(Laptop laptop:laptopList){
            Log.i("laptop",laptop.uuid);
        }

        mProductList = findViewById(R.id.product_list);
        mProductList.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(ShowResultActivity.this);
        mProductList.setLayoutManager(mLayoutManager);

        mProductList.addItemDecoration(new MyDecoration());

        mAdapter = new LinearAdapter(laptopList);
        mProductList.setAdapter(mAdapter);
        //mProductList.setAdapter(new LinearAdapter(ShowResultActivity.this));


    }
    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(Rect outRect,View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,getResources().getDimensionPixelOffset(R.dimen.dividerHeight),0,getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
        }
    }
}