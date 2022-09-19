//ParameterMetaDataTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParameterMetaDataTest {
	 private static final String INSERT_QUERY="INSERT INTO PRODUCT VALUES(?,?,?,?)";

	public static void main(String[] args) {
		
	try(	Connection  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Connection  con=DriverManager.getConnection("jdbc:mysql:///ntaj414db1","root","root");
		    PreparedStatement ps=con.prepareStatement(INSERT_QUERY);
		 ){
		 ParameterMetaData pmd=null;
		  if(ps!=null) {
			  pmd=ps.getParameterMetaData();
		  }
		   if(pmd!=null) {
			   //get parameters count
			    int paramsCount=pmd.getParameterCount();
			    for(int i=1;i<=paramsCount;++i) {
			    	System.out.println("parameter index::"+i);
			    	System.out.println("param mode::"+pmd.getParameterMode(i));
			    	System.out.println("is param signed?"+pmd.isSigned(i));
			    	System.out.println(" is param nullable ?"+pmd.isNullable(i));
			    	System.out.println(" param scale ::"+pmd.getScale(i));
			    	System.out.println( "param precision::"+pmd.getPrecision(i));
			    	
			    }//for
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
