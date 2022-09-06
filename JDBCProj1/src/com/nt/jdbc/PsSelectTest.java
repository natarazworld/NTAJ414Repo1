//PsSelectTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PsSelectTest {
	private final static  String  GET_ALL_STUDENTS="SELECT * FROM STUDENT";

	public static void main(String[] args) {
		
		try(	 //establish the connection
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
				 //create PreparedStatement having pre-compiled SQL Query
				PreparedStatement ps=con.prepareStatement(GET_ALL_STUDENTS);
				//execute the pre-compiled SQL Query
				ResultSet rs=ps.executeQuery();
				){
			//process the ResultSet obj
			 if(rs!=null) {
				 while(rs.next()) {
					 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				 }//while
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
