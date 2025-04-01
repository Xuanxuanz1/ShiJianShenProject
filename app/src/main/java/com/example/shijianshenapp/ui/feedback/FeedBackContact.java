package com.example.shijianshenapp.ui.feedback;

import com.example.shijianshenapp.base.BaseContact;

public interface FeedBackContact {
    interface IFeedBackView extends BaseContact.IBaseView{
        void submit();

        void submitSuccess();
    }
    interface IFeedBackPresenter extends BaseContact.IBasePresenter{
        void submit(String backMessage);
    }
}
