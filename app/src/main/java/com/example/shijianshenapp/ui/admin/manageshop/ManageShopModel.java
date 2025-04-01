package com.example.shijianshenapp.ui.admin.manageshop;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.BaseModel;
import com.example.shijianshenapp.bean.ShopBean;

import org.litepal.Operator;

import java.util.ArrayList;
import java.util.List;

public class ManageShopModel extends BaseModel implements IManageShopModel {

    private List<ShopBean> list = new ArrayList<>();

    public ManageShopModel(Context context) {
        super(context);
    }

    @Override
    public void fetchShopData(ModelCallBack modelCallBack) {
        try{
            List<ShopBean> bean = Operator.findAll(ShopBean.class);

            if (bean.isEmpty()){
                modelCallBack.requestError(generateThrowable("商品数据为空"));
                return;
            }

            list.addAll(bean);
            modelCallBack.requestSuccess();

        }catch (Exception e){
            modelCallBack.requestError(generateThrowable(e));
            e.printStackTrace();
        }
    }

    @Override
    public void editGoods(String goodsName, String goodsQty, String goodsSold, String goodsPrice, int position, ModelCallBack modelCallBack) {
        try{
            ShopBean data = list.get(position);
            data.setGoodsName(goodsName);
            data.setGoodsQty(Integer.parseInt(goodsQty));
            data.setSold(Integer.parseInt(goodsSold));
            data.setGoodsPrice(Integer.parseInt(goodsPrice));
            Operator.saveAll(list);
            modelCallBack.requestSuccess();
        }catch (Exception e){
            modelCallBack.requestError(generateThrowable(e));
            e.printStackTrace();
        }
    }

    @Override
    public void addGoods(String goodsName, String goodsQty, String goodsSold, String goodsPrice,String typeName, ModelCallBack modelCallBack) {
        try{
            List<ShopBean> newList = new ArrayList<>();
            ShopBean data = new ShopBean();
            data.setGoodsName(goodsName);
            data.setGoodsQty(Integer.parseInt(goodsQty));
            data.setSold(0);
            data.setGoodsPrice(Integer.parseInt(goodsPrice));
            data.setTypeName(typeName);
            list.add(data);
            newList.add(data);
            Operator.saveAll(newList);
            modelCallBack.requestSuccess();
        }catch (Exception e){
            modelCallBack.requestError(generateThrowable(e));
            e.printStackTrace();
        }
    }

    @Override
    public void delGoods(int position, ModelCallBack modelCallBack) {
        try{
            ShopBean data = list.get(position);
            Operator.deleteAll(ShopBean.class,"goodsName=?",data.getGoodsName());
            list.remove(position);
            modelCallBack.requestSuccess();
        }catch (Exception e){
            modelCallBack.requestError(generateThrowable(e));
            e.printStackTrace();
        }
    }

    @Override
    public List<ShopBean> getShopList() {
        return list;
    }
}
