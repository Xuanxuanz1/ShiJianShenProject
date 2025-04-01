package com.example.shijianshenapp.ui.recommend;

import android.content.Context;

import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.base.BasePresenter;
import com.example.shijianshenapp.bean.RecommendBean;

import java.util.List;

public class RecommendPresenter extends BasePresenter<RecommendContact.IRecommendView,RecommendModel> implements RecommendContact.IRecommendPresenter {

    private boolean isFirst = true;

    public RecommendPresenter(Context context) {
        super(context);
    }

    @Override
    protected RecommendModel initModel() {
        return new RecommendModel(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fetchBanner();
        fetchRecommendData();
    }

    @Override
    public void fetchBanner() {
        if (!isViewAttach()){
            return;
        }
        view.showBanner();
    }

    @Override
    public void fetchRecommendData() {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在加载数据");
        model.fetchRecommendData(new ModelParamCallBack<List<RecommendBean>>() {
            @Override
            public void requestSuccess(List<RecommendBean> data) {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.showRecommendData(data);
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
    public void fetchNewRecommendData() {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在刷新");
        view.hideLoading();
        if (isFirst){
            view.showToast("暂无最新数据");
            isFirst = false;
        }
    }
}
