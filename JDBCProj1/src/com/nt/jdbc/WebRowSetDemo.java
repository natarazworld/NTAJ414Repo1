package com.nt.jdbc;

import java.io.FileWriter;
import java.sql.SQLException;

import oracle.jdbc.rowset.OracleWebRowSet;

public class WebRowSetDemo {
	
	public static void main(String[] args) {
		
		try(OracleWebRowSet wrowset=new OracleWebRowSet()){
			  //set properties
			  wrowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			  wrowset.setUsername("system");
			  wrowset.setPassword("manager");
			  wrowset.setCommand("SELECT EMPNO,ENAME,JOB,SAL FROM EMP");
			  wrowset.execute();
			  
			  //process the rowset
			  while(wrowset.next()) {
				  System.out.println(wrowset.getInt(1)+"  "+wrowset.getString(2)+"  "+wrowset.getString(3)+" "+wrowset.getFloat(4));
			  }
			  //write  the Rowset records as the xml content to a file
		     wrowset.writeXml(new FileWriter("emp.xml"));
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}//main

}//class
