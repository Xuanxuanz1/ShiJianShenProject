package com.example.shijianshenapp.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ayma.base.util.DateTimeUtils;
import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseActivity;
import com.liuwan.customdatepicker.widget.CustomDatePicker;
import com.wayww.edittextfirework.FireworkView;

import java.util.List;

public class RegisterActivity extends BaseActivity<RegisterContact.IRegisterView,RegisterPresenter> implements RegisterContact.IRegisterView {

    @BindView(R.id.ar_et_user_name)
    EditText etUserName;
    @BindView(R.id.ar_et_user_password)
    EditText etUserPassword;
    @BindView(R.id.ar_et_user_password_again)
    EditText etUserPasswordAgain;
    @BindView(R.id.ar_et_user_phone)
    EditText etUserPhone;
    @BindView(R.id.ar_et_user_height)
    EditText etUserHeight;
    @BindView(R.id.ar_et_user_weight)
    EditText etUserWeight;
    @BindView(R.id.ar_et_user_pet_name)
    EditText etPetName;
    @BindView(R.id.ar_sp_age)
    Spinner spAge;
    @BindView(R.id.ar_tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.fire_work1)
    FireworkView fireworkView1;
    @BindView(R.id.fire_work2)
    FireworkView fireworkView2;
    @BindView(R.id.fire_work3)
    FireworkView fireworkView3;
    @BindView(R.id.fire_work4)
    FireworkView fireworkView4;
    @BindView(R.id.fire_work5)
    FireworkView fireworkView5;
    @BindView(R.id.fire_work6)
    FireworkView fireworkView6;
    @BindView(R.id.fire_work7)
    FireworkView fireworkView7;

    private CustomDatePicker timePicker;

    @Override
    protected String setTitle() {
        return getString(R.string.register);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public RegisterPresenter initPresenter() {
        return new RegisterPresenter(context);
    }

    public static void show(Context context){
        context.startActivity(new Intent(context,RegisterActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fireworkView1.bindEditText(etUserName);
        fireworkView2.bindEditText(etUserPassword);
        fireworkView3.bindEditText(etUserPasswordAgain);
        fireworkView4.bindEditText(etUserPhone);
        fireworkView5.bindEditText(etUserHeight);
        fireworkView6.bindEditText(etUserWeight);
        fireworkView7.bindEditText(etPetName);
    }


    @OnClick(R.id.ar_btn_register)
    public void onRegister(View view){
        checkUserIsSame();
    }

    @OnClick(R.id.ar_tv_birthday)
    public void onSelectDate(View view){
        if (antiShakeAutoRemove(view)){
            return;
        }
        selectBirthDay();
    }

    @Override
    public void showAgeData(List<String> list) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,list);
        spAge.setAdapter(arrayAdapter);
    }

    @Override
    public void checkPasswordSame() {
        if (TextUtils.isEmpty(etUserPassword.getText())){
            showToast("请输入密码");
            return;
        }
        if (TextUtils.isEmpty(etUserPasswordAgain.getText())){
            showToast("请再次输入密码");
            return;
        }
        String password = etUserPassword.getText().toString().trim();
        String passwordAgain = etUserPasswordAgain.getText().toString().trim();
        if (!password.equals(passwordAgain)){
            showToast("两次输入密码不一致，请重新输入");
            return;
        }
    }

    @Override
    public void checkUserIsSame() {
        if (TextUtils.isEmpty(etUserName.getText())){
            showToast("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(etUserPhone.getText())){
            showToast("请输入手机号码");
            return;
        }
        if (TextUtils.isEmpty(etUserHeight.getText())){
            showToast("请输入身高");
            return;
        }
        if (TextUtils.isEmpty(etUserWeight.getText())){
            showToast("请输入体重");
            return;
        }
        if (spAge.getSelectedItem().toString().equals("请选择年龄")){
            showToast("请选择年龄");
            return;
        }
        if (tvBirthday.getText().toString().equals("请选择您的生日")){
            showToast("请选择您的生日");
            return;
        }
        if (TextUtils.isEmpty(etPetName.getText())){
            showToast("请输入用户名");
            return;
        }
        checkPasswordSame();
        presenter.checkUserIsSame(
                etUserName.getText().toString().trim(),
                etUserPassword.getText().toString().trim(),
                etUserPhone.getText().toString().trim(),
                spAge.getSelectedItem().toString(),
                tvBirthday.getText().toString(),
                etUserHeight.getText().toString().trim(),
                etUserWeight.getText().toString().trim(),
                etPetName.getText().toString().trim()
                );
    }

    @Override
    public void registerSuccess() {
        finishActivity();
    }

    @Override
    public void selectBirthDay() {
        if (timePicker == null){
            timePicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
                @Override
                public void onTimeSelected(long timestamp) {
                    tvBirthday.setText(DateTimeUtils.parseDate(timestamp,"yyyy-MM-dd"));
                }
            },"1980-01-01 00:00",DateTimeUtils.getDateToyyyyMMddHHmm());
            timePicker.setCancelable(true);
            timePicker.setCanShowAnim(true);
            timePicker.setCanShowPreciseTime(false);
            timePicker.setScrollLoop(true);
        }
        timePicker.show(DateTimeUtils.getDateToyyyyMMddHHmm());
    }
}