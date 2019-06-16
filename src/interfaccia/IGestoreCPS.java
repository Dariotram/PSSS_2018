package interfaccia;

import java.util.ArrayList;

import entity.Auto;
import entity.Configurazione;
import entity.Utente;

public interface IGestoreCPS {
	
	public ArrayList<String> getAllAuto(Utente u);
	public ArrayList<String> getAllConf(Utente u);
	public void adattaConfigurazione(Auto a,Configurazione c);
	public Utente checkUtente(Utente u);
}