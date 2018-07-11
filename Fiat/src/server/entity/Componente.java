package server.entity;

import java.io.Serializable;

public class Componente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	
	public Componente(String n) {
		nome=n;
	}

	public String getNome() {
		return this.nome;
	}
	
	
}
