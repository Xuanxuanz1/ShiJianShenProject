package com.example.shijianshenapp.ui.find;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.BasePresenter;

public class FindPresenter extends BasePresenter<FindContact.IFindView,FindModel> implements FindContact.IFindPresenter {
    public FindPresenter(Context context) {
        super(context);
    }

    @Override
    protected FindModel initModel() {
        return new FindModel(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fetchVideoData();
    }

    @Override
    public void fetchVideoData() {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在加载数据");
        model.fetchVideoData(new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.notifyVideoList(model.getVideoList());
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
