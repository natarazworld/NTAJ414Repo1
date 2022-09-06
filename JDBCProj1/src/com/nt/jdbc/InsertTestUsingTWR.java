//InsertTestUsingTWR.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTestUsingTWR {

	public static void main(String[] args) {
	
		try (Scanner sc=new Scanner(System.in);
				 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				 Statement st=con.createStatement();
				){
			int no=0;
			String name=null,addrs=null;
			float avg=0.0f;
			if(sc!=null) {
				System.out.println("Enter student number::");
				no=sc.nextInt();  //gives 101
				System.out.println("Enter student name ::");
				name=sc.next();  //gives raja
				System.out.println("Enter student address:");
				addrs=sc.next();  //gives hyd
				System.out.println("Enter student avg::");
				avg=sc.nextFloat();  //gives 90.66
				}
			//convert input values as required for the  SQL Query
			name="'"+name+"'"; //gives  'raja'
			addrs="'"+addrs+"'"; //gives  'hyd'
			
			//prepare SQL Query
			// INSERT INTO STUDENT VALUES(103,'rakesh','hyd',78.99)
			String query="INSERT INTO STUDENT VALUES("+no+","+name+","+addrs+","+avg+")";
			System.out.println(query);
		
			
			//send and execute the SQL Query
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			//process the Result
			if(count==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record Inserted");
				
		}//try  --  all streams objs/jdbc objs createn in try with resource will be closed automatically
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
