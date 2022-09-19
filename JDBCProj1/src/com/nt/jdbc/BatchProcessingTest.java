//BatchProcessingTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessingTest {

	public static void main(String[] args) {
		
		try(Connection  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				 Statement  st=con.createStatement();){
			if(st!=null) {
				//add queries to the batch (only non-select SQL query)
				st.addBatch("INSERT INTO STUDENT VALUES(4561,'rajesh','delhi',78.99)");
				st.addBatch("UPDATE STUDENT SET AVG=AVG-5 WHERE SNO>=10 AND SNO<=500");
				st.addBatch("DELETE FROM STUDENT WHERE SADD='hyd'");
				
				// send and execute batch of SQL Queries in DB s/w
				int result[]=st.executeBatch();
				
				//process the results
				int sum=0;
				for(int i=0;i<result.length;++i)
					sum=sum+result[i];
				
				System.out.println("no.of records that are effected::"+sum);
				
			}//if
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}


	}

}
