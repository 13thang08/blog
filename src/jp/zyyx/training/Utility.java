package jp.zyyx.training;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.zyyx.training.ArticleBean;

/**
 * ユーティリティクラス
 * @author thangvm
 *
 */
public class Utility {
	/**
	 * 記事は新しいかどうかをチェック
	 * @param article
	 * @return true: 新しい
	 *         false: 新しくない
	 */
	public static boolean isNew(ArticleBean article) {
		if (article == null) return false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		Date current = new Date();
		Date articleDate;
		try {
			articleDate = dateFormat.parse(article.getDate());
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		
		return Math.abs(current.getTime() - articleDate.getTime()) < (1000 * 3600 * 24 * 2) ? true : false;
	}
}
