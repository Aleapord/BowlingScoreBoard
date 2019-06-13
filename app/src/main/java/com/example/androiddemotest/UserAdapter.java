package com.example.androiddemotest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
//与recycleView配套使用的Adapter
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> users;

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //通过布局文件创建容器
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item,viewGroup,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        //为每一个容器设置内容
        final User user=users.get(i);
        viewHolder.score.setText("当前得分："+user.getScoreCur());
        viewHolder.id.setText("选手ID："+user.getId());
        viewHolder.name.setText(""+user.getName());
        //设置触摸监听事件
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), ScoreDetailActivity.class);
                //给下一个界面传送玩家，以便下一个界面通过id查询玩家的详细信息
                intent.putExtra("id",user.getId());
                //启动下一个界面
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    //列表每一行都是一个容器对象
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id;
        public TextView name;
        public TextView score;
        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //定义每一行的View，以便设置触摸响应事件
            view=itemView;
            //通过id找到控件
            id=itemView.findViewById(R.id.id);
            name=itemView.findViewById(R.id.name);
            score=itemView.findViewById(R.id.score);
        }
    }
}