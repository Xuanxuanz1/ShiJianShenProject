package com.example.shijianshenapp.ui.login;

import android.content.Context;
import android.text.TextUtils;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.BaseModel;
import com.example.shijianshenapp.bean.UserBean;

import org.litepal.Operator;

import java.util.List;

public class LoginModel extends BaseModel implements ILoginModel {

    public LoginModel(Context context) {
        super(context);
    }

    @Override
    public void checkLogin(String userName, String password, ModelCallBack modelCallBack) {
        List<UserBean> list = Operator.where("userName=? or petName=?",userName,userName).find(UserBean.class);
        if (list == null || list.isEmpty()){
            modelCallBack.requestError(generateThrowable("该账号不存在"));
            return;
        }
        if (list.get(0).getPassword().equals(password)){
            if (!TextUtils.isEmpty(list.get(0).getPetName())){
                UserBean.userPetName = list.get(0).getPetName();
            }
            UserBean.loginName = list.get(0).getUserName();
            modelCallBack.requestSuccess();
            return;
        }
        else {
            modelCallBack.requestError(generateThrowable("请确认你的密码是正确的"));
        }
    }
}
