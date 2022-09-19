//ResultSetMetaDataTest1.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataTest1 {

	public static void main(String[] args) {
		
		try(//Connection  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				 Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj414db1", "root", "root");
				 Statement  st=con.createStatement();
				ResultSet rs=st.executeQuery("SELECT * FROM PRODUCT");
				){
			ResultSetMetaData rsmd=null;
			 if(rs!=null) 
				 rsmd=rs.getMetaData();
			 
			if( rsmd!=null) {
				//get column count 
				int colCount=rsmd.getColumnCount();
				
				//display  more  details about each col of db table
				for(int i=1;i<=colCount;++i) {
					System.out.println("Column index::"+i);
					System.out.println("Column name::"+rsmd.getColumnName(i));
					System.out.println("Column Data type Name ::"+rsmd.getColumnTypeName(i));
					System.out.println("Column scale :: "+rsmd.getScale(i));
					System.out.println("Column precision::"+rsmd.getPrecision(i));
					System.out.println("Is column  signed::"+rsmd.isSigned(i));
					System.out.println("Is column  AutoIncrement::"+rsmd.isAutoIncrement(i));
					System.out.println("Is column Nullable ::"+rsmd.isNullable(i));
					System.out.println("Is column currency ::"+rsmd.isCurrency(i));
					System.out.println(" Is column  Searchable ::"+rsmd.isSearchable(i));
					System.out.println(" Column display size::"+rsmd.getColumnDisplaySize(i));
					System.out.println(" Is column writable ::"+rsmd.isWritable(i));
					System.out.println("_______________________________________");
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
