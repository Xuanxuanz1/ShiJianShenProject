package com.example.shijianshenapp.ui.recommend.details;

import android.content.Context;

import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.base.BasePresenter;
import com.example.shijianshenapp.bean.RecommendBean;
import com.example.shijianshenapp.bean.RecommendDetailBean;

import java.util.List;

public class RecommendDetailsPresenter extends BasePresenter<RecommendDetailsContact.IRecommendDetailsView,RecommendDetailsModel> implements RecommendDetailsContact.IRecommendDetailsPresenter {
    public RecommendDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected RecommendDetailsModel initModel() {
        return new RecommendDetailsModel(context);
    }

    @Override
    public void fetchNewList(int postition) {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在加载数据");
        model.fetchNewList(postition,new ModelParamCallBack<RecommendDetailBean>() {
            @Override
            public void requestSuccess(RecommendDetailBean data) {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.fetchSccuess(data);
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
