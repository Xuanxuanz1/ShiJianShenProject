package com.example.shijianshenapp.ui.admin.self;

import android.content.Context;

import com.example.shijianshenapp.base.BasePresenter;

public class AdminPresenter extends BasePresenter<AdminContact.IAdminView,AdminModel> implements AdminContact.IAdminPresenter {
    public AdminPresenter(Context context) {
        super(context);
    }

    @Override
    protected AdminModel initModel() {
        return new AdminModel(context);
    }
}
