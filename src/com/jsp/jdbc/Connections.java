package com.jsp.jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;


public class Connections {

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/teca41?user=root&password=12345";
		String Insert ="insert into dept values(3, 'Admistration',40)";
		try {
		Connection connection = DriverManager.getConnection(url);
		System.out.println("Connection Established");
		Statement cs = connection.createStatement();
		System.out.println("State is created");
		cs.execute(Insert);
		 System.out.println("Query Executed...!"); 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
