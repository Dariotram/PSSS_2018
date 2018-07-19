package com.example.zerin.psss.clientCore;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.zerin.psss.ClientEntity.Auto;
import com.example.zerin.psss.ClientEntity.Configurazione;
import com.example.zerin.psss.ClientEntity.Utente;
import com.example.zerin.psss.ClientProxy.GestoreCPS;
import com.example.zerin.psss.userInterfaces.UISelezionaAuto;

import java.util.ArrayList;

public class UtenteBusiness extends UISelezionaAuto {
    private Context cx;
    private static UtenteBusiness utenteBusiness_istance=null;
    private ArrayList<Auto> listaAuto;
    private ArrayList<Configurazione> listaConf;
    private UtenteBusiness(){
        listaAuto=null;
        listaConf=null;
    }


    public static synchronized UtenteBusiness getUtenteBusiness() {
        if(utenteBusiness_istance==null) {
            //	System.out.println("Inizializzo l UtenteBusiness");
            utenteBusiness_istance=new UtenteBusiness();
        }
        //System.out.println("Ritorna l UtenteBusiness");
        return utenteBusiness_istance;
    }

    public void getAllAuto(Utente u, Activity act) {
        if(listaAuto==null){
            Log.d("1","chiedo al server listaAuto");
            GestoreCPS cps=GestoreCPS.getGestoreCPS();
            cps.getAllAuto(u,act);
        }else{
            Log.d("2","Preso l'attributo listaAuto");
            SelezionaAuto ub=new SelezionaAuto();
            ub.draw(listaAuto,act);
        }
    }

    public void getAllConf(Utente u, Activity act){
        if(listaConf==null){
            Log.d("1","chiedo al server listaConf");
            GestoreCPS cps=GestoreCPS.getGestoreCPS();
            cps.getAllConf(u,act);
        }else{
            Log.d("2","Preso l'attributo listaConf");
            SelezioneConfigurazione ub=new SelezioneConfigurazione();
            ub.draw(listaConf,act);
        }
    }

    public void setListaAuto(ArrayList<Auto> listaA){
        this.listaAuto=listaA;
    }

    public void setListaConf(ArrayList<Configurazione> listaC){
        this.listaConf=listaC;
    }

    public void setAllConfig(ArrayList<Integer> listaId,ArrayList<String> listaNome){
        this.listaConf= new ArrayList<Configurazione>();
        for(int i=0;i<listaId.size();i++) {
            Configurazione c = new Configurazione(listaId.get(i), listaNome.get(i));
            this.listaConf.add(c);
        }
    }

    public void setAllAuto(ArrayList<Integer> listaId,ArrayList<String> listaTarga,ArrayList<String> listaModello){
        this.listaAuto= new ArrayList<Auto>();
        for(int i=0;i<listaId.size();i++) {
            Auto a= new Auto(listaId.get(i),listaTarga.get(i),listaModello.get(i));
            this.listaAuto.add(a);
        }
    }


    public Auto getAutobyTarga(String t){
        for(int i=0;i<listaAuto.size();i++){
            if(listaAuto.get(i).getTarga().equals(t)){
                return listaAuto.get(i);
            }
        }
        return null;
    }
    public Configurazione getConfbyName(String n){
        for(int i=0;i<listaConf.size();i++){
            if(listaConf.get(i).getName().equals(n)){
                return listaConf.get(i);
            }
        }
        return null;
    }

    public ArrayList<Configurazione> getAllConfbyName(ArrayList<String> listaNomeConf){
        ArrayList<Configurazione> listaConfig= new ArrayList<Configurazione>();
        for(int i=0;i<listaNomeConf.size();i++){
            Configurazione c= getConfbyName(listaNomeConf.get(i));
            listaConfig.add(c);
        }
        return listaConfig;
    }

    public ArrayList<Auto> getAllAutobyTarga(ArrayList<String> listaTarga){
        ArrayList<Auto> listaA= new ArrayList<Auto>();
        for(int i=0;i<listaTarga.size();i++){
            Auto a= getAutobyTarga(listaTarga.get(i));
            listaA.add(a);
        }
        return listaA;
    }

}

