package com.example.shijianshenapp.ui.find;

import com.example.shijianshenapp.base.BaseContact;
import com.example.shijianshenapp.bean.TrainBean;

import java.util.List;

public interface FindContact {
    interface IFindView extends BaseContact.IBaseView{
        void notifyVideoList(List<TrainBean> list);
    }
    interface IFindPresenter extends BaseContact.IBasePresenter{
        void fetchVideoData();
    }
}
