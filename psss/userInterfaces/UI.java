package com.example.zerin.psss.userInterfaces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zerin.psss.ClientEntity.Utente;
import com.example.zerin.psss.R;

public class UI extends AppCompatActivity {
    Utente u = new Utente(1,"Gigi","pomigliano","gigi@xxx.com");
    //L'utente è creato così ma in realtà dovrebbe essere preso dalla pagina di Login che noi non implementiamo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button butt = findViewById(R.id.button3);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UI.this, UISelezionaAuto.class).putExtra("Utente",u));
                //alla pressione del pulsante parte la nuova schermata UISelezinaAuto
            }
        });
    }
}
