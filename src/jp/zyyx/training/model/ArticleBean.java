package jp.zyyx.training.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 記事・ビーン
 * @author thangvm
 *
 */
public class ArticleBean implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 記事のId*/
	private int id = 0;
	
	/** 記事の作成された時刻*/
	private String date = null;
	
	/** 記事のタイトル*/
	private String title = null;
	
	/** 記事の内容 */
	private String content = null;
	
	/**
	 * コンストラクター
	 */
	public ArticleBean() {
		id = 0;
		date = null;
		title = null;
		content = null;
	}
	
	/**
	 * id ゲッター
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * id セッター
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * date ゲッター
	 * @return
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * date セッター
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * title ゲッター
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * title セッター
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * content ゲッター
	 * @return
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * content セッター
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 記事が新しいかどうかをチェック
	 * @return true 新しい
	 *         false 新しくない
	 */
	public boolean isNewArticle() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(ArticleService.dateFormat);
		Date current = new Date();
		Date articleDate = null;
		try {
			articleDate = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		return Math.abs(current.getTime() - articleDate.getTime()) < (1000 * 3600 * 24 * 2) ? true : false;
	}
	
	/**
	 * HTMLページで表示するために正しくフォマットのストリングを取る
	 * @return
	 */
	public String getContentEscapeHtml() {
		return StringEscapeUtils.escapeHtml4(content).replaceAll("(\r\n|\n)", "<BR>");
	}
}
