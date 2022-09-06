//DeleteTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
	
		try {
			//read inputs
			sc=new Scanner(System.in);
			
			String city=null;
			if(sc!=null) {
				System.out.println("Enter  studnet  address::");
				city=sc.next();  //gives  hyd
			}//if
			 //convert input values as required for the SQL Query
			 city="'"+city+"'"; //gives 'hyd'
			// Load jdbc driver class
			   //Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			//create JDBC Stsatement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query as required for the DB s/w
			   //DELETE FROM STUDENT WHERE SADD='hyd'
			 String query="DELETE FROM STUDENT WHERE SADD="+city;
			 System.out.println(query);
			 //send and execute the SQL Query in DB s/w
			 int count=0;
			 if(st!=null)
				 count=st.executeUpdate(query);
			 //process the Result
			 if(count==0)
				 System.out.println("No rerecod(s) found for deletion");
			 else
				   System.out.println("no.of records that are effected::"+count);
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}//finally
	}//main

}//class
