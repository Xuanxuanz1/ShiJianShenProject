package com.example.shijianshenapp.ui.login;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.IBaseModel;

interface ILoginModel extends IBaseModel {
    void checkLogin(String userName, String password, ModelCallBack modelCallBack);
}
