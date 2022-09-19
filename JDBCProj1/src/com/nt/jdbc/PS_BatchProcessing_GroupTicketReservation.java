//PS_BatchProcessing_GroupTicketReservation.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.xml.transform.Source;

public class PS_BatchProcessing_GroupTicketReservation {
	private static final String  GROUP_TICKET_RESERVATION="INSERT INTO JDBC_TRAIN_JOURNEY VALUES(TKT_ID_SEQ.NEXTVAL,?,?,?,?,?,?)";

	public static void main(String[] args) {
		
		try(Connection  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				PreparedStatement ps=con.prepareStatement(GROUP_TICKET_RESERVATION);
				Scanner  sc=new Scanner(System.in);
				){
			  int  groupSize=0;
			  String  srcPlace=null,destPlace=null;
			  int trainNo=0;
			  boolean isItGroupReservation=false;
			  String dtoj=null;
			  java.sql.Timestamp sqdtoj=null;
			  if(sc!=null) {
				  System.out.println("Enter group size::");
				  groupSize=sc.nextInt();
				  System.out.println("Enter source place::");
				  srcPlace=sc.next();
				  System.out.println("Enter Dest place::");
				  destPlace=sc.next();
				  System.out.println("Enter train no::");
				  trainNo=sc.nextInt();
				  System.out.println("Enter  Date and time of jounery(dd/MM/yyyy hh:mm:ss) ::");
				  sc.nextLine();
				   dtoj=sc.nextLine();
				  System.out.println("Is it group reservation?");
				  isItGroupReservation=sc.nextBoolean();
				  
				// //convert  String Date and time of Jounery  to  java.sql.Timestamp obj
					 SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					 java.util.Date udtoj=sdf1.parse(dtoj);   //  String date to util date
					sqdtoj=new java.sql.Timestamp(udtoj.getTime());  //util  date to sql Timestamp obj
			  }
			  //read  group of  passengers info and add them to batch of query params
			  if(ps!=null && sc!=null) {
				    for(int i=1;i<=groupSize;++i) {
				    	System.out.println("Enter "+i+" Passegener  name");
				    	String name=sc.next();
				    	//add each set of query param values (passegener ,journery details ) to batch of batch processing
				    	ps.setString(1, name);
				    	ps.setString(2, srcPlace);
				    	ps.setString(3,destPlace);
				    	ps.setBoolean(4, isItGroupReservation);
				    	ps.setInt(5, trainNo);
				    	ps.setTimestamp(6, sqdtoj);
				    	ps.addBatch();
				    }
				    //execute the query with batch query param values
				    int result[]=ps.executeBatch();
				    
				    //process the result[]
				      if(result!=null)
				    	  System.out.println("Group ticket booking is done for "+groupSize+"  passengers");
				      else
				    	  System.out.println("Group ticket booking is not done");
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
