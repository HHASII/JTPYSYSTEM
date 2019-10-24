package com.capgemini.jtp.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import javax.xml.crypto.Data;
import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String CHART_FORMAT = "yyyy-MM-dd HH:mm";
	
    public static final String DEFAULT_ZONE = "GMT+8";
	
	public static final String YYYY = "yyyy";

	public static final String YYYY_MM = "yyyy-MM";

	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	public static final String MM_DD_YYYY = "MM-dd-yyyy";

	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	public static final String YYYY_MM_DD_HH_MM_SS_fff = "yyyy-MM-dd HH:mm:ss.SSS";
	
	public static final String HH_MM = "HH:mm";

	private static String[] parsePatterns = { 
			"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", 
			"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM" };


	public static Date getNowDate() {
		return new Date();
	}

	/**
	 * 获取当前日期, 默认格式为yyyy-MM-dd
	 * 
	 * @return String
	 */
	public static String getDate() {
		return dateTimeNow(YYYY_MM_DD);
	}

	public static final String getTime() {
		return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
	}

	public static final String dateTimeNow() {
		return dateTimeNow(YYYYMMDDHHMMSS);
	}

	public static final String dateTimeNow(final String format) {
		return parseDateToStr(format, new Date());
	}

	public static final String dateTime(final Date date) {
		return parseDateToStr(YYYY_MM_DD, date);
	}

	public static final String parseDateToStr(final String format, final Date date) {
		return new SimpleDateFormat(format).format(date);
	}

	public static final Date dateTime(final String format, final String ts) {
		try {
			return new SimpleDateFormat(format).parse(ts);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 日期路径 即年/月/日 如2018/08/08
	 */
	public static final String datePath() {
		Date now = new Date();
		return DateFormatUtils.format(now, "yyyy/MM/dd");
	}

	/**
	 * 日期路径 即年/月/日 如20180808
	 */
	public static final String dateTime() {
		Date now = new Date();
		return DateFormatUtils.format(now, "yyyyMMdd");
	}

	/**
	 * 日期型字符串转化为日期 格式
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取服务器启动时间
	 */
	public static Date getServerStartDate() {
		long time = ManagementFactory.getRuntimeMXBean().getStartTime();
		return new Date(time);
	}

	

	public static String  currentMonthFirstDay() {
		Calendar   cal_1=Calendar.getInstance();//获取当前日期 
        cal_1.add(Calendar.MONTH, 0);
        cal_1.set(Calendar.DAY_OF_MONTH,1);
        String firstDay = parseDateToStr(YYYY_MM_DD,cal_1.getTime());
        return firstDay;

	}
	

	public static String  currentMonthLastDay() {
		 Calendar cale = Calendar.getInstance();   
	     cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
	     String lastDay = parseDateToStr(YYYY_MM_DD,cale.getTime());
         return lastDay;
	}
	

	public static Integer getRemainSecondsOneDay(Date currentDate) {
        Calendar midnight=Calendar.getInstance();
        midnight.setTime(currentDate);
        midnight.add(midnight.DAY_OF_MONTH,1);
        midnight.set(midnight.HOUR_OF_DAY,0);
        midnight.set(midnight.MINUTE,0);
        midnight.set(midnight.SECOND,0);
        midnight.set(midnight.MILLISECOND,0);
        Integer seconds=(int)((midnight.getTime().getTime()-currentDate.getTime())/1000);
        return seconds;
    }

	public static String addDay(Date beforeDate, int count){
		Calendar cal = Calendar.getInstance();
        cal.setTime(beforeDate);
        cal.add(Calendar.DAY_OF_MONTH, count);
		return parseDateToStr(YYYY_MM_DD, cal.getTime());
	}

	public static String parseToMMDDYYYY(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(MM_DD_YYYY);
		return sdf.format(date);
	}

		//判断选择的日期是否是本周
		public static boolean isThisWeek(long time)
		{
			Calendar calendar = Calendar.getInstance();
			int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
			calendar.setTime(new Date(time));
			int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
			if(paramWeek==currentWeek){
				return true;
			}
			return false;
		}
		//判断选择的日期是否是今天
		public static boolean isToday(long time)
		{
			return isThisTime(time,"yyyy-MM-dd");
		}
		//判断选择的日期是否是本月
		public static boolean isThisMonth(long time)
		{
			return isThisTime(time,"yyyy-MM");
		}
		private static boolean isThisTime(long time,String pattern) {
			Date date = new Date(time);
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			String param = sdf.format(date);//参数时间
			String now = sdf.format(new Date());//当前时间
			if(param.equals(now)){
				return true;
			}
			return false;
		}

		public void testTime(){
			isToday(1416360654000L);
			isThisMonth(1416360654000L);
			isThisWeek(1416360654000L);
		}
}
