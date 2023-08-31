package com.app.process;

import java.util.List;

public class CustomerOnboardingFacade {
	
	CustomerBusinessService service =null; 
	CustomerOnboardingFacade (CustomerBusinessService bservice){
		this.service = bservice;
	}
	
	public List<Customer> processCustomerOperation(String operationname,List<Customer> customerList) throws DatabaseException {
		
		if(operationname!=null && operationname.equals("create")) {
			
			service.createCustomer(customerList);
			//call business service create method
		}
		if(operationname!=null && operationname.equals("getAll")) {
			return service.getCustomers();
			//call business service getCustomers method
		}
		if(operationname!=null && operationname.equals("delete")) {
			//call business service deleteCustomer method
		}
		if(operationname!=null && operationname.equals("update")) {
			//call business service updateCustomer method
		}
		
		return null;
	}

}
