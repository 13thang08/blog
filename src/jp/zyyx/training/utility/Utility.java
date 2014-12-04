package jp.zyyx.training.utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ユーティリティークラス
 * @author thangvm
 *
 */
public class Utility {
	
	/**
	 * ストリングの先頭とリアのスペースを削除
	 * @param str
	 * @return
	 */
	public static String preprocessingString (String str) {
		if (str != null) {
			str = str.trim();
			if (str.length() == 0) {
				str = null;
			}
		}
		return str;
	}
	
	/**
	 * 資源を解放するメソッド
	 * @param con
	 * @param stmt
	 * @param rs
	 */
	public static void closeJDBCResources(Connection con, Statement stmt, ResultSet rs ) {
		try {
			if (con != null) {
				con.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
