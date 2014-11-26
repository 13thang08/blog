package jp.zyyx.training;

public class UtilityProgram {
	public static void main(String[] args) {
		ArticleService articleService = new FileDataService();
		ArticleBean article = new ArticleBean();
		article.setId(29);
		article.setDate("28");
		article.setTitle("28");
		article.setContent("28");
		System.out.println(articleService.editArticle(article));	
	}
}
