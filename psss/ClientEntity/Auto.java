package com.example.zerin.psss.ClientEntity;


import java.io.Serializable;

public class Auto implements Serializable {
        private static final long serialVersionUID = 1L;
        private String targa;
        private int id;
        private String modello;

        public Auto(int id,String targa,String modello) {
            this.targa=targa;
            this.id=id;
            this.modello=modello;
        }
        public String getTarga() {
            return  this.targa;
        }

        public int getId() {
            return this.id;
        }



        public String getModello(){
            return this.modello;
        }
        }


