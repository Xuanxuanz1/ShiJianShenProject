package com.example.shijianshenapp.ui.train;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.ayma.base.mvp.ModelParamCallBack;
import com.example.shijianshenapp.base.BasePresenter;
import com.example.shijianshenapp.bean.TrainBean;

import java.util.List;

public class TrainPresenter extends BasePresenter<TrainContact.ITrainView,TrainModel> implements TrainContact.ITrainPresenter {
    public TrainPresenter(Context context) {
        super(context);
    }

    @Override
    protected TrainModel initModel() {
        return new TrainModel(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fetchTrainData();
    }

    @Override
    public void fetchTrainData() {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在加载数据");
        model.fetchTrainData(new ModelParamCallBack<List<TrainBean>>() {
            @Override
            public void requestSuccess(List<TrainBean> data) {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.showTrainData(data);
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
}
