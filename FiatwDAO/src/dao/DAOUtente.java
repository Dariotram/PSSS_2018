package dao;

import java.sql.*;
import java.util.ArrayList;

import dao.DAOException;
import dao.TransactionManager;
import server.entity.*;

public class DAOUtente {
	 
	
	
	public static Utente create(TransactionManager tm, String nome,String p,String email) throws DAOException{
		
		tm.assertInTransaction();
		
		try(PreparedStatement stat = tm.getConnection().prepareStatement(
                "INSERT INTO Utente (nome,password,email) VALUES(?,?,?)")){
   
   		 stat.setString(1,nome);
   		 stat.setString(2,p);
   		 stat.setString(3,email);
   		 stat.executeUpdate(); 
   	 	}catch(SQLException e){
   	 		throw new DAOException("Impossibile inserire utente!", e);
   	 	}
		  
		
		Utente u = readUtente(tm,email);
		return u;
		
	}
	
	
	
	public static Utente readUtente(TransactionManager tm, String email)throws DAOException {
    	tm.assertInTransaction();
    	Utente utente = null;
    	try (PreparedStatement ps = tm.getConnection().prepareStatement("SELECT * FROM Utente WHERE email  =?")){
    		ps.setString(1, email);
    		try (ResultSet rs = ps.executeQuery()) {
    			if (rs.next() == true) {
    				int id = rs.getInt("id");
    				String nome = rs.getString("nome");
    				String password = rs.getString("password");
    				email = rs.getString("email");
    				utente = new Utente(id,nome,password,email);
				}
    		}
    	} catch (SQLException e) {
			throw new DAOException("Impossibile leggere utente",e);
		}
    	if(utente==null) {
    		throw new DAOException("utente inesistente");
    	}
    	return utente;
    }
	
	public static ArrayList<Auto> readAutoUtente(TransactionManager tm, Utente u)throws DAOException{
		ArrayList<Auto> listaAuto= new ArrayList<Auto>();
		tm.assertInTransaction();
		
		 try (PreparedStatement preparedStatement = tm.getConnection().prepareStatement("SELECT * FROM Proprieta p JOIN  Auto a ON (p.auto=a.id) WHERE p.utente="+u.getId())){
    		 try (ResultSet rs = preparedStatement.executeQuery()) {
    			 while (rs.next() == true){
    				 int id = rs.getInt("auto");
    				 String targa = rs.getString("targa");
	    			 String modello = rs.getString("modello");
	    			 int idConfigurazione = rs.getInt("Configurazione");
    				 Auto a = new Auto(id, targa,modello);
    				 listaAuto.add(a);
    			 }
    		 }
    	 }catch(SQLException e){
    		 throw new DAOException("Impossibile leggere le auto dell'utente ",e);
    	 }
		return listaAuto;
		
	}
	
	public static ArrayList<Configurazione> readConfigurazioniUtente(TransactionManager tm, Utente u)throws DAOException{
		ArrayList<Configurazione> listaConf= new ArrayList<Configurazione>();
		tm.assertInTransaction();
		u.getId();
		 try (PreparedStatement preparedStatement = tm.getConnection().prepareStatement("SELECT * FROM Configurazione WHERE utente="+u.getId())){
    		 try (ResultSet rs = preparedStatement.executeQuery()) {
    			 while (rs.next() == true){
    				 int id = rs.getInt("idConfigurazione");
    				 String nome = rs.getString("nome");
	    			 int idUtente = rs.getInt("utente");
    				 Configurazione c = new Configurazione(id,nome);
    				 listaConf.add(c);
    			 }
    		 }
    	 }catch(SQLException e){
    		 throw new DAOException("Impossibile leggere le auto dell'utente ",e);
    	 }
		return listaConf;
		
	}
	
	
	
public static Proprieta createProprieta (TransactionManager tm,Utente u, Auto a) throws DAOException{
		
		Proprieta prop = new Proprieta(a,u);
		tm.assertInTransaction();
		
		try(PreparedStatement stat = tm.getConnection().prepareStatement(
                "INSERT INTO Proprieta VALUES(?,?,?)")){
   
		stat.setInt(1, u.getId());
		stat.setInt(2, a.getId());
   		 stat.executeUpdate(); 
   	 	}catch(SQLException e){
   	 		throw new DAOException("Impossibile inserire proprieta!", e);
   	 	}
		  
		return prop;
		
	}
	
	

/*
	public static ArrayList<Configurazione> getAllConf(Utente u){
		//si può migliorare questo metodo
		ArrayList<Configurazione> lista = new ArrayList<Configurazione>();
		ArrayList<Componente> listaComp = new ArrayList<Componente>();
		int[] listaIDConf = new int[100];
		int[] listaIDComp = new int[100];
		int[] listaValComp = new int[100];
		int i,a,b;
		i=a=b=0;
		try{ //prelevo tutti gli id di configurazioni dell'utente
			Connection conn= DBmanager.getConnection();
			PreparedStatement prep= conn.prepareStatement("SELECT idConfigurazione FROM Configurazioni WHERE utente="+u.getId());
			ResultSet res = prep.executeQuery();
			while(res.next()){
				listaIDConf[i]=res.getInt("idConfigurazione");
				i++;
			}
			for(int j=0;j<i;j++){ //prelevo tutti i valori associati ai
				//componenti di ogni configurazione con i rispettivi id
				PreparedStatement prep2= conn.prepareStatement("SELECT Componente FROM Settaggio WHERE configurazione="+listaIDConf[j]);
				ResultSet res2 = prep2.executeQuery();
				a=0;
				while(res2.next()){
					listaIDComp[a]=res.getInt("componente");
					listaValComp[a]=res.getInt("valoreComponente");
					a++;
				}
				//prelevo i nomi dei componenti e creo la lista componenti per ogni conf
				PreparedStatement prep3 =conn.prepareStatement("SELECT nome FROM COMPONENTI WHERE ID="+listaIDComp);
				ResultSet res3= prep3.executeQuery();
				while(res3.next()){
					listaComp.add(new Componente(res.getString("nome"),listaValComp[b]));
					b++;
				}
				//creo una nuova configurazione con listaComp
				lista.add(new Configurazione(listaComp));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return lista;
		
	}

		*/
	

}
