package com.example.androiddemotest;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.RadioGroup;


//APP启动的第一个界面，选择比赛模式
public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private int groupNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        groupNum=1;

    }

    public void selectGroup(View view) {
        //通过获取radio group的id来确定分组数
        switch(radioGroup.getCheckedRadioButtonId()){
            case R.id.one:break;
            case R.id.two:groupNum=2;break;
            case R.id.three:groupNum=3;break;
            case R.id.five:groupNum=5;break;
        }
        //创建一个intent来启动下一个页面
        Intent intent=new Intent(MainActivity.this,Main3Activity.class);
        //将分组数传给intent
        intent.putExtra("groupNum",groupNum);
        //启动下一个界面
        startActivity(intent);
    }
}
