//DBCapabilitiesTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBCapabilitiesTest {

	public static void main(String[] args) {
		
		try(Connection  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				//Connection  con=DriverManager.getConnection("jdbc:mysql:///ntaj414db1","root","root");
				){
			
			 //create Database MetaData object
			  DatabaseMetaData  dbmd=null;
			  if(con!=null) {
				  dbmd=con.getMetaData();
			  }
			  // get More info about Db s/w
			   if(dbmd!=null) {
				   System.out.println(" db s/w name ::"+dbmd.getDatabaseProductName());
				   System.out.println("DB s/w version::"+dbmd.getDatabaseProductVersion());
				   System.out.println("DB s/w version::"+dbmd.getDatabaseMajorVersion()+"."+dbmd.getDatabaseMinorVersion());
				   System.out.println(" JDBC driver name ::"+dbmd.getDriverName());
				   System.out.println(" JDBC version::"+dbmd.getJDBCMajorVersion()+" ."+dbmd.getJDBCMinorVersion());
				   System.out.println(" Max Connections count::"+dbmd.getMaxConnections());
				   System.out.println("  Max db table name length::"+dbmd.getMaxTableNameLength());
				   System.out.println(" Max username length ::"+dbmd.getMaxUserNameLength());
				   System.out.println(" MAx Columns count in a dbable ::"+dbmd.getMaxColumnsInTable());
				   System.out.println("Max Row size ::"+dbmd.getMaxRowSize());
				   System.out.println(" Supports Stored Procedures ??"+dbmd.supportsStoredProcedures());
				   System.out.println(" All numeric functions ::"+dbmd.getNumericFunctions());
				   System.out.println("All System functions ::"+dbmd.getSystemFunctions());
				   System.out.println("All SQL keywords ::"+dbmd.getSQLKeywords());
				   
			   }
			  
			
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
