package com.example.zerin.psss.interfaces;

import android.app.Activity;

import com.example.zerin.psss.ClientEntity.Auto;
import com.example.zerin.psss.ClientEntity.Utente;

import java.util.ArrayList;

public interface IGestoreCPS {
    ArrayList<String> getAllAuto(Utente u, Activity a);
    ArrayList<String> getAllConf(Utente u, Activity a);
    void AdattaConfigurazione(int idA, int idC,Activity a);

}
