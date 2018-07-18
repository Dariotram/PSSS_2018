package com.example.zerin.psss.ClientEntity;

import java.io.Serializable;

public class Utente implements Serializable {
    private String password;
    private String name;
    private String email;
    private int id;

    public Utente(int id,String n, String p, String email) {
        this.id=id;
        this.password=p;
        this.name=n;
        this.email=email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return this.id;
    }
}
