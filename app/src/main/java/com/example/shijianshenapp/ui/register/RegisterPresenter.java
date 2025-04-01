package com.example.shijianshenapp.ui.register;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.base.BasePresenter;

import java.util.List;

public class RegisterPresenter extends BasePresenter<RegisterContact.IRegisterView,RegisterModel> implements RegisterContact.IRegisterPresenter {
    public RegisterPresenter(Context context) {
        super(context);
    }

    @Override
    protected RegisterModel initModel() {
        return new RegisterModel(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fetchAge();
    }

    @Override
    public void fetchAge() {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在获取年龄数据");
        model.fetchAge(new ModelParamCallBack<List<String>>() {
            @Override
            public void requestSuccess(List<String> data) {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.showAgeData(data);
            }

            @Override
            public void requestError(Throwable t) {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.showSingleAlertNoAction(t.getMessage());
            }

            @Override
            public void requestFailure(Throwable t) {
                requestError(t);
            }
        });
    }

    @Override
    public void checkUserIsSame(String userName,String password,String phone,String age,String birthday,String height,String weight,String userPetName) {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在注册");
        model.checkUserIsSame(userName,password,phone,age,birthday,height,weight,userPetName, new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.showToast("注册成功");
                view.registerSuccess();
            }

            @Override
            public void requestError(Throwable t) {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.showSingleAlertNoAction(t.getMessage());
            }

            @Override
            public void requestFailure(Throwable t) {
                requestError(t);
            }
        });
    }
}
