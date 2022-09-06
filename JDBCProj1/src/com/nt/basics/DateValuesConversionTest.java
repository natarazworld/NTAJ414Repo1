package com.nt.basics;

import java.text.SimpleDateFormat;

public class DateValuesConversionTest {

	public static void main(String[] args)throws Exception {
		// Coverting  String date vlaue to java.util.Date class obj
		String d1="45-20-1990";   //dd-MM-yyyy
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");  //holds the date pattern
		java.util.Date ud1=sdf.parse(d1); //parse(-) takes give d1 string date value in dd-MM-yyyy
		                                                                  //as speficied in SimpleDateFormat class obj
		 System.out.println("util Date::"+ud1);
		 
		 //converting  java.util.Date class obj  to java.sql.Date class obj
		 long ms=ud1.getTime();  //gives ud1 obj date-time value in the form of milliseconds
		                                                 //as elapsed between jan 1st 1970 00:00 hours to java.util.Date class obj date,time
		 java.sql.Date sqd=new java.sql.Date(ms);
		 System.out.println("sql date::"+sqd);
		 
		 //converting  String date value of yyyy-MM-dd pattern directly to java.sql.Date class obj
		 String d2="1990-11-20"; // yyyy-MM-dd
		 java.sql.Date sqd1=java.sql.Date.valueOf(d2);
		 System.out.println("sql date ::"+sqd1);
		 
		 //converting sql/util date to  String date value having our choice date format
		  SimpleDateFormat sdf2=new SimpleDateFormat("MMM-dd-yyyy");
		   String sd2=sdf2.format(sqd1);
		   System.out.println("String date::"+sd2);
		 
		 
		
	}

}
