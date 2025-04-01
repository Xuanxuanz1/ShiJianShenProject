package com.example.shijianshenapp.ui.admin.manageshop;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.IBaseModel;
import com.example.shijianshenapp.bean.ShopBean;

import java.util.List;

public interface IManageShopModel extends IBaseModel {
    void fetchShopData(ModelCallBack modelCallBack);

    void editGoods(String goodsName,String goodsQty,String goodsSold,String goodsPrice,int position,ModelCallBack modelCallBack);

    void addGoods(String goodsName,String goodsQty,String goodsSold,String goodsPrice,String typeName,ModelCallBack modelCallBack);

    void delGoods(int position,ModelCallBack modelCallBack);

    List<ShopBean> getShopList();
}
