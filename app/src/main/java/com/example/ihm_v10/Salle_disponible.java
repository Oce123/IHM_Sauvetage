package com.example.ihm_v10;

//classe permettant de définir les paramètres de nos salles disponibles
public class Salle_disponible {
    int id;
    String numero;
    double heure;
    double heure2;

    public Salle_disponible(int id, String numero, double heure, double heure2) {
        this.id = id;
        this.numero = numero;
        this.heure = heure;
        this.heure2 = heure2;
    }

    public Salle_disponible(String numero, double heure, double heure2) {
        this.numero = numero;
        this.heure = heure;
        this.heure2 = heure2;

    }

    //getter de la variable Id, accède à la variable Id
    public int getId() {
        return id;
    }

    //setter de la variable Id, nous permet de la modifier
    public void setId(int id) {
        this.id = id;
    }

    //getter de la variable Numero, accède à la variable Numéro
    public String getNumero() {
        return numero;
    }

    //setter de la variable Numéro, nous permet de la modifier
    public void setNumero(String numero) {
        this.numero = numero;
    }

    //getter de la variable Heure, accède à la variable Heure
    public double getHeure() {
        return heure;
    }

    //setter de la variable Heure, nous permet de la modifier
    public void setHeure(double heure) {
        this.heure = heure;
    }

    //getter de la variable Heure2, accède à la variable Heure2
    public double getHeure2() {
        return heure2;
    }

    //setter de la variable Heure2, nous permet de la modifier
    public void setHeure2(double heure2) {
        this.heure2 = heure2;
    }
}
