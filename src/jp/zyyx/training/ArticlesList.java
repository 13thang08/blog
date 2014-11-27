package jp.zyyx.training;

import java.util.ArrayList;

/**
 * 表示したい情報を含むクラス
 * @author thangvm
 *
 */
public class ArticlesList {
	/** 表示したいページ*/
	int page;
	
	/** 検索ストリング*/
	String searchText;
	
	/** 教示したいリスト*/
	ArrayList<ArticleBean> list;
	
	/**
	 * コンストラクター
	 * @param searchText 検索ストリング
	 * @param page 表示したいページ
	 */
	public ArticlesList(String searchText, int page) {
		this.searchText = searchText;
		this.page = page;
		list = new ArrayList<ArticleBean>();
	}
	
	/**
	 * リストに記事を追加するメソッド
	 * @param bean 追加したい記事
	 */
	public void addArticle(ArticleBean bean) {
		list.add(bean);
	}
	
	/**
	 * 記事のリストを取るメソッド
	 * @return
	 */
	public ArrayList<ArticleBean> getList() {
		return list;
	}
	
	/**
	 * 表示したいページを取るメソッド
	 * @return ページ
	 */
	public int getPage() {
		return page;
	}
	
	/**
	 * 検索ストリングを取るメソッド
	 * @return 検索ストリング
	 */
	public String getSearchText() {
		return searchText;
	}
}
