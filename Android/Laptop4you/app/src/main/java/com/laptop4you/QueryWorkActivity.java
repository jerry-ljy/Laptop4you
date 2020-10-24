package com.laptop4you;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QueryWorkActivity extends AppCompatActivity {

    private Button mBtnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_work);

        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE");

        mBtnNext = findViewById(R.id.btn_work_next);
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(message.contains("home")){
                    Intent intent = new Intent(v.getContext(), QueryHomeActivity.class);
                    intent.putExtra("MESSAGE",message);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(v.getContext(), QueryPriceActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}