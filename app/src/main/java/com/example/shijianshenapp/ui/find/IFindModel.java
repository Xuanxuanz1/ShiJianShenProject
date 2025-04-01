package com.example.shijianshenapp.ui.find;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.IBaseModel;
import com.example.shijianshenapp.bean.TrainBean;

import java.util.List;

public interface IFindModel extends IBaseModel {
    void fetchVideoData(ModelCallBack modelCallBack);

    List<TrainBean> getVideoList();
}
