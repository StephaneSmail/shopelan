package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	private static String dbhost = "jdbc:mysql://localhost:3306/store?serverTimezone=EST5EDT";
	private static String dbuser = "root";
	private static String dbpass = "";
	
	public static Connection connect() throws SQLException{  
		Connection con = null;
		try{  
			
			Class.forName("com.mysql.cj.jdbc.Driver");  
			
			con = DriverManager.getConnection(dbhost,dbuser,dbpass);  
		}
		catch(Exception e){ 
			System.out.println(e);
		}
		return con;
	}  
	
}
