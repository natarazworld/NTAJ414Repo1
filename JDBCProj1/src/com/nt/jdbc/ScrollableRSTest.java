//ScrollableRSTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableRSTest {

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				//Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);	
				  Statement st=con.createStatement();	
				
						ResultSet rs=st.executeQuery("SELECT * FROM STUDENT")){
			   if(rs!=null) {
				    System.out.println("Records display (top to bottom)");
				    while(rs.next()) {
				    	System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				    }
				    
				    rs.afterLast();
				    
				    System.out.println("Records display ( bottom to top)");
				    while(rs.previous()) {
				    	System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				    }
				    
				    // displaying  records randomly or directly
				    System.out.println("Displaying the records directly or randomly");
				    rs.first();
				    System.out.println(rs.getRow()+"-------->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				    rs.last();
				    System.out.println(rs.getRow()+"-------->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				    rs.absolute(3);
				    System.out.println(rs.getRow()+"-------->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				    rs.absolute(-5);
				    System.out.println(rs.getRow()+"-------->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				    rs.relative(3);
				    System.out.println(rs.getRow()+"-------->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				    rs.relative(-4);
				    System.out.println(rs.getRow()+"-------->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			   }
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
