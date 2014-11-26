package jp.zyyx.training;

import java.util.ArrayList;

public class UtilityProgram {
	public static void main(String[] args) {
		ArticleService articleService = new FileDataService();
		ArticleBean article = articleService.getArticle(30);
		System.out.println(article.getId());
		System.out.println(article.getDate());
		System.out.println(article.getTitle());
		System.out.println(article.getContent());
				
	}
}
