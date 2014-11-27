package jp.zyyx.training;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.zyyx.training.ArticleBean;

public class Utility {
	public static boolean isNew(ArticleBean article) {
		if (article == null) return false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		Date current = new Date();
		Date articleDate;
		try {
			articleDate = dateFormat.parse(article.getDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return Math.abs(current.getTime() - articleDate.getTime()) < (1000 * 3600 * 24 * 2) ? true : false;
	}
}
