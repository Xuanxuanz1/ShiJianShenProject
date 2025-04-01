package com.example.shijianshenapp.ui.admin.checkvideoclass;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseModel;
import com.example.shijianshenapp.bean.TrainBean;

import org.litepal.Operator;

import java.util.ArrayList;
import java.util.List;

public class CheckVideoClassModel extends BaseModel implements ICheckVideoClassModel {

    private List<TrainBean> list = new ArrayList<>();

    public CheckVideoClassModel(Context context) {
        super(context);
    }

    @Override
    public void fetchVideoData(ModelCallBack modelCallBack) {
        try{
            List<TrainBean> bean = Operator.findAll(TrainBean.class);
            list.addAll(bean);
            modelCallBack.requestSuccess();
        }catch (Exception e){
            modelCallBack.requestError(generateThrowable(e));
            e.printStackTrace();
        }
    }

    @Override
    public void editVideo(int position, String title, String message, String time, String likeNumber, String watchNumber, ModelCallBack modelCallBack) {
        try {
            TrainBean data = list.get(position);
            data.setTitle(title);
            data.setMessage(message);
            data.setTime(time);
            data.setLikeNumber(Integer.parseInt(likeNumber));
            data.setWatchNumber(watchNumber);
            Operator.saveAll(list);
            modelCallBack.requestSuccess();
        }catch (Exception e){
            modelCallBack.requestError(generateThrowable(e));
            e.printStackTrace();
        }
    }

    @Override
    public void addVideo(String title, String message, String time, String likeNumber, String watchNumber, ModelCallBack modelCallBack) {
        try{
            List<TrainBean> newList = new ArrayList<>();
            TrainBean data = new TrainBean();
            data.setTitle(title);
            data.setMessage(message);
            data.setTime(time);
            data.setLikeNumber(Integer.parseInt(likeNumber));
            data.setWatchNumber(watchNumber);
            data.setHeadImg(R.drawable.train_02);
            list.add(data);
            newList.add(data);
            Operator.saveAll(newList);
            modelCallBack.requestSuccess();
        }catch (Exception e){
            modelCallBack.requestError(generateThrowable(e));
            e.printStackTrace();
        }
    }

    @Override
    public void deletVideo(int position,ModelCallBack modelCallBack) {
        try{
           Operator.deleteAll(TrainBean.class,"title=?",list.get(position).getTitle());
           list.remove(position);
           modelCallBack.requestSuccess();
        }catch (Exception e){
            modelCallBack.requestError(generateThrowable(e));
            e.printStackTrace();
        }
    }

    @Override
    public List<TrainBean> getVideoList() {
        return list;
    }
}
