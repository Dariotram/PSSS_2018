package server.entity;

public class Proprieta {
	
	private Auto auto;
	private Utente proprietario;
	public Proprieta(Auto a,Utente u) {
		auto=a;
		proprietario=u;
	}
	
	public Auto getAuto() {
		return this.auto;
	}
	
	public Utente getProprietario() {
		return this.proprietario;
	}
}
