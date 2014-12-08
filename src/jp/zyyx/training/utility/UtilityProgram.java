package jp.zyyx.training.utility;
import java.sql.*;

import javax.sql.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import jp.zyyx.training.model.ArticleService;
import jp.zyyx.training.model.ArticlesCalendar;
import jp.zyyx.training.model.ArticlesList;
import jp.zyyx.training.model.DatabaseService;
public class UtilityProgram {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat yearMonthFormat = new SimpleDateFormat("yyyy-MM");
		String yearMonth = "2014-12";
		Date date = yearMonthFormat.parse(yearMonth);
		System.out.println(date);
		
		ArticleService articleService = new DatabaseService();
		ArticlesCalendar articlesCalendar = articleService.getArticlesCalendar("2014-12");
		System.out.println(articlesCalendar);
		
		int count = 0;
		
		for(int i = 0; i < articlesCalendar.getFirstDayOfWeek(); i++) {
			System.out.println("0");
			count++;
		}
		System.out.println("hello");
	}
}
