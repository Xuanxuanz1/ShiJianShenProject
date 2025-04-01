package com.example.shijianshenapp.ui.admin.changeuserdetail;

import com.example.shijianshenapp.base.BaseContact;
import com.example.shijianshenapp.bean.UserBean;

import java.util.List;

public interface ChangeUserDetailContact {
    interface IChangeUserDetailView extends BaseContact.IBaseView{
        void notifyChangeUserList(List<UserBean> list);
    }
    interface IChangeUserDetailPresenter extends BaseContact.IBasePresenter{
        void fetchUserData();
    }
}
