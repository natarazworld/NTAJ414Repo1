//CS_FunctionCallTest.java
package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE FUNCTION FX_GET_STUD_DETAILS_BY_SNO (  NO IN NUMBER , NAME OUT VARCHAR2 ) RETURN FLOAT
AS 
  SAVG FLOAT;
BEGIN
  SELECT SNAME,AVG INTO NAME,SAVG  FROM STUDENT WHERE SNO=NO;

  RETURN SAVG;
END FX_GET_STUD_DETAILS_BY_SNO;*/

public class CS_FunctionCallTest {
	 private static final String  CALL_FUNCTION="{?= call  FX_GET_STUD_DETAILS_BY_SNO(?,?) }";

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
				CallableStatement cs=con.prepareCall(CALL_FUNCTION);
				Scanner sc=new Scanner(System.in);		){
			  //read inputs
			int no=0;
			 if(sc!=null) {
				 System.out.println("enter  student number ::");
				 no=sc.nextInt();
				 
			 }//if
			 
			 if(cs!=null) {
				 //register return,OUT params with JDBC Data types
				 cs.registerOutParameter(1, Types.FLOAT); //return param
				 cs.registerOutParameter(3, Types.VARCHAR);  //out param
				 
				  //set value to IN param
				 cs.setInt(2, no);
				 
				 //call PL/SQL function
				 cs.execute();
				 
				 //Gather results from return ,OUT params
				 System.out.println(" Student AVG:: "+cs.getFloat(1));  // from return param
				 System.out.println(" Student Name::"+cs.getString(3));  //from OUT param
				 
			 }//if
			 
			
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()==1403)
				 System.out.println("Student not found");
			else
				System.out.println("unknown DB problems");
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	
	}//main

}//class
