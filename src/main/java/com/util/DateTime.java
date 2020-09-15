package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public final class DateTime {

	private DateTime() {}

	public static void check(String s) throws Exception {
		DateTime.check(s, "yyyy-MM-dd");
	}

	public static void check(String s, String format) throws java.text.ParseException {
		if ( s == null )
			throw new NullPointerException("date string to check is null");
		if ( format == null ) 
			throw new NullPointerException("format string to check date is null");

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
		java.util.Date date = null;
		try {
			date = formatter.parse(s);
		}
		catch(java.text.ParseException e) {
			throw new java.text.ParseException(
				e.getMessage() + " with format \"" + format + "\"",
				e.getErrorOffset()
			);
		}
		
		if ( ! formatter.format(date).equals(s) )
			throw new java.text.ParseException(
				"Out of bound date:\"" + s + "\" with format \"" + format + "\"",
				0
			);
	}

	/**
	 * @return formatted string representation of current day with  "yyyy-MM-dd".
	 */
	public static String getDateString() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat ("yyyy-MM-dd", java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}


	public static int getDay() {
		return getNumberByPattern("dd");
	}


	public static String getFormatString(String pattern) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat (pattern, java.util.Locale.KOREA);
		String dateString = formatter.format(new java.util.Date());
		return dateString;
	}

	
	public static int getMonth() {
		return getNumberByPattern("MM");
	}

	
	public static int getNumberByPattern(String pattern) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat (pattern, java.util.Locale.KOREA);
		String dateString = formatter.format(new java.util.Date());
		return Integer.parseInt(dateString);
	}

	
	public static String getShortDateString() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat ("yyyyMMdd", java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}

	
	public static String getShortTimeString() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat ("HHmmss", java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}


	public static String getTimeStampString() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat ("yyyy-MM-dd-HH:mm:ss:SSS", java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}

	
	public static String getTimeString() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat ("HH:mm:ss", java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}

	public static String getToday(String format){
		SimpleDateFormat sf = new SimpleDateFormat(format, Locale.KOREAN);
		return sf.format(new Date());
	}
	
	public static int getYear() {
		return getNumberByPattern("yyyy");
	}
	public static String format(Date dt,String format){
		SimpleDateFormat sf = new SimpleDateFormat(format, Locale.KOREAN);
		return sf.format(dt);		
	}
	public static String format(String source,String srcfmt,String tgtfmt) {
		SimpleDateFormat sf = new SimpleDateFormat(srcfmt, Locale.KOREAN);
		Date dt;
		try {
			dt = sf.parse(source);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		
		return format(dt,tgtfmt);
		
	}
}