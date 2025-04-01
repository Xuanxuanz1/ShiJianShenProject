package com.example.shijianshenapp.ui.register;

import com.ayma.base.mvp.ModelCallBack;
import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.base.IBaseModel;

import java.util.List;

public interface IRegisterModel extends IBaseModel {
    void fetchAge(ModelParamCallBack<List<String>> modelParamCallBack);

    void checkUserIsSame(String userName,String password,String phone,String age,String birthday,String height,String weight,String userPetName, ModelCallBack modelCallBack);
}
