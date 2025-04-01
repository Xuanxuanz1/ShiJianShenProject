package com.ayma.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * @ 描述：时间工具类
 * @ 作者：黄业良
 * @ 时间：2020/4/13 17:21
 * @ 包名：com.ayma.base.util
 * @ 类名：DateTimeUtils.java
 */
public class DateTimeUtils {
    public static String getDateToyyyyMMdd() {
        return parseDateToString("yyyy-MM-dd");
    }

    public static String getDateToyyyyMMdWithNoneSep() {
        return parseDateToString("yyyyMMdd");
    }

    public static String getDateToyyyyMMddHHmm() {
        return parseDateToString("yyyy-MM-dd HH:mm");
    }

    public static String getDateToyyyyMMddHHmmss() {
        return parseDateToString("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @ 描述：将传入的毫秒数转成指定格式的字符串
     */
    public static String parseDate(long timestamp, String pattern) {
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(new Date(timestamp));
    }

    /**
     * @ 描述：将当前日期转为指定格式的时间字符串
     */
    public static String parseDateToString(String pattern) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
        return formatter.format(currentTime);
    }

    /**
     * @ 描述：判断指定日期是否是今天
     */
    public static boolean isToday(String day) {
        //当前时间
        Date now = new Date();
        SimpleDateFormat sdf;
        if (day.contains("-")) {
            sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        } else if (day.contains("/")) {
            sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        } else if (day.contains(".")) {
            sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        }
        //获取今天的日期
        String nowDay = sdf.format(now);
        return nowDay.equals(day);
    }

    /**
     * @ 描述：判断指定日期是否是昨天
     */
    public static boolean isYesterday(String day, String pattern) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
            long create = formatter.parse(day).getTime();
            Calendar now = Calendar.getInstance();
            long ms = 1000 * (now.get(Calendar.HOUR_OF_DAY) * 3600 + now.get(Calendar.MINUTE) * 60 + now.get(Calendar.SECOND));//毫秒数
            long ms_now = now.getTimeInMillis();

            return ms_now - create < (ms + 24 * 3600 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @ 描述：判断指定时间跟今天相比的提示
     */
    public static String getDateTips(String createTime) {
        try {
            String ret = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            long create = sdf.parse(createTime).getTime();
            Calendar now = Calendar.getInstance();
            long ms = 1000 * (now.get(Calendar.HOUR_OF_DAY) * 3600 + now.get(Calendar.MINUTE) * 60 + now.get(Calendar.SECOND));//毫秒数
            long ms_now = now.getTimeInMillis();
            if (ms_now - create < ms) {
                ret = "今天";
            } else if (ms_now - create < (ms + 24 * 3600 * 1000)) {
                ret = "昨天";
            } else if (ms_now - create < (ms + 24 * 3600 * 1000 * 2)) {
                ret = "前天";
            } else {
                ret = "更早";
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @ 描述：将传入的日期格式化成新的指定格式
     */
    public static String formatDateStr(String dateStr, String datePattern, String newPattern) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(datePattern, Locale.getDefault());
            Date date = formatter.parse(dateStr);
            SimpleDateFormat newFormat = new SimpleDateFormat(newPattern, Locale.getDefault());
            return newFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取当前日期，格式为yyyy-MM-dd
     */
    public static String getNowDate() {
        return parseDateToString("yyyy-MM-dd");
    }

    /**
     * @ 描述：获取指定格式的当前日期
     */
    public static String getNowDate(String pattern) {
        return parseDateToString(pattern);
    }

    /**
     * @ 描述：获取当前时间字符串
     */
    public static String getNowTime() {
        return parseDateToString("HH:mm:ss");
    }


    public static Date parseToDateyyyyMMdd(String str) {
        return parseToDate(str, "yyyy-MM-dd");
    }

    public static Date parseToDateyyyyMMddHHmmss(String str) {
        return parseToDate(str, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @ 描述：将传入的时间字符串转成日期
     */
    public static Date parseToDate(String dateStr, String pattern) {
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @ 描述：获取传入的日期的下一天
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        return getSpecifiedDayAfterN(specifiedDay, 1);
    }

    /**
     * @ 描述：获取指定日期的后n天
     */
    public static String getSpecifiedDayAfterN(String specifiedDay, int n) {
        String pattern = "";
        if (specifiedDay.contains("/")) {
            pattern = "yyyy/MM/dd";
        } else if (specifiedDay.contains("-")) {
            pattern = "yyyy-MM-dd";
        } else if (specifiedDay.contains(".")) {
            pattern = "yyyy.MM.dd";
        } else {
            pattern = "yyyyMMdd";
        }
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat(pattern, Locale.getDefault()).parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
            return specifiedDay;
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + n);
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(c.getTime());
    }

    /**
     * 比较两个日期,如果日期相等返回0；小于0，参数date1就是在date2之后,大于0，参数date1就是在date2之前
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(String date1, String date2) {
        try {
            SimpleDateFormat sdf;
            if (date1.contains("-")) {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            } else if (date1.contains("/")) {
                sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
            } else if (date1.contains("_")) {
                sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.getDefault());
            } else {
                sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            }
            //将日期转成Date对象作比较
            Date fomatDate1 = sdf.parse(date1);
            Date fomatDate2 = sdf.parse(date2);
            return fomatDate2.compareTo(fomatDate1);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int compareDate(String date1, String date2, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
            //将日期转成Date对象作比较
            Date fomatDate1 = sdf.parse(date1);
            Date fomatDate2 = sdf.parse(date2);
            return fomatDate2.compareTo(fomatDate1);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean compareDate2(String date1, String date2) {
        try {
            SimpleDateFormat sdf;
            if (date1.contains("-")) {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            } else if (date1.contains("/")) {
                sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
            } else if (date1.contains(".")) {
                sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault());
            } else {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            }
            long time1 = sdf.parse(date1).getTime();
            long time2 = sdf.parse(date2).getTime();
            return time1 > time2;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean compareDate4(String date1, String date2) {
        return compareDate2(date1 + " 00:00:00", date2 + " 00:00:00");
    }

    public static boolean compareDate3(String date1, String date2) {
        try {
            SimpleDateFormat sdf;
            if (date1.contains("-")) {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            } else if (date1.contains("/")) {
                sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
            } else if (date1.contains(".")) {
                sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault());
            } else {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            }
            long time1 = sdf.parse(date1).getTime();
            long time2 = sdf.parse(date2).getTime();
            return time1 > time2;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean compareDate4(Date date1, Date date2) {
        try {
            long time1 = date1.getTime();
            long time2 = date2.getTime();
            return time1 > time2;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isAfterToday(String date) {
        try {
            SimpleDateFormat sdf;
            if (date.contains("-")) {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            } else if (date.contains("/")) {
                sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
            } else if (date.contains(".")) {
                sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault());
            } else {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            }
            long time1 = sdf.parse(date).getTime();
            long time2 = new Date().getTime();
            return time1 > time2;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getFormatTimeStamp() {
//		long timeSt = System.currentTimeMillis();
//		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//		String order = format.format(new Date(timeSt));
//		return order;
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + Math.abs(r.nextInt());
        key = key.substring(0, 15);
        return key;
    }

    public static String getDateyyyyMMddHHmmss() {
        Calendar c = Calendar.getInstance();
        String year = String.valueOf(c.get(Calendar.YEAR));
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(c.get(Calendar.MONTH) + 1);
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(c.get(Calendar.MINUTE));
        String second = String.valueOf(c.get(Calendar.SECOND));
        //判断几位数，一位时加0
        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        if (hour.length() == 1) {
            hour = "0" + hour;
        }
        if (minute.length() == 1) {
            minute = "0" + minute;
        }
        if (second.length() == 1) {
            second = "0" + second;
        }
        return year + month + day + hour + minute + second;
    }

}