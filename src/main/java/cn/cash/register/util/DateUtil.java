package cn.cash.register.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 日期工具类
 * 
 * @author YingZhang
 * @version $Id: DateUtil.java, v 0.1 2017年12月26日 上午11:30:19 YingZhang Exp $
 */
public class DateUtil {

    public final static long   ONE_DAY_SECONDS      = 86400L;

    public final static String shortFormat          = "yyyyMMdd";
    public final static String longFormat           = "yyyyMMddHHmmss";
    public final static String msecFormat           = "yyyyMMddHHmmssSSS";
    public final static String webFormat            = "yyyy-MM-dd";
    public final static String timeFormat           = "HHmmss";
    public final static String monthFormat          = "yyyyMM";
    public final static String chineseDtFormat      = "yyyy年MM月dd日";

    /** MM大写是为了区分mm(分), HH表示24小时制, hh表示12小时制 */
    public final static String completeFormat       = "yyyy-MM-dd HH:mm:ss:SSS";
    public final static String newFormat            = "yyyy-MM-dd HH:mm:ss";
    public final static String noSecondFormat       = "yyyy-MM-dd HH:mm";
    public final static long   ONE_DAY_MILL_SECONDS = 86400000L;

    /**
     * 获取pattern字符串对象的日期格式对象。
     * 
     * @param pattern 格式字符串。
     * @return 日期格式对象。
     */
    public static DateFormat getNewDateFormat(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);

