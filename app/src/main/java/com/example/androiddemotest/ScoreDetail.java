package com.example.androiddemotest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
//从服务器程序COPY下来的选手分数详情类
public class ScoreDetail {
    private List<Score> scores;
    private FinalScore finalScore;
    private int totalScore;
    private String detail;

    int getTotalScore() {
        return totalScore;
    }

    String getDetail() {
        return detail;
    }

    ScoreDetail() {
        scores=new ArrayList<>();
        for(int i=1;i<=9;i++){
            scores.add(new Score());
        }
        finalScore=new FinalScore();
        detail=toString();
        totalScore=calTotal();

    }

    public String detailString(){

        Score[] scores1=castListToArray(scores);
        StringBuilder sb=new StringBuilder("");
        sb.append("轮数      ").append("  第一轮"+"\t").append("  第二轮"+"\t").append("  第三轮"+"\t").append("  第四轮"+"\t").append("  第五轮"+"\t").append("\n")
                .append("击倒瓶数"+"\t").append("   "+scores1[0].first+"  "+scores1[0].second+"\t").append("   "+scores1[1].first+"  "+scores1[1].second+"\t").append("        "+scores1[2].first+"  "+scores1[2].second+"\t").append("        "+scores1[3].first+"  "+scores1[3].second+"\t").append("        "+scores1[4].first+"  "+scores1[4].second+"\t").append("\n")
                .append("本轮得分\t").append("    "+scores1[0].score+"\t\t").append("       "+scores1[1].score+"\t\t").append("           "+scores1[2].score+"\t\t").append("          "+scores1[3].score+"\t\t").append("           "+scores1[4].score+"\t\t").append("\n")
                .append("轮数      ").append("  第六轮"+"\t").append("  第七轮"+"\t").append("  第八轮"+"\t").append("  第九轮"+"\t").append("  第十轮"+"\t").append("\n")
                .append("击倒瓶数"+"\t").append("   "+scores1[5].first+"  "+scores1[5].second+"\t").append("   "+scores1[6].first+"  "+scores1[6].second+"\t").append("        "+scores1[7].first+"  "+scores1[7].second+"\t").append("        "+scores1[8].first+"  "+scores1[8].second+"\t").append("        "+finalScore.first+" "+finalScore.second+" "+finalScore.third).append("\n")
                .append("本轮得分\t").append("    "+scores1[5].score+"\t\t").append("       "+scores1[6].score+"\t\t").append("         "+scores1[7].score+"\t\t").append("           "+scores1[8].score+"\t\t").append("         "+finalScore.score+"\t\t").append("\n");

        return sb.toString();
    }

    public ScoreDetail(String detail) {
        scores=new ArrayList<>();
        this.detail = detail;
        fromString(detail);
        totalScore = calTotal();
    }

    @Override
    public String toString() {
        StringBuilder stringBuffer=new StringBuilder("");
        for (Score s :
                scores) {
            stringBuffer.append(s.first).append(" ").append(s.second).append(" ");
        }
        stringBuffer.append(finalScore.first).append(" ").append(finalScore.second).append(" ").append(finalScore.third);
        return stringBuffer.toString();
    }

    private void fromString(String detail) {
        String[] strings=detail.split(" ");
        for(int i=0;i<18;i+=2){
            scores.add(new Score(Integer.parseInt(strings[i]), Integer.parseInt(strings[i+1])));
        }
        finalScore=new FinalScore(Integer.parseInt(strings[18]), Integer.parseInt(strings[19]), Integer.parseInt(strings[20]));

    }

    private int calTotal() {
        Iterator<Score> iterator=scores.iterator();
        Score[] scores=castListToArray(this.scores);
        for(int i=0;i<8;i++){
            if(scores[i].first==10){
                scores[i].score=10+scores[i+1].first+scores[i+1].second;
            }
            else if(scores[i].first+scores[i].second==10){
                scores[i].score=10+scores[i+1].first;
            }
            else {
                scores[i].score=scores[i].first+scores[i].second;
            }

        }


        if(scores[8].first==10){
            scores[8].score=10+finalScore.first+finalScore.second;
        }
        else if(scores[8].first+scores[8].second==10){
            scores[8].score=10+finalScore.first;
        }
        else {
            scores[8].score=scores[8].first+scores[8].second;
        }
        int sum=0;
        for(int i=0;i<9;i++){
            sum+=scores[i].score;
            iterator.next().score=scores[i].score;
        }
        sum+=finalScore.score;
        return sum;
    }
    private Score[] castListToArray(List<Score> scores) {

        Score[] scores1=new Score[scores.size()];
        for(int i=0;i<scores.size();i++){
            scores1[i]= new Score();
        }
        for(int i=0;i<scores.size();i++){
            scores1[i].first=scores.get(i).first;
            scores1[i].second=scores.get(i).second;
            scores1[i].score=scores.get(i).score;
        }
        return scores1;
    }

    static class Score {
        int first;
        int second;
        int score;

        Score(int first, int second) {
            this.first = first;
            this.second = second;
        }

        Score() {
            Random random=new Random();
            first=random.nextInt(11);
            second=random.nextInt(11-first);
        }
    }
    class FinalScore extends Score{
        int third;

        FinalScore(int first, int second, int third) {
            super(first, second);
            this.third = third;
            this.score=first+second+third;
        }
        FinalScore(){
            Random random=new Random();
            first=random.nextInt(11);
            if(first==10){
                second=random.nextInt(11);
                third=random.nextInt(11-second);
            }
            else{
                second=random.nextInt(11-first);
                if(first+second==10){
                    third=random.nextInt(11);

                }
                else {
                    third=random.nextInt(11-first-second);
                }
            }
            score=first+second+third;
        }

    }

    public List<Score> getScores() {
        return scores;
    }

    public FinalScore getFinalScore() {
        return finalScore;
    }
}
