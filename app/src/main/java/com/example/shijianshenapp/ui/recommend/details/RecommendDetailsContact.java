package com.example.shijianshenapp.ui.recommend.details;

import com.example.shijianshenapp.base.BaseContact;
import com.example.shijianshenapp.bean.RecommendBean;
import com.example.shijianshenapp.bean.RecommendDetailBean;

import java.util.List;

public interface RecommendDetailsContact {
    interface IRecommendDetailsView extends BaseContact.IBaseView {
        void fetchSccuess(RecommendDetailBean data);
    }
    interface IRecommendDetailsPresenter extends BaseContact.IBasePresenter{
        void fetchNewList(int postiton);
    }
}
