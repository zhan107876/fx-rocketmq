package com.tan.fx.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作帮助类,主要是把String类型转为为Date
 *
 * @author Administrator
 * <p>
 * 修改记录：
 */
public class DateUtils {

    public final static DateFormat DATEFORMAT_YYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static DateFormat DATEFORMAT_YYMMDDHHMMSS_SSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public final static DateFormat DATEFORMAT_YYMMDDHHMM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public final static DateFormat DATEFORMAT_HHMMSS = new SimpleDateFormat("HH:mm:ss");
    public final static DateFormat DATEFORMAT_YYMMDD = new SimpleDateFormat("yyyy-MM-dd");
    public final static DateFormat DATEFORMAT_YYMMDD_2 = new SimpleDateFormat("yyyy年MM月dd日");
    public final static DateFormat DATEFORMAT_YYMMDD_3 = new SimpleDateFormat("yyyyMMdd");
    public final static DateFormat YEAR_MOTH = new SimpleDateFormat("yyyy-MM");
    public final static DateFormat HOUR_MIN = new SimpleDateFormat("HH:mm");
    public final static DateFormat MIN_SEC = new SimpleDateFormat("mm:ss");

    public static Date dateAddMonths(Date date, int months) {
        return dateAdd(date, Calendar.MONTH, months);
    }

    public static Date dateAddDays(Date date, int days) {
        return dateAdd(date, Calendar.DAY_OF_YEAR, days);
    }

    public static Date dateAddHours(Date date, int hours) {
        return dateAdd(date, Calendar.HOUR, hours);
    }

    public static Date dateAddSecond(Date date, int seconds) {
        return dateAdd(date, Calendar.SECOND, seconds);
    }

    private static Date dateAdd(Date date, int dateType, int add) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateType, add);
        return calendar.getTime();
    }

    public static String dateToYYYMMStr(Date date) {
        return YEAR_MOTH.format(date);
    }


    public static String dateToHHSSStr(Date date) {
        return HOUR_MIN.format(date);
    }

    public static String dateToSSmmStr(Date date) {
        return MIN_SEC.format(date);
    }

    /**
     * 把yyyy-MM-dd HH:mm:ss格式的字符串时间转为Date类型
     *
     * @return
     */
    public static Date yyyyMMddHHmmssToDate(String yyyyMMddHHmmss) {
        Date date = null;
        try {
            date = DATEFORMAT_YYMMDDHHMMSS.parse(yyyyMMddHHmmss);
        } catch (ParseException e) {

        }
        return date;
    }

    /**
     * 把HH:mm:ss格式的字符串时间转为Date类型
     *
     * @param hhmmss
     * @return
     */
    public static Date hhmmssToDate(String hhmmss) {
        Date date = null;
        try {
            date = DATEFORMAT_HHMMSS.parse(hhmmss);
        } catch (ParseException e) {
            System.out.println(e.getMessage() + hhmmss + " 转换为 Date 类型出错...");
            System.out.println(e);

        }
        return date;
    }

    /**
     * 把yyyy-MM-dd格式的字符串时间转为Date类型
     *
     * @param yyyyMMdd
     * @return
     */
    public static Date yyyyMMddToDate(String yyyyMMdd) {
        Date date = null;
        try {
            date = DATEFORMAT_YYMMDD.parse(yyyyMMdd);
        } catch (ParseException e) {
            System.out.println(e.getMessage() + yyyyMMdd + " 转换为 Date 类型出错...");
            System.out.println(e);
        }
        return date;
    }

    public static String formatDateToStringByDefaultFormat(Date date) {
        return DATEFORMAT_YYMMDDHHMMSS.format(date);
    }

    public static String formatDateToStringYYMMDD(Date date) {
        return DATEFORMAT_YYMMDD_3.format(date);
    }

    public static String getCurrentTimeString() {
        return DATEFORMAT_YYMMDDHHMMSS.format(new Date());
    }

    public static String getCurrentTimeMillisecString() {
        return DATEFORMAT_YYMMDDHHMMSS_SSS.format(new Date());
    }


    /**
     * 返回两个日期相差几分钟
     *
     * @param time1
     * @param time2
     * @return
     */
    public static int minDiff(long time1, long time2) {
        int diff = 60000;
        return (int) (time1 - time2) / diff;
    }

    /**
     * 返回两个日期相差几分钟
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int minDiff(Date date1, Date date2) {
        return minDiff(date1.getTime(), date2.getTime());
    }

    /**
     * 返回两个日期相差几个小时
     *
     * @param time1
     * @param time2
     * @return
     */
    public static int hourDiff(long time1, long time2) {
        int diff = 3600000;
        return (int) (time1 - time2) / diff;
    }

    /**
     * 返回两个日期相差几个小时
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int hourDiff(Date date1, Date date2) {
        return hourDiff(date1.getTime(), date2.getTime());
    }

    /**
     * 返回两个日期相差几天.
     *
     * @param time1
     * @param time2
     * @return
     */
    public static int dayDiff(long time1, long time2) {
        int diff = 86400000;
        return (int) (time1 - time2) / diff;
    }

    /**
     * 返回两个日期相差几天.
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int dayDiff(Date date1, Date date2) {
        return dayDiff(date1.getTime(), date2.getTime());
    }

    public static int getSecByHHmm(String hhmmStr) {
        int rslt = 0;

        String[] strArray = hhmmStr.split(":");
        rslt = Integer.parseInt(strArray[0]) * 60;
        rslt += Integer.parseInt(strArray[1]);
        rslt = rslt * 60;

        return rslt;
    }


    public static String getHHmmBySec(Integer sec) {
        String rslt = null;
        if (sec == null) {
            return null;
        }
        int hour = sec / 3600;
        int min = (sec - hour * 3600) / 60;

        if (hour < 10) {
            rslt = "0" + hour;
        } else {
            rslt = "" + hour;
        }
        rslt += ":";

        if (min < 10) {
            rslt += "0" + min;
        } else {
            rslt += min;
        }

        return rslt;
    }

    public static int getSecByMmss(String mmssStr) {
        int rslt = 0;

        String[] strArray = mmssStr.split(":");
        rslt = Integer.parseInt(strArray[0]) * 60;
        rslt += Integer.parseInt(strArray[1]);

        return rslt;
    }


    public static String getMmssBySec(Integer sec) {
        String rslt = null;
        if (sec == null) {
            return null;
        }
        int min = sec / 60;
        int s = (sec - min * 60);

        if (min < 10) {
            rslt = "0" + min;
        } else {
            rslt = "" + min;
        }
        rslt += ":";

        if (s < 10) {
            rslt += "0" + s;
        } else {
            rslt += s;
        }

        return rslt;
    }

    public static String getTimeMillisecStringByDate(Date date) {
        return DATEFORMAT_YYMMDDHHMMSS_SSS.format(date);
    }

    /**
     * 获取两个时间的时间差
     */
    public static Long getTimeCross(Date dFrom, Date dTo) {
        return ((dTo.getTime() - dFrom.getTime()) / 1000);
    }


    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static int getWeekOfDate(Date date) {
        int[] weekDays = {7, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }


}
