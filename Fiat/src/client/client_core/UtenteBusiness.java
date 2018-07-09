package client.client_core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import client.client_proxy.GestoreCPS_Proxy;
import interfaccia.IGestoreCPS;
import server.entity.Utente;
import server.entity.Auto;
import server.entity.Configurazione;

public class UtenteBusiness {

	private IGestoreCPS ges_cps;
	public UtenteBusiness() {
		ges_cps= new GestoreCPS_Proxy();
	}
	
	public void associaConfigurazione(Utente u) {
		try {
			
			ArrayList<Auto> lista_auto= new ArrayList<Auto>();
			lista_auto=ges_cps.getAllAuto(u);
			System.out.println("ottenuto lista Auto con dimensione: "+lista_auto.size());
			System.out.println("Inserisci numero auto");
			BufferedReader stdin = new BufferedReader(new InputStreamReader (System.in));
			String indiceAuto = stdin.readLine();
			int indiceSceltaAuto=Integer.valueOf(indiceAuto);
			Auto a=lista_auto.get(indiceSceltaAuto);
			System.out.println("Scelgo Auto"+indiceSceltaAuto+"con targa:"+lista_auto.get(indiceSceltaAuto).getTarga());
	
			ArrayList<Configurazione> lista_conf=new ArrayList<Configurazione>(ges_cps.getAllConf(u));
			System.out.println("ottenuto lista Configurazione con dimensione: "+lista_conf.size());
			System.out.println("Inserisci numero configurazione");
			String indiceConf = stdin.readLine();
			int indiceSceltaConf=Integer.valueOf(indiceConf);
			Configurazione c= lista_conf.get(indiceSceltaConf);
			
			ges_cps.adattaConfigurazione(a, c);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
