package com.example.androiddemotest;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

public class ScoreDetailActivity extends AppCompatActivity {
    private TextView basic;
    private TextView detail;
    private TextView total;
    private Handler handler;
    private ScoreDetail scoreDetail;
    private User user;
    private int id;
//通过id获得用户实体，并将详细信息展示
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_detail);
        basic=(TextView)findViewById(R.id.textView);
        detail=(TextView)findViewById(R.id.textView2);
        total=(TextView)findViewById(R.id.textView3);
        handler=new Handler();
        Intent intent=getIntent();
        id=intent.getIntExtra("id",1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    user=SqlRequest.getUserById(id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                scoreDetail=new ScoreDetail(user.getScoreDetail());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //在文本框里展示信息
                        basic.append("\n"+user.toString());
                        detail.append(scoreDetail.detailString());
                        total.append(user.getScoreCur()+"");
                    }
                });
            }
        }).start();
    }
}
