package server.core_business;

import java.util.ArrayList;

import entity.Auto;
import entity.Componente;
import entity.Configurazione;
import entity.Utente;
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

	@Override
	public ArrayList<String> getAllAuto(Utente u){
		ArrayList<String> lista =new ArrayList<String>();
		Auto a = new Auto(1,"2345","pandino");
		Auto b = new Auto (2,"4950","ferrarino");
		Auto c= new Auto (3,"0439","jeeppino");
		lista.add("id: "+a.getId()+" Modello: " + a.getModello()+ "targa: "+a.getTarga());
		lista.add("id: "+b.getId()+" Modello: " + b.getModello()+ "targa: "+b.getTarga());
		lista.add("id: "+a.getId()+" Modello: " + b.getModello()+ "targa: "+a.getTarga());
		return lista;
	}

	@Override
	public ArrayList<String> getAllConf(Utente u){
		return g_utente.getListaConf(u);
	}
	
	public Utente checkUtente(Utente u) {
		return g_utente.checkUtente(u);
	}

	@Override
	public void adattaConfigurazione(Auto a, Configurazione c) {
		ArrayList<Componente> allComp_auto=g_auto.getAllComp(a);
		ArrayList<Componente> allComp_conf=g_conf.getListaComp(c);
		System.out.println("Componenti auto sono in numero:"+allComp_auto.size());
		System.out.println("Componenti configurazione sono in numero:"+allComp_conf.size());
		
		//sto ciclo si può ottimizzare con while o uso di altre funzioni
		for(int i=0;i<allComp_auto.size();i++) {
			for(int j=0;j<allComp_conf.size();j++) {
				System.out.println("Comparo il componente Conf:"+allComp_conf.get(j).getNome());
				System.out.println("con il componente Auto:"+allComp_auto.get(i).getNome());
				if( allComp_conf.get(j).getNome().equals( allComp_auto.get(i).getNome() ) ) {
					System.out.println("\nConfiguro il componente:"+allComp_conf.get(j).getNome());
					int val=c.getSettaggio(allComp_conf.get(j) ).getValore();
					g_auto.setComp(a, allComp_conf.get(j),val);
				}
			}
			System.out.println("Comparo con prossimo componete auto\n");
		}
		
		g_auto.configura_Auto(a, c);
		
	}



}
