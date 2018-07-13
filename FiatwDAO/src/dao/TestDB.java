package dao;


import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import client.client_proxy.GestoreCPS_Proxy;
import interfaccia.IGestoreCPS;
import server.entity.Utente;
import server.entity.Auto;
import server.entity.Componente;
import server.entity.Configurazione;
import dao.TransactionManager;
import dao.TransactionManagerFactory;
import server.entity.Auto;
import server.entity.Utente;

/**
 * @author andry
 *
 */
public class TestDB {

	public static void main(String[] args) {
		TransactionManager transactionManager = TransactionManagerFactory.createTransactionManager();
		transactionManager.beginTransaction();
		ArrayList<Configurazione> listaConf= new ArrayList<>();
		ArrayList<Integer> listaValori= new ArrayList<>();
		Utente u = new Utente(3, "andrea", "123", "andreap@gmail.com");
		Componente c = null;
		
		
		try {
		
			
			listaConf = DAOUtente.readConfigurazioniUtente(transactionManager,u);
			transactionManager.commitTransaction();
			
			
			
		} catch (DAOException e) {
			handleException(e);
		    return;
		}
		

		System.out.println();
    	System.out.println("Elenco conf nel database dell'utent");
		for(int i=0;i<listaConf.size();i++)
			{
				
				System.out.println("**********************");
				System.out.print("Id  : ");
					System.out.println(listaConf.get(i).getId());
					
					
				System.out.print("Nome  : ");
					System.out.println(listaConf.get(i).getName());
					

				//System.out.print("Configurazione attuata : ");	
			
					System.out.println("**********************");
					System.out.println();
							
			}
		
	try {
		
			transactionManager.beginTransaction();
			listaValori = DAOConfigurazione.readValoriConfigurazione(transactionManager, listaConf.get(0));
			transactionManager.commitTransaction();
			
			
			
		} catch (DAOException e) {
			handleException(e);
		    return;
		}
		

		
	System.out.println("Configurazione "+listaConf.get(0).getId());
	for(int i=0;i<listaValori.size();i++)
	{
		System.out.println("**********************");
		
		System.out.println(listaValori.get(i));

	
					
	}
					
	}
		
		
		
		
		
		/*
		System.out.println("ottenuto lista Configurazioni con dimensione: "+listaConf.size());
		System.out.println("Inserisci pos conf");
		BufferedReader stdin = new BufferedReader(new InputStreamReader (System.in));
		String indiceAuto=null;
		try {
			indiceAuto = stdin.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int indiceSceltaAuto=Integer.valueOf(indiceAuto);
		Auto a=listaAuto.get(indiceSceltaAuto);
		System.out.println("Scelgo Auto"+indiceSceltaAuto+"con targa:"+listaAuto.get(indiceSceltaAuto).getTarga());
*/
	
	
	
	
	private static void handleException(Exception e) {
    	System.out.format("Errore: %s\nImpossibile completare l'operazione, Riprova!\n", e.getLocalizedMessage());
    	//consoleWriter.format("Errore: %s\nImpossibile completare l'operazione, Riprova!\n", e.getLocalizedMessage());
    	//consoleWriter.flush();
    }
	
	
	
}
