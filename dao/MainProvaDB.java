package dao;

import java.sql.SQLException;
import java.util.ArrayList;
/* Per il corretto funzionamento occorre aggiungere alle jar di progetto mysql-connector
 * inoltre occorre avere installato MYSQL con una tabella auto(id int, targa varchar(x)) 
 */
public class MainProvaDB {
	public static void main(String[] args) {
		try {
			AutoFasulla af= DAOAuto.create(4, "Renzi");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<AutoFasulla> al = new ArrayList<AutoFasulla>();
		al=DAOAuto.readAll();
		System.out.println("La prima macchina della lista ha targa: " +al.get(3).targa);
	}

}
