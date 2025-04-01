package com.example.shijianshenapp.ui.recommend;

import com.example.shijianshenapp.base.BaseContact;
import com.example.shijianshenapp.bean.RecommendBean;

import java.util.List;

public interface RecommendContact {
    interface IRecommendView extends BaseContact.IBaseView{
        void showBanner();

        void showRecommendData(List<RecommendBean> list);
    }
    interface IRecommendPresenter extends BaseContact.IBasePresenter{
        void fetchBanner();

        void fetchRecommendData();

        void fetchNewRecommendData();
    }
}
