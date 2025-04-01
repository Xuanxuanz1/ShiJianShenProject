package com.example.shijianshenapp.ui.admin.changerecommend;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseModel;
import com.example.shijianshenapp.bean.RecommendBean;
import com.example.shijianshenapp.bean.RecommendDetailBean;

import org.litepal.Operator;

import java.util.ArrayList;
import java.util.List;

public class ChangeRecommendModel extends BaseModel implements IChangeRecommendModel {

    private List<RecommendBean> recommendList = new ArrayList<>();
    private List<RecommendDetailBean> detailList = new ArrayList<>();

    public ChangeRecommendModel(Context context) {
        super(context);
    }

    @Override
    public void fetchRecommendData(ModelCallBack modelCallBack) {
        List<RecommendBean> list = Operator.findAll(RecommendBean.class);
        List<RecommendDetailBean> detail = Operator.findAll(RecommendDetailBean.class);
        if (list.isEmpty()){
            modelCallBack.requestError(generateThrowable("数据为空"));
            return;
        }
        recommendList.addAll(list);
        detailList.addAll(detail);
        modelCallBack.requestSuccess();
    }

    @Override
    public void addNewRecommedn(String title, String message, ModelCallBack modelCallBack) {
        try{
            RecommendBean recommdendData = new RecommendBean(title, R.drawable.friend_01);
            RecommendDetailBean detailData = new RecommendDetailBean();
            detailData.setTitle(title);
            detailData.setMessage(message);
            recommendList.add(recommdendData);
            detailList.add(detailData);
            Operator.saveAll(recommendList);
            Operator.saveAll(detailList);
            modelCallBack.requestSuccess();
        }catch (Exception e){
            modelCallBack.requestError(generateThrowable(e));
            e.printStackTrace();
        }
    }

    @Override
    public void editRecommend(int postiton, String title, String message, ModelCallBack modelCallBack) {
        try{
            recommendList.get(postiton).setTitle(title);
            detailList.get(postiton).setTitle(title);
            detailList.get(postiton).setMessage(message);
            Operator.saveAll(recommendList);
            Operator.saveAll(detailList);
            modelCallBack.requestSuccess();
        }catch (Exception e){
            modelCallBack.requestError(generateThrowable(e));
            e.printStackTrace();
        }
    }

    @Override
    public void deletRecommend(int postition,ModelCallBack modelCallBack) {
        try{
            Operator.deleteAll(RecommendBean.class,"title=?",recommendList.get(postition).getTitle());
            Operator.deleteAll(RecommendDetailBean.class,"title=?",recommendList.get(postition).getTitle());
            recommendList.remove(postition);
            detailList.remove(postition);
            modelCallBack.requestSuccess();
        }catch (Exception e){
            modelCallBack.requestError(generateThrowable(e));
            e.printStackTrace();
        }
    }

    @Override
    public List<RecommendBean> getRecomemdList() {
        return recommendList;
    }

    @Override
    public List<RecommendDetailBean> getRecommendDetailList() {
        return detailList;
    }


}
