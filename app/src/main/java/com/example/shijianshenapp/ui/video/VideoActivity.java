package com.example.shijianshenapp.ui.video;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseActivity;
import com.example.shijianshenapp.bean.TrainBean;
import com.example.shijianshenapp.bean.TrainProgramBean;
import com.example.shijianshenapp.ui.trainsport.TrainSportActivity;

import java.util.List;

public class VideoActivity extends BaseActivity<VideoContact.IVideoView,VideoPresenter> implements VideoContact.IVideoView {

    @BindView(R.id.av_vv)
    VideoView videoView;
    @BindView(R.id.av_iv_head)
    ImageView ivUpHead;
    @BindView(R.id.av_tv_title)
    TextView tvTitle;
    @BindView(R.id.av_tv_detail)
    TextView tvDetail;
    @BindView(R.id.av_tv_show_detail)
    TextView tvShowDetail;
    @BindView(R.id.av_rlv)
    RecyclerView rlvData;

    private String url;
    private boolean isShow = false;
    private boolean isShowDialog = false;
    private AlertDialog upDialog;
    private View upView;
    private VideoAdapter adapter;
    private int positition;
    private int clickPosition = -1;

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.train_video);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        positition = Integer.parseInt(intent.getStringExtra("positition_video"));
        TrainProgramBean.isTrainOff = false;
        presenter.fetchVideoData(positition);
        switch (positition){
            case 0:
                url = "android.resource://"+getPackageName()+"/"+R.raw.video_0;
//                url = "https://www.bilibili.com/video/BV1wp4y1S7jw/?from=search&seid=4886565954969722335&spm_id_from=333.337.0.0&vd_source=125f562c708c46027767c354b3f19ef1";
                break;
            case 1:
                url = "android.resource://"+getPackageName()+"/"+R.raw.video_1;
                break;
            case 2:
                url = "android.resource://"+getPackageName()+"/"+R.raw.video_2;
                break;
            case 3:
                url = "android.resource://"+getPackageName()+"/"+R.raw.video_3;
                break;
            case 4:
                url = "android.resource://"+getPackageName()+"/"+R.raw.video_4;
                break;
            case 5:
                url = "android.resource://"+getPackageName()+"/"+R.raw.video_5;
                break;
            case 6:
                url = "android.resource://"+getPackageName()+"/"+R.raw.video_6;
                break;
            case 7:
                url = "android.resource://"+getPackageName()+"/"+R.raw.video_7;
                break;
            case 8:
                url = "android.resource://"+getPackageName()+"/"+R.raw.video_8;
                break;
            case 9:
                url = "android.resource://"+getPackageName()+"/"+R.raw.video_9;
                break;
            default:
                url = "android.resource://"+getPackageName()+"/"+R.raw.video_0;
                break;
        }
        videoView.setVideoURI(Uri.parse(url));
        videoView.start();

        MediaController controller = new MediaController(this);
        controller.setMediaPlayer(videoView);
        videoView.setMediaController(controller);
    }

    @Override
    public VideoPresenter initPresenter() {
        return new VideoPresenter(context);
    }

    public static void show(Context context,String positition){
        Intent intent = new Intent(context,VideoActivity.class);
        intent.putExtra("positition_video",positition);
        context.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (clickPosition == -1){
            return;
        }
        if (TrainProgramBean.isTrainOff){
            presenter.trainSuccess(positition,clickPosition);
        }
    }

    @Override
    public void init(TrainBean data) {
        tvTitle.setText(data.getTitle());
        tvDetail.setText(data.getMessage());
        tvShowDetail.setOnClickListener(v->{
            if (isShow){
                isShow = false;
                tvShowDetail.setText("展开全部");
                tvDetail.setMaxLines(1);
            }
            else {
                isShow = true;
                tvShowDetail.setText("收起展开");
                tvDetail.setMaxLines(5);
            }
        });
        ivUpHead.setOnClickListener(v->{
            showUpDialog();
        });
    }

    @Override
    public void showUpDialog() {
        if (upView == null){
            upView = getLayoutInflater().inflate(R.layout.dialog_up,null,false);
        }
        if (upDialog == null){
            upDialog = new AlertDialog.Builder(context).setView(upView).setCancelable(false).create();
        }

        ImageView ivClose = upView.findViewById(R.id.au_iv_close);
        TextView tvShowAll = upView.findViewById(R.id.au_tv_show);
        TextView tvBrief = upView.findViewById(R.id.au_tv_brief);

        ivClose.setOnClickListener(v->{
            upDialog.dismiss();
        });

        tvShowAll.setOnClickListener(v -> {
            if (isShowDialog){
                isShowDialog = false;
                tvShowAll.setText("展开全部");
                tvBrief.setMaxLines(1);
            }
            else {
                isShowDialog = true;
                tvShowAll.setText("收起展开");
                tvBrief.setMaxLines(5);
            }
        });

        if (upDialog.getWindow() != null){
            upDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        if (!isFinishing()){
            upDialog.show();
        }
    }

    @Override
    public void notifyTrainSportList(List<TrainProgramBean> list) {
        if (adapter == null){
            adapter = new VideoAdapter(list);
            rlvData.setAdapter(adapter);
            rlvData.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        }
        else {
            adapter.notifyDataSetChanged();
        }
    }

    class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{

        List<TrainProgramBean> list;

        public VideoAdapter(List<TrainProgramBean> list){
            this.list = list;
        }

        @NonNull
        @Override
        public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(getLayoutInflater().inflate(R.layout.item_video,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
            TrainProgramBean data = list.get(position);
            holder.tvTrainName.setText(data.getTrainName());
            holder.tvTrainTime.setText(String.format("%ss",data.getTrainTime()));
            holder.ivOk.setVisibility(data.isTrain() ? View.VISIBLE : View.INVISIBLE);
            holder.root.setOnClickListener(v->{
                if (clickPosition != holder.getLayoutPosition()){
                    TrainProgramBean.isTrainOff = false;
                    clickPosition = holder.getLayoutPosition();
                }
                TrainSportActivity.show(context,String.valueOf(holder.getLayoutPosition()),data);
            });
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.item_tv_train_name)
            TextView tvTrainName;
            @BindView(R.id.item_tv_train_time)
            TextView tvTrainTime;
            @BindView(R.id.root)
            LinearLayout root;
            @BindView(R.id.iv_ok)
            ImageView ivOk;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
}