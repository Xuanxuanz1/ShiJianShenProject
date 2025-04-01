package com.ayma.base.util;

import android.os.Handler;
import android.os.Looper;

import java.util.HashSet;
import java.util.Set;

public class AntiShakeUtil {
    private static Handler handler;
    private static AntiShakeUtil instance;
    private final Set<Object> antiShakeSet = new HashSet<>();
    private final Object object = new Object();

    private AntiShakeUtil() {

    }

    public static AntiShakeUtil getInstance() {
        if (instance == null) {
            synchronized (AntiShakeUtil.class) {
                if (instance == null) {
                    instance = new AntiShakeUtil();
                    handler = new Handler(Looper.getMainLooper());
                }
            }
        }
        return instance;
    }

    /**
     * 描述：添加防抖key
     * 作者：黄业良
     * 时间：2021/12/15 16:25
     */
    public boolean antiShake(final Object key) {
        synchronized (object) {
            if (antiShakeSet.contains(key)) {
                return true;
            }
            antiShakeSet.add(key);
            return false;
        }
    }

    /**
     * 描述：防抖，指定时间后自动解除
     * 作者：黄业良
     * 时间：2021/12/15 16:26
     */
    public boolean antiShakeAutoRemove(final Object key, int delay) {
        synchronized (object) {
            if (antiShakeSet.contains(key)) {
                return true;
            }
            antiShakeSet.add(key);
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    antiShakeSet.remove(key);
                }
            }, delay);
            return false;
        }
    }

    /**
     * 描述：移除防抖key
     * 作者：黄业良
     * 时间：2021/12/15 16:25
     */
    public void removeAntiShake(Object key) {
        antiShakeSet.remove(key);
    }

    public void clear() {
        antiShakeSet.clear();
    }
}
