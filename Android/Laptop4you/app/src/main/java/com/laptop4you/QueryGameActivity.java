package com.laptop4you;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.laptop4you.utils.Utils;

import androidx.appcompat.app.AppCompatActivity;

public class QueryGameActivity extends AppCompatActivity {

    private Button mBtnNext;
    private CheckBox mCBmoba, mCBmmo, mCBsmall;
    private Utils mUtils;
    private int[] mUserReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_game);
        mUtils = new Utils();
        mCBmoba = findViewById(R.id.game_opt_1);
        mCBmmo = findViewById(R.id.game_opt_2);
        mCBsmall = findViewById(R.id.game_opt_3);
        mBtnNext = findViewById(R.id.btn_game_next);

        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE");
        Bundle bd = this.getIntent().getExtras();
        mUserReq = bd.getIntArray("userReq");

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((mCBmoba.isChecked() || mCBmmo.isChecked() || mCBsmall.isChecked())) {
                    if (mCBmoba.isChecked()) {
                        mUserReq = mUtils.refreshUserReq("moba", mUserReq);
                    }
                    if (mCBmmo.isChecked()) {
                        mUserReq = mUtils.refreshUserReq("mmorpg", mUserReq);
                    }
                    if (mCBsmall.isChecked()) {
                        mUserReq = mUtils.refreshUserReq("smallgame", mUserReq);
                    }

                    if (message.contains("office")) {
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
                    Toast.makeText(QueryGameActivity.this,"Please select at least one option", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
