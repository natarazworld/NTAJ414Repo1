//PSLoginApp.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PSLoginApp {
   private static final String AUTH_QUERY="SELECT COUNT(*) FROM USER_INFO WHERE UNAME=? AND PWD=? ";
	
   public static void main(String[] args) {
	
		try(Scanner sc=new Scanner(System.in);
			  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				PreparedStatement ps=con.prepareStatement(AUTH_QUERY);
				){
			//read inputs
			String user=null,pass=null;
			if(sc!=null) {
				System.out.println("Enter username ::");
				 user=sc.nextLine();   //gives  raja
				 System.out.println("Enter password::");
				 pass=sc.nextLine();  //gives rani
			}
			
			  //set values to query params
			if(ps!=null) {
				ps.setString(1, user);
				ps.setString(2, pass);
			}
			 
			//send  and execute the SQL Query in Db s/w
				try(ResultSet rs=ps.executeQuery()){  //nested try with resource
					//process the ResultSet obj
					    if(rs!=null) {
					    	  rs.next();
					    	  int count=rs.getInt(1);
					    	  if(count==0)
					    		  System.out.println("Invalid Credentials");
					    	  else
					    		  System.out.println("Valid Credentials");
					    }//if
				}//try2
			
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
