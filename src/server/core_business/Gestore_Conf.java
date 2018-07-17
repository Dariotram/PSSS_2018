package server.core_business;

import java.util.ArrayList;


import dao.DAOConfigurazione;
import dao.DAOException;

import dao.TransactionManager;
import dao.TransactionManagerFactory;

import server.entity.Componente;
import server.entity.Configurazione;
import server.entity.Settaggio;

public class Gestore_Conf {

	private static Gestore_Conf gestoreConf_instance=null;
	
	private Gestore_Conf() {
		
	}
	public static synchronized Gestore_Conf getGestoreConf() {
		if(gestoreConf_instance==null) {
			//System.out.println("Inizializzo il gestoreConf");
			gestoreConf_instance=new Gestore_Conf();
		}
		//System.out.println("Ritorna il gestoreConf");
		return gestoreConf_instance;
	}
	
	public ArrayList<Settaggio> getListaSett(Configurazione c) {
		return c.getListaSett();
	}
	
	public ArrayList<Componente> getAllComp(Configurazione c) {
		return c.getAllComp();
	}
	
	public void aggiungiSettaggio(Configurazione conf,Componente c, int val) {
		conf.addSettaggio(c, val);
	}
	
	
	//***metodi che chiamano metodi DAO*****
	
	public ArrayList<Componente> getListaComp(Configurazione c) throws PersistenceException{
		ArrayList<Componente> listaComponenti = null;
		
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		
		tm.beginTransaction();
		
		
			try {
				listaComponenti = DAOConfigurazione.readComponentiConfigurazione(tm, c);
				tm.commitTransaction();
			} catch (DAOException e) {
				tm.rollbackTransaction();
	            throw new PersistenceException("Impossibile trovare i componenti della configurazione"+c.getId(), e);
			}
			
			return listaComponenti;

		
	}
		
		public ArrayList<Integer> getValoriConf(int id_conf)throws PersistenceException{
			Configurazione c= this.getConf(id_conf);
			ArrayList<Integer> listaValoriConf = null;
			
			TransactionManager tm = TransactionManagerFactory.createTransactionManager();
			
			tm.beginTransaction();
			
				try {
					listaValoriConf = DAOConfigurazione.readValoriConfigurazione(tm, c);
					tm.commitTransaction();
				} catch (DAOException e) {
					tm.rollbackTransaction();
		            throw new PersistenceException("Impossibile trovare i valori dei componenti della configurazione"+c.getId(), e);
				}
				
				return listaValoriConf;

		}
		
		public Configurazione getConf( int id_conf) throws PersistenceException {
			TransactionManager tm = TransactionManagerFactory.createTransactionManager();
			
			tm.beginTransaction();
			
				try {
					Configurazione c=DAOConfigurazione.readConf(tm, id_conf);
					tm.commitTransaction();
					return c;
				} catch (DAOException e) {
					tm.rollbackTransaction();
		            throw new PersistenceException("Impossibile trovare la configurazione con questo id"+id_conf, e);
				}
		}
	
}
