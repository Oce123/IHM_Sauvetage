package com.example.ihm_v10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

//classe pour la partie coopérative, connexion entre étudiants via email esiee/mot de passe. Pas encore traité
public class WelcomeActivity extends AppCompatActivity {

    //appelé à la création de l'activité, sert d'initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    //redirection de notre bouton
    public void OK(View view){
        Intent bouton_OK = new Intent(this, TableActivity.class);
        startActivity(bouton_OK);
    }
}
