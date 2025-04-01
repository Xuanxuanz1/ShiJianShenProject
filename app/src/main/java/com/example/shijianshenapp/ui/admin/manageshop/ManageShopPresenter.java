package com.example.shijianshenapp.ui.admin.manageshop;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.BasePresenter;
import com.example.shijianshenapp.bean.ShopBean;

public class ManageShopPresenter extends BasePresenter<ManageShopContact.IManageShopView,ManageShopModel> implements ManageShopContact.IManageShopPresenter {

    public ManageShopPresenter(Context context) {
        super(context);
    }

    @Override
    protected ManageShopModel initModel() {
        return new ManageShopModel(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fetchShopData();
    }

    @Override
    public void fetchShopData() {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在加载数据");
        model.fetchShopData(new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.notifyShopList(model.getShopList());
            }

            @Override
            public void requestError(Throwable t) {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.showSingleAlertNoAction(t.getMessage());
            }

            @Override
            public void requestFailure(Throwable t) {
                requestError(t);
            }
        });
    }

    @Override
    public void editGoods(int position, String goodsName, String goodsQty, String goodsPrice, String goodsSold) {
        if (!isViewAttach()){
            return;
        }
        model.editGoods(goodsName, goodsQty, goodsSold, goodsPrice, position, new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.notifyShopList(model.getShopList());
            }

            @Override
            public void requestError(Throwable t) {
                if (!isViewAttach()){
                    return;
                }
                view.showSingleAlertNoAction(t.getMessage());
            }

            @Override
            public void requestFailure(Throwable t) {
                requestError(t);
            }
        });
    }

    @Override
    public void addGoods(String goodsName, String goodsQty, String goodsPrice, String goodsSold,String typeName) {
        if (!isViewAttach()){
            return;
        }
        model.addGoods(goodsName, goodsQty, goodsSold, goodsPrice,typeName, new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.notifyShopList(model.getShopList());
            }

            @Override
            public void requestError(Throwable t) {
                if (!isViewAttach()){
                    return;
                }
                view.showSingleAlertNoAction(t.getMessage());
            }

            @Override
            public void requestFailure(Throwable t) {
                requestError(t);
            }
        });
    }

    @Override
    public void delGoods(int position) {
        if (!isViewAttach()){
            return;
        }
        model.delGoods(position, new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.notifyShopList(model.getShopList());
            }

            @Override
            public void requestError(Throwable t) {
                if (!isViewAttach()){
                    return;
                }
                view.showSingleAlertNoAction(t.getMessage());
            }

            @Override
            public void requestFailure(Throwable t) {
                requestError(t);
            }
        });
    }
}
