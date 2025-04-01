package com.example.shijianshenapp.ui.find.circle;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.BasePresenter;
import com.example.shijianshenapp.ui.find.FindContact;

public class FindCirclePresenter extends BasePresenter<FindCircleContact.IFindCircleView,FindCircleModel> implements FindCircleContact.IFindCirclePresenter {
    public FindCirclePresenter(Context context) {
        super(context);
    }

    @Override
    protected FindCircleModel initModel() {
        return new FindCircleModel(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fetchCircleData();
    }

    @Override
    public void fetchCircleData() {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在加载数据");
        model.fetchCircleData(new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.notifyCircle(model.getCircleDate());
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
    public void delItem(int position) {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在删除");
        model.delItem(position, new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.notifyCircle(model.getCircleDate());
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
    public void addItem(String message) {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在发布新的说说");
        model.addItem(message, new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.notifyCircle(model.getCircleDate());
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
