package com.example.jean.quisuis_je.modele;

/**
 * Created by JEAN on 27/12/2016.
 */

public class Theme {
    private int id;
    private String libelle;

    public Theme(int id, String libelle){
        this.id = id;
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
