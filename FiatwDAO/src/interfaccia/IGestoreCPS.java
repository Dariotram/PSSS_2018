package interfaccia;

import java.util.ArrayList;

import server.entity.Auto;
import server.entity.Configurazione;
import server.entity.Utente;

public interface IGestoreCPS {
	
	public Utente getUtente(int id_u);
	public ArrayList<String> getAllAuto(Utente u);
	public ArrayList<String> getAllConf(Utente u);
	public void associaConfigurazione(int id_a,int id_c);
	//public Utente checkUtente(int u);
}