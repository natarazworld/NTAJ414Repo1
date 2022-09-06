//UpdateTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
	
		try {
			//read inputs
			sc=new Scanner(System.in);
			
			String newCity=null;
			float newAvg=0.0f;
			int no=0;
			if(sc!=null) {
				System.out.println("Enter   existing student  number::");
				no=sc.nextInt();  ///gives 111
				System.out.println("Enter  new city of student");
				newCity=sc.next();  //gives  delhi
				System.out.println("Enter new avg of student");
				newAvg=sc.nextFloat();  // gives 90.66
			}//if
			 //convert input values as required for the SQL Query
			 newCity="'"+newCity+"'"; //gives 'delhi'
			 
			// Load jdbc driver class  (optional)
			   //Class.forName("oracle.jdbc.driver.OracleDriver");
			 
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			
			//create JDBC Stsatement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query as required for the DB s/w
			   //UPDATE STUDENT SET SADD='delhi',AVG=67.89 WHERE SNO=121
		       String query="UPDATE STUDENT SET SADD="+newCity+",AVG="+newAvg+" WHERE SNO="+no;
			 System.out.println(query);
			 
			 //send and execute the SQL Query in DB s/w
			 int count=0;
			 if(st!=null)
				 count=st.executeUpdate(query);
			 //process the Result
			 if(count==0)
				 System.out.println("No rerecod(s) found for updation");
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
