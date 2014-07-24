package com.app.framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class Utilities {
	
	// Get the current time in yyyy-MM-dd_hh-mm-ss format
	public  final static String getDateTime()  
	{  
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");  
	    //df.setTimeZone(TimeZone.getTimeZone("IST"));  
	    return df.format(new Date());  
	}
	
	// Generate random number
		public static long getRandomNumberByLength(int noOfDigits) {
			if (noOfDigits == 0)
				throw new IllegalArgumentException("Cannot return 0 length number.");
			return Long.parseLong(String.valueOf(noOfDigits < 1 ? 0 : new Random()
	        .nextInt((9 * (int) Math.pow(10, noOfDigits - 1)) - 1)
	        + (int) Math.pow(10, noOfDigits - 1)));
		}
		
		// Generate random string
		public static String getRandomStringByLength(int noOfCharacters) {
			String chars = "abcdefghijklmnopqrstuvwxyz";
			StringBuilder stringBuilder = new StringBuilder(noOfCharacters);
			Random rnd = new Random();
			for (int i = 0; i < noOfCharacters;i++)
				stringBuilder.append(chars.charAt(rnd.nextInt(chars.length())));
			return stringBuilder.toString();
		}
		
		public static ResultSet getRecordSet(String sServer, String sDataBaseName, String sUserName, String sPassword, String sQuery)
		{
			Connection con = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			
			String sConn = "jdbc:sqlserver://"+sServer+";databaseName="+sDataBaseName+";integratedSecurity=true;";
			
			try {
				con = DriverManager.getConnection(sConn, sUserName, sPassword);
				pst = con.prepareStatement(sQuery);
				rs = pst.executeQuery();	
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (pst != null) pst.close();
					if (con != null) con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
					
			return rs;
			
		}
}
