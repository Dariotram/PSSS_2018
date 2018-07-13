package server.core_business;

import java.util.ArrayList;

import dao.DAOAuto;
import dao.DAOConfigurazione;
import dao.DAOException;
import dao.TransactionManager;
import dao.TransactionManagerFactory;
import server.entity.Auto;
import server.entity.Componente;
import server.entity.Configurabilita;
import server.entity.Configurazione;

public class Gestore_Auto {
	private static Gestore_Auto gestoreAuto_instance=null;
	
	private Gestore_Auto() {
		
	}
	public static synchronized Gestore_Auto getGestoreAuto() {
		if(gestoreAuto_instance==null) {
		//	System.out.println("Inizializzo il gestoreAuto");
			gestoreAuto_instance=new Gestore_Auto();
		}
		//System.out.println("Ritorna il gestoreAuto");
		return gestoreAuto_instance;
	}
		
	
// non so se servirebbero
	public ArrayList<Componente> getAllComp(Auto a) {
		ArrayList<Configurabilita> lista_configurab=new ArrayList<Configurabilita>(a.getListaConfigurabilita() );
		ArrayList<Componente> lista_comp=new ArrayList<Componente>();
		for(int i=0;i<lista_configurab.size();i++) {
			lista_comp.add(lista_configurab.get(i).getComponente());
			}
		return lista_comp;
	}
	
	public void setComp(Auto a,Componente c, int v) {
		ArrayList<Configurabilita> lista_configurab=new ArrayList<Configurabilita>(a.getListaConfigurabilita() );
		for(int i=0;i<lista_configurab.size();i++) {
			if(lista_configurab.get(i).getComponente().equals(c))
				lista_configurab.get(i).setValore(v);
			}
	}
//
	
	//***metodi che chiamano metodi DAO***
	public ArrayList<Componente> getListaComp(Auto a) throws PersistenceException{
		
		ArrayList<Componente> listaComponenti = null;
		
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		
		tm.beginTransaction();
		
			try {
				listaComponenti = DAOAuto.readComponentiAuto(tm, a);
				tm.commitTransaction();
			} catch (DAOException e) {
				tm.rollbackTransaction();
	            throw new PersistenceException("Impossibile trovare i componenti dell'auto"+a.getId(), e);
			}
			
			return listaComponenti;

	
	}
	
	
	public void setValoreComponenteAuto(Auto a,Componente c,int val) throws PersistenceException{
		
		
		//Configurabilita configurabilita=null;
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		
		tm.beginTransaction();
		
		
			try {
				DAOAuto.setValoreConfigurabilita(tm, a, c, val);
				tm.commitTransaction();
			} catch (DAOException e) {
				tm.rollbackTransaction();
	            throw new PersistenceException("Impossibile configurare i componenti dell'auto "+a.getId(), e);
			}
			
			//return configurabilita;
		
	}
	

	public void setConfigurazioneAuto(Auto a, Configurazione c) throws PersistenceException{
	
	TransactionManager tm = TransactionManagerFactory.createTransactionManager();
	
	tm.beginTransaction();
		
			try {
				DAOAuto.setConfigurazioneAuto(tm, a, c);
				tm.commitTransaction();
			} catch (DAOException e) {
				tm.rollbackTransaction();
	            throw new PersistenceException("Impossibile assegnare configurazione all'auto"+a.getId(), e);
			}
			
		
	}
	

}
