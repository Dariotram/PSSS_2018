package server.server_proxy;

import java.util.ArrayList;

import server.core_business.Gestore_Auto;
import server.core_business.Gestore_CPS;
import server.core_business.Gestore_Conf;
import server.core_business.Gestore_Utente;
import server.entity.Auto;
import server.entity.Componente;
import server.entity.Configurazione;
import server.entity.Utente;
import server.entity.Settaggio;

public class Server {

	public static void main(String[] args) {
		Gestore_CPS ges_cps= Gestore_CPS.getGestoreCPS();
		Gestore_Utente gu=Gestore_Utente.getGestoreUtente();
		Gestore_Auto ga=Gestore_Auto.getGestoreAuto();
		Gestore_Conf gc=Gestore_Conf.getGestoreConf();
		ges_cps.runSkeleton();
		
		

		 //Usato per provare il server senza il db
		 
	/*
		Componente c= new Componente(1,"Sediolini");
		Componente c2= new Componente(2,"Specchietti");
		
		Auto a1= new Auto(1,"1234","a");
		Auto a2= new Auto(2,"4321","b");
		a1.aggiungiConfigurabilita(c);
		a1.aggiungiConfigurabilita(c2);
		a2.aggiungiConfigurabilita(c);
		a2.aggiungiConfigurabilita(c2);
		
		ArrayList<Settaggio> listaSett=new ArrayList<Settaggio>();		
		ArrayList<Settaggio> listaSett2=new ArrayList<Settaggio>();
		Settaggio set1= new Settaggio(c,2);
		Settaggio set2= new Settaggio(c2,3);
		
		Settaggio set12= new Settaggio(c,99);
		Settaggio set22= new Settaggio(c2,88);
		listaSett.add(set1);
		listaSett.add(set2);
		listaSett2.add(set12);
		listaSett2.add(set22);
		
		Configurazione conf= new Configurazione(1,"confA");
		Configurazione conf2= new Configurazione(2,"confB");
		
		conf.addSettaggio(c2, 99);
		conf.addSettaggio(c, 99);
		
		conf2.addSettaggio(c2, 1);
		conf2.addSettaggio(c, 1);
		
		if(conf2.getAllComp().get(0).equals(a1.getListaConfigurabilita().get(0).getComponente()) ) {
			System.out.println("OOOOK");
		}
		
		Utente u=gu.registraUtente(3,"andrea","123","andreap@gmail.com");
		
		//System.out.println(u.getId());
		u.aggiungi_Conf(conf);
		u.aggiungi_Proprieta(a1);
		u.aggiungi_Proprieta(a2);
		//fine settaggi
		*/
		
			
	}

}
