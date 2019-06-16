package server.server_proxy;

import java.util.ArrayList;

import entity.Auto;
import entity.Componente;
import entity.Configurazione;
import entity.Settaggio;
import entity.Utente;
import server.core_business.Gestore_Auto;
import server.core_business.Gestore_CPS;
import server.core_business.Gestore_Conf;
import server.core_business.Gestore_Utente;

public class Server {

	public static void main(String[] args) {
		Gestore_CPS ges_cps= Gestore_CPS.getGestoreCPS();
		
		 //Usato per provare il server senza il db
		 
		Gestore_Utente gu=Gestore_Utente.getGestoreUtente();
		Gestore_Auto ga=Gestore_Auto.getGestoreAuto();
		Gestore_Conf gc=Gestore_Conf.getGestoreConf();
		
		Componente c= new Componente("Sediolini");
		Componente c2= new Componente("Specchietti");
		
		Auto a1= new Auto(3, "1234", "Panda");
		Auto a2= new Auto(5, "4321", "Ferrarino");
		Auto a3= new Auto(5, "2934", "Lancia");
		/*a1.aggiungiConfigurabilita(c);
		a1.aggiungiConfigurabilita(c2);
		a2.aggiungiConfigurabilita(c);
		a2.aggiungiConfigurabilita(c2);*/
		
	/*	ArrayList<Settaggio> listaSett=new ArrayList<Settaggio>();		
		ArrayList<Settaggio> listaSett2=new ArrayList<Settaggio>();
		Settaggio set1= new Settaggio(c,2);
		Settaggio set2= new Settaggio(c2,3);
		
		Settaggio set12= new Settaggio(c,99);
		Settaggio set22= new Settaggio(c2,88);
		listaSett.add(set1);
		listaSett.add(set2);
		listaSett2.add(set12);
		listaSett2.add(set22);*/
		
		/*Configurazione conf= new Configurazione(0, "conf1");
		Configurazione conf2= new Configurazione(2, "conf2");
		conf.setSettaggio(c2, 99);
		conf.setSettaggio(c, 99);
		
		conf2.setSettaggio(c2, 1);
		conf2.setSettaggio(c, 1);
		if(conf2.getListaComp().get(0).equals(a1.getListaConfigurabilita().get(0).getComponente()) ) {
			System.out.println("OOOOK");
		}
		
		
		//System.out.println(u.getId());
		u.aggiungi_Conf(conf);
		Utente u=gu.registraUtente("dario", 1234);
		u.aggiungi_Proprieta(a1);
		u.aggiungi_Proprieta(a2);
		u.aggiungi_Proprieta(a3);
		//fine settaggi*/
		
		ges_cps.runSkeleton();
			
	}

}
