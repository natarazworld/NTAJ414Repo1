//SelectTest_Mysql.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest_Mysql {

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///NTAJ414DB", "root", "root");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("SELECT * FROM PRODUCT");
				){
			   //process the ResultSet obj
			    if(rs!=null) {
			    	boolean rsEmpty=true;
			    	while(rs.next()) {
			    		 rsEmpty=false;
			    		System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+"  "+rs.getFloat(4));
			    	}//while
			    	if(rsEmpty)
			    		System.out.println("No records found");
			    	else
			    		System.out.println("Records found and displayed");
			    }//if
			   System.out.println("con objetct clas sname::"+con.getClass());
			   System.out.println("st objetct clas sname::"+st.getClass());
			   System.out.println("rs objet class name::"+rs.getClass());
			   
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main

}//class
