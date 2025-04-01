package com.example.shijianshenapp.ui.admin.self;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseActivity;
import com.example.shijianshenapp.bean.UserBean;
import com.example.shijianshenapp.ui.admin.changerecommend.ChangeRecommendActivity;
import com.example.shijianshenapp.ui.admin.changeuserdetail.ChangeUserDetailActivity;
import com.example.shijianshenapp.ui.admin.checkvideoclass.CheckVideoClassActivity;
import com.example.shijianshenapp.ui.admin.manageshop.ManageShopActivity;
import com.example.shijianshenapp.ui.changepassword.ChangePasswordActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AdminActivity extends BaseActivity<AdminContact.IAdminView,AdminPresenter> implements AdminContact.IAdminView {

    @BindView(R.id.ad_tv_admin)
    TextView tvAdmin;

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.admin);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_admin;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvAdmin.setText(UserBean.loginName);
    }

    @Override
    public AdminPresenter initPresenter() {
        return new AdminPresenter(context);
    }

    public static void show(Context context){
        context.startActivity(new Intent(context,AdminActivity.class));
    }

    @OnClick(R.id.ad_cl_change_recommend)
    public void onBtnChangeRecommend(View view){
        ChangeRecommendActivity.show(context);
    }

    @OnClick(R.id.ad_cl_change_user_detail)
    public void onBtnChangeUserDetail(View view){
        ChangeUserDetailActivity.show(context);
    }

    @OnClick(R.id.ad_cl_check_video_class)
    public void onBtnCheckVideoClass(View view){
        CheckVideoClassActivity.show(context);
    }

    @OnClick(R.id.ad_cl_change_admin_password)
    public void onBtnChangeAdminPassword(View view){
        ChangePasswordActivity.show(context);
    }

    @OnClick(R.id.ad_cl_manage_shop)
    public void onBtnManageShop(View view){
        ManageShopActivity.show(context);
    }

    @OnClick(R.id.ad_cl_exit)
    public void onBtnExit(View view){
        finish();
    }

}