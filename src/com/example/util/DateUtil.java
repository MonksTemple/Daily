package com.example.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;

import android.annotation.SuppressLint;

/**
 * 日期处理类
 */
public class DateUtil {

	/**
	 * 存储周一到周日的字符串
	 */
	public static String[] weekName = { "周日", "周一", "周二", "周三", "周四", "周五","周六" };

	/**
	 * 根据年份和月份得到 规定年规定月的天数
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getMonthDays(int year, int month) {
		if (month > 12) {
			month = 1;
			year += 1;
		} else if (month < 1) {
			month = 12;
			year -= 1;
		}
		int[] arr = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int days = 0;

		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			arr[1] = 29; // 闰年2月29天
		}

		try {
			days = arr[month - 1];
		} catch (Exception e) {
			e.getStackTrace();
		}

		return days;
	}

	/**
	 * 得到年份
	 * @return
	 */
	public static int getYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * 得到月份
	 * @return
	 */
	public static int getMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到当天是几号
	 * @return
	 */
	public static int getCurrentMonthDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到当天是周几
	 * @return
	 */
	public static int getWeekDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 得到当时是几点
	 * @return
	 */
	public static int getHour() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 得到当时是几分
	 * @return
	 */
	public static int getMinute() {
		return Calendar.getInstance().get(Calendar.MINUTE);
	}
	
	/**
	 * 得到下个周日
	 * @return
	 */
	public static CustomDate getNextSunday() {

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 7 - getWeekDay()+1);
		CustomDate date = new CustomDate(c.get(Calendar.YEAR),
				c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH));
		return date;
	}

	/**
	 * 获得当天的 年、月、日，并存储在int数组里面
	 * @param year
	 * @param month
	 * @param day
	 * @param pervious
	 * @return
	 */
	public static int[] getWeekSunday(int year, int month, int day, int pervious) {
		int[] time = new int[3];
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);
		c.add(Calendar.DAY_OF_MONTH, pervious);
		time[0] = c.get(Calendar.YEAR);
		time[1] = c.get(Calendar.MONTH )+1;
		time[2] = c.get(Calendar.DAY_OF_MONTH);
		return time;

	}

	/**
	 * 传入年份和月份 得到某一天是周几
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getWeekDayFromDate(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getDateFromString(year, month));
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week_index < 0) {
			week_index = 0;
		}
		return week_index;
	}

	/**
	 * 传入日期对象，得到某一天是周几
	 * @param date
	 * @return
	 */
	public static int getWeekDayFromDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 2;
		if (week_index < 0) {
			week_index = 0;
		}
		return week_index;
	}

	/**
	 * 将整型的年和月转化为日期类型
	 * @param year
	 * @param month
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static Date getDateFromString(int year, int month) {
		String dateString = year + "-" + (month > 9 ? month : ("0" + month))
				+ "-01";
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return date;
	}

	/**
	 * 传入自定义的日期类型，判断是否是当天
	 * @param CustomDate
	 * @return 返回true说明是今天，返回false说明不是今天
	 */
	public static boolean isToday(CustomDate date){
		return(date.year == DateUtil.getYear() &&
				date.month == DateUtil.getMonth() 
				&& date.day == DateUtil.getCurrentMonthDay());
	}

	public static boolean isCurrentMonth(CustomDate date){
		return(date.year == DateUtil.getYear() &&
				date.month == DateUtil.getMonth());
	}


	/**
	 * 将Date日期对象转化为  "yyyy年MM月dd日 HH:mm" 格式的字符串
	 * @param Date
	 * @return
	 */
	@SuppressLint("SimpleDateFormat") 
	public static String getStringFromDate(Date date){
		String dateString="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		dateString=sdf.format(date);
		return dateString;
	}

	/**
	 * 将 "yyyy年MM月dd日 HH:mm" 格式的字符串 转化为Date 日期对象
	 * @param dateString
	 * @return
	 */
	public static Date getDateFromString(String dateString){
		//System.out.println(dateString);
		String datestring=StringUtil.getString(dateString);
		if(datestring.equals(""))
			return new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		Date date = new Date();
		try {
			//	System.out.println(dateString);
			date = sdf.parse(dateString.toString().trim());
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 判断两个Date 对象是否是同一天
	 * @param date1
	 * @param date2
	 * @return 返回true，则date1和date2就是同一天，返回false，则不是同一天
	 */
	public static boolean compareDates(Date date1,Date date2){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String d1=sdf.format(date1);
		String d2=sdf.format(date2);
		return d1.equals(d2);
	}

	/**
	 * 得到前n天
	 * @param date 
	 * @param int
	 * @return
	 */
	public static Date getBackday(Date date,int n){
		for(int i=0;i<n;i++)
			date.setDate(date.getDate()-1);
		return date;
	}

	/**
	 * 得到未来n天的日期
	 * @param date
	 * @return
	 */
	public static Date getForword(Date date,int n){
		for(int i=0;i<n;i++)
			date.setDate(date.getDate()+1);
		return date;
	}
}
