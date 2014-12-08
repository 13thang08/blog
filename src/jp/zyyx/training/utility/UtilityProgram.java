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
	public static void main(String[] args){
		SimpleDateFormat yearMonthFormat = new SimpleDateFormat("yyyy-MM");
		String yearMonth = "2014-12-10";
		Date date;
		try {
			date = yearMonthFormat.parse(null);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(date);
		

		

	}
}
