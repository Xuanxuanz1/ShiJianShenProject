package com.example.shijianshenapp.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.OnClick;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseActivity;
import com.example.shijianshenapp.bean.UserBean;
import com.example.shijianshenapp.ui.admin.self.AdminActivity;
import com.example.shijianshenapp.ui.forget.ForgetActivity;
import com.example.shijianshenapp.ui.main.MainActivity;
import com.example.shijianshenapp.ui.register.RegisterActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.wayww.edittextfirework.FireworkView;

import org.litepal.Operator;

import java.util.List;

public class LoginActivity extends BaseActivity<LoginContact.ILoginView,LoginPresenter> implements LoginContact.ILoginView {

    @BindView(R.id.al_et_user_name)
    EditText etUserName;
    @BindView(R.id.al_et_user_password)
    EditText etUserPassword;
    @BindView(R.id.fire_work1)
    FireworkView fireworkView1;
    @BindView(R.id.fire_work2)
    FireworkView fireworkView2;
    @BindView(R.id.root)
    ConstraintLayout layout;
    @BindView(R.id.al_ck_remember)
    CheckBox ckRemember;
    @BindView(R.id.al_el_user_name)
    TextInputLayout tlUserName;
    @BindView(R.id.al_el_user_password)
    TextInputLayout tlPassword;
    @BindView(R.id.al_tv_night)
    TextView tvNight;
    @BindView(R.id.al_tv_day)
    TextView tvDay;
    @BindView(R.id.al_tv_lose_password)
    TextView tvLosePassword;
    @BindView(R.id.al_tv_new_user)
    TextView tvNewUser;


    @Override
    protected String setTitle() {
        return getString(R.string.login);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fireworkView1.bindEditText(etUserName);
        fireworkView2.bindEditText(etUserPassword);
    }

    @OnClick(R.id.al_tv_new_user)
    public void onNewUser(View view){
        RegisterActivity.show(context);
    }

    @OnClick(R.id.al_tv_night)
    public void onNight(View view){
        layout.setBackgroundColor(0xFF000000);
        ckRemember.setTextColor(getResources().getColor(R.color.white));
        etUserName.setBackgroundColor(getResources().getColor(R.color.night_black));
        etUserPassword.setBackgroundColor(getResources().getColor(R.color.night_black));
        tvNewUser.setTextColor(getResources().getColor(R.color.white));
        tvLosePassword.setTextColor(getResources().getColor(R.color.white));
    }

    @OnClick(R.id.al_tv_day)
    public void onDay(View view){
        layout.setBackgroundColor(0xFFFFFFFF);
        ckRemember.setTextColor(getResources().getColor(R.color.black));
        etUserName.setBackgroundColor(getResources().getColor(R.color.edit_login));
        etUserPassword.setBackgroundColor(getResources().getColor(R.color.edit_login));
        tvLosePassword.setTextColor(getResources().getColor(R.color.light_black));
        tvNewUser.setTextColor(getResources().getColor(R.color.light_black));
    }

    @OnClick(R.id.al_tv_lose_password)
    public void onForgetPassword(View view){
        ForgetActivity.show(context);
    }

    @OnClick(R.id.al_btn_login)
    public void onBtnLogin(View view){
        login();
    }

    @Override
    public void login() {
        if (TextUtils.isEmpty(etUserName.getText())){
            showToast("请输入账号/用户名");
            return;
        }
        if (TextUtils.isEmpty(etUserPassword.getText())){
            showToast("请输入密码");
            return;
        }
        String userName = etUserName.getText().toString();
        String password = etUserPassword.getText().toString();
        presenter.checklogin(userName,password);
    }

    @Override
    public void loginSuccess() {
//        UserBean.loginName = etUserName.getText().toString().trim();
        MainActivity.show(context);
        etUserName.setText("");
        etUserPassword.setText("");
        etUserName.requestFocus();//获取焦点
    }

    @Override
    public void loginAdmin() {
        AdminActivity.show(context);
        etUserName.setText("");
        etUserPassword.setText("");
        etUserName.requestFocus();
    }
}