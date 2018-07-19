package com.example.zerin.psss.ClientProxy;
import android.app.Activity;

import com.example.zerin.psss.ClientEntity.Auto;
import com.example.zerin.psss.ClientEntity.Utente;
import com.example.zerin.psss.clientCore.BackgroundSocket;
import com.example.zerin.psss.clientCore.CallbackImpl;
import com.example.zerin.psss.interfaces.ICallback;
import com.example.zerin.psss.interfaces.IGestoreCPS;
import java.util.ArrayList;

public class GestoreCPS implements IGestoreCPS {
    ICallback callback;

    private static GestoreCPS gestoreCPS_instance=null;

    private GestoreCPS() {
    }

    public static synchronized GestoreCPS getGestoreCPS() {
        if(gestoreCPS_instance==null) {
            //	System.out.println("Inizializzo il gestoreCPS");
            gestoreCPS_instance=new GestoreCPS();
        }
        //System.out.println("Ritorna il gestoreCPS");
        return gestoreCPS_instance;
    }

    @Override
    public ArrayList<String> getAllAuto(Utente u, Activity act) {
        callback=new CallbackImpl();
        new BackgroundSocket(u,callback,act,1).execute();
        return null;
        //il metodo avvia il task contenente la socket inviandogli, oltre che all'utente, una istanza di callback e di activity
        //anche il comando (vedi background socket
    }



    @Override
    public ArrayList<String> getAllConf(Utente u,Activity act) {
        callback=new CallbackImpl();
        new BackgroundSocket(u,callback,act,2).execute();
        //il metodo avvia il task contenente la socket inviandogli, oltre che all'utente, una istanza di callback e di activity
        return null;
    }

    public void AdattaConfigurazione(int idA, int idC,Activity a){
        callback=new CallbackImpl();
        new BackgroundSocket(idA,idC,3,callback,a).execute();
        //il metodo avvia il task contenente la socket inviandogli, oltre che all'activity una istanza di callback e di activity anche il comando e gli id

    }
}
