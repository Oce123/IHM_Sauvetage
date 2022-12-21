package com.example.ihm_v10;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

//classe permettant de gérer nos paramètres ainsi que leurs comportements
public class SettingsActivity extends AppCompatActivity {

    SwitchCompat Sw24h;
    SwitchCompat SwSombre;
    SwitchCompat Sw3D;
    SwitchCompat SwSalle;

    public static boolean varHeure;

    //getter de la variable VarHeure, accède à la variable VarHeure
    public static boolean getVarHeure() {
        return varHeure;
    }

    //appelé à la création de l'activité, sert d'initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Sw24h = findViewById(R.id.format24);
        SwSombre = findViewById(R.id.mode_sombre);
        Sw3D = findViewById(R.id.mode_3d);
        SwSalle = findViewById(R.id.affichage_salle);

        //stocke une valeur à une clé
        SharedPreferences sp = getSharedPreferences("Save",MODE_PRIVATE); //group save

        //valeurs par défaut de l'application
        Sw24h.setChecked(sp.getBoolean("12h24",false));
        SwSombre.setChecked(sp.getBoolean("Dark",false));
        Sw3D.setChecked(sp.getBoolean("2D3D",false));
        SwSalle.setChecked(sp.getBoolean("Salle",false));

        //interaction avec le bouton 24h/12h
        Sw24h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Sw24h.isChecked())
                {
                    // Bouton Switch ON, on affiche en format 24h
                    SharedPreferences.Editor editor = getSharedPreferences("Save",MODE_PRIVATE).edit();
                    editor.putBoolean("12h24",true); // On met true à l'étiquette value du groupe save
                    editor.apply();
                    varHeure=true;
                    Sw24h.setChecked(true);
                }
                else
                {
                    // Bouton Switch OFF, on affiche en format 12h
                    SharedPreferences.Editor editor = getSharedPreferences("Save",MODE_PRIVATE).edit();
                    editor.putBoolean("12h24",false); // On met false à l'étiquette value du groupe save
                    editor.apply();
                    varHeure=false;
                    Sw24h.setChecked(false);
                }
            }
        });

        //interaction avec le bouton pour le mode sombre
        SwSombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SwSombre.isChecked())
                {
                    // Bouton Switch ON, on affiche l'application en mode sombre
                    SharedPreferences.Editor editor = getSharedPreferences("Save",MODE_PRIVATE).edit();
                    editor.putBoolean("Dark",true); // On met true à l'étiquette value du groupe save
                    editor.apply();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); // Utilise en permanence les ressources night
                    SwSombre.setChecked(true);

                }
                else
                {
                    // Bouton Switch OFF, on n'affiche pas l'application en mode sombre
                    SharedPreferences.Editor editor = getSharedPreferences("Save",MODE_PRIVATE).edit();
                    editor.putBoolean("Dark",false); // On met false à l'étiquette value du groupe save
                    editor.apply();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // Utilise en permanence les ressources normals
                    SwSombre.setChecked(false);
                }
            }
        });

        //interaction avec le bouton pour la vue 3D/2D
        Sw3D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Sw3D.isChecked())
                {
                    // Bouton Switch ON, on affiche la vue 3D
                    SharedPreferences.Editor editor = getSharedPreferences("Save",MODE_PRIVATE).edit();
                    editor.putBoolean("2D3D",true); // On met true à l'étiquette value du groupe save
                    editor.apply();
                    Sw3D.setChecked(true);

                }
                else
                {
                    // Bouton Switch OFF, on affiche la vue 2D
                    SharedPreferences.Editor editor = getSharedPreferences("Save",MODE_PRIVATE).edit();
                    editor.putBoolean("2D3D",false); // On met false à l'étiquette value du groupe save
                    editor.apply();
                    Sw3D.setChecked(false);
                }
            }
        });

        //interaction avec le boouton pour afficher uniquement les salles disponibles
        SwSalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SwSalle.isChecked())
                {
                    // Bouton Switch ON, on affiche uniquement les salles libres
                    SharedPreferences.Editor editor = getSharedPreferences("Save",MODE_PRIVATE).edit();
                    editor.putBoolean("Salle",true); // On met true à l'étiquette value du groupe save
                    editor.apply();
                    SwSalle.setChecked(true);

                }
                else
                {
                    // Bouton Switch OFF, on affiche tout
                    SharedPreferences.Editor editor = getSharedPreferences("Save",MODE_PRIVATE).edit();
                    editor.putBoolean("Salle",false); // On met false à l'étiquette value du groupe save
                    editor.apply();
                    Sw3D.setChecked(false);
                }
            }
        });
    }

    //redirection du bouton
    public void retour(View view){
        Intent bouton_retour = new Intent(this, TableActivity.class);
        startActivity(bouton_retour);
    }
}
