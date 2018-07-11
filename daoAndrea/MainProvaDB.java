package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
/* Per il corretto funzionamento occorre aggiungere alle jar di progetto mysql-connector
 * inoltre occorre avere installato MYSQL con una tabella auto(id int, targa varchar(x)) 
 */
public class MainProvaDB {
	public static void main(String[] args) {
		try {
			Random rand = new Random();
			int  n = rand.nextInt(50) + 1;

			AutoFasulla af= DAOAuto.create(n, "Renzi");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<AutoFasulla> al = new ArrayList<AutoFasulla>();
		al=DAOAuto.readAll();
		System.out.println("La prima macchina della lista ha targa: " +al.get(1).targa+"ed il suo id e':"+ al.get(1).id);
	}

}
