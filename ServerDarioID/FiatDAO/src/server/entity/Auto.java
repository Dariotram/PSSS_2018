package server.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Auto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String targa;
	private int id;
	private String modello;
	private ArrayList<Configurabilita> lista_configurabilita=null;
	
	public Auto(int id,String targa,String modello) {
		lista_configurabilita= new ArrayList<Configurabilita>();
		this.targa=targa;
		this.id=id;
		this.modello=modello;
	}
	public int getId() {
		return this.id;
	}
	
	public String getTarga() {
		return  this.targa;
	}
	
	public String getModello() {
		return  this.modello;
	}
	
	public ArrayList<Configurabilita> getListaConfigurabilita() {
		return lista_configurabilita;
	}
	
	public void aggiungiConfigurabilita(Componente comp) {
		Configurabilita configurab= new Configurabilita(this,comp);
		lista_configurabilita.add(configurab);
	}

	
	
	
	
	
	
}
