package jp.zyyx.training.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

/**
 * カレンダーを定義するクラス
 * @author thangvm
 *
 */
public class ArticlesCalendar {
	
	/** 年月ストリングのフォマット */
	public static final String yearMonthFormat = "yyyy-MM";
	
	/** 最初の記事がある月 */
	String firstMonth = null;
	
	/** 最後の記事がある月 */
	String lastMonth = null;
	
	/** 表示したい月 */
	String currentMonth = null;
	
	/** 月の１日の曜日 */
	int firstDayOfWeek = 0;
	
	/** 月の最後の日（30、31…） */
	int lastDayOfMonth = 0;
	
	/** 記事がある各日 */
	HashSet<Integer> articleDays;
	
	/**
	 * コンストラクター
	 */
	public ArticlesCalendar() {
		articleDays = new HashSet<Integer>();
	}
	
	/**
	 * firstMonth ゲッター
	 * @return
	 */
	public String getFirstMonth() {
		return firstMonth;
	}
	
	/**
	 * firstMonth セッター
	 * @param firstMonth
	 */
	public void setFirstMonth(String firstMonth) {
		this.firstMonth = firstMonth;
	}
	
	/**
	 * lastMonth ゲッター
	 * @return
	 */
	public String getLastMonth() {
		return lastMonth;
	}
	
	/**
	 * lastMonth セッター
	 * @param lastMonth
	 */
	public void setLastMonth(String lastMonth) {
		this.lastMonth = lastMonth;
	}
	
	/**
	 * currentMonth ゲッター
	 * @return
	 */
	public String getCurrentMonth() {
		return currentMonth;
	}
	
	/**
	 * currentMonth セッター
	 * @param currentMonth
	 */
	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}
	
	/**
	 * firstDayOfWeek ゲッター
	 * @return
	 */
	public int getFirstDayOfWeek() {
		return firstDayOfWeek;
	}
	
	/**
	 * firstDayOfWeek セッター
	 * @param firstDayOfWeek
	 */
	public void setFirstDayOfWeek(int firstDayOfWeek) {
		this.firstDayOfWeek = firstDayOfWeek;
	}
	
	/**
	 * lastDayOfMonth ゲッター
	 * @return
	 */
	public int getLastDayOfMonth() {
		return lastDayOfMonth;
	}
	
	/**
	 * lastDayOfMonth セッター
	 * @param lastDayOfMonth
	 */
	public void setLastDayOfMonth(int lastDayOfMonth) {
		this.lastDayOfMonth = lastDayOfMonth;
	}
	
	/**
	 * articleDays ゲッター
	 * @return
	 */
	public HashSet<Integer> getArticleDays() {
		return articleDays;
	}
	
	/**
	 * articleDays セッター
	 * @param articleDays
	 */
	public void setArticleDays(HashSet<Integer> articleDays) {
		this.articleDays = articleDays;
	}
	
	/**
	 * articleDaysに記事を追加するメソッド
	 * @param day
	 * @return true 追加成功
	 *         false 追加失敗
	 */
	public boolean addArticleDay(int day) {
		return articleDays.add(day);
	}
	
	/**
	 * デバッグのため
	 */
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
	
	/**
	 * カレンダーを表示するための必要な行
	 * @return 行
	 */
	public int getRows() {
		return (int) Math.ceil((double) (lastDayOfMonth + firstDayOfWeek - 1) / 7);
	}
	
	/**
	 * 前の月を取る
	 * @return 前の月
	 *         なかったら、nullになる
	 */
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
	
	/**
	 * 次の月を取る
	 * @return 次の月
	 *         なかったらnullになる
	 */
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
