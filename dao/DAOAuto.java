package dao;

import java.sql.*;
import java.util.ArrayList;


public class DAOAuto {
	public static AutoFasulla create (int id, String targa) throws SQLException{
		AutoFasulla a = new AutoFasulla(id,targa);
		Connection c = DBmanager.getConnection();
		PreparedStatement stat = c.prepareStatement("INSERT INTO Auto (ID,TARGA) VALUES(?,?)");
		stat.setInt(1, id);
		stat.setString(2, targa);
		stat.executeUpdate();
		stat.close();
		
		return a;
		
	}

	public static ArrayList<AutoFasulla> readAll(){
		ArrayList<AutoFasulla> lista= new ArrayList<AutoFasulla>();
		
		try {
			Connection c = DBmanager.getConnection();
			PreparedStatement stat = c.prepareStatement("SELECT * FROM AUTO");
			ResultSet res=stat.executeQuery();
			
			while(res.next()){
						lista.add(new AutoFasulla(res.getInt("ID"),res.getString("TARGA")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return lista;
		
	}
}
