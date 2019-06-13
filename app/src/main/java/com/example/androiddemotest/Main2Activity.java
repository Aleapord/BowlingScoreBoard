package com.example.androiddemotest;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.util.List;
//从服务器获取所有玩家信息，并通过RecycleView显示
public class Main2Activity extends AppCompatActivity {
    private List<User> users;
    private RecyclerView rv;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        rv=(RecyclerView)findViewById(R.id.rv);
        handler=new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    users=SqlRequest.getAllUser();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //通过玩家列表创建一个Recycle View的适配器
                        UserAdapter userAdapter=new UserAdapter(users);
                        //创建一个布局管理器，并设置为垂直显示
                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(Main2Activity.this,LinearLayoutManager.VERTICAL,false);
                        //设置布局管理器
                        rv.setLayoutManager(linearLayoutManager);
                        //设置默认动画
                        rv.setItemAnimator(new DefaultItemAnimator());
                        //设置适配器
                        rv.setAdapter(userAdapter);
                    }
                });

            }
        }).start();

    }
}
