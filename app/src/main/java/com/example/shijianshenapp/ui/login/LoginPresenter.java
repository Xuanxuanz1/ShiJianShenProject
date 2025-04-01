package com.example.shijianshenapp.ui.login;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginContact.ILoginView,LoginModel> implements LoginContact.ILoginPresenter {

    public LoginPresenter(Context context) {
        super(context);
    }

    @Override
    protected LoginModel initModel() {
        return new LoginModel(context);
    }

    @Override
    public void checklogin(String userName, String password) {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在登录");
        model.checkLogin(userName, password, new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                if (userName.equals("admin")){
                    view.loginAdmin();
                }
                else {
                    view.loginSuccess();
                }
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
