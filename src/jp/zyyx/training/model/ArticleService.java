package jp.zyyx.training.model;

/**
 * データをアクセスするインターフェース
 * @author thangvm
 *
 */
public interface ArticleService {
	/**
	 * 1ページの記事数
	 */
	public static final int numArticlesPerPage = 5;
	
	/**
	 * 時刻フォマット
	 */
	public static final String dateFormat = "yyyy-MM-dd-HH:mm:ss";
	
	/**
	 * 記事を表示するメソッド
	 * @param searchText 検索のストリング、検索しない場合はnullになります
	 * @param page 表示したいページ
	 * @return 検索結果のArticlesListオブジェクト
	 */
	public ArticlesList showArticles(SearchInfo searchInfo);
	
	/**
	 * 記事データに記事を追加するメソッド
	 * @param article 追加したい記事
	 * @return true 追加成功
	 *         false 追加失敗
	 */
	public boolean addArticle(ArticleBean article);
	
	/**
	 * 記事を削除するメソッド
	 * @param id 削除したい記事のid
	 * @return true 削除成功
	 *         false 削除失敗
	 */
	public boolean removeArticle(int id);
	
	/**
	 * 記事を編集するメソッド
	 * @param article 編集したい情報があるAritcleBeanオブジェクト
	 * @return true 編集成功
	 *         false 編集失敗
	 */
	public boolean editArticle(ArticleBean article);
	
	/**
	 * 記事をとるメソッド
	 * @param id 取りたい記事のid
	 * @return idがある記事
	 */
	public ArticleBean getArticle(int id);
	
	public ArticlesCalendar getArticlesCalendar(String yearMonth);
}
