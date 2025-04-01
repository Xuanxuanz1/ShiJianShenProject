package com.example.shijianshenapp.ui.trainsport;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseModel;
import com.example.shijianshenapp.bean.TrainProgramBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainSportModel extends BaseModel implements ITrainSportModel {

    private TrainProgramBean data  = new TrainProgramBean();

    public TrainSportModel(Context context) {
        super(context);
    }

    @Override
    public void fetchTrainSportData(TrainProgramBean bean,ModelParamCallBack<TrainProgramBean> modelParamCallBack) {
        if (bean == null){
            modelParamCallBack.requestError(generateThrowable("数据为空"));
            return;
        }
        data = bean;
        modelParamCallBack.requestSuccess(bean);
    }

    @Override
    public TrainProgramBean getTrainData() {
        return data;
    }
}
