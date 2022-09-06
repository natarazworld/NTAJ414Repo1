//SelectTest1_Mysql.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest1_Mysql {

	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:mysql:///NTAJ414DB", "root", "root");
				Statement st=con.createStatement();
					){
			//read inputs
			  float start=0.0f, end=0.0f;
			   if(sc!=null) {
				   System.out.println("Enter start price range");
				   start=sc.nextFloat();  //gvies 1000
				   System.out.println("Enter end price range");
				  end=sc.nextFloat();  //gives 3000
			   }
			    //prepare SQL Query
			    //  SELECT * FROM PRODUCT WHERE PRICE>=1000 AND PRICE<=3000
			    String query=" SELECT * FROM PRODUCT WHERE PRICE>="+start+" AND PRICE<="+end;
			    System.out.println(query);
			try(ResultSet rs=st.executeQuery(query);){  //nested try with resource
			   //process the ResultSet obj
			    if(rs!=null) {
			    	boolean rsEmpty=true;
			    	while(rs.next()) {
			    		 rsEmpty=false;
			    		System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+"  "+rs.getFloat(4));
			    	}//while
			    	if(rsEmpty)
			    		System.out.println("No records found");
			    	else
			    		System.out.println("Records found and displayed");
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
