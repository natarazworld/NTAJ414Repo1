//Cs_ProcedureCallTest2.java
package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/*CREATE OR REPLACE PROCEDURE P_GET_EMP_DETAILS_ENAME_CHARS 
(
  NAMECHARS IN VARCHAR2 
, DETAILS OUT SYS_REFCURSOR 
) AS 
BEGIN
    OPEN  DETAILS FOR
      SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE NAMECHARS;
END P_GET_EMP_DETAILS_ENAME_CHARS;*/



public class Cs_ProcedureCallTest2 {
	  private static final String CALL_PROCEDURE="{call P_GET_EMP_DETAILS_ENAME_CHARS (?,?) } ";

	public static void main(String[] args) {
	
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE);
				Scanner sc=new Scanner(System.in);
				){
			    //read inputs
			  String initChars=null;
			 if(sc!=null) {
				 System.out.println("Enter  employee name initial chars::");
				initChars=sc.next();
			 }
			 if(cs!=null) {
			 //register  OUT params with JDBC types
			 cs.registerOutParameter(2,OracleTypes.CURSOR);
		
			  //set values to IN params
			  cs.setString(1, initChars+"%");   // % indicates any chars
			   // call PL/SQL Procedure
			   cs.execute();
			   //gather ResultSet from OUT params
		            try(ResultSet rs=(ResultSet)cs.getObject(2)){
		            	if(rs!=null) {
		            		boolean isRSEmpty=false;
		            		while(rs.next()) {
		            			System.out.println(rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
		            			isRSEmpty=true;
		            		}//while
		            		if(!isRSEmpty)
		            			System.out.println("Records are not found");
		            		else
		            			System.out.println("records are found and displayed");
		            	}//if
		            }//try2
			 }//if
			 
			
		}//try1
		catch(SQLException se) {
			if(se.getErrorCode()==1403)
			     System.out.println("Emp number not found");
			else if(se.getErrorCode()==1017)
				System.out.println("Invalid credentilals");
			else
				System.out.println("Some DB problem");
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
