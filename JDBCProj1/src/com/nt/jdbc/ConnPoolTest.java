// ConnPoolTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class ConnPoolTest {
  private static final String  GET_STUDENTS_QUERY="SELECT * FROM STUDENT";
	public static void main(String[] args) {
		OracleConnectionPoolDataSource ds=null;
		try {
		//create DataSource object
		 ds=new OracleConnectionPoolDataSource();
		//set jdbc properties to it
		ds.setDriverType("thin");
		ds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
		ds.setUser("system");
		ds.setPassword("manager");
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
		
		//get pooled  jdbc con object
		try(Connection con=ds.getConnection();
				PreparedStatement ps=con.prepareStatement(GET_STUDENTS_QUERY);
				ResultSet rs=ps.executeQuery()
				){
			  //process the ResultSEt obj
			 if(rs!=null) {
				 while(rs.next()) {
					 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+"  "+rs.getFloat(4));
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
