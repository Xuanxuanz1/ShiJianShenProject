package com.example.shijianshenapp.base;

import android.content.Context;

import com.ayma.base.mvp.MvpBasePresenter;

public abstract class BasePresenter<V extends BaseContact.IBaseView,T extends BaseModel> extends MvpBasePresenter<V,T> implements BaseContact.IBasePresenter{
    public BasePresenter(Context context) {
        super(context);
    }
}
