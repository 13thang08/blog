package jp.zyyx.training.model;

import jp.zyyx.training.utility.Utility;

/**
 * 検索情報を持っているクラス
 * @author thangvm
 *
 */
public class SearchInfo {
	
	/** 検索ストリング */
	String searchText;
	
	/** 表示したいページ */
	int page;
	
	/** 表示したい年月日 */
	String searchDate;
	
	/**
	 * コンストラクター
	 * @param searchText
	 * @param page
	 * @param searchDate
	 */
	public SearchInfo(String searchText, int page, String searchDate) {
		this.searchText = Utility.preprocessingString(searchText);
		this.page = page > 1 ? page : 1;
		this.searchDate = Utility.preprocessingString(searchDate);
	}
	
	/**
	 * searchText ゲッター
	 * @return
	 */
	public String getSearchText() {
		return searchText;
	}
	
	/**
	 * searchText セッター
	 * @param searchText
	 */
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	/**
	 * page ゲッター
	 * @return
	 */
	public int getPage() {
		return page;
	}
	
	/**
	 * page セッター
	 * @param page
	 */
	public void setPage(int page) {
		this.page = page;
	}
	
	/**
	 * searchDate ゲッター
	 * @return
	 */
	public String getSearchDate() {
		return searchDate;
	}
	
	/**
	 * searchDate セッター
	 * @param searchDate
	 */
	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}
}
