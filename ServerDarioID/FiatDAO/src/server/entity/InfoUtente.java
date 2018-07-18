package server.entity;

import java.util.ArrayList;


public class InfoUtente {

	private static InfoUtente InfoUtente_instance=null;
	private ArrayList<Utente> lista_utente;
	
	private InfoUtente() {
		lista_utente= new ArrayList<Utente>();
	}
	
	public static synchronized InfoUtente getInfoUtente() {
		if(InfoUtente_instance==null) {
		//	System.out.println("Inizializzo l infoUtente");
			InfoUtente_instance=new InfoUtente();
		}
		//System.out.println("Ritorna l infoUtente");
		return InfoUtente_instance;
	}
	
	
	public int getId(Utente u) {
		return u.getId();
	}
	
	public String getPassword(Utente u) {
		return u.getPassword();
	}

	public String getName(Utente u) {
		return u.getName();
	}
	public String getEmail(Utente u) {
		return u.getEmail();
	}
	
	public ArrayList<Configurazione> getListaConf(Utente u) {
		return u.getListaConf();
	}
	
	public ArrayList<Proprieta> getListaProprieta(Utente u){
		return u.getListaProprieta();
	}
	
	public void aggiungi_Conf(Utente u,Configurazione c) {
		u.aggiungi_Conf(c);
	}
	
	public void aggiungi_Proprieta(Utente u,Auto a) {
		u.aggiungi_Proprieta(a);
	}
	
	public Utente getUtente(int id) {
		for(int i=0;i<lista_utente.size();i++) {
			if(lista_utente.get(i).getId()==id) {
				return lista_utente.get(i);
			}
		}
		return null;
	}
	
	public Utente registraUtente(int id, String s, String p, String email) {
		Utente u= new Utente(id,s,p,email);
		lista_utente.add(u);
		return u;
	}
	
	
	public Utente checkUtente(Utente u) {
		for(int i=0;i<lista_utente.size();i++) {
			if(lista_utente.get(i).getEmail().equals(u.getEmail()) ) {//DA MODIFICARE
				System.out.println("Trovato l'utente "+u.getEmail());
				return lista_utente.get(i);
			}
		}
		return null;
	}
	
	
}
