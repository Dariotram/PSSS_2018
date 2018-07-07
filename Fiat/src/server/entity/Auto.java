package server.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Auto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int targa;
	private int id;
	private ArrayList<Configurabilita> lista_configurabilita=null;
	private Configurazione configurazione;
	
	public Auto(int t,int id) {
		lista_configurabilita= new ArrayList<Configurabilita>();
		this.targa=t;
		this.id=id;
	}
	public int getTarga() {
		return  this.targa;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void aggiungiConfigurabilita(Componente comp) {
		Configurabilita configurab= new Configurabilita(this,comp);
		lista_configurabilita.add(configurab);
	}
	
	public ArrayList<Configurabilita> getListaConfigurabilita() {
		return lista_configurabilita;
	}
	
	public void configura_Auto(Configurazione c) {
		this.configurazione=c;
	}
	
	public Configurazione getConfigurazione() {
		return this.configurazione;
	}
	
}
