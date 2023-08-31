package com.app.process;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerOperations {
	
	    String JDBC_DRIVER = "org.h2.Driver";   
	    String DB_URL = "jdbc:h2:~/test";  
	   
	   //  Database credentials 
	    String USER = "sa"; 
	    String PASS = ""; 


	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		System.out.println("Hello");
		CustomerOperations obj = new CustomerOperations();	
		 Connection conn = null; 
		 
		    Statement stmt = null; 
		    PreparedStatement ptmt = null;
		    try { 
		       // STEP 1: Register JDBC driver 
		       Class.forName(obj.JDBC_DRIVER); 
		           
		       //STEP 2: Open a connection 
		       System.out.println("Connecting to database..."); 
		       conn = DriverManager.getConnection(obj.DB_URL,obj.USER,obj.PASS);  
		       conn.setAutoCommit(false);
		  	 
		       //STEP 3: Execute a query 
		       System.out.println("Creating table in given database..."); 
		       stmt = conn.createStatement(); 
		    }catch(Exception ex) {
		    	ex.printStackTrace();
		    }
		  obj.createTable();
		//  obj.inserData(stmt);
		  obj.readCustomerData();
		  obj.getCustomerData(20, conn);
	}

public void createTable() {
    Connection conn = null; 
    Statement stmt = null; 
    try { 
       // STEP 1: Register JDBC driver 
       Class.forName(JDBC_DRIVER); 
           
       //STEP 2: Open a connection 
       System.out.println("Connecting to database..."); 
       conn = DriverManager.getConnection(DB_URL,USER,PASS);  
       
       //STEP 3: Execute a query 
       System.out.println("Creating table in given database..."); 
       stmt = conn.createStatement(); 
       String sql =  "CREATE TABLE   Customer " + 
          "(id INTEGER not NULL, " + 
          " firstName VARCHAR(255), " +  
          " lastName VARCHAR(255), " +  
          " age INTEGER, " +  
          " PRIMARY KEY ( id ))";  
       stmt.executeUpdate(sql);
       System.out.println("Created table in given database..."); 
       
       // STEP 4: Clean-up environment 
       stmt.close(); 
       conn.close(); 
    } catch(SQLException se) { 
       //Handle errors for JDBC 
       se.printStackTrace(); 
    } catch(Exception e) { 
       //Handle errors for Class.forName 
       e.printStackTrace(); 
    } finally { 
       //finally block used to close resources 
       try{ 
          if(stmt!=null) stmt.close(); 
       } catch(SQLException se2) { 

}	
    }
}

public void inserData( Statement stmt,Connection con) throws SQLException {
    try{
       // STEP 1: Register JDBC driver 
       String sql = "INSERT INTO Customer " + "VALUES (100, 'Zara', 'Ali', 18)"; 
       
       stmt.executeUpdate(sql); 
       sql = "INSERT INTO Customer " + "VALUES (101, 'Mahnaz', 'Fatma', 25)";  
       
       stmt.executeUpdate(sql); 
       sql = "INSERT INTO Customer " + "VALUES (102, 'Zaid', 'Khan', 30)"; 
       
       stmt.executeUpdate(sql); 
       sql = "INSERT INTO Customer " + "VALUES(103, 'Sumit', 'Mittal', 28)"; 
       
       stmt.executeUpdate(sql); 
       System.out.println("Inserted records into the table..."); 
       
       // STEP 4: Clean-up environment 
       stmt.close(); 
       con.commit();
       
        
    } catch(SQLException se) { 
       // Handle errors for JDBC 
       se.printStackTrace(); 
       con.rollback();
    } catch(Exception e) { 
       // Handle errors for Class.forName 
       e.printStackTrace(); 
    } finally { 
       // finally block used to close resources 
       try {
          if(stmt!=null) stmt.close();  
       } catch(SQLException se2) { 
       } // nothing we can do 
       // end finally try 
    } // end try 
    System.out.println("Goodbye!"); 

}
  public void readCustomerData() {
      Connection conn = null; 
      Statement stmt = null; 
      try { 
         // STEP 1: Register JDBC driver 
         Class.forName(JDBC_DRIVER); 
         
         // STEP 2: Open a connection 
         System.out.println("Connecting to database..."); 
         conn = DriverManager.getConnection(DB_URL,USER,PASS);  
         
         // STEP 3: Execute a query 
         System.out.println("Connected database successfully..."); 
         stmt = conn.createStatement(); 
         String sql = "SELECT id, firstName, lastName, age FROM Customer"; 
         ResultSet rs = stmt.executeQuery(sql); 
         
         // STEP 4: Extract data from result set 
         while(rs.next()) { 
            // Retrieve by column name 
            int id  = rs.getInt("id"); 
            int age = rs.getInt("age"); 
            String first = rs.getString("firstName"); 
            String last = rs.getString("lastName");  
            
            // Display values 
            System.out.print("ID: " + id); 
            System.out.print(", Age: " + age); 
            System.out.print(", FirstName: " + first); 
            System.out.println(", LastName: " + last); 
         } 
         // STEP 5: Clean-up environment 
         rs.close(); 
      } catch(SQLException se) { 
         // Handle errors for JDBC 
         se.printStackTrace(); 
      } catch(Exception e) { 
         // Handle errors for Class.forName 
         e.printStackTrace(); 
      } finally { 
         // finally block used to close resources 
         try { 
            if(stmt!=null) stmt.close();  
         } catch(SQLException se2) { 
         } // nothing we can do 
         try { 
            if(conn!=null) conn.close(); 
         } catch(SQLException se) { 
            se.printStackTrace(); 
         } // end finally try 
      } // end try 
      System.out.println("Goodbye!"); 

  }
  
  public void getCustomerData(int age,Connection con) throws SQLException {
	  
	  
	  String query = "select * from Customer where age=?";
	  
	  PreparedStatement ptmt = con.prepareStatement(query);
			  
      ptmt.setInt(1,age);
      
         ResultSet rs =  ptmt.executeQuery();
         if(null!=rs)
         {
        	 while(rs.next()) {
        		 int id = rs.getInt(1);
        		 int custAge = rs.getInt(2);
        		 String fname = rs.getString(3);
        		 String lName = rs.getString(4);
        		 
        		 System.out.println("Customer details - "+ id + "custAge "+"fname "+lName+"lName "+lName);
        	 }
         }
  }
}
