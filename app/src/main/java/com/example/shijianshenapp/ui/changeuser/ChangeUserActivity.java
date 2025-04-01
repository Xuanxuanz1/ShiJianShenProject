package com.example.shijianshenapp.ui.changeuser;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseActivity;
import com.example.shijianshenapp.bean.UserBean;
import com.example.shijianshenapp.utils.DoubleToString;
import com.wayww.edittextfirework.FireworkView;

import java.util.List;

public class ChangeUserActivity extends BaseActivity<ChangeUserContact.IChangeUserView,ChangeUserPresenter> implements ChangeUserContact.IChangeUserView {

    @BindView(R.id.acu_et_pet_name)
    EditText etPetName;
    @BindView(R.id.acu_et_age)
    EditText etAge;
    @BindView(R.id.acu_et_birthday)
    EditText etBirthday;
    @BindView(R.id.acu_et_phone)
    EditText etPhone;
    @BindView(R.id.acu_et_height)
    EditText etHeight;
    @BindView(R.id.acu_et_weight)
    EditText etWeight;
    @BindView(R.id.app_title_iv_left)
    View backView;
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

    private UserBean data;
    private String userName;

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.change_user);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backView.setOnClickListener(v->{
            if (data == null){
                finishActivity();
                return;
            }
            if (isChangeUser()){
                showAlert("提示",
                        "您有已修改的信息尚未保存，是否继续退出？",
                        "取消", null,
                        "确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finishActivity();
                            }
                        }
                );
                return;
            }
            finishActivity();
        });

        Intent intent = getIntent();
        userName = intent.getStringExtra("name");
        presenter.fetchUserData(userName);
    }

    @Override
    public void onBackPressed() {
        if (data == null){
            finishActivity();
            return;
        }
        if (isChangeUser()){
            showAlert("提示",
                    "您有已修改的信息尚未保存，是否继续退出？",
                    "取消", null,
                    "确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finishActivity();
                        }
                    }
            );
            return;
        }
        super.onBackPressed();
    }

    @Override
    public ChangeUserPresenter initPresenter() {
        return new ChangeUserPresenter(context);
    }

    public static void show(Context context,String name){
//        context.startActivity(new Intent(context,ChangeUserActivity.class));
        Intent intent = new Intent(context,ChangeUserActivity.class);
        intent.putExtra("name",name);
        context.startActivity(intent);
    }

    @OnClick(R.id.acu_btn_submit)
    public void onChangeUserData(View view){
        if (!isChangeUser()){
            showToast("暂无修改个人信息");
            return;
        }
        presenter.changeUserData(
                etPetName.getText().toString().trim(),
                Integer.parseInt(etAge.getText().toString().trim()),
                etBirthday.getText().toString().trim(),
                etPhone.getText().toString().trim(),
                Double.parseDouble(etHeight.getText().toString().trim()),
                Double.parseDouble(etWeight.getText().toString().trim())
        );
    }

    @Override
    public void init(List<UserBean> list) {
        data = list.get(0);
        if (TextUtils.isEmpty(data.getPetName())){
            etPetName.setText("");
        }
        if (!TextUtils.isEmpty(data.getPetName())){
            etPetName.setText(data.getPetName());
        }
        etAge.setText(String.valueOf(data.getAge()));
        etBirthday.setText(data.getBirthday());
        etPhone.setText(data.getPhone());
        etWeight.setText(DoubleToString.doubleParseString(data.getWeight()));
        etHeight.setText(DoubleToString.doubleParseString(data.getHight()));

        fireworkView1.bindEditText(etPetName);
        fireworkView2.bindEditText(etAge);
        fireworkView3.bindEditText(etBirthday);
        fireworkView4.bindEditText(etPhone);
        fireworkView5.bindEditText(etHeight);
        fireworkView6.bindEditText(etWeight);
    }

    @Override
    public boolean isChangeUser() {
        if (data == null){
            return false;
        }
        if (!TextUtils.isEmpty(data.getPetName())){
            if (!data.getPetName().equals(etPetName.getText().toString().trim())){
                return true;
            }
        }
        if (TextUtils.isEmpty(data.getPetName())){
            if (!etPetName.getText().toString().trim().equals("")){
                return true;
            }
        }
        if (data.getAge() != Integer.parseInt(etAge.getText().toString().trim())){
            return true;
        }
        if (!data.getBirthday().equals(etBirthday.getText().toString().trim())){
            return true;
        }
        if (!data.getPhone().equals(etPhone.getText().toString().trim())){
            return true;
        }
        if (data.getHight() != Double.parseDouble(etHeight.getText().toString().trim())){
            return true;
        }
        if (data.getWeight() != Double.parseDouble(etWeight.getText().toString().trim())){
            return true;
        }
        return false;
    }

    @Override
    public void changeSuccess() {
        showToast("修改成功");
        finishActivity();
    }
}