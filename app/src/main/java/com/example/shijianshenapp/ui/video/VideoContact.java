package com.example.shijianshenapp.ui.video;

import com.example.shijianshenapp.base.BaseContact;
import com.example.shijianshenapp.bean.TrainBean;
import com.example.shijianshenapp.bean.TrainProgramBean;

import java.util.List;

public interface VideoContact {
    interface IVideoView extends BaseContact.IBaseView{
        void init(TrainBean data);

        void showUpDialog();

        void notifyTrainSportList(List<TrainProgramBean> list);
    }
    interface IVideoPresenter extends BaseContact.IBasePresenter{
        void fetchVideoData(int position);

        void trainSuccess(int position,int clickPosition);
    }
}
