package com.example.androiddemotest;

import android.content.Intent;
import android.os.Handler;
import android.os.Trace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Main3Activity extends AppCompatActivity {
    private TextView textView;
    private Handler handler;
    private static int groupNum;

    //本活动创建线程来给服务器发送http请求，并提示等待
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView=(TextView)findViewById(R.id.textView5);
        //从intent获取分组数
        groupNum=getIntent().getIntExtra("groupNum",1);
        handler=new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SqlRequest.start();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("正在分组。。。");
                        }
                    });
                    SqlRequest.group(groupNum);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("正在进行激烈的比赛^_^ 请稍后");
                        }
                    });
                    SqlRequest.play();
                   handler.post(new Runnable() {
                       @Override
                       public void run() {
                           textView.setText("裁判员正在统计比赛结果。。。");
                       }
                   });
                    SqlRequest.rank(groupNum);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Main3Activity.this,"终于结束了！",Toast.LENGTH_SHORT).show();
                        }
                    });
                    startActivity(new Intent(Main3Activity.this,Main2Activity.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


}
