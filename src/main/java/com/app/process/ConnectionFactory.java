package com.app.process;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static ConnectionFactory obj = null;
	
	private ConnectionFactory() {
		
	}

	public static synchronized ConnectionFactory getInstance() {
		
		if(obj ==null) {
			return new ConnectionFactory();
		} else {
			return obj;
		}
		
	}
	
	public Connection getConnection() throws ConnectionException {
		String JDBC_DRIVER = "org.h2.Driver";   
	    String DB_URL = "jdbc:h2:~/test1";  
	   
	   //  Database credentials 
	    String USER = "sa"; 
	    String PASS = ""; 
	    
	    Connection con = null;
	    try {
	    	 Class.forName(JDBC_DRIVER);
	  	   
			 con = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ConnectionException(e.getMessage()+ "There is problem in creating connection");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ConnectionException(e.getMessage()+ "Driver not found");
			
			
		}

	    return con;
	}
}
