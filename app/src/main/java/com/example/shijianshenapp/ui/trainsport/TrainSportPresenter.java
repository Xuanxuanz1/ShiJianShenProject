package com.example.shijianshenapp.ui.trainsport;

import android.content.Context;

import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.base.BasePresenter;
import com.example.shijianshenapp.bean.TrainProgramBean;

public class TrainSportPresenter extends BasePresenter<TrainSportContact.ITrainSportView,TrainSportModel> implements TrainSportContact.ITrainSportPresenter {
    public TrainSportPresenter(Context context) {
        super(context);
    }

    @Override
    protected TrainSportModel initModel() {
        return new TrainSportModel(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void fetchTrainSportData(TrainProgramBean bean) {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在加载数据");
        model.fetchTrainSportData(bean,new ModelParamCallBack<TrainProgramBean>() {
            @Override
            public void requestSuccess(TrainProgramBean data) {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.loadTrainSportData(data);
            }

            @Override
            public void requestError(Throwable t) {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.showSingleAlertNoAction(t.getMessage());
            }

            @Override
            public void requestFailure(Throwable t) {
                requestError(t);
            }
        });
    }

    @Override
    public void resetTrainSportData() {
        if (!isViewAttach()){
            return;
        }
        view.loadTrainSportData(model.getTrainData());
    }

}
