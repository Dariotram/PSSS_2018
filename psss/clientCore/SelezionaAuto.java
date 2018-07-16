package com.example.zerin.psss.clientCore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zerin.psss.ClientEntity.Utente;
import com.example.zerin.psss.R;
import com.example.zerin.psss.userInterfaces.UISelezionaAuto;
import com.example.zerin.psss.userInterfaces.UISelezionaConfigurazione;

import java.util.ArrayList;

public class SelezionaAuto extends UISelezionaAuto {
    private Context cx;
    private ArrayList<String> allAuto;
    private Activity act;


    public void draw(ArrayList<String> allAut, Activity a){
        //setContentView(R.layout.activity_uiseleziona_auto);
        this.act=a;
        this.allAuto=allAut;
        if(allAuto==null){
           Toast err= Toast.makeText(act, "Non va bene...", Toast.LENGTH_LONG);
           err.show();
            Log.v("1","allAuto=null");
        }
        else{
            //creo una lista a schermo
             for (int i = 0; i < allAuto.size(); i++) {
                 Log.v("2", " ci siamo");
                 LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                 lparams.setMargins(50, 40 * (i + 1), 0, 0);
                 TextView tv = new TextView(act);
                 tv.setLayoutParams(lparams);
                 tv.setText(allAuto.get(i));
                 tv.setTextSize(1,16);
                 act.addContentView(tv, lparams);}}

                 final EditText tx = act.findViewById(R.id.editText);
                 Button B1 = act.findViewById(R.id.subbut);
                //alla pressione del bottone submit...

                 B1.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         String stringa = tx.getText().toString();
                         int a = Integer.parseInt(stringa)-1; //l'id parte da 1 mentre quello dell'array parte da 0
                         if (a > allAuto.size()+1) {
                             Toast errorToast = Toast.makeText(act, "Error, pls enter a valid id", Toast.LENGTH_SHORT);
                             errorToast.show();
                         } else {
                             String scelta=  allAuto.get(a);
                             Log.v("33","fin qui arriva");
                             Utente u=(Utente) act.getIntent().getSerializableExtra("Utente");
                             if(u==null){
                                 Log.e("393939","utente nullo");
                             }
                             act.startActivity(new Intent(act, UISelezionaConfigurazione.class).putExtra("Auto", scelta).putExtra("idAuto",a).putExtra("Utente",u));

                         }
             }
        });
    }
    }

