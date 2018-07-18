package com.example.zerin.psss.clientCore;

import android.app.Activity;

import com.example.zerin.psss.interfaces.ICallback;

import java.util.ArrayList;

public class CallbackImpl implements ICallback {
    SelezionaAuto ub;

    //richiamo il metodo delle classi di selezionaAuto, selezionaConfigurazione e Ack
    //NB: le suddette classi sono estensioni delle rispettive UI
    @Override
    public void call(ArrayList<String> strings, Activity act) {
        ub=new SelezionaAuto();
        ub.draw(strings,act);

    }

    @Override
    public void call2(ArrayList<String> strings, Activity ac) {
        SelezioneConfigurazione cb = new SelezioneConfigurazione();
        cb.draw(strings,ac);

    }

    public void call3(ArrayList<String> stringa, Activity ac){
        Ack a=new Ack();
        a.draw(stringa,ac);

    }
}
