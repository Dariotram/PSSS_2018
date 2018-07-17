package client.client_core;

import java.io.IOException;
import server.entity.Utente;

public class UtenteConsole {

	public static void main(String[] args) throws IOException {
		UtenteBusiness ub=new UtenteBusiness();
		Utente u= new Utente(3,"andrea","123","andreap@gmail.com");
		System.out.println("Avvio associaConfigurazione");
		ub.associaConfigurazione(u);
	}

}
