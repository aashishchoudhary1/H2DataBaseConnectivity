package com.app.process;

import java.util.List;

public class CustomerBusinessService {
	
	CustomerRepository repo = null;
	
	public CustomerRepository getRepo() {
		return repo;
	}


	public void setRepo(CustomerRepository repo) {
		this.repo = repo;
	}


	CustomerBusinessService(CustomerRepository repository){
		
		this.repo = repository;
	}
	
	
	public void createCustomer(List<Customer> customerList) throws DatabaseException {
		repo.createCustomer(customerList);
	}
	
	public List<Customer> getCustomers() throws DatabaseException{
		
		return repo.getCustomers();
		
	}
	
	public void deleteCustomers() {
		
	}
	
	public boolean updateCustomer() {
		
		return true;
	}
	
	

}
