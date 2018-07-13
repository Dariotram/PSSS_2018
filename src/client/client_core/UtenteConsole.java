package client.client_core;

import java.io.IOException;

import entity.Utente;

public class UtenteConsole {

	public static void main(String[] args) throws IOException {
		UtenteBusiness ub=new UtenteBusiness();
		Utente u= new Utente("dario",1234);
		System.out.println("Avvio associaConfigurazione");
		ub.associaConfigurazione(u);
	}

}
