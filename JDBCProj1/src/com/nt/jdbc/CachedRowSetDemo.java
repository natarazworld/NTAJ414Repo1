package com.nt.jdbc;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleCachedRowSet;

public class CachedRowSetDemo {
	
	public static void main(String[] args) {
		
		try(OracleCachedRowSet crowset=new OracleCachedRowSet()){
			  //set properties
			  crowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			  crowset.setUsername("system");
			  crowset.setPassword("manager");
			  crowset.setCommand("SELECT EMPNO,ENAME,JOB,SAL FROM EMP");
			  crowset.execute();
			  
			  //process the rowset
			  while(crowset.next()) {
				  System.out.println(crowset.getInt(1)+"  "+crowset.getString(2)+"  "+crowset.getString(3)+" "+crowset.getFloat(4));
			  }
			  
			   System.out.println(" Stop the Db s/w");
			   Thread.sleep(50000);
			   //modify the cached RowSet in  offline mode
			   crowset.absolute(5);
			   crowset.updateFloat(4, 90000);
			   crowset.updateRow();
			   System.out.println(" Start the Db s/w");
			   Thread.sleep(50000);
			   //process the rowset
			     //accept the chanages to db table
			    crowset.acceptChanges();  // to sync with db table
				  while(crowset.next()) {
					  System.out.println(crowset.getInt(1)+"  "+crowset.getString(2)+"  "+crowset.getString(3)+" "+crowset.getFloat(4));
				  }
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}//main

}//class
