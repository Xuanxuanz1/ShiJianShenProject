package com.example.shijianshenapp.ui.admin.changerecommend;

import com.example.shijianshenapp.base.BaseContact;
import com.example.shijianshenapp.bean.RecommendBean;
import com.example.shijianshenapp.bean.RecommendDetailBean;

import java.util.List;

public interface ChangeRecommendContact {
    interface IChangeRecommendView extends BaseContact.IBaseView{
        void notifyChangeRecommend(List<RecommendBean> list);

        void showAddRecomendDialog();

        void showEditRecommendDialog(int postition,RecommendDetailBean data);
    }
    interface IChangeRecommendPresenter extends BaseContact.IBasePresenter{
        void fetchRecommend();

        void addNewRecommend(String title,String message);

        void editRecommend(int postition,String title,String message);

        void deletRecommend(int postition);

        RecommendDetailBean getRecommendDetail(int postition);
    }
}
