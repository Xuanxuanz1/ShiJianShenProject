package com.ayma.base.util;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteExceptionToFileUtil {
    private static final String TAG="WriteExceptionToFile";
    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    public static String saveCatchInfo2File(Context mContext,Throwable ex) {
        StringBuilder sb = new StringBuilder();
//        for (Map.Entry<String, String> entry : infos.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            sb.append(key).append("=").append(value).append("\n");
//        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);

        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = formatter.format(new Date());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("description", sb.toString());
            jsonObject.put("phoneModel", Build.MODEL);
            jsonObject.put("appVersion", CommonUtil.getVersionName(mContext));
            jsonObject.put("occurTime", formatter2.format(new Date()));
            jsonObject.put("androidVersion", Build.VERSION.RELEASE);

            long timestamp = System.currentTimeMillis();
            // 用于格式化日期,作为日志文件名的一部分

            String fileName = "crash-" + time + "-" + timestamp + ".txt";
            String path = mContext.getFilesDir().getAbsolutePath() + "/crash/";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(path + fileName);
            fos.write(jsonObject.toString().getBytes(Charset.forName("UTF-8")));
            Log.e(TAG, jsonObject.toString());
//            Log.i(TAG, "已保存崩溃信息");
//            Log.e(TAG, "路径" + path + fileName);
            // 发送给开发人员
//                sendCrashLog2PM(path + fileName);
            fos.close();
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
        return null;
    }
}
