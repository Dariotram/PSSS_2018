package com.example.zerin.psss.interfaces;

import android.app.Activity;

import com.example.zerin.psss.ClientEntity.Auto;

import java.util.ArrayList;
import com.example.zerin.psss.ClientEntity.Configurazione;
public interface ICallback {
    void call(ArrayList<Auto> strings, Activity ac);
    void call2(ArrayList<Configurazione> strings, Activity ac);
    void call3(ArrayList<String> stringa,Activity ac);

}
