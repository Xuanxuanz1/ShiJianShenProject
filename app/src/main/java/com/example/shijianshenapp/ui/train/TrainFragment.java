package com.example.shijianshenapp.ui.train;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseFragment;
import com.example.shijianshenapp.bean.TrainBean;
import com.example.shijianshenapp.ui.video.VideoActivity;

import java.util.List;

public class TrainFragment extends BaseFragment<TrainContact.ITrainView,TrainPresenter> implements TrainContact.ITrainView {

    @BindView(R.id.atf_rlv)
    RecyclerView rlvData;

    private TrainAdapter adapter;
    @Override
    public TrainPresenter initPresenter() {
        return new TrainPresenter(activity);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_train_fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String setTitle() {
        return getString(R.string.train);
    }

    @Override
    public void showTrainData(List<TrainBean> list) {
        if (adapter == null){
            TypedArray ar = getResources().obtainTypedArray(R.array.train_video);
            int i = 0;
            for (TrainBean data : list){
                if (i <= 10){
                    data.setHeadImg(ar.getResourceId(i,0));
                }
                else {
                    data.setHeadImg(R.drawable.friend_01);
                }
                i++;
            }
            adapter = new TrainAdapter(list);
            rlvData.setAdapter(adapter);
            rlvData.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        }
        else {
            adapter.notifyDataSetChanged();
        }
    }

    class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.ViewHolder>{

        List<TrainBean> list;

        public TrainAdapter(List<TrainBean> list){
            this.list = list;
        }

        @NonNull
        @Override
        public TrainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(getLayoutInflater().inflate(R.layout.item_train,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull TrainAdapter.ViewHolder holder, int position) {
            TrainBean data = list.get(position);

            Glide.with(activity).load(data.getHeadImg()).centerCrop().into(holder.ivHead);
            holder.tvTime.setText(data.getTime());
            holder.tvWatch.setText(data.getWatchNumber());
            holder.tvTitle.setText(data.getTitle());
            if (data.isLike()){
                holder.ivLike.setImageResource(R.mipmap.love_click);
                holder.tvLikeNumber.setText(data.getLikeNumber() > 10000 ? String.format("%s万",data.getLikeNumber() / 10000) :String.valueOf(data.getLikeNumber()+1));
            }
            if (!data.isLike()){
                holder.ivLike.setImageResource(R.mipmap.love_unclick);
                holder.tvLikeNumber.setText(data.getLikeNumber() > 10000 ? String.format("%s万",data.getLikeNumber() / 10000) : String.valueOf(data.getLikeNumber()));
            }
            holder.ivLike.setOnClickListener(v->{
                data.setLike(!data.isLike());
                showTrainData(list);
            });
            holder.root.setOnClickListener(v->{
//                showToast(holder.getLayoutPosition()+"");
                VideoActivity.show(activity,String.valueOf(holder.getLayoutPosition()));
            });
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.it_iv_head)
            ImageView ivHead;
            @BindView(R.id.it_tv_time)
            TextView tvTime;
            @BindView(R.id.it_tv_watch)
            TextView tvWatch;
            @BindView(R.id.it_title)
            TextView tvTitle;
            @BindView(R.id.it_iv_like)
            ImageView ivLike;
            @BindView(R.id.it_tv_like)
            TextView tvLikeNumber;
            @BindView(R.id.root)
            CardView root;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
}