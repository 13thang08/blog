package jp.zyyx.training;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class UtilityProgram {
	public static void main(String[] args) {
		ArticleService articleService = new FileDataService();
		for(int i = 1 ; i <= 27; i++) {
			System.out.println(Utility.isNew(articleService.getArticle(i)));
		}
	}
}
