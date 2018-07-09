package interfaccia;

import java.util.ArrayList;

import server.entity.Auto;
import server.entity.Configurazione;
import server.entity.Utente;

public interface IGestoreCPS_Server extends IGestoreCPS{

	public void associaConfigurazione(Utente u);
}
