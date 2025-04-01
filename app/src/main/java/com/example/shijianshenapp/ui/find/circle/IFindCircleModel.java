package com.example.shijianshenapp.ui.find.circle;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.IBaseModel;
import com.example.shijianshenapp.bean.FriendCircleBean;

import java.util.List;

public interface IFindCircleModel extends IBaseModel {
    void fetchCircleData(ModelCallBack modelCallBack);

    void delItem(int position,ModelCallBack modelCallBack);

    void addItem(String message,ModelCallBack modelCallBack);

    List<FriendCircleBean> getCircleDate();
}
