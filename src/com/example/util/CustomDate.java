package com.example.util;

import java.io.Serializable;

/**
 * 自己定义一个日期类型 的类
 */
public class CustomDate implements Serializable{
	
	/**序列化ID**/
	private static final long serialVersionUID = 1L;
	/**
	 * 全局变量 年份
	 */
	public int year;
	/**
	 * 全局变量 月份
	 */
	public int month;
	/**
	 * 全局变量  天数
	 */
	public int day;
	/**
	 * 全局变量周
	 */
	public int week;
	
	public CustomDate(int year,int month,int day){
		if(month > 12){
			month = 1;
			year++;
		}else if(month <1){
			month = 12;
			year--;
		}
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public CustomDate(){
		this.year = DateUtil.getYear();
		this.month = DateUtil.getMonth();
		this.day = DateUtil.getCurrentMonthDay();
	}
	
	public static CustomDate modifiDayForObject(CustomDate date,int day){
		CustomDate modifiDate = new CustomDate(date.year,date.month,day);
		return modifiDate;
	}
	@Override
	public String toString() {
		return year+"-"+month+"-"+day;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

}
