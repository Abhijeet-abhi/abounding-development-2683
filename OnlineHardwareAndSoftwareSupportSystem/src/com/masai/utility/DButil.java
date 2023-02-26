package com.masai.utility;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DButil {

	public static Connection provideConnection() {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			ResourceBundle bundle = ResourceBundle.getBundle("dbdetails");
			String url = bundle.getString("url");
			String user = bundle.getString("username");
			String password = bundle.getString("password");
			conn = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn == null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return conn;
	}
	public static void closeConnection(Connection conn) throws SQLException{
		if(conn != null)
			conn.close();
	}
}
