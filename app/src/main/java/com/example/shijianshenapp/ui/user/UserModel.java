package com.example.shijianshenapp.ui.user;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.BaseModel;
import com.example.shijianshenapp.bean.UserBean;

import org.litepal.Operator;

import java.util.List;

public class UserModel extends BaseModel implements IUserModel {
    public UserModel(Context context) {
        super(context);
    }

    @Override
    public void cancleUser(ModelCallBack modelCallBack) {
        Operator.deleteAll(UserBean.class,"userName=?",UserBean.loginName);
        modelCallBack.requestSuccess();
    }
}
