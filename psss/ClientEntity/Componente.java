package com.example.zerin.psss.ClientEntity;


import java.io.Serializable;

public class Componente implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String nome;
    private int id;
    public Componente(){

    }
    public Componente(int id,String n) {
        nome=n;
        this.id=id;
    }

    public String getNome() {
        return this.nome;
    }
    public int getId() {
        return this.id;
    }

}
