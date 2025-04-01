package com.example.shijianshenapp.ui.admin.changerecommend;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.IBaseModel;
import com.example.shijianshenapp.bean.RecommendBean;
import com.example.shijianshenapp.bean.RecommendDetailBean;

import java.util.List;

public interface IChangeRecommendModel extends IBaseModel {
    void fetchRecommendData(ModelCallBack modelCallBack);

    void addNewRecommedn(String title,String message,ModelCallBack modelCallBack);

    void editRecommend(int postiton,String title,String message,ModelCallBack modelCallBack);

    void deletRecommend(int postition,ModelCallBack modelCallBack);

    List<RecommendBean> getRecomemdList();

    List<RecommendDetailBean> getRecommendDetailList();
}
