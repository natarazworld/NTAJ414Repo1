//ExcelInsertTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ExcelInsertTest {
	 private static final String  EXCEL_INSERT_QUERY="INSERT INTO Sheet1 VALUES(?,?,?,?)";

	public static void main(String[] args) {
	
		 try(Connection con=DriverManager.getConnection("jdbc:Excel:///E:\\Worskpaces\\advjava\\NTAJ414\\College.xlsx");
				 PreparedStatement ps=con.prepareStatement(EXCEL_INSERT_QUERY);
				 Scanner  sc=new Scanner(System.in);
				 ){
			    int no=0;
			    String name=null,addrs=null;
			    float avg=0.0f;
			
			    if(sc!=null) {
			    	System.out.println("Enter  student number ::");
			    	 no=sc.nextInt();
			    	System.out.println("Enter student name::");
			    	 name=sc.next();
			    	System.out.println("enter  student address::");
			    	 addrs=sc.next();
			    	System.out.println("Etner student avg::");
			    	 avg=sc.nextFloat();
			    }
			   if(ps!=null) {
				    //set values to query params
				   ps.setInt(1,no);
				   ps.setString(2, name);
				   ps.setString(3, addrs);
				   ps.setFloat(4, avg);
				   //execute the Query
				   int count=ps.executeUpdate();
				   //process the result
				   if(count==0)
					   System.out.println("Record not inserted");
				   else
					   System.out.println("Record inserted");
			   }
			  
			    
			 
		 }//try
		 catch(SQLException se) {
			 se.printStackTrace();
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }

	}//main

}//class
