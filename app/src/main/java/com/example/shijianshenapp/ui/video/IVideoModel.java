package com.example.shijianshenapp.ui.video;

import com.ayma.base.mvp.ModelCallBack;
import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.base.IBaseModel;
import com.example.shijianshenapp.bean.TrainBean;
import com.example.shijianshenapp.bean.TrainProgramBean;

import java.util.List;

public interface IVideoModel extends IBaseModel {
    void fetchVideoData(ModelCallBack modelCallBack);

    void trainSuccess(int position,int clickPosition,ModelCallBack modelCallBack);

    TrainBean getTrainList(int position);

    List<TrainProgramBean> getTrainPro(int position);
}
