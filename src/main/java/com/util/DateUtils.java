package com.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	public enum DayOfWeek {
		SUN, MON, TUE, WED, THU, FRI, SAT
	}

	public enum DateType {
		YEAR, MONTH, DAY
	}

	public static int diffDays(Date startYear, Date endYear) {
		try {
			long df = (endYear.getTime() - startYear.getTime()) / (1000 * 60 * 60 * 24);

			return (int) df;
		} catch (Exception e) {
			return -1;
		}
	}

	public static int diffHours(Date startYear, Date endYear) {
		try {
			long df = (endYear.getTime() - startYear.getTime()) / (1000 * 60 * 60);

			return (int) df;
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * <p>
	 * 입력받은 날짜의 형식을 확인한다.
	 * </p>
	 * 
	 * <pre>
	 * - 사용 예
	 * boolean isCheck = DateUtil.checkDate("201105", "yyyyMM");
	 * 결과 : true
	 * 결과가 false 이면 날짜 형식 문제
	 * </pre>
	 * 
	 * @param value
	 *            확인할 날짜값
	 * @param formatValue
	 *            검사할 날짜 형식
	 * @return true 면 정상 false 이면 날짜 형식 문제
	 */
	public static boolean checkDate(String value, String formatValue) {
		try {
			if (StringUtils.isEmpty(value) || StringUtils.isEmpty(formatValue))
				return false;

			if (value.length() != formatValue.length())
				return false;

			SimpleDateFormat dateFormat = new SimpleDateFormat(formatValue);
			Date valueDate = dateFormat.parse(value);

			return (valueDate == null) ? false : true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * <p>
	 * 입력받은 날짜의 형식으로 리턴한다.
	 * </p>
	 * 
	 * <pre>
	 * - 사용 예
	 * Date date = DateUtil.getDateFormat("201105", "yyyyMM");
	 * 결과 : true
	 * 결과가 false 이면 날짜 형식 문제
	 * </pre>
	 * 
	 * @param value
	 *            변환할 날짜값
	 * @param formatValue
	 *            변환할 날짜 형식
	 * @return 형식에 문제가 없으면 Date 로 리턴, 아니면 null 리턴
	 */
	public static Date getDateFormat(String value, String formatValue) {
		try {
			if (StringUtils.isEmpty(value) || StringUtils.isEmpty(formatValue))
				return null;

			if (value.length() != formatValue.length())
				return null;

			SimpleDateFormat dateFormat = new SimpleDateFormat(formatValue);
			Date valueDate = dateFormat.parse(value);

			return valueDate;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 현재 날짜가 기준일 이전인지 체크
	 * 
	 * @param endDay
	 *            (ex : 20140417)
	 * @return
	 */
	public static boolean isBeforeDay(String endDay) {

		try {
			Date nowTime = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
			Date endDate = formater.parse(formater.format(nowTime));

			if (endDate.before(formater.parse(endDay)))
				return true;
			else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * <p>
	 * Date형 날짜를 String형으로 변환
	 * </p>
	 * 
	 * <pre>
	 * - 사용 예
	 * Date date = DateUtil.stringToDate(Date date, "yyyy-MM-dd");
	 * 결과 : Date형식 날짜
	 * 결과가 null이면 오류
	 * </pre>
	 * 
	 * @param value
	 *            변환할 날짜값
	 * @param formatValue
	 *            변환 날짜 형식
	 * @return null 이면 오류
	 */
	public static String dateToString(Date value, String formatValue) {
		return dateToString(value, formatValue, null);
	}

	public static String dateToString(Date value, String formatValue, Locale fromLocale) {
		try {
			SimpleDateFormat dateFormat = null;
			if (fromLocale != null)
				dateFormat = new SimpleDateFormat(formatValue, fromLocale);
			else
				dateFormat = new SimpleDateFormat(formatValue);
			return dateFormat.format(value);

		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * <p>
	 * String형 날짜를 Date형으로 변환
	 * </p>
	 * 
	 * <pre>
	 * - 사용 예
	 * Date date = DateUtil.stringToDate("2011-01-02", "yyyy-MM-dd");
	 * 결과 : Date형식 날짜
	 * 결과가 null이면 오류
	 * </pre>
	 * 
	 * @param value
	 *            변환할 날짜값
	 * @param formatValue
	 *            변환 날짜 형식
	 * @return null 이면 오류
	 */
	public static Date stringToDate(String value, String formatValue) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(formatValue);
			return dateFormat.parse(value);

		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * <pre>
	 * 문자열 형태의 날짜를 원하는 형태로 변환합니다.
	 * 
	 * 예시)
	 * “yyyy.MM.dd G ‘at’ HH:mm:ss z”	2001.07.04 AD at 12:08:56 PDT
	 * “EEE, MMM d, ”yy”	Wed, Jul 4, ’01
	 * “h:mm a”	12:08 PM
	 * “hh ‘o”clock’ a, zzzz”	12 o’clock PM, Pacific Daylight Time
	 * “K:mm a, z”	0:08 PM, PDT
	 * “yyyyy.MMMMM.dd GGG hh:mm aaa”	02001.July.04 AD 12:08 PM
	 * “EEE, d MMM yyyy HH:mm:ss Z”	Wed, 4 Jul 2001 12:08:56 -0700
	 * “yyMMddHHmmssZ”	010704120856-0700
	 * “yyyy-MM-dd’T’HH:mm:ss.SSSZ”	2001-07-04T12:08:56.235-0700
	 * </pre>
	 * 
	 * @param date
	 *            변환할 날짜
	 * @param fromFormatString
	 *            변환될 포맷
	 * @param toFormatString
	 *            변환할 포맷
	 * @return 변환된 날짜 문자열
	 */
	public static String formattedDate(String date, String fromFormatString, String toFormatString, Locale fromLocale) {
		SimpleDateFormat fromFormat = null;
		if (fromLocale != null)
			fromFormat = new SimpleDateFormat(fromFormatString, fromLocale);
		else
			fromFormat = new SimpleDateFormat(fromFormatString);

		SimpleDateFormat toFormat = new SimpleDateFormat(toFormatString);
		Date fromDate = null;

		try {
			fromDate = fromFormat.parse(date);
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		return toFormat.format(fromDate);
	}

	public static Date getYesterday() {
		Date today = new Date();
		Date yesterday = new Date();
		yesterday.setTime(today.getTime() - ((long) 1000 * 60 * 60 * 24));
		return yesterday;
	}

	/**
	 * 시:분:초(01:20:30) 형태의 시간을 초단위로 변경한다.
	 * 
	 * @param time
	 * @return
	 */
	public static int getTimeToSecond(String time) {
		String[] arr = time.split("[:]");
		int second = 0;
		if (arr != null && arr.length > 0) {
			if (arr.length == 1) {
				second = Integer.parseInt(arr[0]);
			} else if (arr.length == 2) {
				second = (Integer.parseInt(arr[0]) * 60) + Integer.parseInt(arr[1]);
			} else if (arr.length == 3) {
				second = (Integer.parseInt(arr[0]) * 60 * 60) + (Integer.parseInt(arr[1]) * 60) + Integer.parseInt(arr[2]);
			}
		}
		return second;
	}

	/**
	 * startDay < now <= endDay
	 * 
	 * @param startDay
	 * @param endDay
	 * @return
	 */
	public static boolean isBetweenDay(String startDay, String endDay) {
		try {
			Date nowTime = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
			Date endDate = addDay(formater.parse(endDay), 1);
			if (nowTime.after(formater.parse(startDay)) && nowTime.before(endDate))
				return true;
			else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private static final Date addDate(Date date, int field, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);
		return cal.getTime();
	}

	public static Date addYear(Date date, int amount) {
		return addDate(date, 1, amount);
	}

	public static Date addMonth(Date date, int amount) {
		return addDate(date, 2, amount);
	}

	public static Date addDay(Date date, int amount) {
		return addDate(date, 5, amount);
	}

	public static Date addHour(Date date, int amount) {
		return addDate(date, 10, amount);
	}

	public static Date addMinute(Date date, int amount) {
		return addDate(date, 12, amount);
	}

	public static Date addSecond(Date date, int amount) {
		return addDate(date, 13, amount);
	}

	public static String formattedDateToStr(String date, Locale locale) {
		String[] possibleFormats = new String[] { "EEE, d MMM yyyy HH:mm:ss Z", "EEE, d MMM yyyy HH:mm:ssZ", "EEE, d MMM yyyy HH:mm Z", "EEE, d  MMM yyyy HH:mm:ss Z",
				"EEE,d MMM yyyy HH:mm:ss Z", "EEE,d MMM yyyy HH:mm Z", "EEE, d MMM yyyy HH:mm:ss", "EEE d MMM yyyy HH:mm:ss Z", "EEE d  MMM yyyy HH:mm:ss Z",
				"EEE MMM d yyyy HH:mm:ss", "EEE, MMM d yyyy HH:mm:ss", "EEE, d MMM yyyy", "d MMM yyyy HH:mm:ss Z", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" };

		String day = "";
		for (String f : possibleFormats) {
			try {
				day = DateUtils.formattedDate(date, f, "yyyy-MM-dd HH:mm:ss", locale);
				if (StringUtils.isNotEmpty(day) && day.length() == 19)
					return day;
			} catch (Exception e) {
			}
		}
		if (StringUtils.isEmpty(day))
			System.out.println("Error Format Date :" + date);

		return day;
	}

	public static String dayOfWeek() {
		String day = "";
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int dayNum = cal.get(Calendar.DAY_OF_WEEK); // 요일을 구해온다. 

			switch (dayNum) {
			case 1:
				day = DayOfWeek.SUN.name();
				break;
			case 2:
				day = DayOfWeek.MON.name();
				break;
			case 3:
				day = DayOfWeek.TUE.name();
				break;
			case 4:
				day = DayOfWeek.WED.name();
				break;
			case 5:
				day = DayOfWeek.THU.name();
				break;
			case 6:
				day = DayOfWeek.FRI.name();
				break;
			case 7:
				day = DayOfWeek.SAT.name();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return day;
	}

	/**
	 * 특정 시간이 현시간 기준으로 년/월/일 지정 시간을 경과했는지 확인한다.
	 *
	 * @param targetDate
	 * @param dateType
	 * @param amount
	 * @return
	 */
	public static boolean isDateOverdue(Date targetDate, DateType dateType, int amount) {
		if (targetDate == null || dateType == null || amount == 0)
			return false;

		boolean result = false;
		Date pastOfNow;
		amount *= -1;

		switch (dateType) {
			case YEAR:
				pastOfNow = DateUtils.addDate(new Date(), 1, amount);
				break;
			case MONTH:
				pastOfNow = DateUtils.addDate(new Date(), 2, amount);
				break;
			case DAY:
				pastOfNow = DateUtils.addDate(new Date(), 5, amount);
				break;
			default:
				throw new UnsupportedOperationException("Only supports YEAR / MONTH / DAY");
		}

		if (targetDate.before(pastOfNow))
			result = true;

		return result;
	}

	/**
	 * 생일로 만나이 계산
	 *
	 * @param yyyyMMdd
	 * @return
	 */
	public static int getFullAge(String yyyyMMdd) {
		if (StringUtils.isEmpty(yyyyMMdd) || yyyyMMdd.length() != 8)
			return -1;

		int birthYear = Integer.valueOf(yyyyMMdd.substring(0, 4));
		int birthMonth = Integer.valueOf(yyyyMMdd.substring(4, 6));
		int birthDay = Integer.valueOf(yyyyMMdd.substring(6, 8));
		Calendar current = Calendar.getInstance();
		int currentYear = current.get(Calendar.YEAR);
		int currentMonth = current.get(Calendar.MONTH) + 1;
		int currentDay = current.get(Calendar.DAY_OF_MONTH);

		int age = currentYear - birthYear;
		// 생일 안 지난 경우 -1
		if (birthMonth * 100 + birthDay > currentMonth * 100 + currentDay)
			age--;

		return age;
	}
}