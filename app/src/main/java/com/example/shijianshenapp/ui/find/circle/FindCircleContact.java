package com.example.shijianshenapp.ui.find.circle;

import com.example.shijianshenapp.base.BaseContact;
import com.example.shijianshenapp.bean.CommentBean;
import com.example.shijianshenapp.bean.FriendCircleBean;

import java.util.List;

public interface FindCircleContact {
    interface IFindCircleView extends BaseContact.IBaseView{

        void showSendCircleDialog();

        void notifyCircle(List<FriendCircleBean> list);

    }
    interface IFindCirclePresenter extends BaseContact.IBasePresenter{

        void fetchCircleData();

        void delItem(int position);

        void addItem(String message);
    }
}
