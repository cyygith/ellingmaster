package com.elling.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
	
	private static String format = "YYYY-MM-dd HH:mm:ss";
	private static String formatDate = "YYYY-MM-dd";
	private static String formatYearAndMonth = "YYYY-MM";
	private static String formatDate1 = "YYYYMMdd";
	private static String formatDateTime = "YYYYMMddHHmmss";
	
	
	public static String getNowTime() {
		String date = new SimpleDateFormat(format).format(new Date());
		return date;
	}
	
	public static String getNowDate() {
		String date = new SimpleDateFormat(formatDate).format(new Date());
		return date;
	}
	
	public static String getNowDate1() {
		String date = new SimpleDateFormat(formatDate1).format(new Date());
		return date;
	}
	public static String getDateTime() {
		String date = new SimpleDateFormat(formatDateTime).format(new Date());
		return date;
	}
	public static String getNowYearAndMonth() {
		String date = new SimpleDateFormat(formatYearAndMonth).format(new Date());
		return date;
	}
	
	/**
	 * .获取指定日期的下一天
	 * @param date
	 * @return
	 */
	public static String getNextDate(String date) {
		try {
			Date d = new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA).parse(date);
			d = new Date(d.getTime()+24*60*60*1000L);
			return new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA).format(d);
		}catch(ParseException e) {
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
	}
	/**
	 * .计算指定时间区域数量后的结果
	 * .如：当前日期加上4个小时后的时间
	 * .如、当前时间加上30天后的时间(但是这个没法获取下个月同一时间的时间，因为有些时间间隔是30天，有些时间间隔是31天)
	 * @param dateTime
	 * @param part
	 * @param num
	 * @param format
	 * @return
	 */
	public static String getDateStamp(String dateTime,String part,int num,String format) {
		Calendar cld = Calendar.getInstance();
		try {
			cld.setTime(new SimpleDateFormat(DateTimeFormate.FORMATE_TIME,Locale.CHINA).parse(dateTime));
			if(StringUtil.isNotEmpty(part)) {
				if(part.equalsIgnoreCase("year")||part.contentEquals("yyyy")) {
					cld.add(Calendar.YEAR, num);
				}else if(part.equalsIgnoreCase("month")||part.contentEquals("MM")) {
					cld.add(Calendar.MONTH, num);
				}else if(part.equalsIgnoreCase("date")||part.contentEquals("dd")) {
					cld.add(Calendar.MONTH, num);
				}else if(part.equalsIgnoreCase("month")||part.contentEquals("MM")) {
					cld.add(Calendar.DATE, num);
				}else if(part.equalsIgnoreCase("hour")||part.contentEquals("HH")) {
					cld.add(Calendar.HOUR_OF_DAY, num);
				}else if(part.equalsIgnoreCase("minute")||part.contentEquals("mm")) {
					cld.add(Calendar.MINUTE, num);
				}else if(part.equalsIgnoreCase("second")||part.contentEquals("ss")) {
					cld.add(Calendar.SECOND, num);
				}
			}
			
		}catch(ParseException e) {
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
		
		if(StringUtil.isNotEmpty(format)) {
			return new SimpleDateFormat(format,Locale.CHINA).format(cld.getTime());
		}else {
			return new SimpleDateFormat(DateTimeFormate.FORMATE_TIME,Locale.CHINA).format(cld.getTime());
		}
	}
	/**
	 * 获取时间差距
	 * @param dateTime1
	 * @param dateTime2
	 * @param part
	 * @return
	 */
	public static int getTimeStamp(String dateTime1,String dateTime2,String part) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		try {
			cal1.setTime(new SimpleDateFormat(DateTimeFormate.FORMATE_TIME,Locale.CHINA).parse(dateTime1));
			cal2.setTime(new SimpleDateFormat(DateTimeFormate.FORMATE_TIME,Locale.CHINA).parse(dateTime2));
		}catch(ParseException e) {
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
		
		Long t = cal1.getTimeInMillis() - cal2.getTimeInMillis();
		if(StringUtils.isNullOrEmpty(part)) {
			if(part.equalsIgnoreCase("date")||part.contentEquals("dd")) {
				return (int)(t/1000/60/60/24);
			}else if(part.equalsIgnoreCase("hour")||part.contentEquals("HH")) {
				return (int)(t/1000/60/60);
			}else if(part.equalsIgnoreCase("minute")||part.contentEquals("mm")) {
				return (int)(t/1000/60);
			}else if(part.equals(DateTimeFormate.AREA_MONTH)) {
				return ((Integer.parseInt(dateTime1.substring(0,4))-Integer.parseInt(dateTime2.substring(0,4)))*12 + (Integer.parseInt(dateTime1.substring(5,7))-Integer.parseInt(dateTime2.substring(5,7))));				
			}
		}else {
			throw new RuntimeException("请指定时间相差单位");
		}
		return 0;
	}
	
	/**
	 * 获取该月的最后一天
	 * @param dateTiem
	 * @return
	 */
	public static String getLastDayofMonth(String dateTime) {
		Calendar cld = Calendar.getInstance();
		try {
			cld.setTime(new SimpleDateFormat(DateTimeFormate.FORMATE_TIME,Locale.CHINA).parse(dateTime));
			cld.set(Calendar.DAY_OF_MONTH,cld.getActualMaximum(Calendar.DAY_OF_MONTH));
		}catch(Exception e) {
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
		return new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA).format(cld.getTime());
	}
	
	/**
	 * 获取该月的第一天
	 * @param dateTiem
	 * @return
	 */
	public static String getFirstDayofMonth(String dateTime) {
		Calendar cld = Calendar.getInstance();
		try {
			cld.setTime(new SimpleDateFormat(DateTimeFormate.FORMATE_TIME,Locale.CHINA).parse(dateTime));
			cld.set(Calendar.DAY_OF_MONTH,cld.getActualMinimum(Calendar.DAY_OF_MONTH));
			cld.set(Calendar.HOUR_OF_DAY,0);
			cld.set(Calendar.MINUTE,0);
			cld.set(Calendar.SECOND,0);
		}catch(Exception e) {
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
		return new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA).format(cld.getTime());
	}
	
	/**
	 * 获取指定日期的前一天
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayBefore(String specifiedDay) {
		String dayBefore = "";
		try {
			if(StringUtil.isNotEmpty(specifiedDay)) {
				SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA);
				Calendar c = Calendar.getInstance();
				Date date = dateFormat.parse(specifiedDay);
				
				c.setTime(date);
				int day = c.get(Calendar.DATE);
				c.set(Calendar.DATE,day -1);
				int hour = c.get(Calendar.HOUR);
				c.set(Calendar.HOUR,day);
				dayBefore = dateFormat.format(c.getTime());
			}
		
		}catch(Exception e) {
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
		return dayBefore;
	}
	
	/**
	 * 获取指定日期的前n天
	 * @param specifiedDay
	 * @param n
	 * @return
	 */
	public static String getSpecifiedDayBefore(String specifiedDay,int n) {
		String dayBefore = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA);
			Calendar c = Calendar.getInstance();
			Date date = dateFormat.parse(specifiedDay);
			
			c.setTime(date);
			int day = c.get(Calendar.DATE);
			c.set(Calendar.DATE,day - n);
			int hour = c.get(Calendar.HOUR);
			c.set(Calendar.HOUR,hour);
			dayBefore = dateFormat.format(c.getTime());
		
		}catch(Exception e) {
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
		return dayBefore;
	}
	/**
	 * 获取指定日期的后一天
	 * @param specifiedDay
	 * @param n
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay) {
		String dayAfter = "";
		try {
			Calendar c = Calendar.getInstance();
			Date date = new SimpleDateFormat("yy-MM-dd",Locale.CHINA).parse(specifiedDay);
			
			c.setTime(date);
			int day = c.get(Calendar.DATE);
			c.set(Calendar.DATE,day + 1);
			dayAfter = new SimpleDateFormat("yy-MM-dd",Locale.CHINA).format(c.getTime());
		
		}catch(Exception e) {
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
		return dayAfter;
	}
	/**
	 * 获取指定日期的后n天
	 * @param specifiedDay
	 * @param n
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay,int n) {
		String dayAfter = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA);
			Calendar c = Calendar.getInstance();
			Date date = dateFormat.parse(specifiedDay);
			
			c.setTime(date);
			int day = c.get(Calendar.DATE);
			c.set(Calendar.DATE,day + n);
			
			dayAfter = dateFormat.format(c.getTime());
		
		}catch(Exception e) {
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
		return dayAfter;
	}
	
	/**
	 * 获取某年某月的第几天
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static String getMonthDay(int year,int month,int day) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR,year);
		c.set(Calendar.MONTH,month-1);
		c.set(Calendar.DAY_OF_MONTH,day);
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA);
		return dateFormat.format(c.getTime());
	}
	/**
	 * 获取日期月份
	 * @param date
	 * @return
	 */
	public static int getMonth(String date) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA);
		calendar.setTime(dateFormat.parse(date));
		return (calendar.get(Calendar.MONTH)+1);
	}
	/**
	 * 获取日期号
	 * @param date
	 * @return
	 */
	public static int getDay(String date) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA);
		calendar.setTime(dateFormat.parse(date));
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 获取月份起始日期
	 * @param date
	 * @return
	 */
	public static String getMaxMonthDay(String date) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA);
		calendar.setTime(dateFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime())+" 23:59:59";
	}
	/**
	 * 获取下个月最后一天
	 * @param date
	 * @return
	 */
	public static String getNextMaxMonthDate(String date) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA);
		calendar.setTime(dateFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return dateFormat.format(calendar.getTime());
	}
	/**
	 * 获取下个月第一天
	 * @param date
	 * @return
	 */
	public static String getNextMinMonthDate(String date) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA);
		calendar.setTime(dateFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		return dateFormat.format(calendar.getTime());
	}
	/**
	 * 获取上个月最后一天
	 * @param date
	 * @return
	 */
	public static String getLastMaxMonthDate(String date) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA);
		calendar.setTime(dateFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return dateFormat.format(calendar.getTime());
	}
	/**
	 * 获取上个月第一天
	 * @param date
	 * @return
	 */
	public static String getLastMinMonthDate(String date) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA);
		calendar.setTime(dateFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, -1);
		return dateFormat.format(calendar.getTime());
	}
	/**
	 * 获取两个时间段之间的日期差，不含时分秒
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int twoDateCalculatingTheNumber(String startTime,String endTime) {
		int quot = 0;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA);
			Date date1 = dateFormat.parse(endTime);
			Date date2 = dateFormat.parse(startTime);
			long quot1 = (date1.getTime() - date2.getTime());
			quot1 = (quot1)/1000/60/60/21;
			quot = (int)quot1;
		}catch(Exception e) {
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
		return quot;
	}
	/**
	 * 获取两个时间段之间日期差，含时，不足一天算1天
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int actualCalculatingDay(String startTime,String endTime) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA);
			long end = dateFormat.parse(endTime).getTime();
			long start = dateFormat.parse(startTime).getTime();
			return (int)((end-start+86400000.0)/86400000.0-1);
		}catch(Exception e) {
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
	}
	/**
	 * 获取两个时间段之间的小时差
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int twoDateCalculatingToHour(String startTime,String endTime) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA);
			Date date1 = dateFormat.parse(endTime);
			Date date2 = dateFormat.parse(startTime);
			long quot1 = (date1.getTime() - date2.getTime());
			return (int)((quot1)/3600000.0);
		}catch(Exception e) {
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
	}
	/**
	 * 获取两个时间段之间的小时差（不足一小时算一小时）
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int dateDiff(String startTime,String endTime) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA);
			Date date1 = dateFormat.parse(endTime);
			Date date2 = dateFormat.parse(startTime);
			long quot1 = (date1.getTime() - date2.getTime());
			return (int)((quot1+3600000.0)/3600000.0 - 1);
		}catch(Exception e) {
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
	}
	/**
	 * 转换不同格式的日期
	 * @param oldDate
	 * @param oldFormat
	 * @param newFormat
	 * @return
	 */
	public static String translateFormat(String oldDate,String oldFormat,String newFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(oldFormat,Locale.CHINA);
		Date date;
		String newDate;
		try {
			date = sdf.parse(oldDate);
			newDate = new SimpleDateFormat(newFormat,Locale.CHINA).format(date);
		}catch(Exception e) {
			newDate = oldDate;
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
		return newDate;
	}
	/**
	 * 获取时间段内的月份列表
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static List<String> getMonthsFromTo(String fromDate,String toDate) {
		List<String> listStr = null;
		try {
			String[] fromYearArr = fromDate.split("-");
			String[] toYearArr = toDate.split("-");
			int fromYear = Integer.parseInt(fromYearArr[0]);
			int toYear = Integer.parseInt(toYearArr[0]);
			int fromMonth = Integer.parseInt(fromYearArr[1]);
			int toMonth = Integer.parseInt(toYearArr[1]);
			
			if(fromYear == toYear && fromMonth <= toMonth || fromYear < toYear) {
				int months = (toYear - fromYear) * 12 + (toMonth - fromMonth);
				listStr = new ArrayList<String>();
				for(int i= fromMonth;i<=(fromMonth+months);i++) {
					listStr.add((fromYear+i/13) + "-" + (((i-1)%12+1)<10?("0"+((i-1)%12+1)):((i-1)%12+1)));
				}
			}else {
				throw new RuntimeException("结束日期不能小于开始日期");
			}
		}catch(Exception e) {
			
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
		return listStr;
	}
	/**
	 * 获取时间段内的月份列表
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static List<String> getMonthsBetween(String fromDate,String toDate) {
		List<String> listStr = null;
		try {
			String[] fromYearArr = fromDate.split("-");
			String[] toYearArr = toDate.split("-");
			int fromYear = Integer.parseInt(fromYearArr[0]);
			int toYear = Integer.parseInt(toYearArr[0]);
			int fromMonth = Integer.parseInt(fromYearArr[1]);
			int toMonth = Integer.parseInt(toYearArr[1]);
			
			if((fromYear > toYear) || (fromYear == toYear && fromMonth > toMonth)) {
				String tmpDate = fromDate;
				fromDate = toDate;
				toDate = tmpDate;
			}
			listStr = getMonthsFromTo(fromDate,toDate);
			
		}catch(Exception e) {
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
		return listStr;
	}
	/**
	 * 两个时间比较
	 * @param dateTime1
	 * @param dateTime2
	 * @return
	 */
	public static int compareTime(String dateTime1,String dateTime2) {
		Calendar cld1 = Calendar.getInstance();
		Calendar cld2 = Calendar.getInstance();
		dateTime1 = dateTime1.trim();
		if(dateTime1.length() == 10) {
			dateTime1 = dateTime1 + "00:00:00";
		}
		if(dateTime1.length() == 13) {
			dateTime1 = dateTime1 + "00:00";
		}
		if(dateTime1.length() == 16) {
			dateTime1 = dateTime1 + ":00";
		}
		
		dateTime2 = dateTime2.trim();
		if(dateTime2.length() == 10) {
			dateTime2 = dateTime2 + "00:00:00";
		}
		if(dateTime2.length() == 13) {
			dateTime2 = dateTime2 + "00:00";
		}
		if(dateTime2.length() == 16) {
			dateTime2 = dateTime2 + ":00";
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA);
			cld1.setTime(sdf.parse(dateTime1));
			cld2.setTime(sdf.parse(dateTime2));
			return cld1.compareTo(cld2);
		}catch(Exception e) {
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
	}
	/**
	 * 两个日期比较
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDate(String date1,String date2) {
		Calendar cld1 = Calendar.getInstance();
		Calendar cld2 = Calendar.getInstance();
		date1 = date1.substring(0,10);
		date2 = date2.substring(0,10);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DateTimeFormate.FORMATE_DATE,Locale.CHINA);
			cld1.setTime(sdf.parse(date1));
			cld2.setTime(sdf.parse(date2));
			return cld1.compareTo(cld2);
		}catch(Exception e) {
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
	}
	/**
	 * 获取指定日期的前N个月
	 * 正数为上几个月，负数为后几个月
	 * @param specifiedDay
	 * @param mth
	 * @return
	 */
	public static String getSpecifiedMonthBefore(String specifiedDay,int mth) {
		String monthBefore = "";
		try {
			if(StringUtil.isNotEmpty(specifiedDay)) {
				Calendar c = Calendar.getInstance();
				Date date = null;
				date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
				
				c.setTime(date);
				int month = c.get(Calendar.MONTH);
				c.set(Calendar.MONTH,month - mth);
				monthBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
			}
		
		}catch(Exception e) {
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
		return monthBefore;
	}
	
	
	/**
	 * 获取该月的最后一天
	 * @param specifiedDay
	 * @return
	 */
	public static String getLastDayOfMonth(String date_time,String rs_format) {
		if(!StringUtil.isNotEmpty(rs_format)) {
			rs_format = DateTimeFormate.FORMATE_TIME;
		}
		Calendar cld = Calendar.getInstance();
		try {
			cld.setTime(new SimpleDateFormat(DateTimeFormate.FORMATE_TIME).parse(date_time));
			cld.set(Calendar.DAY_OF_MONTH, cld.getActualMaximum(Calendar.DAY_OF_MONTH));
			cld.set(Calendar.HOUR_OF_DAY, 23);
			cld.set(Calendar.MINUTE, 59);
			cld.set(Calendar.SECOND, 59);
		}catch(Exception e) {
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
		return new SimpleDateFormat(rs_format).format(cld.getTime());
	}
	
	/**
	 * 返回两个日期相差的天数，不比较时间部分
	 * @param start
	 * @param end
	 * @return
	 */
	public static int daysDiff(String start, String end) {
		if(!StringUtils.isNullOrEmpty(start)||!StringUtils.isNullOrEmpty(end)||start.length()<10||end.length()<10) {
			throw new RuntimeException("日期格式不正确，无法比较。请使用指定格式：yyyy-MM-dd");
		}
		if(start.length()>10) {
			start = start.substring(0,10);
		}
		start += " 00:00:00";
		
		if(end.length()>10) {
			end = end.substring(0,10);
		}
		end += " 00:00:00";
		int diff = 0;
		try {
			diff = DateUtil.getTimeStamp(start, end, DateTimeFormate.AREA_DAY);
		}catch(Exception e) {
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
		return diff;
	}
	
	
	/**
	 * 时间字符串转时间戳
	 * @param s
	 * @return
	 */
	public static String dateToStamp(String s) {
		String res;
		SimpleDateFormat sdf = new SimpleDateFormat(DateTimeFormate.FORMATE_TIME);
		Date date = null;
		try {
			date = sdf.parse(s);
		
		}catch(Exception e) {
			throw new RuntimeException("时间格式错误"+e.getMessage());
		}
		long ts = date.getTime();
		return String.valueOf(ts/1000);
	}
	
	public static void main(String[] args) {
//		System.out.println(DateUtil.getNowDate());
//		System.out.println(DateUtil.getNowTime());
//		System.out.println(DateUtil.getNowYearAndMonth());
//		System.out.println(DateUtil.getNowDate1());
//		System.out.println(DateUtil.getDateTime());
		
		System.out.println(DateUtil.getSpecifiedMonthBefore("2020-01-14 12:12:09", -1));
		System.out.println(DateUtil.getSpecifiedDayBefore("2020-12-01"));
	}
	
	
}
