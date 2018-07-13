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
			System.out.println();
	    	System.out.println("Elenco Auto dell'utente");
			for(int i=0;i<lista_auto.size();i++)
				{
					
					System.out.println("**********************");
					System.out.println("Auto indice  : "+i);
					System.out.print("Id  : ");
					System.out.println(lista_auto.get(i).getId());
						
						
					System.out.print("Targa  : ");
					System.out.println(lista_auto.get(i).getTarga());
						
					System.out.print("Modello  : ");
					System.out.println(lista_auto.get(i).getModello());
				
					System.out.println("**********************");
					System.out.println();
								
				}
			
			System.out.println("ottenuto lista Auto con dimensione: "+lista_auto.size());
			System.out.println("Inserisci numero auto");
			BufferedReader stdin = new BufferedReader(new InputStreamReader (System.in));
			String indiceAuto = stdin.readLine();
			int indiceSceltaAuto=Integer.valueOf(indiceAuto);
			Auto a=lista_auto.get(indiceSceltaAuto);
			System.out.println("Scelgo Auto"+indiceSceltaAuto+"con targa:"+lista_auto.get(indiceSceltaAuto).getTarga());
	
			ArrayList<Configurazione> lista_conf=new ArrayList<Configurazione>();
			lista_conf = ges_cps.getAllConf(u);
			
			System.out.println("Elenco Configurazioni dell'utente");
			for(int i=0;i<lista_conf.size();i++)
				{
					
					System.out.println("**********************");
					System.out.print("Id  : ");
						System.out.println(lista_conf.get(i).getId());
						
						
					System.out.print("nome  : ");
						System.out.println(lista_conf.get(i).getName());
						System.out.println("**********************");
						System.out.println();
								
				}
			
			System.out.println("ottenuto lista Configurazione con dimensione: "+lista_conf.size());
			System.out.println("Inserisci numero configurazione");
			String indiceConf = stdin.readLine();
			int indiceSceltaConf=Integer.valueOf(indiceConf);
			Configurazione c= lista_conf.get(indiceSceltaConf);
			
			ges_cps.associaConfigurazione(a, c);
		}catch(IOException e) {
			e.printStackTrace();
		}catch(Exception e){
			handleException(e);
			return;
		}
	}
	
	private static void handleException(Exception e) {
    	System.out.format("Errore: %s\nImpossibile completare l'operazione, Riprova!\n", e.getLocalizedMessage());
    	
    }
}
