package com.example.shijianshenapp.ui.user;

import butterknife.BindView;
import butterknife.OnClick;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseFragment;
import com.example.shijianshenapp.bean.UserBean;
import com.example.shijianshenapp.ui.about.AboutActivity;
import com.example.shijianshenapp.ui.changepassword.ChangePasswordActivity;
import com.example.shijianshenapp.ui.changeuser.ChangeUserActivity;
import com.example.shijianshenapp.ui.feedback.FeedBackActivity;
import com.example.shijianshenapp.ui.shop.ShopActivity;
import com.example.shijianshenapp.ui.sport.SportActivity;
import com.example.shijianshenapp.ui.train.TrainFragment;

import org.litepal.Operator;

import java.util.List;

public class UserFragment extends BaseFragment<UserContact.IUserView,UserPresenter> implements UserContact.IUserView {

    @BindView(R.id.auf_tv_user_name)
    TextView tvUserName;
    @BindView(R.id.auf_tv_user_id)
    TextView tvUserId;
    @BindView(R.id.auf_tv_shop_number)
    TextView tvShopNumber;

    @Override
    public UserPresenter initPresenter() {
        return new UserPresenter(activity);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvUserId.setText(UserBean.loginName);
        if (TextUtils.isEmpty(UserBean.userPetName)){
            tvUserName.setText(UserBean.loginName);
            List<UserBean> data = Operator.where("userName=?",UserBean.loginName).find(UserBean.class);
            tvShopNumber.setText(String.valueOf(data.get(0).getIntegration()));
            return;
        }
        tvUserName.setText(UserBean.userPetName);
        List<UserBean> data = Operator.where("petName=?",UserBean.userPetName).find(UserBean.class);
        tvShopNumber.setText(String.valueOf(data.get(0).getIntegration()));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(UserBean.userPetName)){
            tvUserName.setText(UserBean.loginName);
            List<UserBean> data = Operator.where("userName=?",UserBean.loginName).find(UserBean.class);
            tvShopNumber.setText(String.valueOf(data.get(0).getIntegration()));
            return;
        }
        tvUserName.setText(UserBean.userPetName);
        List<UserBean> data = Operator.where("petName=?",UserBean.userPetName).find(UserBean.class);
        tvShopNumber.setText(String.valueOf(data.get(0).getIntegration()));
    }

    @OnClick(R.id.auf_cl_change_password)
    public void onToChangePassword(View view){
        ChangePasswordActivity.show(activity);
    }

    @OnClick(R.id.auf_tv_change_user)
    public void onToChangeUser(View view){
        ChangeUserActivity.show(activity,UserBean.loginName);
    }

    @OnClick(R.id.auf_cl_sport)
    public void onToSport(View view){
        SportActivity.show(activity);
    }

    @OnClick(R.id.auf_cl_exit)
    public void onExit(View view){
        exit();
    }

    @OnClick(R.id.auf_cl_cancle_user)
    public void onCancleUser(View view){
        showAlert("提示",
                String.format("是否注销账号：%s ,\n用户名：%s ?", UserBean.loginName, UserBean.userPetName),
                "取消", null,
                "确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.cancelUser();
                    }
                }
        );
    }

    @OnClick(R.id.auf_cl_about)
    public void onToAbout(View view){
        AboutActivity.show(activity);
    }

    @OnClick(R.id.auf_cl_feedback)
    public void onToFeedBack(View view){
        FeedBackActivity.show(activity);
    }

    @OnClick(R.id.auf_cl_train)
    public void onToTrain(View view){
        ShopActivity.show(activity,tvShopNumber.getText().toString());
    }

    @Override
    protected String setTitle() {
        return getString(R.string.user);
    }

    @Override
    public void exit() {
        activity.finish();
    }
}