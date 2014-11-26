package jp.zyyx.training;

import java.util.ArrayList;

public class UtilityProgram {
	public static void main(String[] args) {
		ArticleService articleService = new FileDataService();
		ArticleBean article = new ArticleBean();
		article.setId(40);
		article.setTitle("AAA");
		article.setDate("2019-11-25-10.37.41");
		article.setContent("BDFDFDFD");
		System.out.println(articleService.addArticle(article));
	}
}
