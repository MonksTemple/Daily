package com.example.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;

import android.annotation.SuppressLint;


public class DateUtil {

	public static String[] weekName = { "周日", "周一", "周二", "周三", "周四", "周五","周六" };

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
	
	public static int getYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public static int getMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public static int getCurrentMonthDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	public static int getWeekDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	}

	public static int getHour() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}
	public static int getMinute() {
		return Calendar.getInstance().get(Calendar.MINUTE);
	}
	public static CustomDate getNextSunday() {
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 7 - getWeekDay()+1);
		CustomDate date = new CustomDate(c.get(Calendar.YEAR),
				c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH));
		return date;
	}

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

	public static int getWeekDayFromDate(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getDateFromString(year, month));
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week_index < 0) {
			week_index = 0;
		}
		return week_index;
	}
	
	public static int getWeekDayFromDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 2;
		if (week_index < 0) {
			week_index = 0;
		}
		return week_index;
	}

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
	
	public static boolean isToday(CustomDate date){
		return(date.year == DateUtil.getYear() &&
				date.month == DateUtil.getMonth() 
				&& date.day == DateUtil.getCurrentMonthDay());
	}
	
	public static boolean isCurrentMonth(CustomDate date){
		return(date.year == DateUtil.getYear() &&
				date.month == DateUtil.getMonth());
	}
	
	
	@SuppressLint("SimpleDateFormat") 
	public static String getStringFromDate(Date date){
		String dateString="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		dateString=sdf.format(date);
		return dateString;
	}
	
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
	
	
	public static String changeDateFromString(String dateString){
		String datestring=StringUtil.getString(dateString);
		if(datestring.equals(""))
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd");
		Date date = new Date();
		try {
			date = sdf.parse(dateString.trim());
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return sdf1.format(date);
	}
	
	public static boolean compareDates(Date date1,Date date2){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String d1=sdf.format(date1);
		String d2=sdf.format(date2);
		return d1.equals(d2);
	}
	
	/**
	 * 得到前一天
	 * @param date 传入的日期
	 * @return
	 */
	public static Date getYesterday(Date date){
		date.setDate(date.getDate()-1);
		return date;
	}
	
	/**
	 * 得到明天的日期
	 * @param date
	 * @return
	 */
	public static Date getTomm(Date date){
		date.setDate(date.getDate()+1);
		return date;
	}

	public static String getDateBetween(String date1,String date2){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String dateBetween="";
		Date d1=getDateFromString(date1);
		Date d2=getDateFromString(date2);
		String da1=sdf.format(d1);
		String da2=sdf.format(d2);
		dateBetween=(da1+"~"+da2);
		return dateBetween;
	}
}
