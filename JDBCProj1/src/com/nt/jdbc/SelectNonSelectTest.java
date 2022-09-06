package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelectTest {

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				Statement st=con.createStatement();
				Scanner sc=new Scanner(System.in);
				){
			String query=null;
			 if(sc!=null) {
				 System.out.println("Enter SQL Query");
				 query=sc.nextLine();
			 }
			 //execute the Query
			  if(st!=null) {
				  boolean  flag=st.execute(query);
				  if(flag) {
					  System.out.println("SELECT SQL Query is executed");
					   try(ResultSet rs=st.getResultSet()){
						     if(rs!=null) {
						    	 while(rs.next()) {
						    		 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
						    	 }
						     }
						   
					   }//try
					  
				  }//if
				  else {
					  System.out.println("NON-SELECT SQL Query is executed");
					  int count=st.getUpdateCount();
					  System.out.println("No.of records that are effected::"+count);
				  }//else
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
