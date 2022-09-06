// LOBsRetrieveTest_Oracle.java
package com.nt.jdbc;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class LOBsRetrieveTest_Oracle {
	 private static final String GET_ACTOR_INFO_BY_ID="SELECT * FROM ACTOR_INFO WHERE AID=?";

	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in);
				Connection  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
				 PreparedStatement ps=con.prepareStatement(GET_ACTOR_INFO_BY_ID);
				){
			
			 int aid=0;
			 if(sc!=null) {
			   System.out.println("Enter Actor Id::");
			   aid=sc.nextInt();
			 }
			   //set value to Query param
			    if(ps!=null) 
			    	ps.setInt(1, aid);
			    //execute the Query
			    try(ResultSet rs=ps.executeQuery()){
			    	  
			    	if(rs!=null) {
			    	if(rs.next()) {
			    		int id=rs.getInt(1);
			    		String name=rs.getString(2);
			    		try(	  //read Lobs from ResultSet as streams
			    				InputStream is=rs.getBinaryStream(3);
			    	         	Reader reader=rs.getCharacterStream(4);
			    				  //create empty destination files using streams
			    				OutputStream os=new FileOutputStream("retrieve_photo.jpeg");
			    				Writer writer=new FileWriter("retrieve_profile.txt");
			    				){
			    			   //  copy  LOBs collected from Db table to destination files
			    			         IOUtils.copy(is,os);
			    			         IOUtils.copy(reader, writer);
			    			         System.out.println("actor Info:"+id+"  "+name+"  LOBs are retrieved");
			    		}//try3
			    	  }//if
			    	else {
			    		System.out.println("Actor not found");
			    	}
			    	}//if
			    }//try2
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//main
