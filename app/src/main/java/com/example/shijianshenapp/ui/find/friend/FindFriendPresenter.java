package com.example.shijianshenapp.ui.find.friend;

import android.content.Context;

import com.example.shijianshenapp.base.BasePresenter;

public class FindFriendPresenter extends BasePresenter<FindFriendContact.IFindFriendView,FindFriendModel> implements FindFriendContact.IFindFriendPresenter {
    public FindFriendPresenter(Context context) {
        super(context);
    }

    @Override
    protected FindFriendModel initModel() {
        return new FindFriendModel(context);
    }
}
