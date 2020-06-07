package com.tadm.framelayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recycleView;
    private String[] gameNames = {"打地鼠", "石头剪刀布", "扑克牌"};
    private int[] gameImgs = {R.drawable.wifi01, R.drawable.wifi02, R.drawable.wifi03};
    List<GameInfoBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initDatas();
        initViews();
    }

    private void initDatas() {
        for (int i = 0; i < gameNames.length; i++) {
            GameInfoBean gameInfoBean = new GameInfoBean();
            gameInfoBean.setGameImage(gameImgs[i]);
            gameInfoBean.setGameName(gameNames[i]);
            list.add(gameInfoBean);
        }
    }

    private void initViews() {
        recycleView = (RecyclerView) findViewById(R.id.recycleView);
//        recycleView.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this));
//        recycleView.setLayoutManager(new GridLayoutManager(RecyclerView.this,2));
        recycleView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recycleView.setAdapter(new MyAdapter(list, RecyclerViewActivity.this));
    }
}

class MyAdapter extends RecyclerView.Adapter<MyHolder2> {
    private List<GameInfoBean> list;
    private Context context;

    public MyAdapter(List<GameInfoBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gamelist_item, null);
        MyHolder2 myHolder2 = new MyHolder2(view);

        return myHolder2;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder2 holder, int position) {
        final GameInfoBean gameInfoBean = list.get(position);
        holder.imgListitem.setImageResource(gameInfoBean.getGameImage());
        holder.textListitem.setText(gameInfoBean.getGameName());
//        holder.textListitem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, gameInfoBean.getGameName(), Toast.LENGTH_SHORT).show();
//            }
//        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Your choice is "+gameInfoBean.getGameName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class MyHolder2 extends RecyclerView.ViewHolder {
    public ImageView imgListitem;
    public TextView textListitem;

    public MyHolder2(@NonNull View itemView) {
        super(itemView);
        imgListitem = (ImageView) itemView.findViewById(R.id.img_listitem);
        textListitem = (TextView) itemView.findViewById(R.id.text_listitem);
    }
}

