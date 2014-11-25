package jp.zyyx.training;

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
	
	
	public ArticleBean() {
		id = 0;
		date = null;
		title = null;
		content = null;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
