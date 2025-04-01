package com.example.shijianshenapp.ui.user;

import com.example.shijianshenapp.base.BaseContact;

public interface UserContact {
    interface IUserView extends BaseContact.IBaseView{
        void exit();
    }
    interface IUserPresenter extends BaseContact.IBasePresenter{
        void cancelUser();
    }
}
