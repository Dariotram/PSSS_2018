package com.example.zerin.psss.clientCore;

import android.app.Activity;
import android.widget.TextView;

import com.example.zerin.psss.R;
import com.example.zerin.psss.userInterfaces.UIAck;

import java.util.ArrayList;

public class Ack extends UIAck {
    public void draw(ArrayList<String> stringa, Activity a){
        TextView tx= a.findViewById(R.id.textView33);
        if(stringa.get(0).equalsIgnoreCase("OK")){
           tx.setText("AUTO CONFIGURATA");
        }
        else{
            tx.setText("C'è stato un errore...");

        }



    }
}
