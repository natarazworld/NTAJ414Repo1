//LobsInsertionTest_MySQL.java
package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class LobsInsertionTest_MySQL {
	private static final String   ACTOR_INSERT_QUERY="INSERT INTO ACTOR_INFO(ANAME,PHOTO,PROFILE)  VALUES (?,?,?)";

	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in);
			   Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj414db1", "root", "root");
				PreparedStatement ps=con.prepareStatement(ACTOR_INSERT_QUERY);
				){
			   //read input values from enduser
			 String name=null, photoPath=null , profilePath=null;
			 if(sc!=null) {
				 System.out.println("Enter Actor name::");
				 name=sc.next();
				 System.out.println("Enter actor  photo Path::");
				 photoPath=sc.next().trim().replace("?", "");
				 System.out.println("Enter  actor  Profile Path::");
				 profilePath=sc.next().trim().replace("?", "");
			 }
			 // create Streams pointing to the files
			 try(InputStream is=new FileInputStream(photoPath);
					 Reader reader=new FileReader(profilePath) ){
				 
				
				 if(ps!=null) {
					 //set values to Query paams
					 ps.setString(1, name);
					 ps.setBinaryStream(2, is);
					 ps.setCharacterStream(3, reader);
					 //execute the Query
					 int count=ps.executeUpdate();
					 //process the Result
					  if(count==0)
						 System.out.println("Record not inserted");
					  else
						  System.out.println("record inserted");
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

}//class
