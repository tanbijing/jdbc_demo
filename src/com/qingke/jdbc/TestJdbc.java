package com.qingke.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TestJdbc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connection = null;
		Statement state = null;
		ResultSet result = null;
		try {
			loadJdbcDriver();
			connection = getConnection();
			state = connection.createStatement();
			String height = "'' or '1'='1'";
			result = state.executeQuery("select * from student where id=1 and height="+height);
			while(result.next()){
				System.out.println(result.getDouble("height"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cleanUp(connection, state, result);
		}
		
		PreparedStatement ps = null;
		try {
			connection = getConnection();
			ps = connection.prepareStatement("select * from student where id=? and height=?;");
			ps.setInt(1, 1);
			ps.setString(2, "'' or '1'='1'");
			result = ps.executeQuery();
			
			while(result.next()){
				System.out.println(result.getDouble("height"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cleanUp(connection, ps, result);
		}
		
	}

	public static void cleanUp(Connection connection, Statement state, ResultSet result) {
		try {
			if (result!=null){
				result.close();
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			if(state!=null){
				state.close();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			if(connection!=null){
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection connection;
		String url = getProperty("db.url");
		try {
			connection = DriverManager.getConnection(url, getProperty("db.username"), getProperty("db.password"));
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * @throws ClassNotFoundException
	 */
	public static void loadJdbcDriver(){
		String driver = getProperty("db.driver");
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String getProperty(String key){
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("jdbc.properties"));
			return props.getProperty(key);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}
