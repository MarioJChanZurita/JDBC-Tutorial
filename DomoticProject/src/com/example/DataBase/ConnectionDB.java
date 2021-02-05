package com.example.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	
	private Connection connection;
	
	private final String driver = "org.postgresql.Driver";
	private final String url = "jdbc:postgresql://localhost:5432/devices";
	private final String user = "postgres";
	private final String password = "123";
	
	public Connection connect(){
		try{
			connection = DriverManager.getConnection(url, user, password);
			Class.forName(driver);
		}catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return connection;
	}
	
	public void close() throws SQLException {
		if(connection != null) {
			if (!connection.isClosed()) {
				connection.close();
			}
		}
	}
	
}
