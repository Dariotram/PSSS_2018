package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBmanager {
	private static Connection con=null;
	//private Statement st=null;
	
	public static Connection getConnection(){
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prova?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false","root","1234");
			//st=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("qui");
			e.printStackTrace();
		}
		return con;


}}