package com.example.ihm_v10;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

//class qui répoertorie les salles créées dans la base de données
public class ListeSalle extends AppCompatActivity {

    ListView ls;
    Helper h = new Helper(ListeSalle.this);

    //appelé à la création de l'activité, sert d'initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_salle);
        ls = findViewById(R.id.liste);

        //afficher toutes les salles créées
        Cursor c = h.getAllSalle();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(ListeSalle.this, R.layout.item, c,
                new String[]{c.getColumnName(0), c.getColumnName(1), c.getColumnName(2), c.getColumnName(3)},
                new int[] {R.id.id,R.id.numerosalle, R.id.heuredebut, R.id.heurefin},1);
        ls.setAdapter(adapter);

        //interaction lorsque l'on appuie sur une salle
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView t = view.findViewById(R.id.id);
                Intent x = new Intent(ListeSalle.this, DetailSalle.class);
                x.putExtra("id", t.getText().toString());
                startActivity(x);
            }
        });



    }

    //redirection de notre bouton
    public void back(View view){
        Intent bouton_back = new Intent(this, MainActivity.class);
        startActivity(bouton_back);
    }
}
