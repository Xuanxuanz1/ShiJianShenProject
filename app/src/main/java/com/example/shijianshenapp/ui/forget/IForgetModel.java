package com.example.shijianshenapp.ui.forget;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.IBaseModel;

public interface IForgetModel extends IBaseModel {
    void checkIsExis(String userName, ModelCallBack modelCallBack);

    void findUser(String userName,String birthday,ModelCallBack modelCallBack);

    void changePassword(String userName,String password,ModelCallBack modelCallBack);
}
