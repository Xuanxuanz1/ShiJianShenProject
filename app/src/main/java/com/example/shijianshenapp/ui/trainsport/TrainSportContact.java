package com.example.shijianshenapp.ui.trainsport;

import com.example.shijianshenapp.base.BaseContact;
import com.example.shijianshenapp.bean.TrainProgramBean;

public interface TrainSportContact {
    interface ITrainSportView extends BaseContact.IBaseView{

        void upDateUi(int time);

        void trainEnd();

        void loadTrainSportData(TrainProgramBean data);
    }
    interface ITrainSportPresenter extends BaseContact.IBasePresenter{
        void fetchTrainSportData(TrainProgramBean bean);

        void resetTrainSportData();
    }
}
