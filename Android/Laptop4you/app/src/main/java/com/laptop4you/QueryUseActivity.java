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
    private int[] mUserReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_use);

        // init user requirement
        mUserReq = new int[]{0,0,0,0};

        mCBGame = findViewById(R.id.use_opt_1);
        mCBOffice = findViewById(R.id.use_opt_2);
        mCBHome = findViewById(R.id.use_opt_3);

        mBtnNext = findViewById(R.id.btn_use_next);
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCBGame.isChecked() || mCBOffice.isChecked() || mCBHome.isChecked()) {
                    //get checkbox status
                    String message = "";
                    if (mCBGame.isChecked()) {
                        message = message + ',' + "game";
                    }
                    if (mCBOffice.isChecked()) {
                        message = message + ',' + "office";
                    }
                    if (mCBHome.isChecked()) {
                        message = message + ',' + "home";
                    }
                    //check which page to go
                    if (message.contains("game")) {
                        Intent intent = new Intent(v.getContext(), QueryGameActivity.class);
                        intent.putExtra("MESSAGE", message);
                        Bundle bd = new Bundle();
                        bd.putIntArray("userReq", mUserReq);
                        intent.putExtras(bd);
                        startActivity(intent);
                    } else if (message.contains("office")) {
                        Intent intent = new Intent(v.getContext(), QueryWorkActivity.class);
                        intent.putExtra("MESSAGE", message);
                        Bundle bd = new Bundle();
                        bd.putIntArray("userReq", mUserReq);
                        intent.putExtras(bd);
                        startActivity(intent);
                    } else if (message.contains("home")) {
                        Intent intent = new Intent(v.getContext(), QueryHomeActivity.class);
                        intent.putExtra("MESSAGE", message);
                        Bundle bd = new Bundle();
                        bd.putIntArray("userReq", mUserReq);
                        intent.putExtras(bd);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(v.getContext(), QueryPriceActivity.class);
                        Bundle bd = new Bundle();
                        bd.putIntArray("userReq", mUserReq);
                        intent.putExtras(bd);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(QueryUseActivity.this,"Please select at least one option", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}