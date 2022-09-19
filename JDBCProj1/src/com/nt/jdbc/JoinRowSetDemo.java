package com.nt.jdbc;

import java.io.FileWriter;
import java.sql.SQLException;

import oracle.jdbc.rowset.OracleCachedRowSet;
import oracle.jdbc.rowset.OracleJoinRowSet;
import oracle.jdbc.rowset.OracleWebRowSet;

public class JoinRowSetDemo {
	
	public static void main(String[] args) {
		
		try(OracleCachedRowSet crs1= new OracleCachedRowSet();
				OracleCachedRowSet crs2=new OracleCachedRowSet();
				OracleJoinRowSet jrs=new OracleJoinRowSet()){
			  //set properties
			  crs1.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			  crs1.setUsername("system");
			  crs1.setPassword("manager");
			  crs1.setCommand("SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP");
			  crs1.setMatchColumn(5);
			  crs1.execute();
			  
			  crs2.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			  crs2.setUsername("system");
			  crs2.setPassword("manager");
			  crs2.setCommand("SELECT DEPTNO,DNAME,LOC FROM DEPT");
			  crs2.setMatchColumn(1);
			  crs2.execute();
			   
			  //add  CachedRowsets  to JoinRowsets
			   jrs.addRowSet(crs2);
			   jrs.addRowSet(crs1);
			  
			  //process the rowset
			  while(jrs.next()) {
				  System.out.println(jrs.getString(1)+"  "+jrs.getString(2)+"  "+jrs.getString(3)+" "+jrs.getString(4)+" "+jrs.getString(5)+"  "+jrs.getString(6)+" "+jrs.getString(7));
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