        df.setLenient(false);
        return df;
    }

    /**
     * 使用指定格式format格式date日期对象。
     * 
     * @param date 待格式化日期对象。
     * @param format 指定格式。
     * @return 格式化好后的日期字符串。
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 使用shortFormat格式将sDate字符串对象转换为日期对象。
     * 
     * @param sDate 字符串对象
     * @param delimit 字符串所使用分隔符
     * @return sDate对应的日期对象
     * @throws ParseException
     */
    public static Date parseDateNoTime(String sDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(shortFormat);

        if ((sDate == null) || (sDate.length() < shortFormat.length())) {
            throw new ParseException("length too little", 0);
        }

        if (!StringUtils.isNumeric(sDate)) {
            throw new ParseException("not all digit", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 使用format格式将sDate字符串对象转换为日期对象。
     * 
     * @param sDate 字符串对象
     * @param format 指定的格式
     * @return sDate对应的日期对象
     * @throws ParseException
     */
    public static Date parseDateNoTime(String sDate, String format) throws ParseException {
        if (StringUtils.isBlank(format)) {
            throw new ParseException("Null format. ", 0);
        }

        DateFormat dateFormat = new SimpleDateFormat(format);

        if ((sDate == null) || (sDate.length() < format.length())) {
            throw new ParseException("length too little", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 使用shortFormat格式将带有delimit分隔符的sDate字符串对象转换为日期对象。
     * 
     * @param sDate 字符串对象
     * @param delimit 字符串所使用分隔符
     * @return sDate对应的日期对象
     * @throws ParseException
     */
    public static Date parseDateNoTimeWithDelimit(String sDate, String delimit) throws ParseException {
        sDate = sDate.replaceAll(delimit, "");

        DateFormat dateFormat = new SimpleDateFormat(shortFormat);

        if ((sDate == null) || (sDate.length() != shortFormat.length())) {
            throw new ParseException("length not match", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 解析longFormat格式的日期字符串为日期对象。
     * 
     * @param sDate 日期对象。
     * @return sDate 对象的日期对象。
     */
    public static Date parseDateLongFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(longFormat);
        Date d = null;

        if ((sDate != null) && (sDate.length() == longFormat.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }

        return d;
    }

    /**
     * 解析newFormat格式的日期字符串为日期对象。
     * 
     * @param sDate 日期对象。
     * @return sDate 对象的日期对象。
     */
    public static Date parseDateNewFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(newFormat);
        Date d = null;
        dateFormat.setLenient(false);
        if ((sDate != null) && (sDate.length() == newFormat.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }
        return d;
    }

    /**
     * 计算当前时间几小时之后的时间
     *
     * @param date
     * @param hours
     *
     * @return
     */
    public static Date addHours(Date date, long hours) {
        return addMinutes(date, hours * 60);
    }

    /**
     * 把date1日期对象推迟minutes分钟后的时间日期。
     *
     * @param date
     * @param minutes
     *
     * @return
     */
    public static Date addMinutes(Date date, long minutes) {
        return addSeconds(date, minutes * 60);
    }

    /**
     * 把date1日期对象推迟sesc秒后的时间日期。
     * 
     * @param date1 日期对象
     * @param secs 延迟秒数
     *
     * @return 推迟sesc秒后的日期对象
     */

    public static Date addSeconds(Date date1, long secs) {
        return new Date(date1.getTime() + (secs * 1000));
    }

    /**
     * 判断输入的字符串是否为合法的小时
     *
     * @param hourStr
     *
     * @return true/false
     */
    public static boolean isValidHour(String hourStr) {
        if (!StringUtils.isEmpty(hourStr) && StringUtils.isNumeric(hourStr)) {
            int hour = new Integer(hourStr).intValue();

            if ((hour >= 0) && (hour <= 23)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断输入的字符串是否为合法的分或秒
     *
     * @param minuteStr
     *
     * @return true/false
     */
    public static boolean isValidMinuteOrSecond(String str) {
        if (!StringUtils.isEmpty(str) && StringUtils.isNumeric(str)) {
            int hour = new Integer(str).intValue();

            if ((hour >= 0) && (hour <= 59)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 将date1日期推迟days天。
     *
     * @param date1 日期
     * @param days 天数
     *
     * @return 新的日期
     */
    public static Date addDays(Date date1, long days) {
        return addSeconds(date1, days * ONE_DAY_SECONDS);
    }

    /**
     * 获取sDate后一天的日期。
     * 
     * @param sDate “yyyyMMdd”格式的日期字符串。
     * @return sDate后一天的日期字符换。“yyyyMMdd”格式。
     * @throws ParseException
     */
    public static String getTomorrowDateString(String sDate) throws ParseException {
        Date aDate = parseDateNoTime(sDate);

        aDate = addSeconds(aDate, ONE_DAY_SECONDS);

        return getDateString(aDate);
    }

    /**
     * 按照longFormat格式化date日期对象。
     * newFormat = “yyyyMMddHHmmss”
     * 
     * @param date 待格式化日期对象。
     * @return 格式化后的字符串对象。
     */
    public static String getLongDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(longFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 按照newFormat格式化date日期对象。
     * newFormat = “yyyy-MM-dd HH:mm:ss”
     * 
     * @param date 待格式化日期对象。
     * @return 格式化后的字符串对象。
     */
    public static String getNewFormatDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(newFormat);
        return getDateString(date, dateFormat);
    }

    /**
     * 获取date日期的完整时间字符串。
     * “yyyy-MM-dd HH:mm:ss:SSS”
     * 
     * @param date 待获取完整时间字符串的日期。
     * @return “yyyy-MM-dd HH:mm:ss:SSS”格式的时间字符串。
     */
    public static String getCompleteDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(completeFormat);
        return getDateString(date, dateFormat);
    }

    /**
     * 按照dateFormat格式改写dat日期的格式。
     * 
     * @param date 待改变格式的日期。
     * @param dateFormat 自定义的格式对象。
     * @return 格式为dateFormat的日期字符串。
     */
    public static String getDateString(Date date, DateFormat dateFormat) {
        if (date == null || dateFormat == null) {
            return null;
        }

        return dateFormat.format(date);
    }

    /**
     * 获取sDate前一天的日期字符串。
     * 
     * @param sDate 日期字符串。
     * @return sDate的昨天日期字符串。
     * @throws ParseException
     */
    public static String getYesterDayDateString(String sDate) throws ParseException {
        Date aDate = parseDateNoTime(sDate);

        aDate = addSeconds(aDate, -ONE_DAY_SECONDS);

        return getDateString(aDate);
    }

    /**
     * 取得“yyyyMMdd”的日期格式
     *
     * @param date 待更改格式的日期对象。
     *
     * @return ”yyyyMMdd”字符串。
     */
    public static String getDateString(Date date) {
        DateFormat df = getNewDateFormat(shortFormat);

        return df.format(date);
    }

    /**
     * 取得“yyyy-MM-dd”的日期格式
     *
     * @param date 待更改格式的日期对象。
     *
     * @return “yyyy-MM-dd”字符串。
     */
    public static String getWebDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(webFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 取得“X年X月X日”的日期格式
     *
     * @param date 待更改格式的日期对象。
     *
     * @return “X年X月X日”
     */
    public static String getChineseDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(chineseDtFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 以"yyyyMMdd"的格式，获取今天的日期。
     * 
     * @return 今天日期的字符串对象
     */
    public static String getTodayString() {
        DateFormat dateFormat = getNewDateFormat(shortFormat);

        return getDateString(new Date(), dateFormat);
    }

    /**
     * 以"HHmmss"的格式，获取固定日期的时间字符串。
     * 
     * @param date 日期对象。
     * @return 时间字符串。
     */
    public static String getTimeString(Date date) {
        DateFormat dateFormat = getNewDateFormat(timeFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 获取当前日期的days天前的日期字符串。
     * 
     * @param days 间隔天数。
     * @return days天前的日期字符串。格式为'yyyyMMdd'
     */
    public static String getBeforeDayString(int days) {
        Date date = new Date(System.currentTimeMillis() - (ONE_DAY_MILL_SECONDS * days));
        DateFormat dateFormat = getNewDateFormat(shortFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 取得两个日期间隔秒数（日期1-日期2）
     *
     * @param one 日期1
     * @param two 日期2
     *
     * @return 间隔秒数
     */
    public static long getDiffSeconds(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / 1000;
    }

    /**
     * 取得两个日期的间隔分钟数（日期1-日期2）
     *
     * @param one 日期1
     * @param two 日期2
     *
     * @return 间隔分钟数
     */
    public static long getDiffMinutes(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / (60 * 1000);
    }

    /**
     * 取得两个日期的间隔天数（日期1-日期2）
     *
     * @param one 日期1
     * @param two 日期2
     *
     * @return 间隔天数
     */
    public static long getDiffDays(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 以“yyyyMMdd”的格式，获取dateString日期days天前的日期。
     * 
     * @param dateString 日期字符串，是“yyyyMMdd”的格式。
     * @param days 间隔天数。
     * @return days天前的日期字符串。
     */
    public static String getBeforeDayString(String dateString, int days) {
        Date date;
        DateFormat df = getNewDateFormat(shortFormat);

        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            date = new Date();
        }

        date = new Date(date.getTime() - (ONE_DAY_MILL_SECONDS * days));

        return df.format(date);
    }

    /**
     * 判断表示时间的字符串是否为符合yyyyMMdd格式
     * 
     * @param strDate 时间字符串
     * @return true,表示为shortDateFormat；反之则不是。
     */
    public static boolean isValidShortDateFormat(String strDate) {
        if (strDate.length() != shortFormat.length()) {
            return false;
        }

        try {
            Integer.parseInt(strDate); //---- 避免日期中输入非数字 ----
        } catch (Exception NumberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(shortFormat);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 判断表示时间的字符串是否为符合yyyyMMdd格式
     * 
     * @param strDate 时间字符串
     * @param delimiter 字符串使用的分隔符
     * @return true,表示为shortDateFormat；反之则不是。
     */
    public static boolean isValidShortDateFormat(String strDate, String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidShortDateFormat(temp);
    }

    /**
     * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式
     * 
     * @param strDate 时间字符串
     * @return true,表示为longFormat；反之则不是。
     */
    public static boolean isValidLongDateFormat(String strDate) {
        if (strDate.length() != longFormat.length()) {
            return false;
        }

        try {
            Long.parseLong(strDate); //---- 避免日期中输入非数字 ----
        } catch (Exception NumberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(longFormat);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式
     * 
     * @param strDate 时间字符串
     * @param delimiter 字符串使用的分隔符
     * @return true,表示为longFormat；反之则不是。
     */
    public static boolean isValidLongDateFormat(String strDate, String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidLongDateFormat(temp);
    }

    /**
     * 获取shortFormat格式的日期字符串。
     * 
     * @param strDate 待格式化的日期字符串。
     * @return “yyyyMMdd”格式的日期字符串.
     */
    public static String getShortDateString(String strDate) {
        return getShortDateString(strDate, "-|/");
    }

    /**
     * 获取shortFormat格式的日期字符串。
     * 
     * @param strDate 待格式化的日期字符串。
     * @param delimiter 字符串使用的分隔符。
     * @return “yyyyMMdd”格式的日期字符串.
     */
    public static String getShortDateString(String strDate, String delimiter) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }

        String temp = strDate.replaceAll(delimiter, "");

        if (isValidShortDateFormat(temp)) {
            return temp;
        }

        return null;
    }

    /**
     * 获取shortFormat格式下，当前月份的第一天。
     * 
     * @return 当前月份第一天的字符串，shortFormat格式。
     */
    public static String getShortFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();

        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat df = getNewDateFormat(shortFormat);

        return df.format(cal.getTime());
    }

    /**
     * 以webFormat格式获取今天的日期。
     * 
     * @return 今天日期的字符串，格式为“yyyy-MM-dd”。
     */
    public static String getWebTodayString() {
        DateFormat df = getNewDateFormat(webFormat);

        return df.format(new Date());
    }

    /**
     * 获取webFormat格式下，当前月份的第一天。
     * 
     * @return 当前月份第一天的字符串，webFormat格式。
     */
    public static String getWebFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();

        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat df = getNewDateFormat(webFormat);

        return df.format(cal.getTime());
    }

    /**
     * 转换日期字符串的格式为formatOut的字符串。
     * 
     * @param dateString 待格式化的日期字符串
     * @param formatIn 原日期字符串的格式
     * @param formatOut 期望的日期格式
     * @return 以formatOut为格式的日期字符串
     */
    public static String convert(String dateString, DateFormat formatIn, DateFormat formatOut) {
        try {
            Date date = formatIn.parse(dateString);

            return formatOut.format(date);
        } catch (ParseException e) {
            return "";
        }
    }

    /**
     * 将shortFormat格式的日期字符串更改为webFormat格式。
     * webFormat = “yyyy-MM-dd”
     * 
     * @param dateString 待改变格式的日期字符串
     * @return webFormat格式的日期字符串
     */
    public static String convert2WebFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(webFormat);

        return convert(dateString, df1, df2);
    }

    /**
     * 将shortFormat格式的日期字符串更改为chineseFormat格式。
     * chienseFormat = “yyyy年MM月dd日”
     * 
     * @param dateString 待改变格式的日期字符串
     * @return chineseFormat格式的日期字符串
     */
    public static String convert2ChineseDtFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(chineseDtFormat);

        return convert(dateString, df1, df2);
    }

    /**
     * 将webFormat格式的日期字符串更改为shortFormat格式。
     * webFormat = “yyyy-MM-dd”
     * shortFormat = “yyyyMMdd”
     * 
     * @param dateString 待改变格式的日期字符串
     * @return shortFormat格式的日期字符串
     */
    public static String convertFromWebFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(webFormat);

        return convert(dateString, df2, df1);
    }

    /**
     * 将longFormat格式的日期字符串更改为newFormat格式。
     * newFormat = “yyyy-MM-dd HH:mm:ss”
     * longFormat = “yyyyMMddHHmmss”
     * 
     * @param dateString 待改变格式的日期字符串
     * @return newFormat格式的日期字符串
     */
    public static String convertLongToNewFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(longFormat);
        DateFormat df2 = getNewDateFormat(newFormat);
        return convert(dateString, df1, df2);
    }

    /**
     * 使用webformat格式解析日期字符串，并判断第一个日期是否比第二个日期晚。
     * 当date1早于date2时，返回false；反之则返回true。
     * 注：webformat = "yyyy-MM-dd HH:mm:ss"
     * 
     * @param date1 字符串日期
     * @param date2 字符串日期
     * @return 两个日期比较大小的布尔值。
     */
    public static boolean webDateNotLessThan(String date1, String date2) {
        DateFormat df = getNewDateFormat(webFormat);

        return dateNotLessThan(date1, date2, df);
    }

    /**
     * 使用指定的日期格式解析日期字符串，并判断第一个日期是否比第二个日期晚。
     * 当date1早于date2时，返回false；反之则返回true。
     * 
     * @param date1 字符串日期
     * @param date2 字符串日期
     * @param format 用于解析字符串的日期格式
     *
     * @return 两个日期比较大小的布尔值。
     */
    public static boolean dateNotLessThan(String date1, String date2, DateFormat format) {
        try {
            Date d1 = format.parse(date1);
            Date d2 = format.parse(date2);

            if (d1.before(d2)) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 转换日期对象的格式为EmailDate格式的字符串。
     * EmailDate格式为”yyyy年MM月dd日HH:mm:ss“
     * 
     * @param today 待改变格式的日期对象
     * @return 格式为”yyyy年MM月dd日HH:mm:ss“的字符串
     */
    public static String getEmailDate(Date today) {
        String todayStr;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");

        todayStr = sdf.format(today);
        return todayStr;
    }

    /**
     * 转换日期对象的格式为SmsDate格式的字符串。
     * SMSDate格式为 ”MM月dd日HH:mm“
     * 
     * @param today 待改变格式的日期对象
     * @return 格式为”MM月dd日HH:mm“的字符串
     */
    public static String getSmsDate(Date today) {
        String todayStr;
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日HH:mm");

        todayStr = sdf.format(today);
        return todayStr;
    }

    /**
     * 
     * 
     * @param startDate
     * @param endDate
     * @param format
     * @return
     */
    public static String formatTimeRange(Date startDate, Date endDate, String format) {
        if ((endDate == null) || (startDate == null)) {
            return null;
        }

        String rt = null;
        long range = endDate.getTime() - startDate.getTime();
        long day = range / DateUtils.MILLIS_PER_DAY;
        long hour = (range % DateUtils.MILLIS_PER_DAY) / DateUtils.MILLIS_PER_HOUR;
        long minute = (range % DateUtils.MILLIS_PER_HOUR) / DateUtils.MILLIS_PER_MINUTE;

        if (range < 0) {
            day = 0;
            hour = 0;
            minute = 0;
        }

        rt = format.replaceAll("dd", String.valueOf(day));
        rt = rt.replaceAll("hh", String.valueOf(hour));
        rt = rt.replaceAll("mm", String.valueOf(minute));

        return rt;
    }

    /**
     * 格式化日期对象为“yyyyMM”的格式。
     * 
     * @param date 待格式化的日期对象。
     * @return 返回格式化后的字符串对象。
     */
    public static String formatMonth(Date date) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(monthFormat).format(date);
    }

    /**
     * 获取系统日期的前一天日期，返回Date
     *
     * @return 系统日期前一天的日期对象
     */
    public static Date getBeforeDate() {
        Date date = new Date();

        return new Date(date.getTime() - (ONE_DAY_MILL_SECONDS));
    }

    /**
     * 获得指定时间当天起点时间
     * 
     * @param date 待获取当天起点时间的日期
     * @return 当天起点时间的日期对象
     */
    public static Date getDayBegin(Date date) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        df.setLenient(false);

        String dateString = df.format(date);

        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            return date;
        }
    }

    /**
     * 判断日期对象date加上min分钟后，是否小于当前时间。
     * 
     * @param date 日期对象。
     * @param min 待加的分钟数。
     * @return true表示小于当前时间，false表示大于当前时间
     */
    public static boolean dateLessThanNowAddMin(Date date, long min) {
        return addMinutes(date, min).before(new Date());

    }

    /**
     * 判断传入的日期对象是否小于当前日期。
     * 传入null值时，返回false。
     * 
     * @param date 待比较对象。
     * @return true代表当前日期大于待比较日期
     *         false代表当前日期小于待比较日期
     */
    public static boolean isBeforeNow(Date date) {
        if (date == null)
            return false;
        return date.compareTo(new Date()) < 0;
    }

    /**
     * 将noSecondFormat格式的sDate字符串解析成日期对象
     * 
     * @param sDate 字符串格式的日期。
     * @return 日期对象。
     * @throws ParseException 传入的字符串为空/不是日期/日期的位数小于无秒格式时抛异常
     */
    public static Date parseNoSecondFormat(String sDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(noSecondFormat);

        if ((sDate == null) || (sDate.length() < noSecondFormat.length())) {
            throw new ParseException("length too little", 0);
        }

        if (!StringUtils.isNumeric(sDate)) {
            throw new ParseException("not all digit", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 比较两个字符串日期相差多少天/多少个月/多少年。
     * 
     * 举例：compareDate("2009-09-12", null, 0);//比较天
     *     compareDate("2009-09-12", null, 1);//比较月
     *     compareDate("2009-09-12", null, 2);//比较年
     *     
     * @param date1 需要比较的时间 不能为空(null),需要正确的日期格式 ,如：2009-09-12
     *            
     * @param date2 被比较的时间 为空(null)则为当前时间
     *            
     * @param stype 比较类型。按照日期/月份/年进行比较。
     *              0为多少天，1为多少个月，2为多少年
     *            
     * @return 返回两个字符串日期的日期/月份/年份差额。
     * 
     */
    public static int compareDate(String startDay, String endDay, int stype) {
        int n = 0;
        //        String[] u = { "天", "月", "年" };
        String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";

        endDay = endDay == null ? getCurrentDate("yyyy-MM-dd") : endDay;

        DateFormat df = new SimpleDateFormat(formatStyle);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(startDay));
            c2.setTime(df.parse(endDay));
        } catch (Exception e3) {
        }
        // List list = new ArrayList();
        while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
            // list.add(df.format(c1.getTime())); // 这里可以把间隔的日期存到数组中 打印出来
            n++;
            if (stype == 1) {
                c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
            } else {
                c1.add(Calendar.DATE, 1); // 比较天数，日期+1
            }
        }
        n = n - 1;
        if (stype == 2) {
            n = (int) n / 365;
        }

        return n;
    }

    /**
     * 获得当前时间，格式自定义
     * 
     * @param format
     * @return
     */
    public static String getCurrentDate(String format) {
        Calendar day = Calendar.getInstance();
        day.add(Calendar.DATE, 0);
        SimpleDateFormat sdf = new SimpleDateFormat(format);// "yyyy-MM-dd"
        String date = sdf.format(day.getTime());
        return date;
    }

    /** 
     * 获取当前月份所处季度。 
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度 
     *  
     * @param date 
     * @return 
     */
    public static int getSeason(Date date) {

        int season = 0;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    /**
     * 获取传入时间所在月份的第一天，如传入时间为2016-10 或者 2016-10-27 的时间<br />
     * 返回 2016-10-1
     * @param theDate
     * @return
     */
    public static Date getFirstDayOfMonth(Date theDate) {
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        return gcLast.getTime();
    }

    /**
     * 获取传入时间所在月份的最后一天，如传入时间为2016-10 或者 2016-10-27 的时间<br />
     * 返回 2016-10-31
     * @param theDate
     * @return
     */
    public static Date getLastDayOfMonth(Date theDate) {
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.add(Calendar.MONTH, 1);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        gcLast.add(Calendar.DAY_OF_MONTH, -1);
        return gcLast.getTime();
    }

    /**
     * 当天最后时间
     * 23：59：59
     * @return
     */
    public static Date getLastTimeOfDay() {
        GregorianCalendar cal = (GregorianCalendar) Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeek(Date dt) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

}
