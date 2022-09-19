//ResultSetMetaDataTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataTest {

	public static void main(String[] args) {
		
		try(Connection  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				 Statement  st=con.createStatement();
				ResultSet rs=st.executeQuery("SELECT * FROM STUDENT");
				){
			ResultSetMetaData rsmd=null;
			 if(rs!=null) {
				 rsmd=rs.getMetaData();
			 }
			if(rs!=null && rsmd!=null) {
				 //get cols count
				 int colCount=rsmd.getColumnCount();
				 //display col names
				 for(int i=1;i<=colCount;++i) {
					 System.out.print(rsmd.getColumnName(i)+"\t\t ");
				 }
				 System.out.println();
				 //display col types
				 for(int i=1;i<=colCount;++i) {
					 System.out.print(rsmd.getColumnTypeName(i)+"\t ");
				 }
				 System.out.println();
				 
				//display col vlaues
				while(rs.next()) {
					for(int i=1;i<=colCount;++i) {
						System.out.print(rs.getString(i)+"\t\t\t");
					}//for
					System.out.println();
				}//while
			}//if
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
