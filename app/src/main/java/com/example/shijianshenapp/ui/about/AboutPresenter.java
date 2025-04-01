package com.example.shijianshenapp.ui.about;

import android.content.Context;

import com.example.shijianshenapp.base.BasePresenter;

public class AboutPresenter extends BasePresenter<AboutContact.IAboutView,AboutModel> implements AboutContact.IAboutPresenter {
    public AboutPresenter(Context context) {
        super(context);
    }

    @Override
    protected AboutModel initModel() {
        return new AboutModel(context);
    }

    @Override
    public void checkNew() {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在检查更新");
        view.hideLoading();
        view.showToast("已是最新版本");
    }
}
