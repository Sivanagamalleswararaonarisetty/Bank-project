package com.jsp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Debit {
	public static void main(String[] args)  {
		String url="jdbc:mysql://localhost:3306?user=root&password=12345";
		String select="SELECT * FROM teca41.bankdetails where mobilenumber=? and password=?";
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your phonenumber:");
		String phonenumber=sc.next();
		System.out.println("enter your password:");
		String password=sc.next();
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(select);
			ps.setString(1, phonenumber);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("Enter your amount");
				double amount=sc.nextDouble();
				if (amount !=0) {
					Double damount=rs.getDouble(4);
					Double add=damount-amount;
					String update="update teca41.bankdetails set amount=? where mobilenumber=?";
					PreparedStatement ps1 = connection.prepareStatement(update);
					ps1.setDouble(1, add);
					ps1.setString(2, phonenumber);
					int num = ps1.executeUpdate();
					if (num !=0) {
						System.out.println("amount debit sucessfully");
						
					}
				} else {
					System.err.println("insufficient funds");

				}
				
				
			} else {
				System.err.println("your essental invalid");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
