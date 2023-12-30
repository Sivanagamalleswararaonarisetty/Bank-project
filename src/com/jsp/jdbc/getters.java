package com.jsp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class getters {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter email:");
		String email=sc.next();
		System.out.println("Enter phone number:");
		String number=sc.next();
		String url="jdbc:mysql://localhost:3306/teca41?user=root&password=12345";
		String select="select * from userinformation where email=? and password=?";
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(select);
			ps.setString(1, email);
			ps.setString(2, number);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Random r=new Random();
				int otp = r.nextInt(1000);
				if (otp<1000) {
					otp+=1000;
					
				} 
				System.out.println("your otp is:"+otp);
				System.out.println("Enter otp :");
				int submit=sc.nextInt();
				if (otp==submit) {
					System.out.println("login sucessfull");
				} else {
					System.err.println("invalid otp");

				}
				
				
			} else {
				System.err.println("login unsucessfull");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
