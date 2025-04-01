package com.example.shijianshenapp.ui.shop;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.IBaseModel;
import com.example.shijianshenapp.bean.ShopBean;

import java.util.List;

public interface IShopModel extends IBaseModel {
    void fetchGoodsData(ModelCallBack modelCallBack);

    void buyGoods(int position,ModelCallBack modelCallBack);

    List<ShopBean> getGoodsList();
}
