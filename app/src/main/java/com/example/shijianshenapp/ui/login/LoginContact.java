package com.example.shijianshenapp.ui.login;

import com.example.shijianshenapp.base.BaseContact;

public interface LoginContact {
    interface ILoginView extends BaseContact.IBaseView{
        void login();

        void loginSuccess();

        void loginAdmin();
    }
    interface ILoginPresenter extends BaseContact.IBasePresenter{
        void checklogin(String userName,String password);
    }
}
