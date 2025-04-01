package com.example.shijianshenapp.ui.changepassword;

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

public class ChangePasswordActivity extends BaseActivity<ChangePasswordContact.IChangePasswordView,ChangePasswordPresenter> implements ChangePasswordContact.IChangePasswordView {

    @BindView(R.id.acp_et_password)
    EditText etPassword;
    @BindView(R.id.acp_et_change_password)
    EditText etChangePassword;
    @BindView(R.id.acp_et_change_password_again)
    EditText etChangePasswordAgain;
    @BindView(R.id.fire_work1)
    FireworkView fireworkView1;
    @BindView(R.id.fire_work2)
    FireworkView fireworkView2;
    @BindView(R.id.fire_work3)
    FireworkView fireworkView3;

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.change_password);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fireworkView1.bindEditText(etPassword);
        fireworkView2.bindEditText(etChangePassword);
        fireworkView3.bindEditText(etChangePasswordAgain);
    }

    public static void show(Context context){
        context.startActivity(new Intent(context,ChangePasswordActivity.class));
    }

    @OnClick(R.id.acp_btn_change_password)
    public void onbtnChangePassword(View view){
        changePassword();
    }

    @Override
    public ChangePasswordPresenter initPresenter() {
        return new ChangePasswordPresenter(context);
    }

    @Override
    public void changePassword() {
        if(TextUtils.isEmpty(etPassword.getText())){
            showToast("请输入原密码");
            return;
        }
        if (TextUtils.isEmpty(etChangePassword.getText())){
            showToast("请输入新密码");
            return;
        }
        if (TextUtils.isEmpty(etChangePasswordAgain.getText())){
            showToast("请再次输入新密码");
            return;
        }
        if (!etChangePassword.getText().toString().trim().equals(etChangePasswordAgain.getText().toString().trim())){
            showToast("两次输入的新密码不一致");
            return;
        }
        presenter.changePassword(etPassword.getText().toString().trim(),etChangePassword.getText().toString().trim());
    }

    @Override
    public void changePasswordSuccess() {
        showToast("密码修改成功");
        finishActivity();
    }
}