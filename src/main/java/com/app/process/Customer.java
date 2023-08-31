package com.app.process;

public class Customer {
	
	private int age;
	private String name;
	private String lname;
	private String location;
	
	public int getAge() {
		return age;
	}

	

	public String getName() {
		return name;
	}

	

	public String getLname() {
		return lname;
	}

	

	public String getLocation() {
		return location;
	}

	

	Customer(int custAge,String fname,String lname,String location){
		this.age = custAge;
		this.name = fname;
		this.lname = lname;
		this.location = location;
		
	}

}
