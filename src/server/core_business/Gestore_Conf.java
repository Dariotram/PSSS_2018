package server.core_business;

import java.util.ArrayList;

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
	
	public ArrayList<Componente> getListaComp(Configurazione c) {
		return c.getListaComp();
	}
	
	public void aggiungiSettaggio(Configurazione conf,Componente c, int val) {
		conf.aggiungiSett(c, val);
	}
	
	/*
	public void aggiungiAutoConfig(Auto a,Settaggio s) {
		s.aggiungiAutoSett(a);
	}*/
}
