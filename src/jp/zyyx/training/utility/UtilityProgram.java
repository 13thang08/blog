package jp.zyyx.training.utility;
import java.sql.*;

import javax.sql.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
public class UtilityProgram {
	public static void main(String[] args) {
		try {
			Properties userInfo = new Properties();
			userInfo.put("user", "root");
			userInfo.put("password", "123456");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/blog", userInfo);
			System.out.println("Connection success!\n");
			String query = "SELECT * FROM articles";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) + resultSet.getString(2) + resultSet.getString(3) + resultSet.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
