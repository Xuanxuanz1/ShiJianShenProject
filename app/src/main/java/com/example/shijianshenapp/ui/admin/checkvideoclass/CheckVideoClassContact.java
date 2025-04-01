package com.example.shijianshenapp.ui.admin.checkvideoclass;

import com.example.shijianshenapp.base.BaseContact;
import com.example.shijianshenapp.bean.TrainBean;

import java.util.List;

public interface CheckVideoClassContact {
    interface ICheckVideoClassView extends BaseContact.IBaseView{
        void showAddNewVideoDialog();

        void showEditVideoDialog(int position,TrainBean data);

        void notifyCheckVideoList(List<TrainBean> list);
    }
    interface IChekcVideoClassPresenter extends BaseContact.IBasePresenter{
        void fetchVideoList();

        void editVideo(int position,String title,String message,String time,String likeNumber,String watchNumber);

        void addNewVideo(String title,String message,String time,String likeNumber,String watchNumber);

        void deletVideo(int position);
    }
}
