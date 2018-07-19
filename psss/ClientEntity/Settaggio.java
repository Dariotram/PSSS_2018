package com.example.zerin.psss.ClientEntity;

import java.io.Serializable;

public class Settaggio implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Componente comp;
    private int valore;

    public Settaggio(Componente c,int v) {
        comp=c;
        valore=v;
    }

    public int getValore() {
        return this.valore;
    }

    public void setValore(int v){
        valore=v;
    }

    public Componente getComponente() {
        return this.comp;
    }


}
