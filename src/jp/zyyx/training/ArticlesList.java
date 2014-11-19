package jp.zyyx.training;

import java.util.ArrayList;

public class ArticlesList {
	int pageIndex;
	String searchText;
	ArrayList<ArticleBean> list;
	
	public ArticlesList(String searchText, int pageIndex) {
		this.searchText = searchText;
		this.pageIndex = pageIndex;
		list = new ArrayList<ArticleBean>();
	}
	
	public void addArticle(ArticleBean bean) {
		list.add(bean);
	}
	
	public ArrayList<ArticleBean> getList() {
		return list;
	}
}
