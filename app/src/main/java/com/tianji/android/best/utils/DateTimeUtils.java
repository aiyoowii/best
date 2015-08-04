package com.tianji.android.best.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间日期操作工具类
 */
public class DateTimeUtils {

    /**
     * @param millisecond
     * @return yyyy年MM月dd日
     */
    public static String formatDate(long millisecond) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date(millisecond));
    }

    /**
     * @param time
     * @return yyyy年MM月dd日 HH时mm分
     */
    public static String formatDateTime(long time) {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return mFormat.format(new Date(time));
    }

    public static String formatDateDay(long time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)) + " "
                + getMonth(calendar.get(Calendar.MONTH)) + " "
                + calendar.get(Calendar.DAY_OF_MONTH) + " "
                + calendar.get(Calendar.YEAR);
    }


    public static String formatDateClock(long time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.HOUR) + ":"
                + new SimpleDateFormat("mm").format(new Date(time))
                + (calendar.get(Calendar.AM_PM) == 0?"AM":"PM");
    }

    public static String getMonth(int month){
        if (month == 0) {
            return "January";
        } else if (month == 1) {
            return  "February";
        } else if (month == 2) {
            return "March";
        } else if (month == 3) {
            return "April";
        } else if (month == 4) {
            return "May";
        } else if (month == 5) {
            return "June";
        } else if (month == 6) {
            return "July";
        } else if (month == 7) {
            return "August";
        } else if (month == 8) {
            return "September";
        } else if (month == 9) {
            return "October";
        } else if (month == 10) {
            return "November";
        } else if (month == 11) {
            return "December";
        } else if (month == 12) {
            return "Undecimber";
        } else
            return "";
    }

    public static String getDayOfWeek(int day){
        if (day == 2) {
            return  "Monday";
        } else if (day == 3) {
            return "Tuesday";
        } else if (day == 4) {
            return "Wednesday";
        } else if (day == 5) {
            return "Thursday";
        } else if (day == 6) {
            return "Friday";
        } else if (day == 7) {
            return "Saturday";
        } else if (day == 1) {
            return "Sunday";
        } else
            return "";
    }

    /**
     * 根据时间差值获取时间区间，正值表示以后，负值表示以前，以以前为例（单位：秒）：
     * 0到-60：刚刚
     * -60到-3600：?分钟前
     * -3600到-86400：？小时前
     * ...
     * 超过62208000时返回"两年以上"。
     *
     * @param time 时间差值
     * @return 时间区间描述
     */
    private static StringBuffer getTimeBuffer(long time, boolean isAgo) {
        StringBuffer timeBuffer = new StringBuffer();
        if (time < 60)
            timeBuffer.append(isAgo ? "刚刚" : "稍后");
        else if (time >= 60 && time < 3600)
            timeBuffer.append(time / 60).append(isAgo ? "分钟前" : "分钟后");
        else if (time >= 3600 && time < 86400)
            timeBuffer.append(time / 3600).append(isAgo ? "小时前" : "小时后");
        else if (time >= 86400 && time < 2592000)
            timeBuffer.append(time / 86400).append(isAgo ? "天前" : "天后");
        else if (time >= 2592000 && time < 31104000)
            timeBuffer.append(time / 2592000).append(isAgo ? "个月前" : "月后");
        else if (time >= 31104000 && time < 62208000)
            timeBuffer.append(isAgo ? "1年前" : "1年后");
        else
            timeBuffer.append("两年以上");
        return timeBuffer;
    }

    public static String getRegionOfTime(long time) {
        boolean isAgo = time <= 0;
        time = Math.abs(time / 1000);
        StringBuffer timeBuffer = getTimeBuffer(time, isAgo);
        if (time < 7200) {
            timeBuffer.insert(0, "<font color='#337f3c'>").append("</font>");
        } else if (time < 172800) {
            timeBuffer.insert(0, "<font color='#7f7f7f'>").append("</font>");
        } else if (time < 5184000) {
            timeBuffer.insert(0, "<font color='#a6a6a6'>").append("</font>");
        } else
            timeBuffer.insert(0, "<font color='#bbbbbb'>").append("</font>");
        return timeBuffer.toString();
    }


    public static String getRefreshText(long time) {
        boolean isAgo = time <= 0;
        time = Math.abs(time / 1000);
        StringBuffer timeBuffer = getTimeBuffer(time, isAgo);
        if (time < 1800) {
            timeBuffer.insert(0, "<font color='#949493'>").append("</font>");
        } else if (time < 10800) {
            timeBuffer.insert(0, "<font color='#858482'>").append("</font>");
        } else {
            timeBuffer.insert(0, "<font color='#4d4b47'>").append("</font>");
        }
        return timeBuffer.toString();
    }

    public static String getUpdateDate(long updateTime) {
        StringBuffer timeBuffer = new StringBuffer();
        timeBuffer.append(new SimpleDateFormat("HH:mm MM/dd/yyyy").format(new Date(updateTime)));
        long time = (System.currentTimeMillis() - updateTime) / 60000; ////将毫秒转换成分钟，60 * 1000
        if (time < 720) {
            timeBuffer.insert(0, "<font color='#337f3c'>").append("</font>");
        } else if (time < 1440) {
            timeBuffer.insert(0, "<font color='#72a178'>").append("</font>");
        } else {
            timeBuffer.insert(0, "<font color='#7f7f7f'>").append("</font>");
        }
        return timeBuffer.toString();
    }
}
