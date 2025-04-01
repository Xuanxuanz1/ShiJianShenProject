package com.example.shijianshenapp.ui.changepassword;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.BasePresenter;

public class ChangePasswordPresenter extends BasePresenter<ChangePasswordContact.IChangePasswordView,ChangePasswordModel> implements ChangePasswordContact.IChangePasswordPresenter {
    public ChangePasswordPresenter(Context context) {
        super(context);
    }

    @Override
    protected ChangePasswordModel initModel() {
        return new ChangePasswordModel(context);
    }

    @Override
    public void changePassword(String password, String passwordNew) {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在修改密码");
        model.changePassword(password, passwordNew, new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.changePasswordSuccess();
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
