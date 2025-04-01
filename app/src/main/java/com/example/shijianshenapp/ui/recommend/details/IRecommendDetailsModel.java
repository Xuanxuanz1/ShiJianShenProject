package com.example.shijianshenapp.ui.recommend.details;

import com.ayma.base.mvp.ModelCallBack;
import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.base.IBaseModel;
import com.example.shijianshenapp.bean.RecommendBean;
import com.example.shijianshenapp.bean.RecommendDetailBean;

import java.util.List;

public interface IRecommendDetailsModel extends IBaseModel {
    void fetchNewList(int postition,ModelParamCallBack<RecommendDetailBean> modelParamCallBack);
}
