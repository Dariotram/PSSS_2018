package server.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Configurazione implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Settaggio> lista_sett=null;
	private Auto auto=null;
	private int id=0;
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
	
	public ArrayList<Componente> getAllComp(){
		ArrayList<Componente> lista_comp= new ArrayList<Componente>();
		for(int i=0;i<lista_sett.size();i++) {
			lista_comp.add(lista_sett.get(i).getComponente());
		}
		return lista_comp;
	}
	
	public void addSettaggio(Componente c, int val) {
		Settaggio s= new Settaggio(c,val);
		lista_sett.add(s);
	}
	
	public ArrayList<Settaggio> getSettaggi(){
		return this.lista_sett;
	}
	
	public Settaggio getSettaggioByComponente(Componente c) {
		for(int i=0;i<lista_sett.size();i++) {
			if(lista_sett.get(i).getComponente().equals(c))
				return lista_sett.get(i);
		}
		return null;
	}
	
	
	public Auto getAuto() {
		return this.auto;
	}
	
	public void setAuto(Auto a) {
		this.auto=a;
	}
	
}