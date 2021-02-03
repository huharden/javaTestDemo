package com.hj.study.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class DateUtil {

    // 系统默认日期格式
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_MONTH = "yyyy-MM";
    public static final String DATE_FORMAT_YEAR = "yyyy";
    // 系统默认日期时间格式
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT = "HH:mm:ss";

    public static final String DATETIME_FORMAT_2 = "yyyyMMddHHmmss";

    public static final String DATETIME_FORMAT_4 = "yyyy/MM/dd HH:mm:ss";
    // 10位日期时间格式
    public static final String DATE_TIME_FORMAT_10 = "yyyyMMddHH";
    // 8位日期格式
    public static final String DATE_FORMAT_8 = "yyyyMMdd";
    // 6位日期格式
    public static final String DATE_FORMAT_6 = "yyyyMM";
    // 4位日期格式
    public static final String DATE_FORMAT_4 = "yyyy";
    // 4位日期格式
    public static final String DATE_TEAR = "yyyy";
    // 14位日期时间格式
    public static final String DATE_TIME_FORMAT_14 = "yyyyMMddHHmmss";

    public static final String DATE_FORMAT_MINUTE = "yyyy-MM-dd HH:mm";
    public static final String DATE_TIME_FORMAT_22 = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_TIME_FORMAT_22_ = "yyyyMMddHHmmssSSS";

    public static final String DATE_FORMAT_4_ = "yyyy/MM";

    public static final String DATE_FORMAT_6_ = "yyyy/MM/dd";

    public static final String DATE_FORMAT_7 = "yyyyMM";

    public static final String YEAR = "year";

    public static final String MONTH = "month";

    public static final String DAY = "day";

    public static final String HOUR = "hour";

    public static final String MINUTE = "minute";

    public static final String SECOND = "second";

    public static final String WEEK = "week";

    public static final String DATETIME_FORMAT_19 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        String result = format.format(today);
        return result;
    }

    /**
     * <p>
     * Description: 时间以及时间格式相关的处理功能
     * </p>
     * private static Logger logger = Logger.getLogger(DateUtil.class);
     * <p>
     * /** 得到应用服务器当前日期，以默认格式显示。
     *
     * @return
     */
    public static String getFormatedDate() {
        Date date = getCurrentDate();
        SimpleDateFormat dateFormator = new SimpleDateFormat(DateUtil.DATE_FORMAT);
        return dateFormator.format(date);

    }

    /**
     * 得到应用服务器当前日期时间，以默认格式显示。
     *
     * @return
     */
    public static String getFormatedDateTime() {

        Date date = getCurrentDate();
        SimpleDateFormat dateFormator = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT);
        return dateFormator.format(date);

    }

    /**
     * 得到应用服务器当前日期时间，以默认格式显示。
     *
     * @return
     */
    public static String getFormatedDateTime(String format) {

        Date date = getCurrentDate();
        SimpleDateFormat dateFormator = new SimpleDateFormat(format);
        return dateFormator.format(date);

    }

    /**
     * 得到应用服务器的当前时间
     *
     * @return
     */
    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 得到应用服务器的当前时间，毫秒。
     *
     * @return
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 得到应用服务器当前日期 按照指定的格式返回。
     *
     * @param pattern 格式类型，通过系统常量中定义，如：CapConstants.DATE_FORMAT_8
     * @return
     */
    public static String formatDate(String pattern) {

        Date date = new Date();
        SimpleDateFormat dateFormator = new SimpleDateFormat(pattern);
        String str = dateFormator.format(date);

        return str;
    }

    /**
     * 转换输入日期 按照指定的格式返回。
     *
     * @param date
     * @param pattern 格式类型，通过系统常量中定义，如：CapConstants.DATE_FORMAT_8
     * @return
     */
    public static String formatDate(Date date, String pattern) {

        if (date == null) {
            return "";
        }

        SimpleDateFormat dateFormator = new SimpleDateFormat(pattern);
        String str = dateFormator.format(date);

        return str;
    }

    /**
     * 转换输入日期 按照指定的格式返回。
     *
     * @param date
     * @param pattern 格式类型，通过系统常量中定义，如：CapConstants.DATE_FORMAT_8
     * @param loc     locale
     * @return
     */
    public static String formatDate(Date date, String pattern, Locale loc) {
        if (pattern == null || date == null) {
            return "";
        }
        String newDate = "";
        if (loc == null) {
            loc = Locale.getDefault();
        }
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern, loc);
            newDate = sdf.format(date);
        }
        return newDate;
    }

    /**
     * 将字符时间从一个格式转换成另一个格式。时间的格式，最好通过系统常量定义。 如： String dateStr = "2006-10-9
     * 12:09:08"; DateFormatUtils.convertDateFormat(dateStr,
     * CapConstants.DATE_TIME_FORMAT, CapConstants.DATE_FORMAT_8);
     *
     * @param dateStr
     * @param patternFrom 格式类型，通过系统常量中定义，如：CapConstants.DATE_FORMAT_8
     * @param patternTo   格式类型，通过系统常量中定义，如：CapConstants.DATE_FORMAT_8
     * @return
     */
    public static String convertDateFormat(String dateStr, String patternFrom, String patternTo) {

        if (dateStr == null || patternFrom == null || patternTo == null) {
            return "";
        }

        String newDate = "";

        try {

            Date dateFrom = parseStrToDate(dateStr, patternFrom);
            newDate = formatDate(dateFrom, patternTo);

        }
        catch (Exception e) {
            log.error(e.getMessage());
        }

        return newDate;
    }

    /**
     * 将时间串按照默认格式CapConstants.DATE_FORMAT，格式化成Date。
     *
     * @param dateStr
     * @return
     */
    public static Date parseStrToDate(String dateStr) {

        if (null == dateStr || "".equals(dateStr)) {
            return null;
        }

        SimpleDateFormat dateFormator = new SimpleDateFormat(DateUtil.DATE_FORMAT);

        Date tDate = dateFormator.parse(dateStr, new ParsePosition(0));

        return tDate;
    }

    public static String parseDateStrToDateTimeStr(String dateStr) {

        if (null == dateStr || "".equals(dateStr)) {
            return null;
        }

        SimpleDateFormat dateFormator = new SimpleDateFormat(DateUtil.DATE_FORMAT);

        Date tDate = dateFormator.parse(dateStr, new ParsePosition(0));
        return formatDate(tDate, DateUtil.DATE_TIME_FORMAT);

    }

    /**
     * 将时间串按照默认格式CapConstants.DATE_FORMAT，格式化成Date。
     *
     * @param dateStr
     * @return
     */
    public static Calendar parseStrToCalendar(String dateStr) {

        if (null == dateStr || "".equals(dateStr)) {
            return null;
        }

        SimpleDateFormat dateFormator = new SimpleDateFormat(DateUtil.DATE_FORMAT);

        Date tDate = dateFormator.parse(dateStr, new ParsePosition(0));

        Locale loc = Locale.getDefault();
        Calendar cal = new GregorianCalendar(loc);
        cal.setTime(tDate);

        return cal;
    }

    public static Calendar parseStrToCalendar(String dateStr, String pattern) {

        if (null == dateStr || "".equals(dateStr)) {
            return null;
        }

        SimpleDateFormat dateFormator = new SimpleDateFormat(pattern);

        Date tDate = dateFormator.parse(dateStr, new ParsePosition(0));

        Locale loc = Locale.getDefault();
        Calendar cal = new GregorianCalendar(loc);
        cal.setTime(tDate);

        return cal;
    }

    /**
     * 将时间串按照默认格式CapConstants.DATE_TIME_FORMAT，格式化成Date。
     *
     * @param dateStr
     * @return
     */
    public static Date parseStrToDateTime(String dateStr) {

        if (null == dateStr || "".equals(dateStr)) {
            return null;
        }

        SimpleDateFormat dateFormator = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT);

        Date tDate = dateFormator.parse(dateStr, new ParsePosition(0));

        return tDate;
    }

    /**
     * 将时间串按照指定格式，格式化成Date。
     * @param dateStr
     * @param pattern 格式类型，通过系统常量中定义，如：CapConstants.DATE_FORMAT_8
     * @return
     */

    public static Date parseStrToDate(String dateStr, String pattern) {
        if (null == dateStr || "".equals(dateStr)) {
            return null;
        }

        SimpleDateFormat dateFormator = new SimpleDateFormat(pattern);

        Date tDate = dateFormator.parse(dateStr, new ParsePosition(0));

        return tDate;
    }

    /**
     * 按时间格式相加：
     * 输入要相加的时间基点（字符串型或时间类型），相加的时长（整型），格式（"year"年、"month"月、"day"日、”hour“时、”minute“分、”second“秒、"week"周）
     * 输出相加后的时间（字符串型或时间类型）
     *
     * @param dateStr
     * @param pattern
     * @param step
     * @param type    "year"年、"month"月、"day"日、”hour“时、”minute“分、”second“秒、"week"周
     *                通过常量DateFormatUtils.YEAR等来设置.
     * @return
     */
    public static String addDate(String dateStr, String pattern, int step, String type) {
        if (dateStr == null) {
            return dateStr;
        }
        else {
            Date date1 = DateUtil.parseStrToDate(dateStr, pattern);

            Locale loc = Locale.getDefault();
            Calendar cal = new GregorianCalendar(loc);
            cal.setTime(date1);

            if (DateUtil.WEEK.equals(type)) {

                cal.add(Calendar.WEEK_OF_MONTH, step);

            }
            else if (DateUtil.YEAR.equals(type)) {

                cal.add(Calendar.YEAR, step);

            }
            else if (DateUtil.MONTH.equals(type)) {

                cal.add(Calendar.MONTH, step);

            }
            else if (DateUtil.DAY.equals(type)) {

                cal.add(Calendar.DAY_OF_MONTH, step);

            }
            else if (DateUtil.HOUR.equals(type)) {

                cal.add(Calendar.HOUR, step);

            }
            else if (DateUtil.MINUTE.equals(type)) {

                cal.add(Calendar.MINUTE, step);

            }
            else if (DateUtil.SECOND.equals(type)) {

                cal.add(Calendar.SECOND, step);

            }

            return DateUtil.formatDate(cal.getTime(), pattern);
        }
    }

    /**
     * 按时间格式相减：
     * 输入要相加的时间基点（字符串型或时间类型），相加的时长（整型），格式（"year"年、"month"月、"day"日、”hour“时、”minute“分、”second“秒、"week"周）
     * 输出相加后的时间（字符串型或时间类型）
     *
     * @param dateStr
     * @param pattern
     * @param step
     * @param type    "year"年、"month"月、"day"日、”hour“时、”minute“分、”second“秒、"week"周
     * @return
     */
    public static String minusDate(String dateStr, String pattern, int step, String type) {

        return addDate(dateStr, pattern, (0 - step), type);

    }

    /**
     * 日期增加天数
     *
     * @param date
     * @param days
     * @return cal.getTime()
     */
    public static Date addDay(Date date, int days) {
        if (date == null) {
            return date;
        }
        else {
            Locale loc = Locale.getDefault();
            Calendar cal = new GregorianCalendar(loc);
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, days);
            return cal.getTime();
        }
    }

    public static int getDays(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        else {
            return (int) ((date2.getTime() - date1.getTime()) / 0x5265c00L);
        }
    }

    /**
     * 日期比较大小
     *
     * @param dateStr1
     * @param dateStr2
     * @param pattern
     * @return
     */
    public static int compareDate(String dateStr1, String dateStr2, String pattern) {

        Date date1 = DateUtil.parseStrToDate(dateStr1, pattern);
        Date date2 = DateUtil.parseStrToDate(dateStr2, pattern);

        return date1.compareTo(date2);

    }

    /**
     * @param dateStr
     * @param pattern
     * @return
     */
    public static String getFirstDayInMonth(String dateStr, String pattern) {
        Calendar cal = DateUtil.parseStrToCalendar(dateStr);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        cal.add(Calendar.DAY_OF_MONTH, (1 - day));

        return DateUtil.formatDate(cal.getTime(), pattern);
    }

    /**
     * @param dateStr
     * @param pattern
     * @return
     */
    public static String getLastDayInMonth(String dateStr, String pattern) {
        Calendar cal = DateUtil.parseStrToCalendar(dateStr);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int maxDayInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int step = maxDayInMonth - day;

        cal.add(Calendar.DAY_OF_MONTH, step);

        return DateUtil.formatDate(cal.getTime(), pattern);
    }

    /**
     * @param dateStr
     * @param pattern
     * @return
     */
    public static String getFirstDayInWeek(String dateStr, String pattern) {
        Calendar cal = DateUtil.parseStrToCalendar(dateStr);
        int day = cal.get(Calendar.DAY_OF_WEEK);

        cal.add(Calendar.DAY_OF_MONTH, (1 - day));

        return DateUtil.formatDate(cal.getTime(), pattern);
    }

    /**
     * @param dateStr
     * @param pattern
     * @return
     */
    public static String getLastDayInWeek(String dateStr, String pattern) {
        Calendar cal = DateUtil.parseStrToCalendar(dateStr);
        int day = cal.get(Calendar.DAY_OF_WEEK);

        cal.add(Calendar.DAY_OF_MONTH, (6 - day));

        return DateUtil.formatDate(cal.getTime(), pattern);
    }

    public static long calendarDayPlus(String dateStr1, String dateStr2) {

        if (dateStr1 == null || dateStr2 == null || "".equals(dateStr1) || "".equals(dateStr2)) {
            return 0;
        }
        Date date1 = DateUtil.parseStrToDate(dateStr1);
        Date date2 = DateUtil.parseStrToDate(dateStr2);
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);
        long t1 = c1.getTimeInMillis();
        long t2 = c2.getTimeInMillis();
        long day = (t2 - t1) / (1000 * 60 * 60 * 24);
        return day;
    }

    /**
     * @param dateStr1
     * @param dateStr2
     * @return
     */
    public static int calendarPlus(String dateStr1, String dateStr2) {

        if (dateStr1 == null || dateStr2 == null || "".equals(dateStr1) || "".equals(dateStr2)) {
            return 0;
        }

        Calendar cal1 = DateUtil.parseStrToCalendar(dateStr1);

        Calendar cal2 = DateUtil.parseStrToCalendar(dateStr2);

        int dataStr1Year = cal1.get(Calendar.YEAR);
        int dataStr2Year = cal2.get(Calendar.YEAR);

        int dataStr1Month = cal1.get(Calendar.MONTH);
        int dataStr2Month = cal2.get(Calendar.MONTH);

        return ((dataStr2Year * 12 + dataStr2Month + 1) - (dataStr1Year * 12 + dataStr1Month));

    }

    /**
     * 得到应用服务器当前日期，以8位日期显示。
     *
     * @return
     */
    public static String getDate() {

        Date date = getCurrentDate();
        SimpleDateFormat dateFormator = new SimpleDateFormat(DateUtil.DATE_FORMAT_8);
        return dateFormator.format(date);

    }

    /**
     * 得到应用服务器当前日期，以四位日期显示。
     *
     * @return
     */
    public static String getYear() {

        Date date = getCurrentDate();
        SimpleDateFormat dateFormator = new SimpleDateFormat(DateUtil.DATE_TEAR);
        return dateFormator.format(date);

    }

    /**
     * 得到应用服务器当前日期六位字符。
     *
     * @return
     */
    public static String getNowDate() {
        Date date = getCurrentDate();
        SimpleDateFormat dateFormator = new SimpleDateFormat(DateUtil.DATE_FORMAT_7);
        return dateFormator.format(date);

    }

    /**
     * 得到应用服务器当前日期，以六位日期显示。
     *
     * @return
     */
    public static String getMonth() {

        Date date = getCurrentDate();
        SimpleDateFormat dateFormator = new SimpleDateFormat("yyyyMM");
        return dateFormator.format(date);

    }

    /**
     * 得到应用服务器当前日期，以8位日期显示。
     *
     * @return
     */
    public static String getDateTime() {

        Date date = getCurrentDate();
        SimpleDateFormat dateFormator = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT_14);
        return dateFormator.format(date);

    }

    /**
     * 得到应用服务器当前日期，以8位日期显示。
     *
     * @return
     */
    public static String getDateTime2() {

        Date date = getCurrentDate();
        SimpleDateFormat dateFormator = new SimpleDateFormat(DateUtil.DATE_FORMAT_6_);
        return dateFormator.format(date);

    }

    /**
     * 得到应用服务器当前日期，以DATE_TIME_FORMAT_14 = "yyyyMMddHHmmss"; 位日期显示。
     *
     * @return
     */
    public static String getVSOPDateTime14() {

        Date date = getCurrentDate();
        SimpleDateFormat dateFormator = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT_14);
        return dateFormator.format(date);

    }

    /**
     * 得到应用服务器当前日期，以DATE_FORMAT_8 = "yyyyMMdd"; 位日期显示。
     *
     * @return
     */
    public static String getVSOPDate8() {

        Date date = getCurrentDate();
        SimpleDateFormat dateFormator = new SimpleDateFormat(DateUtil.DATE_FORMAT_8);
        return dateFormator.format(date);

    }

    public static java.sql.Timestamp getTimestamp(String dateStr) {
        DateFormat df = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT);
        java.sql.Timestamp ts = null;
        try {
            Date date = df.parse(dateStr);
            ts = new java.sql.Timestamp(date.getTime());
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
        return ts;
    }

    /**
     * 将Date转换成统一的日期时间格式文本。
     *
     * @return
     */
    public static String getFormatedDateTime(java.sql.Date date) {
        if (null == date) {
            return "";
        }

        SimpleDateFormat dateFormator = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT_14);
        return dateFormator.format(new java.sql.Date(date.getTime()));
    }

    /**
     * 通过统一的格式将文本转换成Date。输入为日期和时间。
     *
     * @return
     */
    public static java.sql.Date parseDateTime(String sdate) {
        if (null == sdate || "".equals(sdate)) {
            return null;
        }

        // 只有日期类型
        if (sdate.length() <= 10) {
            return parseDate(sdate);
        }

        SimpleDateFormat dateFormator = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT_14);

        Date tDate = dateFormator.parse(sdate, new ParsePosition(0));
        if (tDate == null) {
            return null;
        }

        return new java.sql.Date(tDate.getTime());

    }

    /**
     * 通过统一的格式将文本转换成Date。输入为日期。
     *
     * @return
     */
    public static java.sql.Date parseDate(String sdate) {
        if (null == sdate || "".equals(sdate)) {
            return null;
        }

        SimpleDateFormat dateFormator = new SimpleDateFormat(DateUtil.DATE_FORMAT);

        Date tDate = dateFormator.parse(sdate, new ParsePosition(0));
        if (tDate == null) {
            return null;
        }

        return new java.sql.Date(tDate.getTime());
    }

    /**
     * 获取14位系统时间
     *
     * @return
     */
    public static String getDateString14() {
        SimpleDateFormat dateFormator = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormator.format(new java.sql.Date(System.currentTimeMillis()));
    }

    /**
     * 日期简单正则校验
     *
     * @return
     */
    public static boolean checkDateValid(String dateStr) {
        boolean flag = false;
        String reg = "(([0-9]{1,4})(0?[1-9]|1[0-2])(0?[1-9]|1[0-9]|2[0-9]|3[0-1]))";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(dateStr);
        while (matcher.find()) {
            flag = true;
            //// System.out.println(matcher.group());
        }
        return flag;
    }

    public static boolean isValidDate(String dataStr, String pattern) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            Date date = dateFormat.parse(dataStr);
            return dataStr.equals(DateUtil.formatDate(date, pattern));
        }
        catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    public static Date addMonth(Date date, int months) {
        if (date == null) {
            date = new Date();
        }

        Locale loc = Locale.getDefault();
        Calendar cal = new GregorianCalendar(loc);
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * 返回昨天的日期
     *
     * @param pattern 日期的格式
     * @return
     */
    public static String getYesterday(String pattern) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = c.getTime();
        return sdf.format(date);

    }



    /**
     * 返回指定日期的前一天的日期
     *
     * @param today
     * @param pattern
     * @return
     */
    public static String getYesterday(String today, String pattern) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date date2;
        try {
            date2 = s.parse(today);
            c.setTime(date2);
        }
        catch (ParseException e) {
            log.error(e.getMessage());
        }
        c.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = c.getTime();
        return sdf.format(date);
    }

    /**
     *
     * 获取前N个小时
     * @param today n pattern
     * @return startHour
     * @author Jimmy
     */
    public static String getHour(String today, int n, String pattern) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date date;
        try {
            date = formatDate.parse(today);
            calendar.setTime(date);
        }
        catch (ParseException e) {
            log.error(e.getMessage());
        }
        calendar.add(Calendar.HOUR_OF_DAY, -n);
        Date startDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String startHour = sdf.format(startDate);
        return startHour;
    }

    /**
     * 获取当前月份的前N个月的月份
     *
     * @param monthStr 当前月份（比如201305）
     * @param n        前n个月
     * @return YYYYMM 比如201301
     * @throws ParseException
     */
    public static String getMonth(String monthStr, int n) {
        SimpleDateFormat s;
        if (monthStr.length() <= 10) {
            s = new SimpleDateFormat(DATE_FORMAT);
        }
        else {
            s = new SimpleDateFormat(DATE_TIME_FORMAT);
        }
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat formatDate = new SimpleDateFormat(DATE_FORMAT_6);
        Date date;
        String startMonth = null;
        try {
            date = s.parse(monthStr);
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, -n);
            Date startDate = calendar.getTime();
            startMonth = formatDate.format(startDate);
        }
        catch (ParseException e) {
            log.error(e.getMessage());
        }
        return startMonth;
    }

    /**
     * @param monthStr
     * @return month + 1
     * @throws ParseException
     */
    public static int getMonth(String monthStr) throws ParseException {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat formatDate = new SimpleDateFormat(DATE_FORMAT_6);
        Date date = formatDate.parse(monthStr);
        calendar.setTime(date);

        int month = calendar.get(Calendar.MONTH);
        return month + 1;
        // calendar.add(Calendar.MONTH,);
        // Date startDate = calendar.getTime();
        // String startMonth = formatDate.format(startDate);
        // return startMonth;
    }

    /**
     * 获取当前月份的前N个月的月份
     *
     * @param monthStr 当前月份（比如201305）
     * @param n        前n个月
     * @return YYYYMM 比如201301
     * @throws ParseException
     */
    public static String getMonth(String monthStr, int n, String format) throws ParseException {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat formatDate = new SimpleDateFormat(format);
        Date date = formatDate.parse(monthStr);
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -n);
        Date startDate = calendar.getTime();
        String startMonth = formatDate.format(startDate);
        return startMonth;
    }

    /**
     * 获取当前月份的前N个月的日期
     *
     * @param dayStr 当前月份（比如20130531）
     * @param n      前n日
     * @return YYYYMMdd 比如20130101
     * @throws ParseException
     */
    public static String getDay(String dayStr, int n, String format) throws ParseException {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat formatDate = new SimpleDateFormat(format);
        Date date = formatDate.parse(dayStr);
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -n);
        Date startDate = calendar.getTime();
        String startMonth = formatDate.format(startDate);
        return startMonth;
    }

    /**
     * levels为正数时， 获取前N个时间单位 levels为负数时， 获取前第N个时间单位
     *
     * @param tType
     * @param levels
     * @return Author : joshui Date ：2013-6-25
     */
    public static List<String> getTimeInterval(String tType, int levels) {
        String format = "";
        int calendarType = -1;

        if ("t_day".equalsIgnoreCase(tType)) {
            format = "yyyyMMdd";
            calendarType = Calendar.DAY_OF_MONTH;
        }

        if ("t_week".equalsIgnoreCase(tType)) {
            format = "yyyyww";
            calendarType = Calendar.WEEK_OF_YEAR;
        }

        if ("t_month".equalsIgnoreCase(tType)) {
            format = "yyyyMM";
            calendarType = Calendar.MONTH;
        }

        if ("t_month_h".equalsIgnoreCase(tType)) {
            format = "yyyyMM";
            calendarType = Calendar.MONTH;
        }

        if ("t_year".equalsIgnoreCase(tType)) {
            format = "yyyy";
            calendarType = Calendar.YEAR;
        }

        List<String> rs = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat(format);
        if (levels < 0) {
            calendar.add(calendarType, levels);
            rs.add(formatDate.format(calendar.getTime()));
        }
        else {
            for (int i = 0; i < levels; i++) {
                if ("t_day".equalsIgnoreCase(tType)) {
                    calendar.add(calendarType, -1);
                }
                else {
                    calendar.add(calendarType, i == 0 ? 0 : -1);
                }
                rs.add(formatDate.format(calendar.getTime()));
            }
        }

        List<String> reverseRs = new ArrayList<String>();
        for (int i = rs.size() - 1; i >= 0; i--) {
            reverseRs.add(rs.get(i));
        }

        return reverseRs;
    }

    /**
     * 获取当前周是一个月中的第几周，一周中跨两个月的，本周当作下个月的第一周
     *
     * @return
     */
    public static Map<Integer, String> getWeekInfo() {
        Map<Integer, String> map = new HashMap<Integer, String>();

        // 取本周周五的日期
        Calendar friday = Calendar.getInstance();
        friday.setFirstDayOfWeek(Calendar.MONDAY);
        friday.setMinimalDaysInFirstWeek(7);
        friday.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);

        // 获取本周周五在一个月中的第几周
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        map.put(Calendar.MONTH, format.format(friday.getTime()));
        map.put(Calendar.DAY_OF_WEEK_IN_MONTH, String.valueOf(friday.get(Calendar.DAY_OF_WEEK_IN_MONTH)));

        return map;
    }

    /**
     * 获取当天的小时并转为int类型
     *
     * @return
     */
    public static int getCurrentHour() {
        Calendar calendar = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("HH");
        String hour = format.format(calendar.getTime());
        return Integer.parseInt(hour);
    }

    public static String current(String pattern) {
        return formatDate(current(), pattern);
    }

    public static Date current() {
        return new java.sql.Date(System.currentTimeMillis());
    }

    /**
     * 获取两个日期之间的随机日期(开区间)
     *
     * @param begin
     * @param end
     * @return
     */
    public static Date getRandomDate(Date begin, Date end) {

        // 随机日期
        Date randomDate = new Date();

        long beginTime = begin.getTime();
        long endTime = end.getTime();
        // 随机时间范围不对的时候返回null
        if (beginTime >= endTime) {
            return null;
        }

        long randomTime = beginTime + (long) (Math.random() * (endTime - beginTime));

        // 过滤等于时间范围两头的情况
        if (randomTime == beginTime || randomTime == endTime) {
            return getRandomDate(begin, end);
        }

        randomDate.setTime(randomTime);

        return randomDate;
    }

    public static java.sql.Date string2SQLDate(String date) {
        java.sql.Date ret = null;
        if (date != null && date.length() != 0) {
            if (date.length() > 11) {
                if (date.indexOf(45) > 0) {
                    if (date.indexOf(58) > 0) {
                        ret = string2SQLDate(date, "yyyy-MM-dd HH:mm:ss");
                    }
                    else {
                        ret = string2SQLDate(date, "yyyy-MM-dd HH-mm-ss");
                    }
                }
                else if (date.indexOf(47) > 0) {
                    ret = string2SQLDate(date, "yyyy/MM/dd HH:mm:ss");
                }
                else {
                    ret = string2SQLDate(date, "yyyyMMddHHmmss");
                }
            }
            else if (date.indexOf(45) > 0) {
                ret = string2SQLDate(date, "yyyy-MM-dd");
            }
            else if (date.length() == 8) {
                ret = string2SQLDate(date, "yyyyMMdd");
            }
            else {
                ret = string2SQLDate(date, "yyyy年MM月dd日");
            }

            return ret;
        }
        else {
            return ret;
        }
    }

    public static java.sql.Date string2SQLDate(String date, String format) {
        boolean isSucc = true;
        Exception operateException = null;
        if (format == null || format.isEmpty()) {
            isSucc = false;
            operateException = new IllegalArgumentException("the date format string is null!");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (sdf == null) {
            isSucc = false;
            operateException = new IllegalArgumentException(
                    "the date format string is not matching available format object");
        }
        Date tmpDate = null;
        try {
            if (isSucc) {
                tmpDate = sdf.parse(date);
            }
        }
        catch (Exception e) {
            isSucc = false;
            operateException = e;
        }

        if (!isSucc) {
            if (operateException instanceof IllegalArgumentException) {
                throw (IllegalArgumentException) operateException;
            }
            else {
                throw new IllegalArgumentException("the date string " + date + " is not matching format: " + format
                        + ".\n cause by :" + operateException.toString());
            }
        }
        else {
            return new java.sql.Date(tmpDate.getTime());
        }
    }

    // TODO 该方法暂定
    public static java.sql.Date getDBDateTime() {
        java.sql.Date date = null;
        date = new java.sql.Date(System.currentTimeMillis());
        return date;
    }

    public static java.sql.Date offsetMinute(java.sql.Date initDate, long offsetMinutes) {
        Date date = new Date(initDate.getTime());
        return new java.sql.Date(offsetSecond(date, 60 * offsetMinutes).getTime());
    }

    /**
     * @param date Date 锟斤拷始时锟斤拷
     * @return Date
     */
    public static Date offsetSecond(Date date, long seconds) {
        if (date == null) {
            return null;
        }
        // modified by chi.yuxing task(529008)
        long time = date.getTime();
        long time2 = time + (seconds * 1000);
        long time3 = time + (seconds * 1000) - 60 * 60 * 1000;
        Date date2 = new Date(time2);
        Date date3 = new Date(time3);

        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(date);
        Calendar calendarDate2 = Calendar.getInstance();
        calendarDate2.setTime(date2);
        Calendar calendarDate3 = Calendar.getInstance();
        calendarDate3.setTime(date3);

        long dstDate = calendarDate.get(Calendar.DST_OFFSET);
        long dstDate2 = calendarDate2.get(Calendar.DST_OFFSET);
        long dstDate3 = calendarDate3.get(Calendar.DST_OFFSET);

        long dstOffset = dstDate - dstDate2;
        // 前后两个日期偏移相同（含不偏移）或者夏令日开始的那个小时不用补偿，否则要补偿偏移量。
        boolean isFlag = dstDate2 - dstDate3 != 0 && dstDate2 != 0;
        if (dstOffset == 0 || isFlag) {
            return date2;
        }
        else {
            return new Date(time2 + dstOffset);
        }
    }

    public static Date parse(String source) throws Exception {
        DateFormat format = new SimpleDateFormat(DATETIME_FORMAT_19);
        return format.parse(source);
    }

    public static Date strToSqlDate(String str) {
        java.sql.Date returnValue = null;
        if (str != null && !"".equals(str)) {
            returnValue = new java.sql.Date(DateUtil.strToDate(str).getTime());
        }
        return returnValue;
    }

    public static Date strToDate(final String strn) {
        Date returnValue = null;

        if (strn != null && !"".equals(strn) && !"null".equalsIgnoreCase(strn)) {

            if (strn.indexOf("-") != -1 && strn.length() < 12) {
                returnValue = str2date(strn, "yyyy-MM-dd");
            }
            else if (strn.indexOf("-") != -1 && strn.length() >= 12) {
                returnValue = str2date(strn, "yyyy-MM-dd HH:mm:ss");
            }
            else if (strn.indexOf("/") != -1 && strn.length() < 12) {
                returnValue = str2date(strn, "yyyy/MM/dd");
            }
            else if (strn.indexOf("/") != -1 && strn.length() >= 12) {
                returnValue = str2date(strn, "yyyy/MM/dd HH:mm:ss");
            }
            else if (strn.length() == 8) {
                returnValue = str2date(strn, "yyyyMMdd");
            }
            else if (strn.length() == 14) {
                returnValue = str2date(strn, "yyyyMMddHHmmss");
            }
        }
        return returnValue;
    }

    public static Date str2date(final String str, final String format) {
        Date ret = null;
        final SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            ret = sdf.parse(str);
        }
        catch (Exception e) {
            ret = null;
        }
        return ret;
    }

    public static java.sql.Date util2sql(final Date aDate) {
        if (aDate == null) {
            return null;
        }
        return new java.sql.Date(aDate.getTime());
    }

    public static String date2String(java.sql.Date date, String format) {
        if (date == null) {
            return "";
            // 如果为空，填入当前时间的代码取消。框架中不应含有这种带业务逻辑的代码。 zhang.nanyu commented the
            // code 2008-12-12
            // date = new Date();
        }
        SimpleDateFormat sdf = getDateFormat(format);
        return sdf.format(date);
    }

    public static SimpleDateFormat getDateFormat(String format) {
        /*
         * SimpleDateFormat sdf = (SimpleDateFormat) aDateFormateMap.get(format); if
         * (sdf == null) { sdf = new SimpleDateFormat(format);
         * aDateFormateMap.put(format, sdf); }
         */
        return new SimpleDateFormat(format);
    }

    /**
     * @return
     */
    public static int getCurExpire() {
        Long currentTime = getCurrentTimeMillis();
        String curDate = getFormatedDate() + " 23:59:00";
        Long tonightTime = parseStrToDateTime(curDate).getTime();
        int expire = new BigDecimal(tonightTime - currentTime).divide(new BigDecimal(1000), 0, RoundingMode.HALF_UP).intValue();

        return expire;
    }

    /**
     * 判断是否在有效时间内
     *
     * @param startTime 开始
     * @param endTime   结束
     * @return
     */
    public static boolean isEffectiveDate(Time startTime, Time endTime) {
        try {
            if (startTime == null || endTime == null) {
                return false;
            }

            String curentTime = new SimpleDateFormat(TIME_FORMAT).format(new Date());
            Date nowTime = new SimpleDateFormat(TIME_FORMAT).parse(curentTime);

            if (nowTime.getTime() == startTime.getTime()
                    || nowTime.getTime() == endTime.getTime()) {
                return true;
            }

            Calendar date = Calendar.getInstance();
            date.setTime(nowTime);

            Calendar begin = Calendar.getInstance();
            begin.setTime(startTime);

            Calendar end = Calendar.getInstance();
            end.setTime(endTime);

            if (begin.after(end)) {
                //隔天情况
                Date otherEndTime = new SimpleDateFormat(TIME_FORMAT).parse("23:59:59");
                Date otherStartTime = new SimpleDateFormat(TIME_FORMAT).parse("00:00:00");

                Calendar otherBegin = Calendar.getInstance();
                otherBegin.setTime(otherStartTime);

                Calendar otherEnd = Calendar.getInstance();
                otherEnd.setTime(otherEndTime);

                return date.after(begin) && date.before(otherEnd);
            }
            else {
                return date.after(begin) && date.before(end);
            }
        }
        catch (Exception e) {
            return false;
        }

    }

    /**
     * 传入时间参数，获取X1至X7七个时间点 物流中心效期管理
     *
     * @param dateStr
     * @throws ParseException
     */
    public static List<String> get7TimePoint(String dateStr) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_8);
        //X1
        Date x1Date = format.parse(dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(x1Date);

        //X2=X1-7天
        cal.add(Calendar.DAY_OF_MONTH, -7);
        Date x2Date = cal.getTime();

        cal.setTime(x1Date);
        //X3= X1-14天
        cal.add(Calendar.DAY_OF_MONTH, -14);
        Date x3Date = cal.getTime();

        cal.setTime(x1Date);
        //X4=X1-21
        cal.add(Calendar.DAY_OF_MONTH, -21);
        Date x4Date = cal.getTime();

        //X5=X4-1个月，那个月最后一天数据
        cal.add(Calendar.MONTH, -1);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        Date x5Date = cal.getTime();

        cal.setTime(x4Date);
        //X6=X4-2个月，那个月最后一天数据
        cal.add(Calendar.MONTH, -2);
        value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        Date x6Date = cal.getTime();

        cal.setTime(x4Date);
        //X7=X4-3个月，那个月最后一天数据
        cal.add(Calendar.MONTH, -3);
        value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        Date x7Date = cal.getTime();

        //封装返回
        List<String> resultList = new ArrayList<>(7);
        String x1 = format.format(x1Date);
        String x2 = format.format(x2Date);
        String x3 = format.format(x3Date);
        String x4 = format.format(x4Date);
        String x5 = format.format(x5Date);
        String x6 = format.format(x6Date);
        String x7 = format.format(x7Date);

        resultList.add(x7);
        resultList.add(x6);
        resultList.add(x5);
        resultList.add(x4);
        resultList.add(x3);
        resultList.add(x2);
        resultList.add(x1);

        return resultList;
    }

    /**
     * 传入时间参数，获取X1至X2两个时间点 物流中心效期管理
     *
     * @param dateStr
     * @throws ParseException
     */
    public static List<String> get2TimePoint(String dateStr) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_8);
        //X1
        Date x1Date = format.parse(dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(x1Date);

        //X2=X1-7天
        cal.add(Calendar.DAY_OF_MONTH, -7);
        Date x2Date = cal.getTime();

        //封装返回
        List<String> resultList = new ArrayList<>(7);
        String x1 = format.format(x1Date);
        String x2 = format.format(x2Date);

        resultList.add(x1);
        resultList.add(x2);

        return resultList;
    }

    /**
     * 按照格式转化时间为字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String transformDate2String(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String result = simpleDateFormat.format(date);
        return result;
    }

    // ########################## 获取 本日 昨日 本周 本月 本季 本年 相应的开始时间 结束时间 ########################

    /**
     * 获得 本日 0点时间
     * @return
     */
    public static Date getTimesMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得 昨日 0点时间
     * @return
     */
    public static Date getYesterdayMorning() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getTimesMorning().getTime() - 3600 * 24 * 1000);
        return cal.getTime();
    }

    /**
     * 获得 本周 一0点时间
     * @return
     */
    public static Date getTimesWeekMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 获得 本月 第一天0点时间
     * @return
     */
    public static Date getTimesMonthMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * 获得 本季 第一天0点时间
     * @return
     */
    public static Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 0);
            }
            else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 3);
            }
            else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 6);
            }
            else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 9);
            }
            c.set(Calendar.DATE, 1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        }
        catch (Exception e) {
            log.info(e.getMessage());
        }
        return now;
    }

    /**
     * 获得 本年 第一天0点时间
     * @return
     */
    public static Date getCurrentYearStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);
        return cal.getTime();
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return days
     */
    public static int differentDaysByMillisecond(Date date1, Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    /*
     * @author: lijipeng
     * @date: 2020-12-14 11:05
     * @description: 时间戳转换成日期格式字符串
     * @param seconds format
     * @return
     */
    public static String timeStampToDateStr(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || "null".equals(seconds)) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }

    /*
     * @author: lijipeng
     * @date: 2020-12-14 11:21
     * @description: 取得当前时间戳（精确到秒）
     * @param seconds format
     * @return
     */
    public static String timeStamp() {
        long time = System.currentTimeMillis();
        String t = String.valueOf(time / 1000);
        return t;
    }

    /*
     * @author: lijipeng
     * @date: 2020-12-15 15:21
     * @description: 取得当前年月若干个月前的年月 比如当前为202012 获取3个月前的年月 即202009
     * @param severalMonth 若干个月
     * @return
     */
    public static String getSeveralMonthFrontMont(String severalMonth) {

        Date dNow = new Date(); //获取当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow); //把当前时间赋给日历

        Integer severalMonthInt = Integer.valueOf(severalMonth);

        calendar.add(calendar.MONTH, -severalMonthInt); //将时间中的月设置为前3月
        dBefore = calendar.getTime(); //得到前3月的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); //设置时间格式
        String defaultStartDate = sdf.format(dBefore); //格式化前3月的时间

        String beginDateStr = defaultStartDate.replace("-", "");

        return beginDateStr;
    }

    /*
     * @author: lijipeng
     * @date: 2020-12-15 15:21
     * @description: 取得当前年月 比如当前为202012 就拿202012
     * @param severalMonth 若干个月
     * @return
     */
    public static String getCurrentYearMont() {
        Date dNow = new Date(); //当前时间
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow); //把当前时间赋值给日历
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); //设置时间格式
        String defaultEndDate = sdf.format(dNow); //格式化当前时间
        String currentYearMont = defaultEndDate.replace("-", "");

        return currentYearMont;
    }

    public static void main(String[] argsw) {
        Date date = new Date();

        SimpleDateFormat format2 = new  SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String timeStamp2 = "1606807429000";
        String s = timeStampToDateStr(timeStamp2, "yyyy-MM-dd HH:mm:ss");
        System.out.println(s);

        //long lonTime = Long.parseLong(s);
        //date.setTime(lonTime);

        String format1 = format2.format(date);
        System.out.println("format1：" + format1);


        //System.out.println(new SimpleDateFormat(DATETIME_FORMAT_19).format(getTimesMorning()));
        String timeStamp = timeStamp();
        //System.out.println("timeStamp=" + timeStamp); // 运行输出:timeStamp=1470278082
        //System.out.println(System.currentTimeMillis()); // 运行输出:1470278082980

        Map<String, String> map1 = new HashMap<>();
        map1.put("x", "3");
        Map<String, String> map2 = new HashMap<>();
        map2.put("x", "2");
        Map<String, String> map3 = new HashMap<>();
        map3.put("x", "5");

        List<Map<String, String>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        list.add(map3);
        System.out.println("排序前" + list);   // 排序前[{x=3}, {x=2}, {x=5}]

        if (list.size() > 0) {
            Collections.sort(list, new Comparator<Map>() {
                @Override
                public int compare(Map o1, Map o2) {
                    int ret = 0;
                    //比较两个对象的顺序，如果前者小于、等于或者大于后者，则分别返回-1/0/1
                    ret = o1.get("x").toString().compareTo(o2.get("x").toString()); //逆序的话就用o2.compareTo(o1)即可
                    return ret;
                }
            });
        }
        System.out.println("排序后" + list);   // 排序后[{x=2}, {x=3}, {x=5}]

        //------------------------------------------------------------------------------------------------------------

        Map<String, String> treeMap = new TreeMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // return o1.compareTo(o2);	// 默认：升序排列
                return o2.compareTo(o1);  // 降序排列
                // return 0;	// 只返回存储的第一个key的值，这里是"ccccc"
            }
        });
        treeMap.put("c", "ccccc");
        treeMap.put("a", "aaaaa");
        treeMap.put("b", "bbbbb");
        treeMap.put("d", "ddddd");

        Set<String> strings = treeMap.keySet();

        for (String key : treeMap.keySet()) {
            //System.out.println(key + " : " + treeMap.get(key));
        }

        //------------------------------------------------------------------------------------------------------------

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date newNow = null;
        newNow = new Date();
        String format = sdf.format(newNow);

        //System.out.println(format);

        // -----------------------------

        String currrr = "20210101";

        String yyyyMMdd = getYesterday("yyyyMMdd");
        System.out.println("qwertyu-------------------:" + yyyyMMdd);

        //--------------------------------------------

        //创建一个File对象
        File f = new File("D:\\codeTest");
        //判断是否存在
        System.out.println(f.exists());

        //如果D:\codeTest不存在，则以文件的形式创建出来
        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(f.exists());

        long length1 = f.length();
        System.out.println("长度为1---------：" + length1);

        String formatedMonthTime = getFormatedMonthTime("2021-01-22 12:23:15");
        System.out.println("formatedMonthTime:" + formatedMonthTime);


    }

    /*
     * @author: lijipeng
     * @date: 2021-02-03 14:18
     * @description: 根据日期字符串 得到年月。
     * @param fromDateStr 开始日期
     * @return
     */
    public static String getFormatedMonthTime(String fromDateStr) {
        SimpleDateFormat startDateFormator = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date fromDate = null;
        try {
            fromDate = startDateFormator.parse(fromDateStr);
        }
        catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        SimpleDateFormat endDateFormator = new SimpleDateFormat(DATE_FORMAT_6);
        return endDateFormator.format(fromDate);
    }

    /**
     *  获取昨天
     * @return
     */
    public static Date getYesterday() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        return c.getTime();
    }
}
