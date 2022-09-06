//PsUpdatableRSTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PsUpdatableRSTest {

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				PreparedStatement ps=con.prepareStatement("SELECT SNO,SNAME,SADD,AVG FROM STUDENT",
						                                                                                    ResultSet.TYPE_SCROLL_INSENSITIVE,
						                                                                                    ResultSet.CONCUR_UPDATABLE);	
						ResultSet rs=ps.executeQuery()){
			
			   if(rs!=null) {
				   
				   System.out.println("select operation");
				      while(rs.next()) {
				    	  System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+"  "+rs.getFloat(4));
				    }//while
				   /* //insert operation
				      rs.moveToInsertRow();
				      rs.updateInt(1, 1111);
				      rs.updateString(2,"mahesh");
				      rs.updateString(3, "delhi");
				      rs.updateFloat(4, 8900);
				      rs.insertRow();
				      System.out.println("Record inserted");*/
				/*      //update opeartion
				      rs.absolute(4);
				      rs.updateString(3, "delhi");
				      rs.updateRow();  */
				      
				      //delete operation
				       rs.absolute(6);
				       rs.deleteRow();
				   
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
