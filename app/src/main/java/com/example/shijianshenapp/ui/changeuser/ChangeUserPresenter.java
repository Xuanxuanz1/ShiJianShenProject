package com.example.shijianshenapp.ui.changeuser;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.base.BasePresenter;
import com.example.shijianshenapp.bean.UserBean;

import java.util.List;

public class ChangeUserPresenter extends BasePresenter<ChangeUserContact.IChangeUserView,ChangeUserModel> implements ChangeUserContact.IChangeUserPresenter {
    public ChangeUserPresenter(Context context) {
        super(context);
    }

    @Override
    protected ChangeUserModel initModel() {
        return new ChangeUserModel(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void fetchUserData(String userName) {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在加载数据");
        model.fetchUserData(userName, new ModelParamCallBack<List<UserBean>>() {
            @Override
            public void requestSuccess(List<UserBean> data) {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.init(data);
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
    public void changeUserData(String petName, int age, String birthday, String phone, double height, double weight) {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在修改个人信息");
        model.changeUserData(petName, age, birthday, phone, height, weight, new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.changeSuccess();
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
