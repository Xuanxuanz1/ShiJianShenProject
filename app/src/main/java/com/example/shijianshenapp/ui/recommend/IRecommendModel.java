package com.example.shijianshenapp.ui.recommend;

import com.ayma.base.mvp.ModelCallBack;
import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.base.IBaseModel;
import com.example.shijianshenapp.bean.RecommendBean;

import java.util.List;

public interface IRecommendModel extends IBaseModel {
    void fetchRecommendData(ModelParamCallBack<List<RecommendBean>> modelParamCallBack);
}
