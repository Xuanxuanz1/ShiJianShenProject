package com.ayma.base.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.ayma.base.R;
import com.ayma.base.widget.ToastCompat;

import java.lang.reflect.Method;

public class CommonUtil {


    /**
     * 获取机器的类型
     */
    public static String getPhoneType() {
        return Build.MODEL;
    }

    /**
     * 获取手机唯一序列号
     */
    public static String getSerialNumber(Context context) {
        String serial = "";
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {//9.0+
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return "";
                }
                serial = Build.getSerial();
            } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {//8.0+
                serial = Build.SERIAL;
            } else {//8.0-
                Class<?> c = Class.forName("android.os.SystemProperties");
                Method get = c.getMethod("get", String.class);
                serial = (String) get.invoke(c, "ro.serialno");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("e", "读取设备序列号异常：" + e.toString());
        }
        return serial;
    }


    /**
     * Unicode码转中文字符串
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuilder outBuffer = new StringBuilder(len);

        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);

                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;

                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);

                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);

                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';

                    else if (aChar == 'n')
                        aChar = '\n';

                    else if (aChar == 'f')
                        aChar = '\f';

                    outBuffer.append(aChar);
                }

            } else
                outBuffer.append(aChar);

        }
        return outBuffer.toString();
    }

    public static int getVersionCode(Context context) {
        try {
            PackageInfo manager = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return manager.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }

    /**
     * 取得版本名
     */
    public static String getVersionName(Context context) {
        try {
            PackageInfo manager = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return manager.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "Unknown";
        }
    }


    /**
     * 把dip 转换为 px 单位数值
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 把px 转换为 dip 单位数值
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 字符串转化成为16进制字符串
     *
     * @param s
     * @return
     */
    public static String strTo16(String s) {
        String str = "";
        char[] chars = s.toCharArray();
        for (char c : chars) {
            str += Integer.toHexString(c);
        }
        return str;
    }

    /**
     * 16进制转换成为string类型字符串
     *
     * @param s
     * @return
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    /**
     * 显示提示信息
     *
     * @param context
     * @param message
     */
    public static Toast toast;

    public static void showShortToast(final Context context,
                                      final String message) {
//		Logger.e("test","CommonTools showShortToast MESSAGE="+MESSAGE);
        if (toast == null) {
            new Thread(new Runnable() {
                public void run() {
                    Looper.prepare();
                    View view = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
                    TextView text = (TextView) view.findViewById(R.id.toast_message);
                    text.setText(message);
                    toast = new Toast(context);

                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 300);
                    toast.setView(view);
                    ToastCompat.hook(toast);
                    toast.show();
                    Looper.loop();
                }
            }).start();
        } else {
            new Thread(new Runnable() {
                public void run() {
                    Looper.prepare();
                    View view = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
                    TextView text = (TextView) view.findViewById(R.id.toast_message);
                    text.setText(message);
                    toast.setView(view);
                    ToastCompat.hook(toast);
                    toast.show();
                    Looper.loop();
                }
            }).start();
        }

    }

}
