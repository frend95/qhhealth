package com.hexq.qh.common.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author hexq
 * @date 2017/11/15 14:02
 */
public class DateUtil {

    /**
     * 获取当前时间:yyyy-MM-dd HH-mm-ss
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String dateTimeHasColonLine() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 验证是否为指定的日期格式
     * @param str 被验证日期
     * @param format 指定的日期格式
     * @return boolean
     */
    public static boolean validDate(String str, String format) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            sdf.setLenient(false);
            sdf.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }


    /**
     * 日期格式转换，由"yyyy-MM-dd"转换为"yyyyMMdd"
     * @return yyyyMMdd
     */
    public static String transFormDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdfs = new SimpleDateFormat("yyyyMMdd");
        return sdfs.format(date);
    }


    /**
     * 获取当前时间(时分秒) HH:mm:ss
     * @return HH:mm:ss
     */
    public static String getHHmmssHasColon() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 获取当前时间HH:mm:ss.SSS
     * @return hh:mm:ss.SSS
     */
    public static String gethhmmssSSSHasColon() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.SSS");
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 获取当前时间HHmmss
     * @return HHmmss
     */
    public static String getHHmmss() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 获取当前日期yyyyMMdd
     * @return yyyyMMdd
     */
    public static String yyyyMMdd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    public static String yyyyMMdd(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    /**
     * 获取当前日期yyyy-MM-dd
     * @return yyyy-MM-dd
     */
    public static String yyyyMMddHasLine() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 获取当前年份
     * @return year
     */
    public static String years() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(new Date(System.currentTimeMillis()));
    }
    public static String years(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(date);
    }


    /**
     * 获取当前月份
     * @return month
     */
    public static String month() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        return sdf.format(new Date(System.currentTimeMillis()));
    }
    public static String month(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        return sdf.format(date);
    }


    /**
     * 获取两个日期相差的天数
     * @param time1 开始日期yyyyMMdd
     * @param time2 结束日期yyyyMMdd
     * @return 天数
     */
    public static long divideDate(String time1, String time2) {
        long daty = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date1 = df.parse(time1);
            Date date2 = df.parse(time2);
            daty = date1.getTime() - date2.getTime();
            daty = daty / 1000 / 60 / 60 / 24;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return daty;
    }


    /**
     * 获取指定年指定月份的最后一天
     * @param year 年份
     * @param month 月份
     * @return yyyyMMdd
     */
    public static String lastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(cal.getTime());
    }

    /**
     * 获取指定日期的最后一天
     * @param date 日期
     * @return yyyyMMdd
     */
    public static String lastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(cal.getTime());
    }

    /**
     * 获取上个月的月份
     * @return month
     */
    public static String lastMonth() {
        Integer month = Integer.parseInt(month());
        return month == 1 ? "12" : (month - 1) + "";
    }

    /**
     * 获取当月最后一天日期
     * @return yyyyMMdd
     */
    public static String lastDayOfCurrMonth() {
        int year = Integer.parseInt(years());
        int month = Integer.parseInt(month());
        return lastDayOfMonth(year, month);
    }

    /**
     * 获取指定年份的最后一天
     * @param year -年份
     * @return yyyyMMdd
     */
    public static String lastDayOfYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, 11);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(cal.getTime());
    }

    /**
     * 获取指定年指定季度的最后一天
     * @param year 年份
     * @param quarter 季度标识 (1-第一季度, 2-第二季度, 3-第三季度, 4-第四季度)
     * @return yyyyMMdd
     */
    public static String lastDayOfQuarter(int year, int quarter) {
        int month = 0;
        //获取指定季度的最后一个月
        switch (quarter) {
            case 1:
                month = 3;
                break;
            case 2:
                month = 6;
                break;
            case 3:
                month = 9;
                break;
            case 4:
                month = 12;
                break;
            default:
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(cal.getTime());
    }


    /**
     * 获取指定年份上半年或者下半年的最后一天
     * @param year 年份
     * @param halfYear 半年度标识(1-上半年, 2-下半年)
     * @return yyyyMMdd
     */
    public static String lastDayOfHalfYear(int year, int halfYear) {
        int month = 0;
        //获取指定半年度的最后一个月
        switch (halfYear) {
            case 1:
                month = 6;
                break;
            case 2:
                month = 12;
                break;
            default:
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(cal.getTime());
    }

    /**
     * 判断当月是否为季度末
     * @param month 月
     * @return boolean
     */
    public static boolean isQuarter(String month) {
        return "3".equals(month) || "6".equals(month) || "9".equals(month) || "12".equals(month);
    }

    /**
     * 判断当月是第几个季度
     * @param month 月份
     * @return quarter
     */
    public static String monthForQuarter(int month) {
        if (month <= 3) {
            return "1";
        } else if (month <= 6) {
            return "2";
        } else if (month <= 9) {
            return "3";
        } else {
            return "4";
        }
    }

    /**
     * 获取明天日期
     * @return yyyyMMdd
     */
    public static String tomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, + 1);
        Date time = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(time);
    }

    /**
     * 获取传入日期前一天
     * @param date Date
     * @return yyyyMMdd
     */
    public static String lastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }
    
    /**
     * 获取传入日期的前一天
     * @param dateStr date string
     * @return yyyyMMdd
     */
    public static String lastDay(String dateStr) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return sdf.format(date);
    }

    /**
     * 获取上月最后一天(格式为yyyyMMdd)
     * @return yyyyMMdd
     */
    public static String lastDayOfLastMonth() {
        int year = Integer.parseInt(years());
        int month = Integer.parseInt(month());
        if (month == 0) {
            year--;
            month = 12;
        } else {
            month--;
        }
        return lastDayOfMonth(year, month);
    }

    public static String lastDayOfLastMonth(Date date) {
        int year = Integer.parseInt(years(date));
        int month = Integer.parseInt(month(date));
        if (month == 0) {
            year--;
            month = 12;
        } else {
            month--;
        }
        return lastDayOfMonth(year, month);
    }

    /**
     * 获取上个月的月份和年份，数组的形式返回
     * 数组第一个元素是年份，第二个是月份
     * @return date String arr
     */
    public static String[] lastMonthWithYear(Date date) {
        int year = Integer.parseInt(years(date));
        int month = Integer.parseInt(month(date));
        if (month == 0) {
            year--;
            month = 12;
        } else {
            month--;
        }
        return new String[] {year + "", month + ""};
    }

    public static String[] lastMonthWithYear() {
        return lastMonthWithYear(new Date());
    }

    public static Date parseYyyyMmDd(String date) throws ParseException{
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }

    public static Date parse(String date, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(date);
    }

    /**
     * 加一天
     * @param dateStr date string
     * @param format format
     * @param day 天数
     * @return date string
     * @throws ParseException
     */
    public static String addDay(String dateStr, String format, int day) throws ParseException {
        Date prePreDate = DateUtils.addDays(DateUtils.parseDate(dateStr, format), day);
        return DateFormatUtils.format(prePreDate, format);
    }

    /**
     * 获取当前日期的过去几天
     * @param day 天数
     * @return yyyyMMdd
     */
    public static String pastDate(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - day);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(today);
    }
    
    /**
     * 获取当前日期的未来几天
     * @param day 天数
     * @return yyyyMMdd
     */
    public static String futureDate(int day) {
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + day);
        Date today = calendar.getTime();  
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(today);
    }
    
    /**
     * 查询当前时间所属季度的开始日期
     * @param year 年份
     * @param quarter 季度
     * @return yyyyMMdd
     */
    public static String quarterStartDate(int year, int quarter) {
        String startDate = "";
        if (quarter == 1) {
            startDate = year + "0101";
        } else if (quarter == 2) {
            startDate = year + "0401";
        } else if (quarter == 3) {
            startDate = year + "0701";
        } else if (quarter == 4) {
            startDate = year + "1001";
        }
        return startDate;
    }

    /**
     * 判断某天是否为月末
     * @param dateStr date string
     * @return yyyyMMdd
     */
    public static boolean isLastDayOfMonth(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        return calendar.get(Calendar.DAY_OF_MONTH) == Calendar.SUNDAY;
    }

    /**
     * 判断某天是否为年末
     * @param dateStr date string
     * @return yyyyMMdd
     */
    public static boolean isLastDayOfYear(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        return calendar.get(Calendar.DAY_OF_YEAR) == Calendar.SUNDAY;
    }

}
