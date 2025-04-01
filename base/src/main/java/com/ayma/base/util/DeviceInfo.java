package com.ayma.base.util;


import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
 * @ 描述：获取设备信息工具类
 * @ 作者：黄业良
 * @ 时间：2020/3/31 11:28
 * @ 包名：com.ayma.base.util
 * @ 类名：DeviceInfo.java
 */
public class DeviceInfo {
    public static String getDeviceModel() {
        return new Build().MODEL;
    }

    public static String getImei(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    public static String getSN(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimSerialNumber();
    }

    public static String getMacAddress() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) {
                    continue;
                }
                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }
                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }
                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
