package com.example.shijianshenapp.ui.shop;

import com.example.shijianshenapp.base.BaseContact;
import com.example.shijianshenapp.bean.ShopBean;

import java.util.List;

public interface ShopContact {
    interface IShopView extends BaseContact.IBaseView{
        void notifyGoodsList(List<ShopBean> list);

        void notifyTypeList(List<ShopBean> list);

        void showGoodsDialog(ShopBean data);
    }
    interface IShopPresenter extends BaseContact.IBasePresenter{
        void fetchGoodsData();

        void buyGoods(int position);
    }
}
