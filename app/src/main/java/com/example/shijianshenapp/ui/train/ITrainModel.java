package com.example.shijianshenapp.ui.train;

import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.base.IBaseModel;
import com.example.shijianshenapp.bean.TrainBean;

import java.util.List;

public interface ITrainModel extends IBaseModel {
    void fetchTrainData(ModelParamCallBack<List<TrainBean>> modelParamCallBack);
}
