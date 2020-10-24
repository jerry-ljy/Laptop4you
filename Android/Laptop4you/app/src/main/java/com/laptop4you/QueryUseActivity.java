package com.laptop4you;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class QueryUseActivity extends AppCompatActivity {


    private Button mBtnNext;
    private CheckBox mCBGame, mCBOffice, mCBHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_use);

        mCBGame = findViewById(R.id.use_opt_1);
        mCBOffice = findViewById(R.id.use_opt_2);
        mCBHome = findViewById(R.id.use_opt_3);

        mBtnNext = findViewById(R.id.btn_use_next);
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get checkbox status
                String message = "";
                if(mCBGame.isChecked()){
                    message = message +','+"game";
                }
                if(mCBOffice.isChecked()){
                    message = message +','+"office";
                }
                if(mCBHome.isChecked()){
                    message = message +','+"home";
                }
                //check which page to go
                if(message.contains("game")){
                    Intent intent = new Intent(v.getContext(), QueryGameActivity.class);
                    intent.putExtra("MESSAGE",message);
                    startActivity(intent);
                }
                else if(message.contains("office")){
                    Intent intent = new Intent(v.getContext(), QueryWorkActivity.class);
                    intent.putExtra("MESSAGE",message);
                    startActivity(intent);
                }
                else if(message.contains("home")){
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