package com.app.process;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerOnboarding {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ConnectionPool poolobj = new ConnectionPool();
		try {
			poolobj.createConnectionPool();
		
		CustomerRepository repo = new CustomerRepository(poolobj);
		CustomerBusinessService svc = new CustomerBusinessService(repo);
		//svc.setRepo(repo);
		CustomerOnboardingFacade fc = new CustomerOnboardingFacade(svc);
		
		Customer c1 = new Customer(3,"James","Roy","Mumbai");
		Customer c2 = new Customer(4,"Alan","Musk","Bangalore");
		
		List<Customer> custList = new ArrayList<Customer>();
		
		custList.add(c1);
		custList.add(c2);
		
		System.out.println(" poosize after initialization - "+poolobj.size());
		   fc.processCustomerOperation("create", custList);
		   System.out.println(" poosize after create operations - "+poolobj.size());
		   List<Customer> custAllList = fc.processCustomerOperation("getAll", custList);
		   
		   for(Customer c:custAllList) {
			   System.out.println("cUST DETAILS - "+c.getAge() +" fname "+c.getName() 
			   +"lname "+c.getLname()+"location "+c.getLocation());
		   }
		   System.out.println(" poosize after get operations - "+poolobj.size());
			  
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * try { poolobj.createConnectionPool();
		 * 
		 * System.out.println(" poosize after initialization - "+poolobj.size());
		 * Connection con = poolobj.getConnection();
		 * 
		 * if(con!=null && !con.isClosed()) {
		 * System.out.println("we got connection successfully");
		 * System.out.println(" poosize "+poolobj.size()); ///complete all operations
		 * then return it no need to close //create statement //executequery //process
		 * resultset poolobj.returnConnection(con);
		 * System.out.println(" poosize "+poolobj.size());
		 * 
		 * } } catch (ConnectionException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (SQLException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */ catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

}
