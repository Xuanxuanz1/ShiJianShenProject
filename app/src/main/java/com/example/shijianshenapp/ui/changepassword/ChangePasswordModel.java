package com.example.shijianshenapp.ui.changepassword;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.BaseModel;
import com.example.shijianshenapp.bean.UserBean;

import org.litepal.Operator;

import java.util.List;

public class ChangePasswordModel extends BaseModel implements IChangePasswordModel {

    public ChangePasswordModel(Context context) {
        super(context);
    }

    @Override
    public void changePassword(String password, String passwordNew, ModelCallBack modelCallBack) {
        List<UserBean> list = Operator.where("userName=?",UserBean.loginName).find(UserBean.class);
        if (!list.get(0).getPassword().equals(password)){
            modelCallBack.requestError(generateThrowable("原密码不正确"));
            return;
        }
        if (list.get(0).getPassword().equals(passwordNew)){
            modelCallBack.requestError(generateThrowable("原密码与新密码一致"));
            return;
        }
        list.get(0).setPassword(passwordNew);
        Operator.saveAll(list);
        modelCallBack.requestSuccess();
    }
}
