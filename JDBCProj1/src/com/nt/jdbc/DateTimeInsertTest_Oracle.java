//DateTimeInsertTest_Oracle.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;



public class DateTimeInsertTest_Oracle {
	private final  static String INSERT_CUSTOMER_QUERY="INSERT INTO CUSTOMER_INFO VALUES(CNO_SEQ.NEXTVAL,?,?,?,?,?)";

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				PreparedStatement ps=con.prepareStatement(INSERT_CUSTOMER_QUERY);
				Scanner sc=new Scanner(System.in); ){
			//read input vlaues
			String name=null, sdob=null,stop=null, sorderdt=null;
			float billamt=0.0f;
			if(sc!=null) {
				System.out.println("Enter customer name::");
				 name=sc.next();
				 System.out.println("Enter customer bill amount::");
				 billamt=sc.nextFloat();
				 System.out.println("Enter DOB(dd-MM-yyyy)::");
				 sdob=sc.next();
				 System.out.println("Enter TOP(hh:mm:ss)");
				 stop=sc.next();
				 System.out.println("Enter Order Date Time(dd/MM/yyyy hh:mm:ss ::");
				   sc.nextLine();
				 sorderdt=sc.nextLine();
			}
			//convert  String DOB to  java.sql.Date class obj
			 SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			 java.util.Date udob=sdf.parse(sdob);   //  String date to util date
			 java.sql.Date sqdob=new java.sql.Date(udob.getTime());  // util  date to sql date
			 
			 //convert String TOP (Time of purcahse) to  java.sql.Time obj
			 java.sql.Time sqtop=java.sql.Time.valueOf(stop);
			 
			 //convert  String Order date time to  java.sql.Timestamp obj
			 SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			 java.util.Date uorderdt=sdf1.parse(sorderdt);   //  String date to util date
			 java.sql.Timestamp sqorderdt=new java.sql.Timestamp(uorderdt.getTime());  //util  date to sql Timestamp obj
			
			//set values SQL Query params
			 if(ps!=null) {
				 ps.setString(1, name);
				 ps.setFloat(2, billamt);
				 ps.setDate(3, sqdob);
				 ps.setTime(4, sqtop);
				 ps.setTimestamp(5, sqorderdt);
				 
				 //execute the SQL query
				 int count=ps.executeUpdate();
				 
				 if(count==0)
					 System.out.println("Record not inserted");
				 else
					 System.out.println("Record inserted");
			 }
		    
			
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}//main

}//class
