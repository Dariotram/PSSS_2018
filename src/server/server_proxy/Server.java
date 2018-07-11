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
		
		 //Usato per provare il server senza il db
		 
		Gestore_Utente gu=Gestore_Utente.getGestoreUtente();
		Gestore_Auto ga=Gestore_Auto.getGestoreAuto();
		Gestore_Conf gc=Gestore_Conf.getGestoreConf();
		
		Componente c= new Componente("Sediolini");
		Componente c2= new Componente("Specchietti");
		
		Auto a1= new Auto("1234",1);
		Auto a2= new Auto("4321",2);
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
		
		Configurazione conf= new Configurazione(listaSett);
		Configurazione conf2= new Configurazione(listaSett2);
		
		if(conf2.getListaComp().get(0).equals(a1.getListaConfigurabilita().get(0).getComponente()) ) {
			System.out.println("OOOOK");
		}
		Utente u=gu.registraUtente("dario", 1234);
		
		//System.out.println(u.getId());
		u.aggiungi_Conf(conf);
		u.aggiungi_Proprieta(a1);
		u.aggiungi_Proprieta(a2);
		//fine settaggi
		
		ges_cps.runSkeleton();
			
	}

}
