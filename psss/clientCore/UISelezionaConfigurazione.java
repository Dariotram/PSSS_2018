package com.example.zerin.psss.clientCore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.zerin.psss.ClientEntity.Utente;
import com.example.zerin.psss.ClientProxy.GestoreCPS;
import com.example.zerin.psss.R;

public class UISelezionaConfigurazione extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uiseleziona_configurazione);
        Utente u= (Utente) getIntent().getSerializableExtra("Utente");
        GestoreCPS cps=new GestoreCPS();
        cps.getAllConf(u,this);

    }
}
