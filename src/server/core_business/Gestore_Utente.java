package server.core_business;
import java.util.ArrayList;

import entity.Auto;
import entity.Configurazione;
import entity.InfoUtente;
import entity.Proprieta;
import entity.Utente;

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
		ArrayList<Auto> lista =new ArrayList<Auto>();
		lista.add(new Auto(1,"2345","pandino"));
		lista.add(new Auto(2,"4950","ferrarino"));
		lista.add(new Auto(3,"0439","jeeppino"));
		return lista;
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
	
	public ArrayList<String> getListaConf(Utente u) {
		ArrayList<String> lista = new ArrayList<String>();
		Configurazione c= new Configurazione(1,"comoda");
		Configurazione c2= new Configurazione(2,"dormiente");
		Configurazione c3= new Configurazione(3,"sportiva");
		lista.add("id: "+ c.getId()+" nome: "+c.getName());
		lista.add("id: "+ c2.getId()+" nome: "+c2.getName());
		lista.add("id: "+ c3.getId()+" nome: "+c3.getName());
		
		
		return lista;
	}
	
	public ArrayList<Proprieta> getListaProprieta(Utente u){
		return info_utente.getListaProprieta(u);
	}
}

