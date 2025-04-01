package com.example.shijianshenapp.ui.admin.checkvideoclass;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.IBaseModel;
import com.example.shijianshenapp.bean.TrainBean;

import java.util.List;

public interface ICheckVideoClassModel extends IBaseModel {
    void fetchVideoData(ModelCallBack modelCallBack);

    void editVideo(int position, String title, String message, String time, String likeNumber, String watchNumber, ModelCallBack modelCallBack);

    void addVideo(String title,String message,String time,String likeNumber,String watchNumber,ModelCallBack modelCallBack);

    void deletVideo(int position,ModelCallBack modelCallBack);

    List<TrainBean> getVideoList();
}
