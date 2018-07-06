package server.server_proxy;

import java.util.ArrayList;

import server.core_business.Gestore_Auto;
import server.core_business.Gestore_Conf;
import server.core_business.Gestore_Utente;
import server.entity.Auto;
import server.entity.Componente;
import server.entity.Configurazione;
import server.entity.Utente;


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
		//	System.out.println("Inizializzo il gestore");
			gestoreCPS_instance=new Gestore_CPS();
		}
		//System.out.println("Ritorna il gestore");
		return gestoreCPS_instance;
	}
	
	@Override
	public void associaConfigurazione(Utente u) {
		ArrayList<Configurazione> allconf_utente=g_utente.getAllConf(u);
		ArrayList<Auto> allAuto_utente=g_utente.getAllAuto(u);
		//comunico con il cliente queste info che mi rispondera con una conf e una proprieta
		Auto a=allAuto_utente.get(0);
		ArrayList<Componente> allComp_auto=g_auto.getAllComp(a);
	}

}
