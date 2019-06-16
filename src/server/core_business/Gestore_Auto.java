package server.core_business;

import java.util.ArrayList;

import entity.Auto;
import entity.Componente;
import entity.Configurabilita;
import entity.Configurazione;

public class Gestore_Auto {
	private static Gestore_Auto gestoreAuto_instance=null;
	
	private Gestore_Auto() {
		
	}
	public static synchronized Gestore_Auto getGestoreAuto() {
		if(gestoreAuto_instance==null) {
		//	System.out.println("Inizializzo il gestoreAuto");
			gestoreAuto_instance=new Gestore_Auto();
		}
		//System.out.println("Ritorna il gestoreAuto");
		return gestoreAuto_instance;
	}
		
	public ArrayList<Componente> getAllComp(Auto a) {
		ArrayList<Configurabilita> lista_configurab=new ArrayList<Configurabilita>(a.getListaConfigurabilita() );
		ArrayList<Componente> lista_comp=new ArrayList<Componente>();
		for(int i=0;i<lista_configurab.size();i++) {
			lista_comp.add(lista_configurab.get(i).getComponente());
			}
		return lista_comp;
	}
	
	public void setComp(Auto a,Componente c, int v) {
		ArrayList<Configurabilita> lista_configurab=new ArrayList<Configurabilita>(a.getListaConfigurabilita() );
		for(int i=0;i<lista_configurab.size();i++) {
			if(lista_configurab.get(i).getComponente().equals(c))
				lista_configurab.get(i).setValore(v);
			}
	}
	
	public void configura_Auto(Auto a,Configurazione c) {
		a.configura_Auto(c);
	}
	
	public void aggiungiAuto(String targa, String fiat) {
		Auto a= new Auto(4, targa,fiat ) ;
		/*for(int i=0;i<l_comp.size();i++) {
			a.aggiungiConfigurabilita(l_comp.get(i));
		}*/
	}
	

}
