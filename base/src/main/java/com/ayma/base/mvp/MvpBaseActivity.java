package com.ayma.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ayma.base.util.AntiShakeUtil;


public abstract class MvpBaseActivity<V extends IMvpBaseView, T extends MvpBasePresenter> extends AppCompatActivity implements IMvpBaseView {
    protected final String TAG = getClass().getSimpleName();
    protected AntiShakeUtil antiShakeUtil;
    /**
     * 定义一个Presenter 用于解绑持有的View
     * 在onCreate进行初始化Presenter的操作
     * 在onResume中进行绑定
     * 在onDestroy 中进行解绑
     */
    public T presenter;
    public Context context;


    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        presenter = initPresenter();
        presenter.attach((V) this);
    }

    @Override
    protected void onResume() {
        presenter.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        presenter.onStop();
        super.onStop();
    }

    @Override
    protected void onRestart() {
        presenter.onRestart();
        super.onRestart();
    }

    /**
     * 这里是适配为不同的View 装载不同Presenter
     */
    public abstract T initPresenter();

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        if (antiShakeUtil != null) {
            antiShakeUtil.clear();
            antiShakeUtil = null;
        }
        super.onDestroy();
    }

    private static final int THRESHOLD_CLICK_TO_FAST = 200;
    private long firstClick;
    private boolean isClick = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //点击屏幕，收起键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null) {
                if (getCurrentFocus().getWindowToken() != null) {
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
        return super.onTouchEvent(event);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_UP) {
//            if (isClick) {
//                if (System.currentTimeMillis() - firstClick < THRESHOLD_CLICK_TO_FAST) {
//                    firstClick = System.currentTimeMillis();
//                    isClick = false;
//                    Log.e(TAG, "拦截");
//                    return true;
//                }
//                isClick = false;
//                firstClick = System.currentTimeMillis();
//            } else {
//                firstClick = System.currentTimeMillis();
//                isClick = true;
//            }
//        }
//        return super.dispatchTouchEvent(event);
//    }

    public <T extends View> T $(int resId) {
        return (T) findViewById(resId);
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
        return antiShakeAutoRemove(key, 1500);
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
