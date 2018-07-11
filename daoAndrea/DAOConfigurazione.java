package dao;

import java.sql.*;
import java.util.ArrayList;

import server.entity.*;

public class DAOConfigurazione {
	
	private static DAOComponente dcomp;
	
	public static Configurazione create (TransactionManager tm, String nome) throws DAOException{
		Configurazione c = null;
		/*
		 * 
		 */
		return c;
	}
	
	
	public static ArrayList<Componente> readComponentiConfigurazione(TransactionManager tm, Configurazione conf) throws DAOException{
		ArrayList<Componente> listaComponenti = new ArrayList<Componente>();
		
		tm.assertInTransaction();
		
		try (PreparedStatement preparedStatement = tm.getConnection().prepareStatement("SELECT * FROM Settaggio WHERE configurazione="+conf.getId())) {
   		 try (ResultSet rs = preparedStatement.executeQuery()) {
   			 while (rs.next() == true){
   				 int idConf = rs.getInt("configurazione");
   				 int idComponente = rs.getInt("componente");
	    		 int valoreComponente = rs.getInt("valoreComponente");
	    		
	    		 Componente c = dcomp.readComponenteById(tm, idComponente);
 
   				 listaComponenti.add(c);
   			 }
   		 }
		}
		catch(SQLException e){
			throw new DAOException("Impossibile leggere i componenti della configurazione"+conf.getId(),e);
			}
		
		return listaComponenti;
		}

	
	
	/*
	public static Configurazione create(TransactionManager tm, String nome, ArrayList<Settaggio> lista){
		Configurazione conf= new Configurazione(nome,lista);
		int[] listaidComp = new int[100];
		
		
		tm.assertInTransaction();
		
		
		for(int i=0;i<lista.size();i++){
			try {
				PreparedStatement prep = tm.prepareStatement("SELECT id from Componenti WHERE nome="+lista.get(i).getNome());
				
				listaidComp[i]=rs.getInt("id");
				PreparedStatement prep2 = conn.prepareStatement("INSERT INTO Configurazioni values(?,?,?)");
				prep.setInt(1,conf.getId());
				prep.setString(2, nome);
				prep.setInt(3, listaidComp[i]);
				prep.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("Impossibile inserire configurazione!", e);
			}
			}
		return conf;
		
	}
	*/
}
