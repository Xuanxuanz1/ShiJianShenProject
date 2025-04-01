package com.example.shijianshenapp.ui.forget;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.BaseModel;
import com.example.shijianshenapp.bean.UserBean;

import org.litepal.Operator;

import java.util.List;

public class ForgetModel extends BaseModel implements IForgetModel {

    public ForgetModel(Context context) {
        super(context);
    }

    @Override
    public void checkIsExis(String userName, ModelCallBack modelCallBack) {
        List<UserBean> userList = Operator.where("userName=?",userName).find(UserBean.class);
        if (userList.isEmpty()){
            modelCallBack.requestError(generateThrowable("账号不存在"));
            return;
        }
        modelCallBack.requestSuccess();
    }

    @Override
    public void findUser(String userName, String birthday, ModelCallBack modelCallBack) {
        List<UserBean> userList = Operator.where("userName=?",userName).find(UserBean.class);
        if (userList.get(0).getBirthday().equals(birthday)){
            modelCallBack.requestSuccess();
            return;
        }
        modelCallBack.requestError(generateThrowable("验证失败，请确认您的生日"));
    }

    @Override
    public void changePassword(String userName, String password, ModelCallBack modelCallBack) {
        List<UserBean> userList = Operator.where("userName=?",userName).find(UserBean.class);
        if (userList.get(0).getPassword().equals(password)){
            modelCallBack.requestError(generateThrowable("原密码与新密码一致"));
            return;
        }
        userList.get(0).setPassword(password);
        Operator.saveAll(userList);
        modelCallBack.requestSuccess();
    }
}
