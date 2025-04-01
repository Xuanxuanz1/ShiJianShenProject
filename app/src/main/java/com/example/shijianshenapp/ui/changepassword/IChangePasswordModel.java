package com.example.shijianshenapp.ui.changepassword;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.IBaseModel;

public interface IChangePasswordModel extends IBaseModel {
    void changePassword(String password, String passwordNew, ModelCallBack modelCallBack);
}
