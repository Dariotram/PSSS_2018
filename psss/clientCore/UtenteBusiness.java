package com.example.zerin.psss.clientCore;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.zerin.psss.ClientEntity.Utente;
import com.example.zerin.psss.ClientProxy.GestoreCPS;
import com.example.zerin.psss.userInterfaces.UISelezionaAuto;

import java.util.ArrayList;

public class UtenteBusiness extends UISelezionaAuto {
    private Context cx;
    private static UtenteBusiness utenteBusiness_istance=null;
    private ArrayList<String> listaAuto;
    private ArrayList<String> listaConf;
    private UtenteBusiness(){
        listaAuto=null;
        listaConf=null;
    }


    public static synchronized UtenteBusiness getUtenteBusiness() {
        if(utenteBusiness_istance==null) {
            //	System.out.println("Inizializzo il gestoreCPS");
            utenteBusiness_istance=new UtenteBusiness();
        }
        //System.out.println("Ritorna il gestoreCPS");
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

    public void setListaAuto(ArrayList<String> listaA){
        this.listaAuto=listaA;
    }

    public void setListaConf(ArrayList<String> listaC){
        this.listaConf=listaC;
    }
}

