package com.example.shijianshenapp.ui.admin.changeuserdetail;

import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.base.IBaseModel;
import com.example.shijianshenapp.bean.UserBean;

import java.util.List;

public interface IChangeUserDetailModel extends IBaseModel {
    void fetchUser(ModelParamCallBack<List<UserBean>> modelParamCallBack);
}
