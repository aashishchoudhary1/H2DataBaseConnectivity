package com.app.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
	
	ConnectionPool pool = null;
	
	CustomerRepository(ConnectionPool pool){
		this.pool = pool;
	}
	
	
	public void createCustomer(List<Customer> customerList) throws DatabaseException{
		
		//only for first execution
		//createTable();
		
		Connection con = pool.getConnection();
		try {
			if(customerList!=null && customerList.size()>0)
			{
				for(Customer c : customerList) {
					
			PreparedStatement pt = con.prepareStatement("Insert into Customer values(?,?,?,?)");
			pt.setInt(1, c.getAge());
			pt.setString(2, c.getName());
			pt.setString(3, c.getLname());
			pt.setString(4, c.getLocation());
			
			pt.executeUpdate();
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			throw new DatabaseException(e.getMessage() +"There is problem in database insert operation");
		}
		finally {
			try {
				pool.returnConnection(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new DatabaseException(e.getMessage() +"There is problem with pool while returning connection");
			}
		}
		//call
		//call connectionppol
		//getconnection
		//create statement
		//execute
		//return connection
	}
	
	public List<Customer> getCustomers() throws DatabaseException{
		
		List<Customer> custList = new ArrayList<Customer>();
		
		Connection con = pool.getConnection();
		try {
			PreparedStatement pt = con.prepareStatement("Select * from Customer");
			
			ResultSet rs = pt.executeQuery();
			
			   if(rs!=null) {
				   while(rs.next()) {
					   Customer custObj = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getNString(4));
					   custList.add(custObj)	;
				   }
				   
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DatabaseException(e.getMessage() +"There is problem with select customers");

		}finally {
			try {
				pool.returnConnection(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new DatabaseException(e.getMessage() +"There is problem with closing connection");
			}
		}


		
		//call
				//call connectionppol
				//getconnection
				//create statement
				//executequery
				//return connection
		        //return list of customers

		return custList;
		
	}
	
	public void deleteCustomers() {
		
		//call connectionppol
		//getconnection
		//create statement
		//executupdate
		//return connection
        //return 
	}
	
	public boolean updateCustomer() {
		
		//call connectionppol
				//getconnection
				//create statement
				//executupdate
				//return connection
		        //return status

		return true;
	}
	
public void createTable() throws DatabaseException {
	Connection con = pool.getConnection();
	
	try {
		Statement stmt = con.createStatement();
		stmt.executeUpdate("create table customer (age INTEGER,fname varchar(60),lname varchar(100),location varchar(50))");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		try {
			pool.returnConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			throw new DatabaseException(e.getMessage() +" there is problems in pool");
		}
	}
	
	
	
	
}
}
