package com.laptop4you;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.laptop4you.utils.Utils;

public class QueryPriceActivity extends AppCompatActivity {

    private Button mBtnSubmit;
    private RadioGroup mRG;
    private String mCluster;
    private Utils mUtils;
    private int minPrice,maxPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_price);
        mBtnSubmit = findViewById(R.id.btn_price_submit);
        mRG = findViewById(R.id.price_radio_group);
        mUtils = new Utils(this);
        mUtils.initClusters();
        Bundle bd = this.getIntent().getExtras();
        int[] userReq = bd.getIntArray("userReq");
        mCluster = mUtils.getCluster(userReq);
        minPrice=-1;
        maxPrice=-1;
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = mRG.getCheckedRadioButtonId();
                if(id!=-1) {
                    setPrice(id);
                    Intent intent = new Intent(v.getContext(), ShowResultActivity.class);
                    intent.putExtra("cluster", mCluster);
                    intent.putExtra("minPrice", minPrice);
                    intent.putExtra("maxPrice", maxPrice);
                    startActivity(intent);
                }else{
                    Toast.makeText(QueryPriceActivity.this,"Please select at least one option", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setPrice(int id){
        switch(id){
            case  R.id.price_opt_1:
                maxPrice=1000;
                break;
            case R.id.price_opt_2:
                minPrice=1000;
                maxPrice=2000;
                break;
            case R.id.price_opt_3:
                minPrice=2000;
                maxPrice=3000;
                break;
            case R.id.price_opt_4:
                minPrice=3000;
                maxPrice=4000;
                break;
            case R.id.price_opt_5:
                minPrice=4000;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
    }
}