package jp.zyyx.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * データベースをアクセスするクラス
 * @author thangvm
 *
 */
public class DatabaseService implements ArticleService {
	String mySqlUrl;
	Properties userInfo;
	
	

	public DatabaseService() {
		mySqlUrl = "jdbc:mysql://localhost:3306/blog";
		userInfo = new Properties();
		userInfo.put("user", "root");
		userInfo.put("password", "123456");
		
	}

	/**
	 * 記事を表示するメソッド
	 * @param searchText 検索のストリング、検索しない場合はnullになります
	 * @param page 表示したいページ
	 * @return 検索結果のArticlesListオブジェクト
	 */
	@Override
	public ArticlesList showArticles(String searchText, int page) {
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection connection = DriverManager.getConnection(mySqlUrl, userInfo);
			 ArticlesList articlesList = new ArticlesList(searchText, page);
			 
			 // get resultSet with searchText
			 String query = "SELECT * FROM articles ORDER BY date DESC";
			 Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery(query);
			 
			 // output ArticlesList from result, using page parameter
			 int count = 0;
			 while (resultSet.next()) {
				 if (count >= numArticlesPerPage * (page - 1) && count <= numArticlesPerPage * page) {
					 ArticleBean bean = new ArticleBean();
					 bean.setId(resultSet.getInt("id"));
					 bean.setDate(resultSet.getString("date"));
					 bean.setTitle(resultSet.getString("title"));
					 bean.setContent(resultSet.getString("content"));
					 articlesList.addArticle(bean);
				 }
				 count++;
			 }
			 articlesList.setTotalPage((int) Math.ceil((double) count / numArticlesPerPage));
			 connection.close();
			 return articlesList;
		 } catch (Exception e) {
			 e.printStackTrace();
			 return null;
		 }
	}

	/**
	 * 記事データに記事を追加するメソッド
	 * @param article 追加したい記事
	 * @return true 追加成功
	 *         false 追加失敗
	 */
	@Override
	public boolean addArticle(ArticleBean article) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(mySqlUrl, userInfo);
			Statement statement = connection.createStatement();
			String query = "INSERT INTO articles (date, title, content) VALUES('"
			+ article.getDate()
			+ "', '" + article.getTitle()
			+ "', '" + article.getContent() + "')";
			System.out.println(query);
			statement.executeUpdate(query);
			connection.close();
			return true;
			
					
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 記事を削除するメソッド
	 * @param id 削除したい記事のid
	 * @return true 削除成功
	 *         false 削除失敗
	 */
	@Override
	public boolean removeArticle(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(mySqlUrl, userInfo);
			Statement statement = connection.createStatement();
			String query = "DELETE FROM articles WHERE id=" + id;
			statement.executeUpdate(query);
			connection.close();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 記事を編集するメソッド
	 * @param article 編集したい情報があるAritcleBeanオブジェクト
	 * @return true 編集成功
	 *         false 編集失敗
	 */
	@Override
	public boolean editArticle(ArticleBean article) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(mySqlUrl, userInfo);
			Statement statement = connection.createStatement();
			String query = "UPDATE articles SET date='" + article.getDate() + 
					"', title='" + article.getTitle() +
					"', content='" + article.getContent() + "' WHERE id=" + article.getId();
			System.out.println(query);
			statement.executeUpdate(query);
			connection.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 記事をとるメソッド
	 * @param id 取りたい記事のid
	 * @return idがある記事
	 */
	@Override
	public ArticleBean getArticle(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(mySqlUrl, userInfo);
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM articles WHERE id=" + id;
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				ArticleBean article = new ArticleBean();
				article.setId(id);
				article.setDate(resultSet.getString("date"));
				article.setTitle(resultSet.getString("title"));
				article.setContent(resultSet.getString("content"));
				connection.close();
				return article;
			} else {
				connection.close();
				return null;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
