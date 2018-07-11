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
	
	public void setSettaggio(Configurazione conf,Componente c, int val) {
		conf.setSettaggio(c, val);
	}
	
	public Settaggio getSettaggio(Configurazione conf,Componente c) {
		return conf.getSettaggio(c);
	}
	
	public void aggiungiConfigurazione(String n, ArrayList<Componente> l_comp, ArrayList<Integer> l_val ) {
		Configurazione c= new Configurazione(n);
		for(int i=0;i<l_comp.size();i++) {
			c.setSettaggio(l_comp.get(i), l_val.get(i));
		}
		
	}
	/*
	public void aggiungiAutoConfig(Auto a,Settaggio s) {
		s.aggiungiAutoSett(a);
	}*/
}
