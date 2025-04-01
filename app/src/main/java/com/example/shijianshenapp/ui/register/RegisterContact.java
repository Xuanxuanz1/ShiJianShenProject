package com.example.shijianshenapp.ui.register;

import com.example.shijianshenapp.base.BaseContact;

import java.util.List;

public interface RegisterContact {
    interface IRegisterView extends BaseContact.IBaseView{
        void showAgeData(List<String> list);

        void checkPasswordSame();

        void checkUserIsSame();

        void registerSuccess();

        void selectBirthDay();
    }
    interface IRegisterPresenter extends BaseContact.IBasePresenter{

        void fetchAge();

        void checkUserIsSame(String userName,String password,String phone,String age,String birthday,String height,String weight,String userPetName);
    }
}
