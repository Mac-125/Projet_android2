package com.example.projet_android;

import org.json.JSONObject;

public class Contact {

    private String Id,Nom,Adresse,Tel1,Tel2,Entreprise;

    public Contact(JSONObject jObject) {
        this.Id = jObject.optString("Id");
        this.Nom = jObject.optString("Nom");
        this.Nom = jObject.optString("Adresse");
        this.Nom = jObject.optString("Tel1");
        this.Nom = jObject.optString("Tel2");
        this.Nom = jObject.optString("Entreprise");
    }

    public String getReference() {
        return Id;
    }

    public String getNom() {
        return Nom;
    }
    public String getAdresse() {
        return Adresse;
    }

    public String getTel1() {
        return Tel1;
    }
    public String getTel2() {
        return Tel2;
    }

    public String getEntreprise() {
        return Entreprise;
    }
}
