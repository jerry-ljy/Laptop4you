package com.laptop4you;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.laptop4you.utils.Utils;

import androidx.appcompat.app.AppCompatActivity;

public class QueryHomeActivity extends AppCompatActivity {

    private Button mBtnNext;
    private CheckBox mSurf,mVideo;
    private Utils mUtils;
    private int[] mUserReq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_home);
        mBtnNext = findViewById(R.id.btn_home_next);
        mSurf = findViewById(R.id.home_opt_1);
        mVideo=findViewById(R.id.home_opt_2);

        mUtils=new Utils();
        Bundle bd = this.getIntent().getExtras();
        mUserReq = bd.getIntArray("userReq");

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSurf.isChecked() || mVideo.isChecked()) {
                    if (mVideo.isChecked()) {
                        mUserReq = mUtils.refreshUserReq("video", mUserReq);
                    }

                    Intent intent = new Intent(v.getContext(), QueryPriceActivity.class);
                    Bundle bd = new Bundle();
                    bd.putIntArray("userReq", mUserReq);
                    intent.putExtras(bd);
                    startActivity(intent);
                }else{
                    Toast.makeText(QueryHomeActivity.this,"Please select at least one option", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
