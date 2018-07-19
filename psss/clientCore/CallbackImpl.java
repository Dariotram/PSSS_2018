package com.example.zerin.psss.clientCore;

import android.app.Activity;

import com.example.zerin.psss.interfaces.ICallback;

import java.util.ArrayList;
import com.example.zerin.psss.ClientEntity.Configurazione;
import com.example.zerin.psss.ClientEntity.Auto;
public class CallbackImpl implements ICallback {


    //richiamo il metodo delle classi di selezionaAuto, selezionaConfigurazione e Ack
    //NB: le suddette classi sono estensioni delle rispettive UI
    @Override
    public void call(ArrayList<Auto> strings, Activity act) {
        SelezionaAuto ub=new SelezionaAuto();
        ub.draw(strings,act);

    }

    @Override
    public void call2(ArrayList<Configurazione> strings, Activity ac) {
        SelezioneConfigurazione cb = new SelezioneConfigurazione();
        cb.draw(strings,ac);

    }

    public void call3(ArrayList<String> stringa, Activity ac){
        Ack a=new Ack();
        a.draw(stringa,ac);

    }
}
