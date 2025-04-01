package com.example.shijianshenapp.ui.changeuser;

import android.content.Context;
import android.text.TextUtils;

import com.ayma.base.mvp.ModelCallBack;
import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.base.BaseModel;
import com.example.shijianshenapp.bean.UserBean;

import org.litepal.Operator;

import java.util.List;

public class ChangeUserModel extends BaseModel implements IChangeUserModel {
    public ChangeUserModel(Context context) {
        super(context);
    }

    @Override
    public void fetchUserData(String userName, ModelParamCallBack<List<UserBean>> modelParamCallBack) {
        List<UserBean> list = Operator.where("userName=? or petName=?",userName,userName).find(UserBean.class);
        modelParamCallBack.requestSuccess(list);
    }

    @Override
    public void changeUserData(String petName, int age, String birthday, String phone, double height, double weight, ModelCallBack modelCallBack) {
        List<UserBean> list = Operator.where("userName=?",UserBean.loginName).find(UserBean.class);
        UserBean data = list.get(0);
        if (!TextUtils.isEmpty(petName)){
            data.setPetName(petName);
            UserBean.userPetName = petName;
        }
        data.setAge(age);
        data.setBirthday(birthday);
        data.setPhone(phone);
        data.setHight(height);
        data.setWeight(weight);
        Operator.saveAll(list);
        modelCallBack.requestSuccess();
    }
}
