package com.example.ihm_v10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

//classe permettant de voir en détail la salle sélectionné et de la modifier/supprimer
public class DetailSalle extends AppCompatActivity {

    EditText numero, heure, heure2;
    Button mod, sup;
    String id;
    Helper h = new Helper(DetailSalle.this);

    //appelé à la création de l'activité, sert d'initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_salle);

        numero = findViewById(R.id.numero_);
        heure = findViewById(R.id.heure_);
        heure2 = findViewById(R.id.heure_2);
        mod = findViewById(R.id.mod);
        sup = findViewById(R.id.sup);
        id = getIntent().getStringExtra("id");

        //récupère la salle sélectionné ainsi que ses paramètres rentrés (numéro et heure)
        Salle_disponible s = h.getOneSalle(Integer.parseInt(id));
        numero.setText(s.getNumero());
        heure.setText(String.valueOf(s.getHeure()));
        heure2.setText(String.valueOf(s.getHeure2()));

        //interaction avec le bouton modifier
        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Salle_disponible sd = new Salle_disponible(Integer.parseInt(id), numero.getText().toString(),
                        Double.parseDouble(heure.getText().toString()), Double.parseDouble(heure2.getText().toString()));
                h.updtaeSalle(sd);
                Intent i = new Intent(DetailSalle.this, ListeSalle.class);
                startActivity(i);
            }
        });

        //intéraction avec le bouton supprimer
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                h.deleteSalle(Integer.parseInt(id));
                Intent i = new Intent(DetailSalle.this, ListeSalle.class);
                startActivity(i);
            }
        });
    }

    public void retour(View view) {
        Intent bouton_retour = new Intent(this, ListeSalle.class);
        startActivity(bouton_retour);
    }
}
