package jp.zyyx.training;

import java.util.ArrayList;

public class UtilityProgram {
	public static void main(String[] args) {
		ArticleService articleService = new FileDataService();
		ArticleBean article = new ArticleBean();
		article.setId(32);
		article.setDate("2014-11-19-02.51.42");
		article.setTitle("Edited");
		article.setContent("Edited");
//		articleService.addArticle(article);
//		articleService.removeArticle(20);
		articleService.addArticle(article);
//		System.out.println(articleService.editArticle(article));
//		articleService.removeArticle(5);
	}
}
