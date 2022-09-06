//LoginApp.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {

	public static void main(String[] args) {
	
		try(Scanner sc=new Scanner(System.in);
			  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				Statement st=con.createStatement();
				){
			//read inputs
			String user=null,pass=null;
			if(sc!=null) {
				System.out.println("Enter username ::");
				 user=sc.nextLine();   //gives  raja
				 System.out.println("Enter password::");
				 pass=sc.nextLine();  //gives rani
			}
			// convert input values as required for the SQL Query
			  user="'"+user+"'"; //gives  'raja'
			  pass="'"+pass+"'"; //gives 'rani'
			  
			 //prepare SQL Query
			   //  SELECT COUNT(*) FROM USER_INFO WHERE UNAME='RAJA' AND PWD='RANI'
				String query="SELECT COUNT(*) FROM USER_INFO WHERE UNAME="+user+" AND PWD="+pass;
				System.out.println(query);
			//send  and execute the SQL Query in Db s/w
				try(ResultSet rs=st.executeQuery(query)){  //nested try with resource
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

	}

}
