package dao;

import java.sql.*;
import java.util.ArrayList;

import server.entity.*;

public class DAOComponente {
	
	public static Componente readComponenteById(TransactionManager tm, int id) throws DAOException{
		tm.assertInTransaction();
    	Componente comp = null;
    	try (PreparedStatement ps = tm.getConnection().prepareStatement("SELECT * FROM Componente WHERE id  =?")){
    		ps.setInt(1, id);
    		try (ResultSet rs = ps.executeQuery()) {
    			if (rs.next() == true) {
    				id = rs.getInt("id");
    				String nome = rs.getString("nome");
    				comp = new Componente(id,nome);
				}
    		}
    	} catch (SQLException e) {
			throw new DAOException("Impossibile leggere componente",e);
		}
    	if(comp==null) {
    		throw new DAOException("componente inesistente");
    	}
    	return comp;

	}

}
