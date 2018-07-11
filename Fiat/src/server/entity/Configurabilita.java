package server.entity;

import java.io.Serializable;

public class Configurabilita implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Componente comp;
	private Auto auto;
	private int valore;
	
	public Configurabilita(Auto a,Componente c) {
		auto=a;
		comp=c;
		valore=0;
	}
	
	public Componente getComponente() {
		return comp;
	}
	
	public Auto getAuto() {
		return auto;
	}
	
	public int getValore() {
		return valore;
	}
	
	public void setValore(int v) {
		valore=v;
	}
}
