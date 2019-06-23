package server.core_business;
import java.util.ArrayList;


import dao.DAOException;
import dao.DAOUtente;
import dao.TransactionManager;
import dao.TransactionManagerFactory;
import server.entity.Auto;
import server.entity.Configurazione;
import server.entity.Utente;
import server.entity.InfoUtente;
import server.entity.Proprieta;

public class Gestore_Utente {

	private static Gestore_Utente gestoreUtente_instance=null;
	private InfoUtente info_utente;
	
	private Gestore_Utente() {
		info_utente = InfoUtente.getInfoUtente();
	}
	public static synchronized Gestore_Utente getGestoreUtente() {
		if(gestoreUtente_instance==null) {
		//	System.out.println("Inizializzo il gestoreUtente");
			gestoreUtente_instance=new Gestore_Utente();
		}
		//System.out.println("Ritorna il gestoreUtente");
		return gestoreUtente_instance;
	}
	
	
	public ArrayList<Auto> getAllAuto(Utente u){
		ArrayList<Proprieta> lista_proprieta=new ArrayList<Proprieta>(u.getListaProprieta());
		ArrayList<Auto> lista_auto=new ArrayList<Auto>();
		for(int i=0;i<lista_proprieta.size();i++) {
			lista_auto.add(lista_proprieta.get(i).getAuto());
			}
		return lista_auto;
	}
	

	
	public Utente registraUtente(int id,String s,String p,String email) {
		return info_utente.registraUtente(id,s,p,email);
	}
	
	public Utente getUtente(int id_u) throws PersistenceException {
		Utente u=null;
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		
		tm.beginTransaction();
		
			try {
				u= DAOUtente.readUtente(tm, id_u);
				tm.commitTransaction();
			} catch (DAOException e) {
				tm.rollbackTransaction();
	            throw new PersistenceException("Impossibile trovare l'utente con questo id"+id_u, e);
			}
			return u;
	}
	
	public int getPassword(Utente u) {
		return info_utente.getId(u);
	}

	public String getName(Utente u) {
		return info_utente.getName(u);
	}
	
	public int getId(Utente u) {
		return info_utente.getId(u);
	}
	public void aggiungi_Conf(Utente u,Configurazione c) {
		info_utente.aggiungi_Conf(u, c);
	}
	
	public void aggiungi_Proprieta(Utente u,Auto a) {
		info_utente.aggiungi_Proprieta(u, a);
	}
	
	
	
	//***metodi che chiamano metodi DAO
	
	public ArrayList<Configurazione> getListaConf(Utente u) throws IllegalArgumentException, PersistenceException{
		
		ArrayList<Configurazione> listaConfigurazioni = null;
		
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		
		tm.beginTransaction();
		
			try {
				listaConfigurazioni = DAOUtente.readConfigurazioniUtente(tm, u);
				tm.commitTransaction();
			} catch (DAOException e) {
				tm.rollbackTransaction();
	            throw new PersistenceException("Impossibile trovare le configurazioni", e);
			}
			
			return listaConfigurazioni;

		
	}
	
public ArrayList<Auto> getListaAuto(Utente u) throws IllegalArgumentException, PersistenceException{
		
		ArrayList<Auto> listaAuto = null;
		
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		
		tm.beginTransaction();

			try {
				listaAuto = DAOUtente.readAutoUtente(tm, u);
				tm.commitTransaction();
			} catch (DAOException e) {
				tm.rollbackTransaction();
	            throw new PersistenceException("Impossibile trovare le auto", e);
			}
			
			return listaAuto;
		}
	
	
	
	
	public ArrayList<Proprieta> getListaProprieta(Utente u){
		return info_utente.getListaProprieta(u);
	}

	
}

