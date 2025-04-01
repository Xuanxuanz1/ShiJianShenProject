package com.example.shijianshenapp.ui.sport;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.OnClick;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ayma.base.util.ArithUtil;
import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseActivity;
import com.example.shijianshenapp.bean.UserBean;
import com.example.shijianshenapp.weiget.CalendarView;

import org.litepal.Operator;

import java.util.ArrayList;
import java.util.List;

public class SportActivity extends BaseActivity<SportContact.ISportView,SportPresenter> implements SensorEventListener {

    @BindView(R.id.as_tv_plan_sport)
    TextView tvPlanSport;
    @BindView(R.id.as_tv_sport)
    TextView tvSport;
    @BindView(R.id.as_tv_calorie)
    TextView tvCalorie;
    @BindView(R.id.as_tv_clock)
    TextView tvClockState;
    @BindView(R.id.as_cl_clock_text)
    ConstraintLayout clClockText;
    @BindView(R.id.as_cl_clock_day)
    ConstraintLayout clClockDay;
    @BindView(R.id.as_cv)
    CalendarView cv;
    @BindView(R.id.as_btn_clock)
    Button btnClock;


    private SensorManager sensorManager;
    private Sensor sensor;
    float steps = 0;
    private View changePlanSportView;
    private AlertDialog changePlanSportDialog;

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.sport);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sport;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (sensor != null){
            sensorManager.registerListener(this,sensor,1000000);
        }
        else {
            showToast("本机无内置计步器");
        }

        if (UserBean.isClock){
            tvClockState.setText("已打卡");
            tvClockState.setTextColor(getResources().getColor(R.color.clock_success));
            clClockText.setVisibility(View.VISIBLE);
            cv.setVisibility(View.VISIBLE);
            btnClock.setText("已打卡");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        steps = event.values[0];
        tvSport.setText(String.valueOf((int) Float.parseFloat(String.format("%.0f",steps))));
        tvCalorie.setText(String.valueOf(ArithUtil.mul(steps,0.04)));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public SportPresenter initPresenter() {
        return new SportPresenter(context);
    }

    public static void show(Context context){
        context.startActivity(new Intent(context,SportActivity.class));
    }

    @OnClick(R.id.as_tv_change_plan_sport)
    public void onChangePlanSport(View view){
        onChangePlanSport();
    }

    @OnClick(R.id.as_btn_clock)
    public void onClock(View view){
        clockNow();
    }

    private void onChangePlanSport(){
        if (changePlanSportView == null){
            changePlanSportView  = getLayoutInflater().inflate(R.layout.dialog_change_plan_sport,null,false);
        }
        EditText etSport = changePlanSportView.findViewById(R.id.acps_et_change_plan_sport);
        Button btnCancle = changePlanSportView.findViewById(R.id.dcps_btn_cancle);
        Button btnSubmit = changePlanSportView.findViewById(R.id.dcps_btn_submit);

        if (changePlanSportDialog == null){
            changePlanSportDialog = new AlertDialog.Builder(context).setView(changePlanSportView).setCancelable(false).create();
        }

        etSport.setText(tvPlanSport.getText().toString());
        etSport.requestFocus();
        btnCancle.setOnClickListener(v->{
            if (!tvPlanSport.getText().toString().equals(etSport.getText().toString().trim())){
                showAlert("提示",
                        "您修改了计划步数尚未保存，是否继续退出？",
                        "取消", null,
                        "确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                changePlanSportDialog.dismiss();
                            }
                        }
                );
                return;
            }
            changePlanSportDialog.dismiss();
        });

        btnSubmit.setOnClickListener(v->{
            if (tvPlanSport.getText().toString().equals(etSport.getText().toString().trim())){
                showToast("未作任何修改，保存失败");
                return;
            }
            tvPlanSport.setText(etSport.getText().toString().trim());
            changePlanSportDialog.dismiss();
        });

        if (changePlanSportDialog.getWindow() != null){
            changePlanSportDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        if (!isFinishing()){
            changePlanSportDialog.show();
        }
    }

    private void clockNow(){
        if (Integer.parseInt(tvPlanSport.getText().toString()) > Integer.parseInt(tvSport.getText().toString())){
            showToast("今日步数未达标，打卡失败，要继续运动哦！");
            return;
        }
        if (tvClockState.getText().toString().equals("已打卡")) {
            showToast("今日已打卡");
            return;
        }
        showLoading("正在打卡中");
        UserBean.isClock = true;
        List<UserBean> list = Operator.where("userName=?",UserBean.loginName).find(UserBean.class);
        for (UserBean data : list){
            if (TextUtils.isEmpty(String.valueOf(data.getIntegration()))){
                data.setIntegration(1);
            }
            else {
                data.setIntegration(data.getIntegration() + 1);
            }
        }
        Operator.saveAll(list);
        hideLoading();
        tvClockState.setText("已打卡");
        tvClockState.setTextColor(getResources().getColor(R.color.clock_success));
        clClockDay.setVisibility(View.VISIBLE);
        clClockText.setVisibility(View.VISIBLE);
        cv.setVisibility(View.VISIBLE);
    }
}