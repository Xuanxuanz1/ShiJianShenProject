package com.example.shijianshenapp.ui.user;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.BasePresenter;

public class UserPresenter extends BasePresenter<UserContact.IUserView,UserModel> implements UserContact.IUserPresenter{
    public UserPresenter(Context context) {
        super(context);
    }

    @Override
    protected UserModel initModel() {
        return new UserModel(context);
    }

    @Override
    public void cancelUser() {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在注销账号");
        model.cancleUser(new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.showToast("账号已注销");
                view.exit();
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
