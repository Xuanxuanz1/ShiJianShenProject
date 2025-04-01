package com.example.shijianshenapp.ui.forget;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.BasePresenter;

public class ForgetPresenter extends BasePresenter<ForgetContact.IForgetView,ForgetModel> implements ForgetContact.IForgetPresenter {
    public ForgetPresenter(Context context) {
        super(context);
    }

    @Override
    protected ForgetModel initModel() {
        return new ForgetModel(context);
    }

    @Override
    public void checkIsExist(String userName) {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在验证账号");
        model.checkIsExis(userName, new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.userExistSuccess();
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
    public void findUser(String userName, String birthday) {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在验证");
        model.findUser(userName, birthday, new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.findUserSuccess();
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
    public void changePassword(String userName, String password) {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在修改密码");
        model.changePassword(userName, password, new ModelCallBack() {
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
