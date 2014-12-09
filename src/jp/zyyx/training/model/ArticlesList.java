package jp.zyyx.training.model;

import java.util.ArrayList;

/**
 * 表示したい情報を含むクラス
 * @author thangvm
 *
 */
public class ArticlesList {
	
	/** 検索情報のオブジェクト */
	SearchInfo searchInfo;
	
	/** 総ページ */
	int totalPage;
	
	/** 教示したいリスト*/
	ArrayList<ArticleBean> list;
	
	/**
	 * コンストラクター
	 * @param searchText 検索ストリング
	 * @param page 表示したいページ
	 */
	public ArticlesList(SearchInfo searchInfo) {
		this.searchInfo = searchInfo;
		totalPage = 0;
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
	
	public SearchInfo getSearchInfo() {
		return searchInfo;
	}

	/**
	 * 総ページを取るメソッド
	 * @return 総ページ
	 */
	public int getTotalPage() {
		return totalPage;
	}
	
	/**
	 * 総ページを設定するメソッド
	 * @param totalPage
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}
