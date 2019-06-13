package com.example.androiddemotest;
  //用户实体类
public class User {
    /*以下参数一一对应数据库中的每一列
     */
    private int id;
    private String name;
    private String sex;
    private int age;
    private String comeFrom;
    private double scoreCur;
    private int rankCur;
    private String scoreDetail;

    //将选手基本信息输出成字符串
    @Override
    public String toString() {
        return "选手姓名："+name+"\n" +
                "选手编号："+id+"\n" +
                "选手性别："+sex+"\n" +
                "来源地："+comeFrom+"\n" +
                "年龄："+age+"\n" +
                "组别："+groupCur+"\n" +
                "小组（单人）排名："+rankCur+"\n" +
                "得分详情：";}

    public int getGroupCur() {
        return groupCur;
    }

    public void setGroupCur(int groupCur) {
        this.groupCur = groupCur;
    }

    private int groupCur;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getComeFrom() {
        return comeFrom;
    }

    public void setComeFrom(String comeFrom) {
        this.comeFrom = comeFrom;
    }

    public double getScoreCur() {
        return scoreCur;
    }

    public void setScoreCur(double scoreCur) {
        this.scoreCur = scoreCur;
    }

    public int getRankCur() {
        return rankCur;
    }

    public void setRankCur(int rankCur) {
        this.rankCur = rankCur;
    }

    public String getScoreDetail() {
        return scoreDetail;
    }

    public void setScoreDetail(String scoreDetail) {
        this.scoreDetail = scoreDetail;
    }
}