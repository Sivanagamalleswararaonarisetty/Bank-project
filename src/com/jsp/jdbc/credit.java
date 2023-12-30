package com.jsp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class credit {
	public static void main(String[] args)  {
		String url="jdbc:mysql://localhost:3306/teca41?user=root&password=12345";
		String select="select * from bankdetails where mobilenumber=? and password=?";
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(select);
			Scanner sc=new Scanner(System.in);
			System.out.println("enter your phonenumber:");
			String number=sc.next();
			System.out.println("enter your password: ");
			String password=sc.next();
			ps.setString(1, number);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
			     System.out.println("Enter amount :");
			     double amount=sc.nextDouble();
			     if (amount>0) {
			    	 Double damount=rs.getDouble(4);
			    	 Double add=damount+amount;
			    	 String update="update bankdetails set amount=? where mobilenumber=? ";
			    	 PreparedStatement ps1 = connection.prepareStatement(update);
			    	 ps1.setDouble(1, add);
			    	 ps1.setString(2, number);
			    	 int num = ps1.executeUpdate();
			    	 if (num !=0) {
			    		 System.out.println("amount add sucessfully...");
						
					} else {
						System.err.println("amount add unsucessfully....");

					}
					
				} else {
					System.err.println("insufficient funds");

				}
				
			} else {
				System.err.println("invalid user name and password");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
