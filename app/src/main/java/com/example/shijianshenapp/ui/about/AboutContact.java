package com.example.shijianshenapp.ui.about;

import com.example.shijianshenapp.base.BaseContact;

public interface AboutContact {
    interface IAboutView extends BaseContact.IBaseView{
        void checkNew();

        void callUs();
    }
    interface IAboutPresenter extends BaseContact.IBasePresenter{
        void checkNew();
    }
}
