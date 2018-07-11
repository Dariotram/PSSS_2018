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
                "INSERT INTO auto (TARGA,MODELLO) VALUES(?,?,?)")){
   		 
   		 stat.setString(2,targa);
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
    	try (PreparedStatement ps = tm.getConnection().prepareStatement("SELECT * FROM auto WHERE targa  =?")){
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
	
	
	
	public static ArrayList<Auto> readAll(TransactionManager tm)throws DAOException{
		ArrayList<Auto> listaAuto= new ArrayList<Auto>();
		tm.assertInTransaction();
		
		 try (PreparedStatement preparedStatement = tm.getConnection().prepareStatement("SELECT * FROM auto")) {
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
		
		try (PreparedStatement preparedStatement = tm.getConnection().prepareStatement("SELECT * FROM Configurabilita WHERE auto="+a.getId())) {
   		 try (ResultSet rs = preparedStatement.executeQuery()) {
   			 while (rs.next() == true){
   				 int idAuto = rs.getInt("auto");
   				 int idComponente = rs.getInt("componente");
	    		 int valoreComponente = rs.getInt("valoreComponente");
	    		
	    		 Componente c = dcomp.readComponenteById(tm, idComponente);
  
   				 listaComponenti.add(c);
   			 }
   		 }
		}
		catch(SQLException e){
			throw new DAOException("Impossibile leggere le auto",e);
			}
		
		return listaComponenti;
		}
	
	
	//getsComponent renzi
	/*
	 *
	 * try {
			PreparedStatement stat,stat2;
			stat = c.prepareStatement("SELECT * FROM Configurabilita WHERE auto="+a.getId());
			ResultSet res=stat.executeQuery(); 
			/*da configurabilita prendo gli id dei componenti
			faccio questo perchè per creare un oggetto configurabilita
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
