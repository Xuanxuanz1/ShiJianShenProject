package com.example.shijianshenapp.ui.changeuser;

import com.ayma.base.mvp.ModelCallBack;
import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.base.IBaseModel;
import com.example.shijianshenapp.bean.UserBean;

import java.util.List;

public interface IChangeUserModel extends IBaseModel {
    void fetchUserData(String userName, ModelParamCallBack<List<UserBean>> modelParamCallBack);

    void changeUserData(String petName,int age,String birthday,String phone,double height,double weight, ModelCallBack modelCallBack);
}
