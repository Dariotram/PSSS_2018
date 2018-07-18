package com.example.zerin.psss.clientCore;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.zerin.psss.ClientEntity.Utente;
import com.example.zerin.psss.ClientProxy.GestoreCPS;
import com.example.zerin.psss.R;

public class UISelezionaAuto extends AppCompatActivity {
    private GestoreCPS cps;
    Utente u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uiseleziona_auto);
        u= (Utente) getIntent().getSerializableExtra("Utente"); //riprendo l'utente dalla schermata precedente
        cps=new GestoreCPS();
        cps.getAllAuto(u,this);
        //avvio getAllAuto del gestore passandogli oltre che all'utente un riferimento alla schermata


    }
}
