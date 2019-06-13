package com.example.androiddemotest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
这个类的主要功能是：给服务器发送http请求，操控服务器程序对数据库中的数据进行修改
 */
public class SqlRequest {
    //服务器地址
    private final static String url="http://207.148.101.11:8080/";
   // private final static String url="http://localhost:8080/";

    //清空分数数据
    public static void start() throws IOException {
        OkHttpClient client=new OkHttpClient();
        client.setReadTimeout(1, TimeUnit.DAYS);

        Request request=new Request.Builder().url(url+"start").build();
        client.newCall(request).execute();
    }
    //给玩家分组
    public static void group(int groupNum) throws IOException {
        OkHttpClient client=new OkHttpClient();
        client.setReadTimeout(1, TimeUnit.DAYS);

        Request request=new Request.Builder().url(url+"group?groupNum="+groupNum).build();
        client.newCall(request).execute();
    }
    //进行游戏（生成随机分数）
    public static void play() throws IOException {
        OkHttpClient client=new OkHttpClient();
        client.setReadTimeout(1, TimeUnit.DAYS);

        Request request=new Request.Builder().url(url+"play").build();
        client.newCall(request).execute();
    }
    //按小组方式进行排名（如果两个玩家在一个小组，那么他们的排名是相同的）
    public static void rank(int groupNum) throws IOException {
        OkHttpClient client=new OkHttpClient();
        client.setReadTimeout(1, TimeUnit.DAYS);

        Request request=new Request.Builder().url(url+"rank?groupNum="+groupNum).build();
        client.newCall(request).execute();
    }
    //获取所有玩家实体（使用Gson库对服务器返回的JSON数据处理，返回所有玩家列表）
    public static List<User> getAllUser() throws IOException {

        OkHttpClient client=new OkHttpClient();
        client.setReadTimeout(1, TimeUnit.DAYS);

        Request request=new Request.Builder().url(url).build();
        Response response=client.newCall(request).execute();
        String responseDate=response.body().string();
        Gson gson=new Gson();
        return gson.fromJson(responseDate,new TypeToken<List<User>>(){}.getType());
    }
    //获取一组玩家实体
    static List<User> getGroup(int groupNum) throws IOException {
        OkHttpClient client=new OkHttpClient();
        client.setReadTimeout(1, TimeUnit.DAYS);

        Request request=new Request.Builder().url(url+"selectGroup?groupNum="+groupNum).build();
        Response response=client.newCall(request).execute();
        String responseDate=response.body().string();
        Gson gson=new Gson();
        return gson.fromJson(responseDate,new TypeToken<List<User>>(){}.getType());
    }
    //通过id获取一名玩家
    static User getUserById(int id) throws IOException {
        OkHttpClient client=new OkHttpClient();
        client.setReadTimeout(1, TimeUnit.DAYS);

        Request request=new Request.Builder().url(url+"ById?id="+id).build();
        Response response=client.newCall(request).execute();
        String responseDate=response.body().string();
        Gson gson=new Gson();
        return gson.fromJson(responseDate,new TypeToken<User>(){}.getType());
    }

}
