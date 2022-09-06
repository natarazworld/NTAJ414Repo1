//SelectTest4.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest4 {

	public static void main(String[] args) {
		
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//Load jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection with Db s/w
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				// create Statement object
			if (con != null)
				st = con.createStatement();
			
			//prepare SQL Query
			  String query="SELECT  COUNT(*) FROM EMP";
			  
			 //send and execute the SQL Query in DB s.w
			  if(st!=null)
				  rs=st.executeQuery(query);
			  
			  //process the ResultSEt
			  if(rs!=null) {
				  rs.next();
				  //System.out.println("Records count::"+rs.getInt(1));
				  System.out.println("Records count::"+rs.getInt("COUNT(*)"));
			  }//if

		}//try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close jdbc objs
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

		
		} // finally
	}//main

}//class
