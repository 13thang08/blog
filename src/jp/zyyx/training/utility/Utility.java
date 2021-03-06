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
			return str.trim();
		} else {
			return "";
		}
	}
	
	/**
	 * 資源を解放するメソッド
	 * @param con
	 */
	public static void closeJDBCResources(Connection con) {
		try {
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 資源を解放するメソッド
	 * @param stmt
	 */
	public static void closeJDBCResources(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 資源を解放するメソッド
	 * @param rs
	 */
	public static void closeJDBCResources(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
