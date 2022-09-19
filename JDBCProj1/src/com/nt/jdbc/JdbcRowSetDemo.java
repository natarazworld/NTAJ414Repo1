package com.nt.jdbc;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleJDBCRowSet;

public class JdbcRowSetDemo {
	
	public static void main(String[] args) {
		
		try(OracleJDBCRowSet jrowset=new OracleJDBCRowSet()){
			  //set properties
			  jrowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			  jrowset.setUsername("system");
			  jrowset.setPassword("manager");
			  jrowset.setCommand("SELECT EMPNO,ENAME,JOB,SAL FROM EMP");
			  jrowset.execute();
			  
			  //process the rowset
			  while(jrowset.next()) {
				  System.out.println(jrowset.getInt(1)+"  "+jrowset.getString(2)+"  "+jrowset.getString(3)+" "+jrowset.getFloat(4));
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
