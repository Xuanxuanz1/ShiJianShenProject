package com.example.shijianshenapp.ui.admin.manageshop;

import android.widget.EditText;

import com.example.shijianshenapp.base.BaseContact;
import com.example.shijianshenapp.bean.ShopBean;

import java.util.List;

public interface ManageShopContact {
    interface IManageShopView extends BaseContact.IBaseView{
        void notifyShopList(List<ShopBean> list);

        void showGoodsEditDialog(int position,ShopBean data);

        void showAddGoodsDialog();

        boolean checkGoodsMessage(EditText etGoodsName, EditText etQty, EditText etSold, EditText etPrice,boolean isAdd);
    }
    interface IManageShopPresenter extends BaseContact.IBasePresenter{
        void fetchShopData();

        void editGoods(int position,String goodsName,String goodsQty,String goodsPrice,String goodsSold);

        void addGoods(String goodsName,String goodsQty,String goodsPrice,String goodsSold,String typeName);

        void delGoods(int position);
    }
}
