package com.revature.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCConnectionUtil {
	
	private static JDBCConnectionUtil cu = null;
	private static Properties prop = new Properties();
	private JDBCConnectionUtil() {
		super();
		InputStream dbProps = JDBCConnectionUtil.class.getClassLoader()
				.getResourceAsStream("database.properties");
		try {
			prop.load(dbProps);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static JDBCConnectionUtil getInstance() {
		if(cu==null)
			cu=new JDBCConnectionUtil();
		return cu;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			// We have to register the driver class
//			String url="jdbc:oracle:thin:@datn-revature.curj437axptx.us-east-2.rds.amazonaws.com:1521:ORCL";	
//			String user = "Project1_ERS";
//			String pass = "p4ssw0rd";
//			conn = DriverManager.getConnection(url, user, pass);
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("usr"),
					prop.getProperty("pwd"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
