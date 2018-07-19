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

import com.example.zerin.psss.R;
import com.example.zerin.psss.userInterfaces.UIAck;
import com.example.zerin.psss.userInterfaces.UISelezionaConfigurazione;

import java.util.ArrayList;

public class SelezioneConfigurazione extends UISelezionaConfigurazione {
    private Context cx;
    private ArrayList<String> allConf;
    private Activity act;


    public void draw(ArrayList<String> allC, Activity a){
        //setContentView(R.layout.activity_uiseleziona_auto);
        this.act=a;
        this.allConf=allC;
        if(allConf==null){
            Toast err= Toast.makeText(act, "Non va bene...", Toast.LENGTH_LONG);
            err.show();
            Log.v("1","allConf=null");
        }
        else{ //creo a schermo la lista di configurazioni
            for (int i = 0; i < allConf.size(); i++) {
                Log.v("2", " ci siamo");
                LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lparams.setMargins(50, 200 * (i + 1), 0, 50);
                TextView tv = new TextView(act);
                tv.setLayoutParams(lparams);
                tv.setText(allConf.get(i));
                tv.setTextSize(1,16);
                act.addContentView(tv, lparams);}
            for (int i = 0; i < allConf.size(); i++) { //creo a schermo i bottoni per il riepilogo
                //NB i bottoni non sono implementati
                Log.v("2", " ci siamo");
                LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lparams.setMargins(650, 200 * (i + 1), 0, 0);
                Button b= new Button(act);
                b.setLayoutParams(lparams);
                b.setText("RIEPILOGO CONFIGURAZIONE");
                act.addContentView(b, lparams);}}
        //qui poi va il codice per passare all'activity di selezione configurazione
        final EditText tx = act.findViewById(R.id.editText2);
        Button B1 = act.findViewById(R.id.button4);

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //alla pressione del bottone submit...
                String stringa = tx.getText().toString();
                int a = Integer.parseInt(stringa)-1;
                if (a > allConf.size()+1) {
                    Toast errorToast = Toast.makeText(act, "Error, pls enter a valid id", Toast.LENGTH_SHORT);
                    errorToast.show();
                } else {
                    String sceltaAuto= (String) act.getIntent().getSerializableExtra("Auto");
                    int idAuto = (int) act.getIntent().getSerializableExtra("idAuto");
                    String conf = allConf.get(a);
                    act.startActivity(new Intent(act, UIAck.class).putExtra("Conf",conf).putExtra("idConf",a).putExtra("idAuto",idAuto).putExtra("Auto",sceltaAuto));
                }
                //CON PUTEXTRA PASSO ALLA SUCCESSIVA SCHERMATA DEI VALORI
            }
        });
    }

}
