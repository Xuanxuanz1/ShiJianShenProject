package com.example.shijianshenapp.ui.admin.changerecommend;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.BasePresenter;
import com.example.shijianshenapp.bean.RecommendDetailBean;

public class ChangeRecommendPresenter extends BasePresenter<ChangeRecommendContact.IChangeRecommendView,ChangeRecommendModel> implements ChangeRecommendContact.IChangeRecommendPresenter {
    public ChangeRecommendPresenter(Context context) {
        super(context);
    }

    @Override
    protected ChangeRecommendModel initModel() {
        return new ChangeRecommendModel(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fetchRecommend();
    }

    @Override
    public void fetchRecommend() {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在加载数据");
        model.fetchRecommendData(new ModelCallBack() {
            @Override
            public void requestSuccess() {
                view.hideLoading();
                view.notifyChangeRecommend(model.getRecomemdList());
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
    public void addNewRecommend(String title, String message) {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在发布新资讯");
        model.addNewRecommedn(title, message, new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.notifyChangeRecommend(model.getRecomemdList());
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
    public void editRecommend(int postition, String title, String message) {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在发布资讯");
        model.editRecommend(postition, title, message, new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.notifyChangeRecommend(model.getRecomemdList());
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
    public void deletRecommend(int postition) {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在删除资讯");
        model.deletRecommend(postition, new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.notifyChangeRecommend(model.getRecomemdList());
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
    public RecommendDetailBean getRecommendDetail(int postition) {
        return model.getRecommendDetailList().get(postition);
    }
}
