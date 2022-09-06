//PostgreSQLInsertTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PostgreSQLInsertTest {
	private static final String   INSERT_PRODUCT_QUERY="INSERT INTO PRODUCT VALUES(NEXTVAL('PID_SEQ'),?,?,?)";

	public static void main(String[] args) {
		
		try(	Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/ntaj414db", "postgres","tiger");
				PreparedStatement ps=con.prepareStatement(INSERT_PRODUCT_QUERY);
				Scanner sc=new Scanner(System.in);
				){
			//read values from enduser
			String name=null;
			double  price=0.0, qty=0.0;
			 if(sc!=null) {
				 System.out.println("Enter product name::");
				 name=sc.next();
				 System.out.println("Enter product price::");
				 price=sc.nextDouble();
				 System.out.println("Enter product qty::");
				 qty=sc.nextDouble();
			 }
			 // set  values to Query params
			 if(ps!=null) {
				 ps.setString(1, name);
				 ps.setDouble(2, price);
				 ps.setDouble(3, qty);
				 //execute the Query
				 int count=ps.executeUpdate();
				 //process the result
				  if(count==0)
					   System.out.println("record not inserted");
				  else
					  System.out.println("Record inserted");
			       }//if
				}//try
		    catch(SQLException se) {
		    	se.printStackTrace();
		    }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
				
	}//main

}//class
