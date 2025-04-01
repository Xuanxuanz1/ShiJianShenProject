package com.ayma.base.mvp;

import android.content.Context;

import com.ayma.base.util.AntiShakeUtil;

public abstract class MvpBasePresenter<V extends IMvpBaseView, T extends MvpBaseModel> implements IMvpBasePresenter {
    protected final String TAG = this.getClass().getSimpleName();
    protected T model;
    protected V view;
    protected Context context;
    private AntiShakeUtil antiShakeUtil;

    public MvpBasePresenter(Context context) {
        this.context = context;
        model = initModel();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onDestroy() {
        detach();
        model.onDestroy();
        if (antiShakeUtil!=null){
            antiShakeUtil.clear();
            antiShakeUtil = null;
        }
    }

    protected void attach(V view) {
        this.view = view;
    }

    protected void detach() {
        view = null;
    }

    protected abstract T initModel();

    protected boolean isViewAttach() {
        return view != null;
    }



    //region 防抖相关

    /**
     * 描述：防抖
     * 作者：黄业良
     * 时间：2021/12/15 16:27
     */
    public boolean antiShake(Object key) {
        if (antiShakeUtil == null) {
            antiShakeUtil = AntiShakeUtil.getInstance();
        }
        return antiShakeUtil.antiShake(key);
    }

    /**
     * 描述：获取上一层的方法名称
     * 作者：黄业良
     * 时间：2021/12/15 16:25
     */
    public String getMethodName() {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

    /**
     * 描述：移除防抖key
     * 作者：黄业良
     * 时间：2021/12/15 16:25
     */
    public void removeAntiShakeKey(Object key) {
        if (antiShakeUtil == null) {
            return;
        }
        antiShakeUtil.removeAntiShake(key);
    }

    /**
     * 描述：防抖，1秒后自动解除
     * 作者：黄业良
     * 时间：2021/12/15 16:26
     */
    public boolean antiShakeAutoRemove(Object key) {
        return antiShakeAutoRemove(key, 1000);
    }

    /**
     * 描述：防抖，指定时间后自动解除
     * 作者：黄业良
     * 时间：2021/12/15 16:26
     */
    public boolean antiShakeAutoRemove(Object key, int delay) {
        if (antiShakeUtil == null) {
            antiShakeUtil = AntiShakeUtil.getInstance();
        }
        return antiShakeUtil.antiShakeAutoRemove(key, delay);
    }
    //endregion
}
