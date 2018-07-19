package server.core_business;

import java.util.ArrayList;
import java.util.HashMap;

import dao.TransactionManager;
import dao.TransactionManager.TransactionStateException;
import dao.TransactionManagerFactory;
import server.entity.Auto;
import server.entity.Componente;
import server.entity.Configurazione;

import server.entity.Utente;
import server.server_proxy.Gestore_CPS_Skeleton;


public class Gestore_CPS extends Gestore_CPS_Skeleton{
	private static Gestore_CPS gestoreCPS_instance=null;
	private Gestore_Utente g_utente;
	private Gestore_Auto g_auto;
	private Gestore_Conf g_conf;
	

		
	private Gestore_CPS() {
		g_utente=Gestore_Utente.getGestoreUtente();
		g_auto=Gestore_Auto.getGestoreAuto();
		g_conf=Gestore_Conf.getGestoreConf();
	}
	
	public static synchronized Gestore_CPS getGestoreCPS() {
		if(gestoreCPS_instance==null) {
		//	System.out.println("Inizializzo il gestoreCPS");
			gestoreCPS_instance=new Gestore_CPS();
		}
		//System.out.println("Ritorna il gestoreCPS");
		return gestoreCPS_instance;
	}

	
	
	public Utente getUtente(int id_u) {
		Utente u=null;
		try {
			u=g_utente.getUtente(id_u);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	
	@Override
	public ArrayList<Auto> getAllAuto(Utente u){ 
		ArrayList<String> lista = new ArrayList<String>();
		ArrayList<Auto> listaAuto = new ArrayList<Auto>();
		try {
			listaAuto = g_utente.getListaAuto(u);
		/*	for(int i=0;i<listaAuto.size();i++) {
				//lista.add("id: "+ listaAuto.get(i).getId()+" modello"+ listaAuto.get(i).getModello() +" targa:"+listaAuto.get(i).getTarga() );
				lista.add(Integer.toString( listaAuto.get(i).getId() ));
			}*/
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (PersistenceException e) {
			
			e.printStackTrace();
		}
		
		return listaAuto;
	}

	@Override
	public ArrayList<Configurazione> getAllConf(Utente u){
		ArrayList<String> lista = new ArrayList<String>();
		ArrayList<Configurazione> listaConfigurazioni = new ArrayList<Configurazione>();
		try {
			listaConfigurazioni = g_utente.getListaConf(u);
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (PersistenceException e) {
			
			e.printStackTrace();
		}
		/*
		for(int i=0;i<listaConfigurazioni.size();i++) {
			//lista.add("id: "+listaConfigurazioni.get(i).getId()+" Nome: " + listaConfigurazioni.get(i).getName() );
			lista.add(Integer.toString( listaConfigurazioni.get(i).getId() ));
		}*/
		
		return listaConfigurazioni;
	}
	


	@Override
	public void associaConfigurazione(int   a_id, int c_id) {
		
		
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		
		tm.beginTransaction();
		
		ArrayList<Componente> allComp_auto= new ArrayList<>();
		ArrayList<Componente> allComp_conf= new ArrayList<>();
		ArrayList<Integer> allValues_conf= new ArrayList<>();
		
		Auto a=null;	
		Configurazione c= null;
		try {
			a=g_auto.getAuto(a_id);	
			c= g_conf.getConf(c_id);
			allComp_auto = g_auto.getListaComp(a);
			
			
			allComp_conf = g_conf.getListaComp(c);
			allValues_conf = g_conf.getValoriConf(c_id);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		
		System.out.println("Configurazione "+c_id);
		for(int i=0;i<allComp_conf.size();i++)
		{
			System.out.println("**********************");
			System.out.print("IdComponente  : ");
			System.out.println(allComp_conf.get(i).getId());

			System.out.print("NomeComponente  : ");
			System.out.println(allComp_conf.get(i).getNome());
			
			System.out.print("ValoreComponente  : ");
			System.out.println(allValues_conf.get(i));
			
			System.out.println("**********************");
			System.out.println();
						
		}
		
		
		
		System.out.println("Componenti auto sono in numero:"+allComp_auto.size());
		System.out.println("Componenti configurabili dell'AUTO scelta");
		for(int i=0;i<allComp_auto.size();i++)
		{
			System.out.println("**********************");
			System.out.print("IdComponente  : ");
			System.out.println(allComp_auto.get(i).getId());

			System.out.print("NomeComponente  : ");
			System.out.println(allComp_auto.get(i).getNome());
	
			System.out.println("**********************");
			System.out.println();
						
		}
		System.out.println("Componenti configurazione sono in numero:"+allComp_conf.size());
		System.out.println("Componenti della CONFIGURAZIONE scelta");
		for(int i=0;i<allComp_conf.size();i++)
		{
			
			System.out.println("**********************");
			System.out.print("IdComponente  : ");
			System.out.println(allComp_conf.get(i).getId());

			System.out.print("NomeComponente  : ");
			System.out.println(allComp_conf.get(i).getNome());
			
			System.out.println("**********************");
			System.out.println();
						
		}
		
		//sto ciclo si pu� ottimizzare con while o uso di altre funzioni
		for(int i=0;i<allComp_auto.size();i++) {
			for(int j=0;j<allComp_conf.size();j++) {
				System.out.println("Comparo il componente Conf:"+allComp_conf.get(j).getNome());
				System.out.println("con il componente Auto:"+allComp_auto.get(i).getNome());
				if( allComp_conf.get(j).getNome().equals( allComp_auto.get(i).getNome() ) ) {
					System.out.println("\n"+allComp_conf.get(j).getNome()+" �' configurabile! Configuro il componente:"+allComp_conf.get(j).getNome());
					
					// faccio la UPDATE  di configurabilita -> allValues_conf.get(j)
					//faccio la UPDATE AUTO -> metto c.getId()
					
					try {
						g_auto.setValoreComponenteAuto(a, allComp_auto.get(i), allValues_conf.get(j));
						g_auto.setConfigurazioneAuto(a, c);
					} catch (PersistenceException e) {
						
						e.printStackTrace();
					} catch (TransactionStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//int val=c.getSettaggio(allComp_conf.get(j) ).getValore();
					//g_auto.setComp(a, allComp_conf.get(j),val);
				}
			}
			System.out.println("Comparo con prossimo componente auto\n");
		}
		
		
	}
	
}
