//Cs_ProcedureCallTest1.java
package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;



/*CREATE OR REPLACE PROCEDURE P_GET_EMP_DETAILS_BY_EMPNO 
(
  NO IN NUMBER
, NAME OUT VARCHAR2 
, SALARY OUT FLOAT
) AS 
BEGIN
    SELECT ENAME,SAL INTO NAME,SALARY FROM EMP WHERE EMPNO=NO;
  
END P_GET_EMP_DETAILS_BY_EMPNO;*/

public class Cs_ProcedureCallTest1 {
	  private static final String CALL_PROCEDURE="{call P_GET_EMP_DETAILS_BY_EMPNO(?,?,?) } ";

	public static void main(String[] args) {
	
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE);
				Scanner sc=new Scanner(System.in);
				){
			    //read inputs
			  int no=0;
			 if(sc!=null) {
				 System.out.println("Enter  employee number::");
				 no=sc.nextInt();
			 }
			 if(cs!=null) {
			 //register  OUT params with JDBC types
			 cs.registerOutParameter(2,Types.VARCHAR);
			 cs.registerOutParameter(3, Types.FLOAT);
			  //set values to IN params
			  cs.setInt(1, no);
			   // call PL/SQL Procedure
			   cs.execute();
			   //gather results from OUT params
			   System.out.println(" EMP NAME::"+cs.getString(2));
			   System.out.println("EMP SALARY ::"+cs.getFloat(3));
			 }//if
			 
			
		}//try
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
