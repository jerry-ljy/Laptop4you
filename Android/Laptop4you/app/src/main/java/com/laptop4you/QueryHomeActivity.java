package com.laptop4you;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class QueryHomeActivity extends AppCompatActivity {

    private Button mBtnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_home);
        mBtnNext = findViewById(R.id.btn_home_next);

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), QueryPriceActivity.class);
                startActivity(intent);
            }
        });
    }
}
