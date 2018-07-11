package server.core_business;
import java.util.ArrayList;

import server.entity.Auto;
import server.entity.Configurazione;
import server.entity.Utente;
import server.entity.InfoUtente;
import server.entity.Proprieta;

public class Gestore_Utente {

	private static Gestore_Utente gestoreUtente_instance=null;
	private InfoUtente info_utente;
	
	private Gestore_Utente() {
		info_utente = InfoUtente.getInfoUtente();
	}
	public static synchronized Gestore_Utente getGestoreUtente() {
		if(gestoreUtente_instance==null) {
		//	System.out.println("Inizializzo il gestoreUtente");
			gestoreUtente_instance=new Gestore_Utente();
		}
		//System.out.println("Ritorna il gestoreUtente");
		return gestoreUtente_instance;
	}
	
	public ArrayList<Auto> getAllAuto(Utente u){
		ArrayList<Proprieta> lista_proprieta=new ArrayList<Proprieta>(u.getListaProprieta());
		ArrayList<Auto> lista_auto=new ArrayList<Auto>();
		for(int i=0;i<lista_proprieta.size();i++) {
			lista_auto.add(lista_proprieta.get(i).getAuto());
			}
		return lista_auto;
	}
	
	public Utente registraUtente(String s,int p) {
		return info_utente.registraUtente(s, p);
	}
	
	public Utente getUtente(int id) {
		return info_utente.getUtente(id);
	}
	
	public Utente checkUtente(Utente u) {
		return info_utente.checkUtente(u);
	}
	
	public int getPassword(Utente u) {
		return info_utente.getId(u);
	}

	public String getName(Utente u) {
		return info_utente.getName(u);
	}
	
	public int getId(Utente u) {
		return info_utente.getId(u);
	}
	public void aggiungi_Conf(Utente u,Configurazione c) {
		info_utente.aggiungi_Conf(u, c);
	}
	
	public void aggiungi_Proprieta(Utente u,Auto a) {
		info_utente.aggiungi_Proprieta(u, a);
	}
	
	public ArrayList<Configurazione> getListaConf(Utente u) {
		return info_utente.getListaConf(u);
	}
	
	public ArrayList<Proprieta> getListaProprieta(Utente u){
		return info_utente.getListaProprieta(u);
	}
}

