package com.example.shijianshenapp.ui.forget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import com.wayww.edittextfirework.FireworkView;

public class ForgetActivity extends BaseActivity<ForgetContact.IForgetView,ForgetPresenter> implements ForgetContact.IForgetView {

    @BindView(R.id.af_et_user_name)
    EditText etUserName;
    @BindView(R.id.af_cl_check_question)
    ConstraintLayout clCheckQuestion;
    @BindView(R.id.af_et_question)
    EditText etQuestion;
    @BindView(R.id.af_cl_change_password)
    ConstraintLayout clChangePassword;
    @BindView(R.id.af_et_change_password)
    EditText etChangePassword;
    @BindView(R.id.af_et_change_password_again)
    EditText etChangePasswordAgain;
    @BindView(R.id.fire_work1)
    FireworkView fireworkView1;
    @BindView(R.id.fire_work2)
    FireworkView fireworkView2;
    @BindView(R.id.fire_work3)
    FireworkView fireworkView3;
    @BindView(R.id.fire_work4)
    FireworkView fireworkView4;

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.forget);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fireworkView1.bindEditText(etUserName);
        fireworkView2.bindEditText(etQuestion);
        fireworkView3.bindEditText(etChangePassword);
        fireworkView4.bindEditText(etChangePasswordAgain);
    }

    @OnClick(R.id.af_btn_check)
    public void onCheckUserNameIsExist(View view){
        checkIsExist();
    }

    @OnClick(R.id.af_btn_check_birthday)
    public void onFindUser(View view){
        findUser();
    }

    @OnClick(R.id.af_btn_submit)
    public void onChangePassword(View view){
        changePassword();
    }

    @Override
    public ForgetPresenter initPresenter() {
        return new ForgetPresenter(context);
    }

    public static void show(Context context){
        context.startActivity(new Intent(context,ForgetActivity.class));
    }

    @Override
    public void checkIsExist() {
        if (TextUtils.isEmpty(etUserName.getText())){
            showToast("请输入您的账号");
            return;
        }
        presenter.checkIsExist(etUserName.getText().toString().trim());
    }

    @Override
    public void userExistSuccess() {
        clCheckQuestion.setVisibility(View.VISIBLE);
    }

    @Override
    public void findUser() {
        if (TextUtils.isEmpty(etQuestion.getText())){
            showToast("请输入您的生日");
            return;
        }
        presenter.findUser(etUserName.getText().toString().trim(),etQuestion.getText().toString().trim());
    }

    @Override
    public void findUserSuccess() {
        clChangePassword.setVisibility(View.VISIBLE);
    }

    @Override
    public void changePassword() {
        if (TextUtils.isEmpty(etChangePassword.getText())){
            showToast("请输入新密码");
            return;
        }
        if (TextUtils.isEmpty(etChangePasswordAgain.getText())){
            showToast("请再次输入新密码");
            return;
        }
        if (!etChangePassword.getText().toString().trim().equals(etChangePasswordAgain.getText().toString().trim())){
            showToast("两次输入密码不一致，请确认后重新输入");
            return;
        }
        presenter.changePassword(etUserName.getText().toString().trim(),etChangePassword.getText().toString().trim());
    }

    @Override
    public void changePasswordSuccess() {
        showToast("密码修改成功");
        finishActivity();
    }
}