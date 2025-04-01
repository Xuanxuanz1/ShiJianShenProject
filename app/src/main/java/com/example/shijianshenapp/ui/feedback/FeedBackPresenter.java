package com.example.shijianshenapp.ui.feedback;

import android.content.Context;

import com.example.shijianshenapp.base.BasePresenter;

public class FeedBackPresenter extends BasePresenter<FeedBackContact.IFeedBackView,FeedBackModel> implements FeedBackContact.IFeedBackPresenter {
    public FeedBackPresenter(Context context) {
        super(context);
    }

    @Override
    protected FeedBackModel initModel() {
        return new FeedBackModel(context);
    }

    @Override
    public void submit(String backMessage) {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("我们正在接收您的宝贵意见");
        view.hideLoading();
        view.submitSuccess();
    }
}
