package server.core_business;

import java.util.ArrayList;

import dao.DAOException;
import dao.TransactionManager;
import dao.TransactionManager.TransactionStateException;
import dao.TransactionManagerFactory;
import server.entity.Auto;
import server.entity.Componente;
import server.entity.Configurazione;
import server.entity.Settaggio;
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

	
	
	public Utente checkUtente(Utente u) {
		return g_utente.checkUtente(u);
	}
	
	
	@Override
	public ArrayList<Auto> getAllAuto(Utente u){
		ArrayList<Auto> listaAuto = new ArrayList<>();
		try {
			listaAuto = g_utente.getListaAuto(u);
			
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (PersistenceException e) {
			
			e.printStackTrace();
		}
		return listaAuto;
	}

	@Override
	public ArrayList<Configurazione> getAllConf(Utente u){
		ArrayList<Configurazione> listaConfigurazioni = new ArrayList<>();
		try {
			listaConfigurazioni = g_utente.getListaConf(u);
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (PersistenceException e) {
			
			e.printStackTrace();
		}
		return listaConfigurazioni;
	}
	


	@Override
	public void associaConfigurazione(Auto a, Configurazione c) {
		
		
		TransactionManager tm = TransactionManagerFactory.createTransactionManager();
		
		tm.beginTransaction();
		
		ArrayList<Componente> allComp_auto= new ArrayList<>();
		ArrayList<Componente> allComp_conf= new ArrayList<>();
		ArrayList<Integer> allValues_conf= new ArrayList<>();
		try {
			allComp_auto = g_auto.getListaComp(a);
			allComp_conf = g_conf.getListaComp(c);
			allValues_conf = g_conf.getValoriConf(c);
		} catch (PersistenceException e) {
			e.printStackTrace();
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
		//sto ciclo si può ottimizzare con while o uso di altre funzioni
		for(int i=0;i<allComp_auto.size();i++) {
			for(int j=0;j<allComp_conf.size();j++) {
				System.out.println("Comparo il componente Conf:"+allComp_conf.get(j).getNome());
				System.out.println("con il componente Auto:"+allComp_auto.get(i).getNome());
				if( allComp_conf.get(j).getNome().equals( allComp_auto.get(i).getNome() ) ) {
					System.out.println("\n"+allComp_conf.get(j).getNome()+" è' configurabile! Configuro il componente:"+allComp_conf.get(j).getNome());
					
					// faccio la UPDATE  di configurabilita -> allValues_conf.get(j)
					//faccio la UPDATE AUTO -> metto c.getId()
					
					try {
						g_auto.setValoreComponenteAuto(a, allComp_auto.get(i), allValues_conf.get(j));
						tm.commitTransaction();
						g_auto.setConfigurazioneAuto(a, c);
						tm.commitTransaction();
					} catch (PersistenceException e) {
						
						e.printStackTrace();
					} catch (TransactionStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DAOException e) {
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
