package com.example.shijianshenapp.ui.admin.changeuserdetail;

import android.content.Context;

import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.base.BasePresenter;
import com.example.shijianshenapp.bean.UserBean;

import java.util.List;

public class ChangeUserDetailPresenter extends BasePresenter<ChangeUserDetailContact.IChangeUserDetailView,ChangeUserDetailModel> implements ChangeUserDetailContact.IChangeUserDetailPresenter {

    @Override
    public void onCreate() {
        super.onCreate();
        fetchUserData();
    }

    public ChangeUserDetailPresenter(Context context) {
        super(context);
    }

    @Override
    protected ChangeUserDetailModel initModel() {
        return new ChangeUserDetailModel(context);
    }

    @Override
    public void fetchUserData() {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在加载用户数据");
        model.fetchUser(new ModelParamCallBack<List<UserBean>>() {
            @Override
            public void requestSuccess(List<UserBean> data) {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.notifyChangeUserList(data);
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
