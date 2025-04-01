package com.example.shijianshenapp.ui.train;

import com.example.shijianshenapp.base.BaseContact;
import com.example.shijianshenapp.bean.TrainBean;

import java.util.List;

public interface TrainContact {
    interface ITrainView extends BaseContact.IBaseView{
        void showTrainData(List<TrainBean> list);
    }
    interface ITrainPresenter extends BaseContact.IBasePresenter{
        void fetchTrainData();
    }
}
