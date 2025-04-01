package com.example.shijianshenapp.ui.changepassword;

import com.example.shijianshenapp.base.BaseContact;

public interface ChangePasswordContact {
    interface IChangePasswordView extends BaseContact.IBaseView{
        void changePassword();

        void changePasswordSuccess();
    }
    interface IChangePasswordPresenter extends BaseContact.IBasePresenter{
        void changePassword(String password,String passwordNew);
    }
}
