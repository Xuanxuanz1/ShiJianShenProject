package com.example.shijianshenapp.ui.sport;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.BasePresenter;

public class SportPresenter extends BasePresenter<SportContact.ISportView,SportModel> implements SportContact.ISportPresenter {
    public SportPresenter(Context context) {
        super(context);
    }

    @Override
    protected SportModel initModel() {
        return new SportModel(context);
    }


}
