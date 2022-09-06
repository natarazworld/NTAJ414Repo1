//Cs_ProcedureCallTest.java
package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*create or replace  procedure  p_sum_data( x in number, y in number , z out number)
as
begin
   z:=x+y;
end;
/*/


public class Cs_ProcedureCallTest {
	private static final String  CALL_PROCEDURE="{call  p_sum_data(?,?,?)}";

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE);
				Scanner sc=new Scanner(System.in);
				){
			//read inputs
			int val1=0,val2=0;
			 if(sc!=null) {
				 System.out.println("Enter value1::");
				 val1=sc.nextInt();
				 System.out.println("Enter Value2::");
				 val2=sc.nextInt();
			 }
			
			 if(cs!=null) {
				 //register OUT params with  JDBC type (All out ,return params must be registered)
				 cs.registerOutParameter(3, Types.INTEGER);
				 //set values to IN params
				 cs.setInt(1,val1);
				 cs.setInt(2, val2);
				 // CALL PL/SQL procedure
				 cs.execute();
				 //gather results from OUT params
				 int result=cs.getInt(3);
				 System.out.println("Result ::"+result);
			 }//if
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
