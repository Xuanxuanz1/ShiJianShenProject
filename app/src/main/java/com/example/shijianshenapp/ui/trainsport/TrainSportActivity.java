package com.example.shijianshenapp.ui.trainsport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseActivity;
import com.example.shijianshenapp.bean.TrainBean;
import com.example.shijianshenapp.bean.TrainProgramBean;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TrainSportActivity extends BaseActivity<TrainSportContact.ITrainSportView,TrainSportPresenter> implements TrainSportContact.ITrainSportView {

    @BindView(R.id.ats_iv_ok)
    ImageView ivOk;
    @BindView(R.id.ats_iv_head)
    ImageView ivHead;
    @BindView(R.id.ats_tv_time)
    TextView tvTime;
    @BindView(R.id.ats_tv_train_sport_hint)
    TextView tvTrainTitle;
    @BindView(R.id.ats_btn_train_sport)
    Button btnTrainSport;
    @BindView(R.id.ats_btn_end_train_sport)
    Button btnEndTrainSport;

    private boolean isBegin = false;
    private int trainTime;
    private int waitTime;
    private final int UPDATE_UI_TIME = 1024;
    private final int UPDATE_UI_STOP = -1;
    private Timer timer;
    private TrainProgramBean trainProgramBean;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == UPDATE_UI_TIME){
                if (waitTime >= 0){
                    upDateUi(waitTime);
                    waitTime--;
                }
                else {
                    trainEnd();
                }
            }
        }
    };

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.train_sport);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_train_sport;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String posointent = intent.getStringExtra("trainPosition");
        TrainProgramBean data = (TrainProgramBean) intent.getSerializableExtra("data");
        presenter.fetchTrainSportData(data);
    }

    @Override
    public TrainSportPresenter initPresenter() {
        return new TrainSportPresenter(context);
    }

    public static void show(Context context, String position, TrainProgramBean data){
        Intent intent = new Intent(context,TrainSportActivity.class);
        intent.putExtra("trainPosition",position);
        intent.putExtra("data",data);
        context.startActivity(intent);
    }

    @OnClick(R.id.ats_btn_train_sport)
    public void btnTrainSport(View view){
        if (isBegin){
            isBegin = false;
            btnTrainSport.setText("开始训练");
            if (timer != null){
                timer.cancel();
                timer.purge();
                timer = null;
            }
        }
        else {
            if (timer == null){
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(UPDATE_UI_TIME);
                    }
                },0,1000);
            }
            isBegin = true;
            btnTrainSport.setText("暂停训练");
        }
    }

    @OnClick(R.id.ats_btn_end_train_sport)
    public void btnTrainEndSport(View view){
        if (trainTime == waitTime){
            showToast("未开始训练");
            return;
        }
        if (timer != null){
            timer.cancel();
            timer.purge();
            timer = null;
        }
        showAlert("提示",
                String.format("你的训练剩余%s秒", waitTime + 1),
                "继续训练", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isBegin == true){
                            if (timer == null){
                                timer = new Timer();
                                timer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        handler.sendEmptyMessage(UPDATE_UI_TIME);
                                    }
                                },0,1000);
                            }
                        }
                    }
                },
                "结束训练", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.resetTrainSportData();
                    }
                }
        );
    }

    @Override
    public void upDateUi(int time) {
        tvTime.setText(waitTime >= 10?String.format("00:%s",time):String.format("00:0%s",time));
    }

    @Override
    public void trainEnd() {
        TrainProgramBean.isTrainOff = true;
        showSingleAlertNoAction("提示",
                String.format("本次健身项目：%s 已完成",tvTrainTitle.getText().toString())
                );
        if (timer != null){
            timer.cancel();
            timer.purge();
            timer = null;
        }
        if (trainProgramBean != null){
            trainProgramBean.setTrain(true);
        }
        presenter.resetTrainSportData();
    }

    @Override
    public void loadTrainSportData(TrainProgramBean data) {
        trainProgramBean = data;
        Glide.with(this).load(data.getTrainImg()).asGif().centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(ivHead);
        tvTrainTitle.setText(data.getTrainName());
        waitTime = Integer.parseInt(data.getTrainTime());
        trainTime = Integer.parseInt(data.getTrainTime());
        tvTime.setText(waitTime >= 10?String.format("00:%s",data.getTrainTime()):String.format("00:0%s",data.getTrainTime()));
        trainTime = Integer.parseInt(data.getTrainTime());
        isBegin = false;
        ivOk.setVisibility(data.isTrain() ? View.VISIBLE : View.GONE);
        btnTrainSport.setText("开始训练");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null){
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }
}