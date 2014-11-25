package jp.zyyx.training;

import java.util.ArrayList;

/**
 * 表示したい情報を含むクラス
 * @author thangvm
 *
 */
public class ArticlesList {
	/** 表示したいページインデックス、0から数える*/
	int pageIndex;
	
	/** 検索ストリング*/
	String searchText;
	
	/** 教示したいリスト*/
	ArrayList<ArticleBean> list;
	
	/**
	 * コンストラクター
	 * @param searchText 検索ストリング
	 * @param pageIndex 表示したいページ
	 */
	public ArticlesList(String searchText, int pageIndex) {
		this.searchText = searchText;
		this.pageIndex = pageIndex;
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
	 * 表示したいページインデックスを取るメソッド
	 * @return ページインデックス
	 */
	public int getPageIndex() {
		return pageIndex;
	}
	
	/**
	 * 検索ストリングを取るメソッド
	 * @return 検索ストリング
	 */
	public String getSearchText() {
		return searchText;
	}
}
