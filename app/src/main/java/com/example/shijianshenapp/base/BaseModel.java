package com.example.shijianshenapp.base;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.ayma.base.mvp.ModelCallBack;
import com.ayma.base.mvp.MvpBaseModel;

import androidx.annotation.NonNull;

public class BaseModel extends MvpBaseModel implements IBaseModel {

    private static final int WHAT_SORT_SUCCESS = 1230;
    private static final int WHAT_SORT_FAIL = 1231;
    private ModelCallBack sortCallBack;

    protected Context context;
    protected Handler baseHandler;

    public BaseModel(Context context){
        this.context = context;
        initHandler();
    }

    @Override
    public void initHandler() {
        baseHandler = new Handler(context.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case WHAT_SORT_SUCCESS:
                        if (sortCallBack == null){
                            return;
                        }
                        sortCallBack.requestSuccess();
                        break;
                    case WHAT_SORT_FAIL:
                        if (sortCallBack == null){
                            return;
                        }
                        sortCallBack.requestError((Throwable) msg.obj);
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }
}
