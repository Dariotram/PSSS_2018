package interfaccia;

import java.util.ArrayList;

import server.entity.Auto;
import server.entity.Configurazione;
import server.entity.Utente;

public interface IGestoreCPS {
	
	public ArrayList<Auto> getAllAuto(Utente u);
	public ArrayList<Configurazione> getAllConf(Utente u);
	public void associaConfigurazione(Auto a,Configurazione c);
	public Utente checkUtente(Utente u);
}