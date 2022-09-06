package com.nt.basics;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimeValuesConversionTest {

	public static void main(String[] args) throws Exception{
	   //converting String time  value to java.sql.Time obj
		String t1="10:25:45";  //hh:mm:ss
		java.sql.Time sqtime=java.sql.Time.valueOf(t1);
		System.out.println("sql time:"+sqtime);
		
		//converting  String date,time value to java.sql.Timestamp obj
		String dt1="20-10-1990 10:25:45";  //dd-MM-yyyy hh:mm:ss
		SimpleDateFormat  sdf=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		java.util.Date udt1=sdf.parse(dt1);
			java.sql.Timestamp sqdt1=new Timestamp(udt1.getTime());
		System.out.println("sql timestamp ::"+sqdt1);
		
		// if Stirng date,time values is in yyyy-MM-dd hh:mm:ss patttern then
		// it can be converted directly to  java.sql.Timestamp obj with out converting into  java.util.Date class obj
		String dt2="1990-10-23 10:25:45";  //yyyy-MM-dd hh:mm:ss
         java.sql.Timestamp sqdt2=java.sql.Timestamp.valueOf(dt2);
         System.out.println(sqdt2);
         
         //converting  java.sql.Time object  obj data as the String value
           long ms=sqdt1.getTime();  //  Timestamp obj data in the form of Milli Seconds
           java.util.Date ud1=new java.util.Date(ms);  //convert MilliSeconds into java.util.Date class obj
           SimpleDateFormat  sdf1=new SimpleDateFormat("hh:mm:ss MMM/yyyy/dd");
           String sd1=sdf1.format(ud1);  //converts  java.util.Date class obj to  string date value
           System.out.println("string date value ::"+sd1);
         

	}

}
