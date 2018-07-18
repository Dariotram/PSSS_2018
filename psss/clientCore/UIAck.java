package com.example.zerin.psss.clientCore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zerin.psss.ClientProxy.GestoreCPS;
import com.example.zerin.psss.R;

public class UIAck extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finale);

        //riprendo il valore passato dalle vecchie schermate attraverso putExtra!
       //int idAuto = (int) getIntent().getSerializableExtra("idAuto");
       // int idConf = (int) getIntent().getSerializableExtra("idConf");
        String sceltaA=(String)getIntent().getSerializableExtra("Auto");
        String sceltaB =(String) getIntent().getSerializableExtra("Conf");
        int idAuto=Integer.parseInt(sceltaA);
        int idConf= Integer.parseInt(sceltaB);
        GestoreCPS cps= new GestoreCPS();
        cps.AdattaConfigurazione(idAuto,idConf,this); //avvio adattaConfigurazione

        Button b = findViewById(R.id.button10);
        TextView tx = findViewById(R.id.textView3);
        //STAMPO A SCHERMO UN RIEPILOGO
        String sceltaA1=(String)getIntent().getSerializableExtra("Auto");
        String sceltaB1 =(String) getIntent().getSerializableExtra("Conf");
        tx.setText("Auto scelta: "+sceltaA1+"\n"+"Configurazione scelta: "+ sceltaB1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UIAck.this,UI.class)); //torno al menu
            }
        });
    }
}
