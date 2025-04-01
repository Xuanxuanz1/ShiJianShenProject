package com.ayma.base.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ayma.base.util.AntiShakeUtil;


public abstract class MvpBaseFragment<V extends IMvpBaseView, T extends MvpBasePresenter> extends Fragment implements IMvpBaseView {
    protected final String TAG = this.getClass().getSimpleName();
    protected View rootView;
    private boolean isFirstLoad = true;  //是否是第一次加载

    /**
     * 定义一个Presenter 用于解绑持有的View
     * 在onCreate进行初始化Presenter的操作
     * 在onResume中进行绑定
     * 在onDestroy 中进行解绑
     */
    public T presenter;
    public Activity activity;
    private AntiShakeUtil antiShakeUtil;

    /**
     * 这里是适配为不同的View 装载不同Presenter
     */
    public abstract T initPresenter();

    //加载布局
    public abstract int getLayoutId();

    //加载数据
    public abstract void loadData();

    protected boolean onBackPress(){
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        presenter = initPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
        }
        presenter.attach((V) this);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            loadData();
            isFirstLoad = false;
        }
    }

    @Override
    public void onDestroyView() {
        presenter.onDestroy();
        rootView = null;
        isFirstLoad = true;
        super.onDestroyView();
    }

    protected void initView(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    return onBackPress();
                }
                return false;
            }
        });
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

    public <T extends View> T $(int resId){
        return (T) rootView.findViewById(resId);
    }
}
