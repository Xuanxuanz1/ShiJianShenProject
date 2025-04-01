package com.example.shijianshenapp.ui.forget;

import com.example.shijianshenapp.base.BaseContact;

public interface ForgetContact {
    interface IForgetView extends BaseContact.IBaseView{
        void checkIsExist();

        void userExistSuccess();

        void findUser();

        void findUserSuccess();

        void changePassword();

        void changePasswordSuccess();
    }
    interface IForgetPresenter extends BaseContact.IBasePresenter{
        void checkIsExist(String userName);

        void findUser(String userName,String birthday);

        void changePassword(String userName,String password);
    }
}
