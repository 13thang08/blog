package jp.zyyx.training.model;

import jp.zyyx.training.utility.Utility;

public class SearchInfo {
	String searchText;
	int page;
	String searchDate;
	public SearchInfo(String searchText, int page, String searchDate) {
		this.searchText = Utility.preprocessingString(searchText);
		this.page = page > 1 ? page : 1;
		this.searchDate = Utility.preprocessingString(searchDate);
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}
	
	
}
