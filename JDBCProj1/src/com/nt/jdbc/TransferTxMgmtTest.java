//TransferTxMgmtTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TransferTxMgmtTest {
	private static final String GET_BALANCE_BY_ACNO="SELECT BALANCE FROM JDBC_ACCOUNT_INFO WHERE ACNO=?";

	public static void main(String[] args) {
	    
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "manager");
				Statement st=con.createStatement();
				PreparedStatement ps=con.prepareStatement(GET_BALANCE_BY_ACNO);
				 Scanner sc=new Scanner(System.in);
				){
			long srcNo=0,destNo=0;
			float amount=0.0f;
			 if(sc!=null) {
				 System.out.println("Enter source Account number ::");
				 srcNo=sc.nextLong();
				 System.out.println("Enter Dest Account number ::");
				 destNo=sc.nextLong();
				 System.out.println("Enter  Amount to transfer::");
				 amount=sc.nextFloat();
			 }
			 //set value to query param
			  if(ps!=null) {
				  ps.setLong(1, srcNo);
				  }
			 try(ResultSet rs=ps.executeQuery()){
				    float balance=0.0f;
				      if(rs.next()) {
				    	  balance=rs.getFloat(1);
				    	  if(amount>balance) {
				    		  System.out.println(" Insufficient  funds in the source account(Tx aborted)");
				    		  return;
				    	  }//if
				    		  
				      }//if
				      else {
				    	  System.out.println("Source account not found");
				      }//else
			 }//try
			 
			  //Begin Tx
			  if(con!=null)
				  con.setAutoCommit(false);
			  
			  if(st!=null) {
			  // add Queries to the batch
			               //for withdraw operation
			       st.addBatch("UPDATE JDBC_ACCOUNT_INFO SET BALANCE=BALANCE-"+amount+" WHERE ACNO="+srcNo);
	                      //for deposite operation
				   st.addBatch("UPDATE JDBC_ACCOUNT_INFO SET BALANCE=BALANCE+"+amount+" WHERE ACNO="+destNo);
				 // execute the Batch
				 int result[]=st.executeBatch();
				
				 //process the results  by applying  TxMgmt
				  if(result!=null) {
					     boolean taskFlag=true;
					     
					     for(int i=0;i<result.length;++i) {
					    	 if(result[i]==0) {
					    		 taskFlag=false;
					    		 break;
					    	 }//if
					     }//for
					     
					     if(taskFlag) {
					    	con.commit();
					    	System.out.println("Transaction Committed( Money Transffered)");
					     }
					     else {
					    	 con.rollback();
					    	 System.out.println("Transaction not Committed(rolledback)( Money not Transffered)");
					      
					     }//else
					  
				  }//if
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
