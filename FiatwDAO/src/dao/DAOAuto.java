package dao;

import java.sql.*;
import java.util.ArrayList;

import dao.DAOException;
import dao.TransactionManager;
import server.entity.*;
public class DAOAuto {
	
	
	private static DAOComponente dcomp;
	
	public static Auto create (TransactionManager tm,String targa,String modello) throws DAOException{
		Auto a = null;
		tm.assertInTransaction();
		
		try(PreparedStatement stat = tm.getConnection().prepareStatement(
                "INSERT INTO auto (TARGA,MODELLO) VALUES(?,?)")){
   		 
   		 stat.setString(1,targa);
   		 stat.setString(2,modello);	
   		 stat.executeUpdate(); 
   	 	}catch(SQLException e){
   	 		throw new DAOException("Impossibile inserire auto!", e);
   	 	}
		a = readAuto(tm,targa);
		return a;
	}
 
	public static Auto readAuto(TransactionManager tm, String targa)throws DAOException {
    	tm.assertInTransaction();
    	Auto auto = null;
    	try (PreparedStatement ps = tm.getConnection().prepareStatement("SELECT * FROM Auto WHERE targa  =?")){
    		ps.setString(1, targa);
    		try (ResultSet rs = ps.executeQuery()) {
    			if (rs.next() == true) {
    				int id = rs.getInt("id");
    				targa = rs.getString("targa");
    				String modello = rs.getString("modello");
    				int idConf = rs.getInt("idConfigurazioe");
    				auto = new Auto(id,targa,modello);
				}
    		}
    	} catch (SQLException e) {
			throw new DAOException("Impossibile leggere utente",e);
		}
    	if(auto==null) {
    		throw new DAOException("utente inesistente");
    	}
    	return auto;
    }
	
	//invoca per la comunicazione con il client
	public static Auto readAuto(TransactionManager tm, int id_auto)throws DAOException{
		Auto a=null;
		tm.assertInTransaction();
		 try (PreparedStatement preparedStatement = tm.getConnection().prepareStatement("SELECT * FROM Auto WHERE id=?")) {
			 preparedStatement.setInt(1, id_auto);
	    		 try (ResultSet rs = preparedStatement.executeQuery()) {
	    			 if (rs.next() == true){
	    				 String targa = rs.getString("targa");
		    			 String modello = rs.getString("modello");
		    			 int idConfigurazione = rs.getInt("Configurazione");
	    				 a = new Auto(id_auto,targa,modello);
	    			 }
	    		 }
	    	 }catch(SQLException e){
	    		 throw new DAOException("Impossibile trovare auto",e);
	    	 }
	    	if(a==null) {
	    		throw new DAOException("auto inesistente");
	    	}
	    	return a;	
	}
	
	public static ArrayList<Auto> readAll(TransactionManager tm)throws DAOException{
		ArrayList<Auto> listaAuto= new ArrayList<Auto>();
		tm.assertInTransaction();
		
		 try (PreparedStatement preparedStatement = tm.getConnection().prepareStatement("SELECT * FROM Auto")) {
    		 try (ResultSet rs = preparedStatement.executeQuery()) {
    			 while (rs.next() == true){
    				 int id = rs.getInt("id");
    				 String targa = rs.getString("targa");
	    			 String modello = rs.getString("modello");
	    			 int idConfigurazione = rs.getInt("Configurazione");
    				 Auto a = new Auto(id,targa,modello);
    				 listaAuto.add(a);
    			 }
    		 }
    	 }catch(SQLException e){
    		 throw new DAOException("Impossibile leggere le auto",e);
    	 }
		return listaAuto;
		
	}
	
	
	
	
	
	public  static ArrayList<Componente> readComponentiAuto(TransactionManager tm, Auto a) throws DAOException{
		ArrayList<Componente> listaComponenti = new ArrayList<Componente>();
		
		tm.assertInTransaction();
		Componente c = new Componente();
		try (PreparedStatement preparedStatement = tm.getConnection().prepareStatement("SELECT * FROM Configurabilita WHERE auto="+a.getId())) {
   		 try (ResultSet rs = preparedStatement.executeQuery()) {
   			 while (rs.next() == true){
   				 int idAuto = rs.getInt("auto");
   				 int idComponente = rs.getInt("componente");
	    		 int valoreComponente = rs.getInt("valoreComponente");
	    		
	    		 c = dcomp.readComponenteById(tm, idComponente);
  
   				 listaComponenti.add(c);
   			 }
   		 }
		}
		catch(SQLException e){
			throw new DAOException("Impossibile leggere le auto",e);
			}
		
		return listaComponenti;
		}
	
	
	
	
	public static void setValoreConfigurabilita(TransactionManager tm,Auto a, Componente c, int val) throws DAOException{
		
		tm.assertInTransaction();
		int idAuto = a.getId();
		int idComp = c.getId();
		try(PreparedStatement stat = tm.getConnection().prepareStatement(
                "UPDATE Configurabilita SET valoreComponente = ? WHERE auto=? AND componente=?")){
   		 
   		 stat.setInt(1,val);
   		 stat.setInt(2,idAuto);
   		 stat.setInt(3,idComp);
   		 stat.executeUpdate(); 
   	 	}catch(SQLException e){
   	 		throw new DAOException("Impossibile inserire valore  per auto"+a.getId()+" e componente"+c.getId(), e);
   	 	}
		
	}
	
	public static void setConfigurazioneAuto(TransactionManager tm,Auto a, Configurazione c) throws DAOException{
		
		tm.assertInTransaction();
		int idAuto = a.getId();
		
		try(PreparedStatement stat = tm.getConnection().prepareStatement(
                "UPDATE Auto SET Configurazione = ? WHERE id=?")){
   		 
   		 stat.setInt(1,c.getId());
   		 stat.setInt(2,idAuto);
   		 stat.executeUpdate(); 
   	 	}catch(SQLException e){
   	 		throw new DAOException("Impossibile inserire valore configurazione per auto"+a.getId()+" e componente"+c.getId(), e);
   	 	}

	}
	
	
	//getsComponent renzi
	/*
	 *
	 * try {
			PreparedStatement stat,stat2;
			stat = c.prepareStatement("SELECT * FROM Configurabilita WHERE auto="+a.getId());
			ResultSet res=stat.executeQuery(); 
			/*da configurabilita prendo gli id dei componenti
			faccio questo perch� per creare un oggetto configurabilita
			ho bisogno di un oggetto componente
			while(res.next()){
				idComp[i]=res.getInt("componente");
				i++;
			}
			for(int j=0;i<i;j++){
				stat2 = c.prepareStatement("SELECT * FROM Componenti WHERE id ="+idComp[j]);
				ResultSet res2=stat.executeQuery();
				//prendo gli oggetti componenti e con questo creo le configurabilita
				while(res2.next()){
					Componente comp = new Componente(res.getString("nome"),0);
					lista.add(new Configurabilita(a,comp));
				}
				
	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	 * 
	 * */
	 
	
	
	
		
}
