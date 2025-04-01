package com.example.shijianshenapp.ui.about;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.OnClick;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseActivity;
import com.example.shijianshenapp.ui.feedback.FeedBackActivity;

public class AboutActivity extends BaseActivity<AboutContact.IAboutView,AboutPresenter> implements AboutContact.IAboutView {

    private View callUsView;
    private AlertDialog callUsDialog;

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.about);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public AboutPresenter initPresenter() {
        return new AboutPresenter(context);
    }

    public static void show(Context context){
        context.startActivity(new Intent(context,AboutActivity.class));
    }

    @OnClick(R.id.aa_cl_feedback)
    public void onToFeedBack(View view){
        FeedBackActivity.show(context);
    }

    @OnClick(R.id.aa_cl_check_new)
    public void onCheckNew(View view){
        checkNew();
    }

    @OnClick(R.id.aa_cl_call_us)
    public void onCallUs(View view){
        callUs();
    }

    @Override
    public void checkNew() {
        presenter.checkNew();
    }

    @Override
    public void callUs() {
        if (callUsView == null){
            callUsView = getLayoutInflater().inflate(R.layout.dialog_about_call_us,null,false);
        }
        if (callUsDialog == null){
            callUsDialog = new AlertDialog.Builder(context).setView(callUsView).setCancelable(false).create();
        }

        Button btnCancle = callUsView.findViewById(R.id.dacu_btn_cancle);
        Button btnSubmit = callUsView.findViewById(R.id.dacu_btn_submit);

        btnCancle.setOnClickListener(v->{
            callUsDialog.dismiss();
        });

        btnSubmit.setOnClickListener(v->{
            callUsDialog.dismiss();
        });

        if (callUsDialog.getWindow() != null){
            callUsDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        if (!isFinishing()){
            callUsDialog.show();
        }
    }
}