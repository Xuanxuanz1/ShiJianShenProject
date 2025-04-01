package com.example.shijianshenapp.ui.feedback;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseActivity;

public class FeedBackActivity extends BaseActivity<FeedBackContact.IFeedBackView,FeedBackPresenter> implements FeedBackContact.IFeedBackView {

    @BindView(R.id.afb_et_feed_back)
    EditText etFeedBack;

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.feedback);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public FeedBackPresenter initPresenter() {
        return new FeedBackPresenter(context);
    }

    public static void show(Context context){
        context.startActivity(new Intent(context,FeedBackActivity.class));
    }

    @OnClick(R.id.afb_btn_submit)
    public void onBtnSumit(View view){
        submit();
    }

    @Override
    public void submit() {
        if (TextUtils.isEmpty(etFeedBack.getText().toString().trim()) || etFeedBack.getText().toString().trim().equals("")){
            showToast("您要提交的意见为空");
            return;
        }
        presenter.submit(etFeedBack.getText().toString().trim());
    }

    @Override
    public void submitSuccess() {
        showToast("我们已收到您的反馈");
        finishActivity();
    }
}