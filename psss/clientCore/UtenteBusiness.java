package com.example.zerin.psss.clientCore;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zerin.psss.ClientEntity.Auto;
import com.example.zerin.psss.R;

import java.util.ArrayList;

public class UtenteBusiness extends UISelezionaAuto {
    private Context cx;


    public void draw(ArrayList<Auto> allAuto, Activity act){
        //setContentView(R.layout.activity_uiseleziona_auto);
        if(allAuto==null){
           Toast err= Toast.makeText(act, "Non va bene...", Toast.LENGTH_LONG);
           err.show();
            Log.v("1","allAuto=null");
        }
        else{
             for (int i = 0; i < allAuto.size(); i++) {
                 Log.v("2", " ci siamo");
                 LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                 lparams.setMargins(50, 40 * (i + 1), 0, 0);
                 TextView tv = new TextView(act);
                 tv.setLayoutParams(lparams);
                 tv.setText(allAuto.get(i).getModello());
                 act.addContentView(tv, lparams);
                 //qui poi va il codice per passare all'activity di selezione configurazione
             }
        } }
    }

