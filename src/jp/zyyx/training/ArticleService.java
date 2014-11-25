package jp.zyyx.training;

public interface ArticleService {
	public static final int numArticlesPerPage = 5;
	public ArticlesList showArticles(String searchText, int page);
	public boolean addArticle(ArticleBean article);
	public boolean removeArticle(int id);
	public boolean editArticle(ArticleBean article);
}
