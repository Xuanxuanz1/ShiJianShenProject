package com.example.shijianshenapp.ui.find;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseFragment;
import com.example.shijianshenapp.bean.FindBean;
import com.example.shijianshenapp.bean.TrainBean;
import com.example.shijianshenapp.ui.find.circle.FindCircleActivity;
import com.example.shijianshenapp.ui.find.friend.FindFriendActivity;
import com.example.shijianshenapp.ui.train.TrainFragment;
import com.example.shijianshenapp.ui.video.VideoActivity;
import com.example.shijianshenapp.weiget.TanStackLayout;

import java.util.ArrayList;
import java.util.List;

public class FindFragment extends BaseFragment<FindContact.IFindView,FindPresenter> implements FindContact.IFindView {

    @BindView(R.id.aff_tv_rlv)
    RecyclerView rlvVideo;

    private FindAdapter adapter;

    @Override
    public FindPresenter initPresenter() {
        return new FindPresenter(activity);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_find_fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String setTitle() {
        return getString(R.string.find);
    }

    @OnClick(R.id.aff_cl_friend)
    public void onToFindFriend(View view){
        FindFriendActivity.show(activity);
    }

    @OnClick(R.id.aff_cl_circle)
    public void onToFindCircle(View view){
        FindCircleActivity.show(activity);
    }

    @Override
    public void notifyVideoList(List<TrainBean> list) {
        if (adapter == null){
            List<TrainBean> bean = new ArrayList<>();
            TypedArray ar = getResources().obtainTypedArray(R.array.train_video);
            for (int i = 0;i < 4;i++){
                TrainBean data = list.get(i);
                data.setHeadImg(ar.getResourceId(i,0));
                bean.add(data);
            }
            ar.recycle();
            adapter = new FindAdapter(bean);
            rlvVideo.setAdapter(adapter);
            rlvVideo.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        }
        else {
            adapter.notifyDataSetChanged();
        }
    }

    class FindAdapter extends RecyclerView.Adapter<FindAdapter.ViewHolder>{

        List<TrainBean> list;

        public FindAdapter(List<TrainBean> list){
            this.list = list;
        }

        @NonNull
        @Override
        public FindAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(getLayoutInflater().inflate(R.layout.item_train,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull FindAdapter.ViewHolder holder, int position) {
            TrainBean data = list.get(position);

            Glide.with(activity).load(data.getHeadImg()).centerCrop().into(holder.ivHead);
            holder.tvTime.setText(data.getTime());
            holder.tvWatch.setText(data.getWatchNumber());
            holder.tvTitle.setText(data.getTitle());
            holder.ivLike.setVisibility(View.GONE);
            holder.tvLikeNumber.setVisibility(View.GONE);
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