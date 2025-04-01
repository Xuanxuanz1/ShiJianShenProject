package com.example.shijianshenapp.ui.trainsport;

import com.ayma.base.mvp.ModelCallBack;
import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.base.IBaseModel;
import com.example.shijianshenapp.bean.TrainProgramBean;

public interface ITrainSportModel extends IBaseModel {
    void fetchTrainSportData(TrainProgramBean bean,ModelParamCallBack<TrainProgramBean> modelParamCallBack);

    TrainProgramBean getTrainData();
}
