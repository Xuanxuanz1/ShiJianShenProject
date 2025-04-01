package com.example.shijianshenapp.ui.changeuser;

import com.example.shijianshenapp.base.BaseContact;
import com.example.shijianshenapp.bean.UserBean;

import java.util.List;

public interface ChangeUserContact {
    interface IChangeUserView extends BaseContact.IBaseView{
        void init(List<UserBean> list);

        boolean isChangeUser();

        void changeSuccess();
    }
    interface IChangeUserPresenter extends BaseContact.IBasePresenter{
        void fetchUserData(String userName);

        void changeUserData(String petName,int age,String birthday,String phone,double height,double weight);
    }
}
