//SelectTest3.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest3 {

	public static void main(String[] args) {

		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// read inputs
			sc = new Scanner(System.in);
			int deptno = 0;
			if (sc != null) {
				System.out.println("Enter  Dept number::");
				deptno = sc.nextInt();
			}
			// Load jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// establish the connection with Db s/w
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			// create Statement object
			if (con != null)
				st = con.createStatement();

			// prepare SQL Query
			// SELECT * FROM DEPT WHERE DEPTNO=10
			String query = "SELECT * FROM DEPT WHERE DEPTNO=" + deptno;
			System.out.println(query);
			// send and execute the SQL Query in DB s/w
			if (st != null)
				rs = st.executeQuery(query);
			// process the ResultSet
			if (rs != null)
				if (rs.next())
					System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
				else
					System.out.println("Record not found");

		} // try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close jdbc objs
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (sc != null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // finally

	}// main

}// class
