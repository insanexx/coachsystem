package com.cc.coachsystem.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private final static String jdbcurl = "jdbc:mysql://localhost:3306/coachsystem";
	private final static String username = "root";
	private final static String password = "root";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动类找不到。");
			System.exit(-1);
		}
	}
	/**
	 * 连接数据库
	 * @return
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(jdbcurl, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
