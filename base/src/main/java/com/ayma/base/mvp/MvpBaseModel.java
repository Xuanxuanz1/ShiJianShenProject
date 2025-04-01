package com.ayma.base.mvp;

import com.ayma.base.util.AntiShakeUtil;
import com.ayma.base.util.ThrowHandleUtil;

import retrofit2.Response;

public class MvpBaseModel implements IMvpBaseModel {
    private AntiShakeUtil antiShakeUtil;

    public Throwable generateThrowable(Throwable t) {
        return ThrowHandleUtil.generateThrowable(t);
    }


    @Override
    public void onDestroy() {
        if (antiShakeUtil!=null){
            antiShakeUtil.clear();
            antiShakeUtil = null;
        }
    }

    @Override
    public Throwable generateThrowable(String message) {
        return ThrowHandleUtil.generateThrowable(message);
    }

    public Throwable generateThrowable(Response response) {
        return ThrowHandleUtil.generateThrowable(response);
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
