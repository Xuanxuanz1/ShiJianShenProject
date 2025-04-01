package com.example.shijianshenapp.ui.register;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.base.BaseModel;
import com.example.shijianshenapp.bean.UserBean;

import org.litepal.Operator;

import java.util.ArrayList;
import java.util.List;

public class RegisterModel extends BaseModel implements IRegisterModel {
    public RegisterModel(Context context) {
        super(context);
    }

    @Override
    public void fetchAge(ModelParamCallBack<List<String>> modelParamCallBack) {
        List<String> list = new ArrayList<>();
        list.add("请选择年龄");
        for (int i = 1; i <= 100;i++){
            list.add(String.valueOf(i));
        }
        modelParamCallBack.requestSuccess(list);
    }

    @Override
    public void checkUserIsSame(String userName,String password,String phone,String age,String birthday,String height,String weight,String userPetName, ModelCallBack modelCallBack) {
        List<UserBean> list = Operator.where("userName=?",userName).find(UserBean.class);
        if (!list.isEmpty()){
            modelCallBack.requestError(generateThrowable("该用户名已存在"));
            return;
        }
        List<UserBean> bean = new ArrayList<>();
        UserBean data = new UserBean();
        data.setUserName(userName);
        data.setPassword(password);
        data.setPhone(phone);
        data.setAge(Integer.parseInt(age));
        data.setBirthday(birthday);
        data.setHight(Integer.parseInt(height));
        data.setWeight(Integer.parseInt(weight));
        data.setPetName(userPetName);
        data.setIntegration(0);
        bean.add(data);
        Operator.saveAll(bean);
        modelCallBack.requestSuccess();
    }
}
