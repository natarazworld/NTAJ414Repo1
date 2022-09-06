//PsSensitveRSTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PsSensitveRSTest {

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				PreparedStatement ps=con.prepareStatement("SELECT SNO,SNAME,SADD,AVG FROM STUDENT",
						                                                                                    ResultSet.TYPE_SCROLL_INSENSITIVE,
						                                                                                    ResultSet.CONCUR_UPDATABLE);	
						ResultSet rs=ps.executeQuery()){
			
			   if(rs!=null) {
				    System.out.println("Records display (top to bottom)");
				    int count=0;
				    while(rs.next()) {
				    	
				    	 if(count==0) {
				    		 System.out.println(" In next 40 secs modify the records student db table ");
				    		 Thread.sleep(20000);
				    	 }
				    	
				    	 count++;
				    	 
				    	     System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
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
