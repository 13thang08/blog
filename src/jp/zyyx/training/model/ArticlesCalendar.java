package jp.zyyx.training.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class ArticlesCalendar {
	public static final String yearMonthFormat = "yyyy-MM";
	String firstMonth = null;
	String lastMonth = null;
	String currentMonth = null;
	int firstDayOfWeek = 0;
	int lastDayOfMonth = 0;
	HashSet<Integer> articleDays;
	
	public ArticlesCalendar() {
		articleDays = new HashSet<Integer>();
	}
	public String getFirstMonth() {
		return firstMonth;
	}
	public void setFirstMonth(String firstMonth) {
		this.firstMonth = firstMonth;
	}
	public String getLastMonth() {
		return lastMonth;
	}
	public void setLastMonth(String lastMonth) {
		this.lastMonth = lastMonth;
	}
	public String getCurrentMonth() {
		return currentMonth;
	}
	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}
	public int getFirstDayOfWeek() {
		return firstDayOfWeek;
	}
	public void setFirstDayOfWeek(int firstDayOfWeek) {
		this.firstDayOfWeek = firstDayOfWeek;
	}
	public int getLastDayOfMonth() {
		return lastDayOfMonth;
	}
	public void setLastDayOfMonth(int lastDayOfMonth) {
		this.lastDayOfMonth = lastDayOfMonth;
	}
	public HashSet<Integer> getArticleDays() {
		return articleDays;
	}
	public void setArticleDays(HashSet<Integer> articleDays) {
		this.articleDays = articleDays;
	}
	public static String getYearmonthformat() {
		return yearMonthFormat;
	}
	
	public boolean addArticleDay(int day) {
		return articleDays.add(day);
	}
	
	public String toString() {
		String ret = null;
		ret = "First month: " + firstMonth + "\n";
		ret += "Last month: " + lastMonth + "\n";
		ret += "Current month: " + currentMonth + "\n";
		ret += "First day of week: " + firstDayOfWeek + "\n";
		ret += "Last day of month: " + lastDayOfMonth + "\n";
		ret += "Rows: " + getRows() + "\n";
		ret += "Days article: ";
		for (int day : articleDays) {
			ret += day + " ";
		}
		return ret;
	}
	
	public int getRows() {
		return (int) Math.ceil((double) (lastDayOfMonth + firstDayOfWeek - 1) / 7);
	}
	
	public String getPreviousMonth() {
		SimpleDateFormat yearMonth = new SimpleDateFormat(yearMonthFormat);
		try {
			Date date = yearMonth.parse(currentMonth);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, -1);
			date = calendar.getTime();
			String previousMonth = yearMonth.format(date);
			if (previousMonth.compareTo(firstMonth) >= 0 ) {
				return previousMonth;
			} else {
				return null;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String getNextMonth() {
		SimpleDateFormat yearMonth = new SimpleDateFormat(yearMonthFormat);
		try {
			Date date = yearMonth.parse(currentMonth);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, 1);
			date = calendar.getTime();
			String nextMonth = yearMonth.format(date);
			if (nextMonth.compareTo(lastMonth) <= 0) {
				return nextMonth;
			} else {
				return null;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
