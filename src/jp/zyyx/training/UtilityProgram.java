package jp.zyyx.training;

import java.util.ArrayList;

public class UtilityProgram {
	public static void main(String[] args) {
		ArticleService articleService = new FileDataService();
		ArticleBean article = new ArticleBean();
		article.setDate("2014-11-19-02.51.42");
		article.setTitle("Salalalalal");
		article.setContent("ddkfdakfa");
		articleService.addArticle(article);
		articleService.removeArticle("20");
	}
}
