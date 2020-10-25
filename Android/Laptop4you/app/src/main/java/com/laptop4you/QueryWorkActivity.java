package com.laptop4you;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.laptop4you.utils.Utils;

public class QueryWorkActivity extends AppCompatActivity {

    private Button mBtnNext;
    private CheckBox mDesign, mCode, mOffice;
    private Utils mUtils;
    private int[] mUserReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_work);
        mDesign=findViewById(R.id.work_opt_1);
        mCode=findViewById(R.id.work_opt_2);
        mOffice=findViewById(R.id.work_opt_3);

        mUtils = new Utils();
        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE");
        Bundle bd = this.getIntent().getExtras();
        mUserReq = bd.getIntArray("userReq");

        mBtnNext = findViewById(R.id.btn_work_next);
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDesign.isChecked() || mCode.isChecked() || mOffice.isChecked()) {
                    if (mDesign.isChecked()) {
                        mUserReq = mUtils.refreshUserReq("design", mUserReq);
                    }
                    if (mCode.isChecked()) {
                        mUserReq = mUtils.refreshUserReq("code", mUserReq);
                    }
                    if (mOffice.isChecked()) {
                        mUserReq = mUtils.refreshUserReq("office", mUserReq);
                    }

                    if (message.contains("home")) {
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
                    Toast.makeText(QueryWorkActivity.this,"Please select at least one option", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}