package jp.zyyx.training.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
	 * データベースのコケクションを取る
	 * @return コケクション
	 */
	private Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Cann't load driver!\n");
			e.printStackTrace();
			return null;
		}
		
		try {
			Connection connection = DriverManager.getConnection(mySqlUrl, userInfo);
			return connection;
		} catch (SQLException e) {
			System.out.println("Cann't load driver!\n");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 記事を表示するメソッド
	 * @param searchText 検索のストリング、検索しない場合はnullになります
	 * @param page 表示したいページ
	 * @return 検索結果のArticlesListオブジェクト
	 */
	@Override
	public ArticlesList showArticles(SearchInfo searchInfo) {
		
		Connection connection = getConnection();
		if (connection == null) {
			return null;
		}
		
		ArticlesList articlesList = new ArticlesList(searchInfo);
		String query = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		
		try {
			// get resultSet with searchText
			query = "SELECT * FROM articles WHERE (title like ? OR content like ?) AND date like ? ORDER BY date DESC";
			stmt = connection.prepareStatement(query);
			stmt.setString(1, "%" + searchInfo.getSearchText() + "%");
			stmt.setString(2, "%" + searchInfo.getSearchText() + "%");
			stmt.setString(3, searchInfo.getSearchDate() + "%");
			
			System.out.println(stmt.toString());
			resultSet = stmt.executeQuery();
			
			// output ArticlesList from result, using page parameter
			int count = 0;
			while (resultSet.next()) {
				if (count >= numArticlesPerPage * (searchInfo.getPage() - 1)
						&& count < numArticlesPerPage * searchInfo.getPage()) {
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
		Connection connection = getConnection();
		if (connection == null) {
			return false;
		}
		
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
		Connection connection = getConnection();
		if (connection == null) {
			return false;
		}
		
		PreparedStatement stmt = null;
		try {
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
		Connection connection = getConnection();
		if (connection == null) {
			return false;
		}
		
		PreparedStatement stmt = null;
		try {
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
		Connection connection = getConnection();
		if (connection == null) {
			return null;
		}
		
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
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

	/**
	 * 表示したい月のカレンダだーを取る
	 * @param yearMonth 表示したい年月のストリング
	 * @return カレンダー
	 */
	@Override
	public ArticlesCalendar getArticlesCalendar(String yearMonth) {
		Connection con = getConnection();
		if (con == null) {
			return null;
		}
		
		ArticlesCalendar articlesCalendar = new ArticlesCalendar();
		Statement stmt = null;
		ResultSet rs = null;
		Date date = null;
		String dateString = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(ArticleService.dateFormat);
		SimpleDateFormat yearMonthFormat = new SimpleDateFormat(ArticlesCalendar.yearMonthFormat);
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
		
		// check if input yearMonth format is valid
		try {
			yearFormat.parse(yearMonth);
		} catch (Exception e) {
			Date currentDate = new Date();
			yearMonth = yearMonthFormat.format(currentDate);
		}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT MAX(date) FROM articles");
			
			if (rs.next()) {
				dateString = rs.getString(1);
				date = dateFormat.parse(dateString);
				articlesCalendar.setLastMonth(yearMonthFormat.format(date));
			} else {
				return null;
			}
			Utility.closeJDBCResources(rs);
			
			rs = stmt.executeQuery("SELECT MIN(date) FROM articles");
			if (rs.next()) {
				dateString = rs.getString(1);
				date = dateFormat.parse(dateString);
				articlesCalendar.setFirstMonth(yearMonthFormat.format(date));
			}
			
			articlesCalendar.setCurrentMonth(yearMonth);
			date = yearMonthFormat.parse(yearMonth);
			int year = Integer.parseInt(yearFormat.format(date));
			int month = Integer.parseInt(monthFormat.format(date)) - 1; // month value is 0-based
			Calendar calendar = Calendar.getInstance();
			calendar.clear();
			calendar.set(year, month, 1);
			articlesCalendar.setFirstDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DATE, -1);
			articlesCalendar.setLastDayOfMonth(calendar.get(Calendar.DATE));
			Utility.closeJDBCResources(rs);
			
			
			rs = stmt.executeQuery("SELECT * FROM articles WHERE date LIKE '" + yearMonth + "%'" );
			System.out.println(rs);
			while (rs.next()) {
				dateString = rs.getString("date");
				date = dateFormat.parse(dateString);
				articlesCalendar.addArticleDay(Integer.parseInt(dayFormat.format(date)));
			}
			return articlesCalendar;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} finally {
			Utility.closeJDBCResources(con);
			Utility.closeJDBCResources(stmt);
			Utility.closeJDBCResources(rs);
		}
	}
}
