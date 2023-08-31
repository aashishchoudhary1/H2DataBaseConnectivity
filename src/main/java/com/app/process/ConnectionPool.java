package com.app.process;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPool {
	
	
	
	private ArrayList<Connection> conList = new ArrayList<Connection>();
	int pooSize = 5;
	int maxSize = 50;
	int index = 0;
	public  void createConnectionPool() throws ConnectionException {
		
		
		for(int i =0;i<pooSize;i++)
		{
			if(conList.size()==50)
			{
				break;
			}
			conList.add(ConnectionFactory.getInstance().getConnection());
			
		}
		
	}

	public Connection getConnection() {
		Connection con = null;
		if(conList.size()>0) {
		 con =  conList.get(index);
		conList.remove(index);
		}
		else {
			try {
				createConnectionPool();
			} catch (ConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			if(con==null && con.isClosed())
			{
				 con =  conList.get(index);
			conList.remove(index);
			}	
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public void returnConnection(Connection con) throws SQLException {
		if(con!=null && !con.isClosed())
		conList.add(con);
	}
	
	public int size() {
		return conList.size();
	}
}
