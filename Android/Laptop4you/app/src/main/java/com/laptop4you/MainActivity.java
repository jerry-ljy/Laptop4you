package com.laptop4you;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.laptop4you.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private Utils mUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // init database
        mUtils = new Utils(this);
        mUtils.initDatabase();

        btnStart = findViewById(R.id.btn_next_1);
        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,QueryUseActivity.class);
                startActivity(intent);
            }
        });
    }
}