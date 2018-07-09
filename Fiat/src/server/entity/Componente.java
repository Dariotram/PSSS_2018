package server.entity;

import java.io.Serializable;

public class Componente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private int valore;
	public Componente(String n,int a) {
		nome=n;
		valore=a;
	}
	
	public void configuraComp(Componente c) {
		valore=c.valore;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public int getValore() {
		return this.valore;
	}
	
	
}
