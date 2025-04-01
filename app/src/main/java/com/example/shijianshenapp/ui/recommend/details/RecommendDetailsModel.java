package com.example.shijianshenapp.ui.recommend.details;

import android.content.Context;

import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.base.BaseModel;
import com.example.shijianshenapp.bean.RecommendBean;
import com.example.shijianshenapp.bean.RecommendDetailBean;

import org.litepal.Operator;

import java.util.List;

public class RecommendDetailsModel extends BaseModel implements IRecommendDetailsModel {
    public RecommendDetailsModel(Context context) {
        super(context);
    }

    @Override
    public void fetchNewList(int postition,ModelParamCallBack<RecommendDetailBean> modelParamCallBack) {
        List<RecommendDetailBean> list = Operator.findAll(RecommendDetailBean.class);
        if (list.isEmpty()){
            modelParamCallBack.requestError(generateThrowable("无数据"));
            return;
        }
        modelParamCallBack.requestSuccess(list.get(postition));
    }
}
