package com.ayma.base.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mars on 2017/8/25.
 */

public class SharedPreferencesUtils {
    private static final String SP_NAME = "RFID";

    /**
     * 保存数据的方法，需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context 调用方法的对象
     *                //     * @param fileName 保存文件名
     * @param key     要保存的值名
     * @param object  要保存的值
     */
    public static boolean setParam(Context context, String key, Object object) {
//    public static boolean setParam(Context context, String fileName, String key, Object object){

        String type = object.getClass().getSimpleName();
//        SharedPreferences.Editor editor = context.getSharedPreferences(fileName,
        SharedPreferences.Editor editor = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit();

        switch (type) {
            case "String":
                editor.putString(key, (String) object);
                break;
            case "Integer":
                editor.putInt(key, (Integer) object);
                break;
            case "Boolean":
                editor.putBoolean(key, (Boolean) object);
                break;
            case "Float":
                editor.putFloat(key, (Float) object);
                break;
            case "Long":
                editor.putLong(key, (Long) object);
                break;
        }
        return editor.commit();
    }

    /**
     * 得到保存数据的方法，根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context       调用方法的对象
     *                      //     * @param fileName 保存文件名
     * @param key           要保存的值名
     * @param defaultObject 取值失败时赋予的默认值
     * @return 返回取出的值
     */
    public static Object getParam(Context context, String key, Object defaultObject) {
//    public static Object getParam(Context context, String fileName, String key, Object defaultObject){
        String type = defaultObject.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
//        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);

        switch (type) {
            case "String":
                return sharedPreferences.getString(key, (String) defaultObject);
            case "Integer":
                return sharedPreferences.getInt(key, (Integer) defaultObject);
            case "Boolean":
                return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
            case "Float":
                return sharedPreferences.getFloat(key, (Float) defaultObject);
            case "Long":
                return sharedPreferences.getLong(key, (Long) defaultObject);
        }
        return defaultObject;
    }
}
