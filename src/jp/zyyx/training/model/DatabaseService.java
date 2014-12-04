package jp.zyyx.training.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import jp.zyyx.training.utility.Utility;

/**
 * データベースをアクセスするクラス
 * @author thangvm
 *
 */
public class DatabaseService implements ArticleService {
	
	/** データベースのURL */
	String mySqlUrl;
	
	/** データベースにアクセス出来るユーザ情報 */
	Properties userInfo;
	
	
	/**
	 * データベースのURLとユーザ情報を初期化
	 */
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
		} catch (ClassNotFoundException e) {
			System.out.println("Cann't load driver!\n");
			e.printStackTrace();
			return null;
		}
		
		ArticlesList articlesList = new ArticlesList(searchText, page);
		String query = null;
		PreparedStatement stmt = null;
		Connection connection = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(mySqlUrl, userInfo);
			
			// get resultSet with searchText
			if (searchText == null) {
				query = "SELECT * FROM articles ORDER BY date DESC";
				stmt = connection.prepareStatement(query);
			} else {
				query = "SELECT * FROM articles WHERE title like ? OR content like ? ORDER BY date DESC";
				stmt = connection.prepareStatement(query);
				stmt.setString(1, "%" + searchText + "%");
				stmt.setString(2, "%" + searchText + "%");
			}
			
			System.out.println(stmt.toString());
			resultSet = stmt.executeQuery();
			
			// output ArticlesList from result, using page parameter
			int count = 0;
			while (resultSet.next()) {
				if (count >= numArticlesPerPage * (page - 1)
						&& count < numArticlesPerPage * page) {
					ArticleBean bean = new ArticleBean();
					bean.setId(resultSet.getInt("id"));
					bean.setDate(resultSet.getString("date"));
					bean.setTitle(resultSet.getString("title"));
					bean.setContent(resultSet.getString("content"));
					articlesList.addArticle(bean);
				}
				count++;
			}
			
			articlesList.setTotalPage((int) Math.ceil((double) count
					/ numArticlesPerPage));
			return articlesList;
		} catch (SQLException e) {
			System.out.println("Database error!\n");
			e.printStackTrace();
			return null;
		} finally {
			Utility.closeJDBCResources(connection);
			Utility.closeJDBCResources(stmt);
			Utility.closeJDBCResources(resultSet);
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
		} catch (ClassNotFoundException e) {
			System.out.println("Cann't load driver!\n");
			e.printStackTrace();
			return false;
		}
		
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DriverManager.getConnection(mySqlUrl, userInfo);
			String query = "INSERT INTO articles (date, title, content) VALUES (?,?,?)";
			stmt = connection.prepareStatement(query);
			stmt.setString(1, article.getDate());
			stmt.setString(2, article.getTitle());
			stmt.setString(3, article.getContent());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			Utility.closeJDBCResources(connection);
			Utility.closeJDBCResources(stmt);
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
		} catch (ClassNotFoundException e) {
			System.out.println("Cann't load driver!\n");
			e.printStackTrace();
			return false;
		}
		
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DriverManager.getConnection(mySqlUrl, userInfo);
			String query = "DELETE FROM articles WHERE id=?";
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			// System.out.println(stmt.toString());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			Utility.closeJDBCResources(connection);
			Utility.closeJDBCResources(stmt);
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
		} catch (ClassNotFoundException e) {
			System.out.println("Cann't load driver!\n");
			e.printStackTrace();
			return false;
		}
		
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = DriverManager.getConnection(mySqlUrl, userInfo);
			String query = "UPDATE articles SET date=?, title=?, content=? WHERE id=?";
			stmt = connection.prepareStatement(query);
			stmt.setString(1, article.getDate());
			stmt.setString(2, article.getTitle());
			stmt.setString(3, article.getContent());
			stmt.setInt(4, article.getId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			Utility.closeJDBCResources(connection);
			Utility.closeJDBCResources(stmt);
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
		} catch (ClassNotFoundException e) {
			System.out.println("Cann't load driver!\n");
			e.printStackTrace();
			return null;
		}
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection(mySqlUrl, userInfo);
			String query = "SELECT * FROM articles WHERE id=?";
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				ArticleBean article = new ArticleBean();
				article.setId(id);
				article.setDate(resultSet.getString("date"));
				article.setTitle(resultSet.getString("title"));
				article.setContent(resultSet.getString("content"));
				return article;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			Utility.closeJDBCResources(connection);
			Utility.closeJDBCResources(stmt);
			Utility.closeJDBCResources(resultSet);
		}
	}
}
