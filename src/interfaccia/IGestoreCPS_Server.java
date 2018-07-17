package interfaccia;

import java.util.ArrayList;

import server.entity.Auto;
import server.entity.Configurazione;
import server.entity.Utente;

public interface IGestoreCPS_Server extends IGestoreCPS{

	public ArrayList<Auto> SelezionaAuto(Utente u);
	public ArrayList<Configurazione> SelezionaConfigurazione(Utente u);
	public void associaConfigurazione(Auto a,Configurazione c);
	public Utente checkUtente(Utente u);
}
