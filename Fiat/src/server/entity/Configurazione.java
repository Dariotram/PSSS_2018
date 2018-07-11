package server.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Configurazione implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Settaggio> lista_sett=null;
	private static int id=0;
	private String name;
	
	public Configurazione(int id, String n) {
		lista_sett=new ArrayList<Settaggio>();
		this.name=n;
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Settaggio> getListaSett() {
		return lista_sett;
	}
	
	public ArrayList<Componente> getListaComp(){
		ArrayList<Componente> lista_comp= new ArrayList<Componente>();
		for(int i=0;i<lista_sett.size();i++) {
			lista_comp.add(lista_sett.get(i).getComponente());
		}
		return lista_comp;
	}
	
	public void setSettaggio(Componente c, int val) {
		Settaggio s= new Settaggio(c,val);
		lista_sett.add(s);
	}
	
	public Settaggio getSettaggio(Componente c) {
		for(int i=0;i<lista_sett.size();i++) {
			if(lista_sett.get(i).getComponente().equals(c))
				return lista_sett.get(i);
		}
		return null;
	}
	
	/*
	public void aggiungiAutoConf(Auto a) {
		lista_auto_conf.add(a);
	}
	
	public ArrayList<Auto> getAutoConf() {
		return lista_auto_conf;
	}*/
}