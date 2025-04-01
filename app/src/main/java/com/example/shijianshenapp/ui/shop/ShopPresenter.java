package com.example.shijianshenapp.ui.shop;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.BasePresenter;

public class ShopPresenter extends BasePresenter<ShopContact.IShopView,ShopModel> implements ShopContact.IShopPresenter {
    public ShopPresenter(Context context) {
        super(context);
    }

    @Override
    protected ShopModel initModel() {
        return new ShopModel(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fetchGoodsData();
    }


    @Override
    public void fetchGoodsData() {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在加载商品数据");
        model.fetchGoodsData(new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.notifyGoodsList(model.getGoodsList());
                view.notifyTypeList(model.getGoodsList());
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
    public void buyGoods(int position) {
        if (!isViewAttach()){
            return;
        }
        model.buyGoods(position, new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.notifyTypeList(model.getGoodsList());
                view.notifyGoodsList(model.getGoodsList());
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
