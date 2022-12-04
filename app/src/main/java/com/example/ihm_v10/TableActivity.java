package com.example.ihm_v10;

import static com.example.ihm_v10.SettingsActivity.getVarHeure;

import android.content.Intent;
import android.os.Bundle;
import android.security.keystore.StrongBoxUnavailableException;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {

    private Spinner spinnerHeure;
    private Spinner spinnerHeure_de_fin;

    boolean FormatHeure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        FormatHeure = getVarHeure();

        if(FormatHeure == true)
        {
            this.spinnerHeure = (Spinner) findViewById(R.id.heure_de_debut);
            Heure[] heure = HeureDataUtils.getHeure();

            ArrayAdapter<Heure> adapter = new ArrayAdapter<Heure>(this,android.R.layout.simple_spinner_item,heure);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            this.spinnerHeure.setAdapter(adapter);

            this.spinnerHeure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    onItemSelectedHandler(parent, view, position, id);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            this.spinnerHeure_de_fin = (Spinner) findViewById(R.id.heure_de_fin);
            Heure_de_fin[] heure_de_fin = HeureDataUtils.getHeure_de_fin();

            ArrayAdapter<Heure_de_fin> adapter2 = new ArrayAdapter<Heure_de_fin>(this, android.R.layout.simple_spinner_item, heure_de_fin);

            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            this.spinnerHeure_de_fin.setAdapter(adapter2);

            this.spinnerHeure_de_fin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //onItemSelectedHandler2(parent, view, position, id);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
        else
        {
            this.spinnerHeure = (Spinner) findViewById(R.id.heure_de_debut);
            Heure_12[] Heure12 = HeureDataUtils.getHeure_12();

            ArrayAdapter<Heure_12> adapter = new ArrayAdapter<Heure_12>(this,android.R.layout.simple_spinner_item,Heure12);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            this.spinnerHeure.setAdapter(adapter);

            this.spinnerHeure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    onItemSelectedHandler12h(parent, view, position, id);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            this.spinnerHeure_de_fin = (Spinner) findViewById(R.id.heure_de_fin);
            Heure_de_fin_12[] Heure_de_fin_12 = HeureDataUtils.getHeure_de_fin_12();

            ArrayAdapter<Heure_de_fin_12> adapter2 = new ArrayAdapter<Heure_de_fin_12>(this,android.R.layout.simple_spinner_item,Heure_de_fin_12);

            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            this.spinnerHeure_de_fin.setAdapter(adapter2);

            this.spinnerHeure_de_fin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //onItemSelectedHandler2(parent, view, position, id);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
        }



        private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id)
        {
            Adapter adapter = adapterView.getAdapter();
            Heure heure = (Heure) adapter.getItem(position);

            Toast.makeText(getApplicationContext(), "Heure de début selectionnée: " + heure.getFullHeure() ,Toast.LENGTH_SHORT).show();
        }

        private void onItemSelectedHandler12h(AdapterView<?> adapterView, View view, int position, long id)
        {
            Adapter adapter = adapterView.getAdapter();
            Heure_12 heure_12 = (Heure_12) adapter.getItem(position);

            Toast.makeText(getApplicationContext(), "Heure de début selectionnée: " + heure_12.getFullHeure_12() ,Toast.LENGTH_SHORT).show();
        }

        private void onItemSelectedHandler2(AdapterView<?> adapterView, View view, int position, long id)
        {
            Adapter adapter = adapterView.getAdapter();
            Heure_de_fin heure_de_fin = (Heure_de_fin) adapter.getItem(position);

            Toast.makeText(getApplicationContext(), "Heure de fin selectionnée: " + heure_de_fin.getFullHeure_de_fin() ,Toast.LENGTH_SHORT).show();
        }

        private void onItemSelectedHandler2_12h(AdapterView<?> adapterView, View view, int position, long id)
        {
            Adapter adapter = adapterView.getAdapter();
            Heure_de_fin_12 heure_de_fin_12 = (Heure_de_fin_12) adapter.getItem(position);

            Toast.makeText(getApplicationContext(), "Heure de fin selectionnée: " + heure_de_fin_12.getFullHeure_de_fin_12() ,Toast.LENGTH_SHORT).show();
        }


    public void parametres(View view){
        Intent bouton_parametres = new Intent(this, SettingsActivity.class);
        startActivity(bouton_parametres);
    }

    public void deconnexion(View view){
        Intent bouton_deconnexion = new Intent(this, WelcomeActivity.class);
        startActivity(bouton_deconnexion);
    }

    public void back(View view){
        Intent bouton_back = new Intent(this, MainActivity.class);
        startActivity(bouton_back);
    }
    //....................................Affichage des salles libres
    public void récuperation(){

    }
    //Creation d'une instance de la classe Helper
    Helper bddsalle = new Helper(this);
    //bddsalle.opened();

    //récuperation du ListView pour chaque epi présent dans l'IHM
    ListView lv1 = (ListView) findViewById(R.id.epi1_liste);
    ListView lv2 = (ListView) findViewById(R.id.epi2_liste);
    ListView lv3 = (ListView) findViewById(R.id.epi3_liste);
    ListView lv4 = (ListView) findViewById(R.id.epi4_liste);
    ListView lv5 = (ListView) findViewById(R.id.epi5_liste);
    ListView lv6 = (ListView) findViewById(R.id.epi6_liste);

    ArrayList list1 = new ArrayList<String>();
    //list1 = null; //a completer
}
