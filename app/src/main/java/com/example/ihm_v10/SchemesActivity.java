package com.example.ihm_v10;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

//classe permettant l'affichage sous format 3D, pas encore traité
public class SchemesActivity extends AppCompatActivity {

    //appelé à la création de l'activité, sert d'initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schemes);
    }
}