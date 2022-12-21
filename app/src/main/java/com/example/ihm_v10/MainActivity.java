package com.example.ihm_v10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

//classe main de notre projet où l'on crée les salles occupées pour la base de données
//en parallèle, essais de récupération de la base de données de la planif via http
public class MainActivity extends AppCompatActivity {

    EditText numero, heure, heure2;
    Button b;

    Helper h = new Helper(MainActivity.this);

    //permission internet
    public static final int PERM_COMPLETED_STORAGE_ACCESS = 1;
    public static final int WRITE_FILE_REQUEST_CODE = 43;
    public static String id = "test_channel_01";
    private Spinner spinnerheure;

    TextView logger;
    String TAG = "MainFragment";

    //appelé à la création de l'activité, sert d'initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero = findViewById(R.id.numero);
        heure = findViewById(R.id.heure);
        heure2 = findViewById(R.id.heure_fin);
        b = findViewById(R.id.ajouter);

        //interaction avec notre bouton pour ajouter une nouvelle salle a la base de données
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Salle_disponible s = new Salle_disponible(numero.getText().toString(),Double.parseDouble(heure.getText().toString()), Double.parseDouble(heure2.getText().toString()));
                h.insertSalle(s);
                Intent i = new Intent(MainActivity.this, ListeSalle.class);
                startActivity(i);
            }
        });

        //......................connexion a http, en cours de réalisation
        //logger = findViewById(R.id.logger);
        //findViewById(R.id.btn_file).setOnClickListener((view) -> {CheckPerm();} );
    };

    //redirection de notre bouton
    public void redirection(View view) {
        Intent redirect = new Intent(this, TableActivity.class);
        startActivity(redirect);
    }

    //redirection de notre bouton
    public void liste_salle(View view){
        Intent bouton_salle = new Intent(this, ListeSalle.class);
        startActivity(bouton_salle);
    }

    //méthode nous permettant de créer un fichier dans lequel le téléchargement internet se stocke
    private void createFile(String mimeType, String fileName) {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Crée un fichier du type de l'extension indiqué en paramètres
        intent.setType(mimeType);
        intent.putExtra(Intent.EXTRA_TITLE, fileName);
        startActivityForResult(intent, MainActivity.WRITE_FILE_REQUEST_CODE);
    }

    //méthode qui nous permet d'aller télécharger le fichier depuis l'url
    public void downloadFile() {
        URL url = null;
        String file = "";

        try {
            url = new URL("XXX");
            file = URLUtil.guessFileName(url.getFile(), null, null);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                createFile("image/png",file);
                return;
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //...................configure et envoie le fichier
        //...................en cours de traitement mais crash donc suppression des classes concernées :
        //...................FileDownloaderIntentService, MainFragment
        /*
        Intent getfile = new Intent(this.getBaseContext(), FileDownloaderIntentService.class);
        getfile.putExtra("FILE", file);
        getfile.putExtra("URL", url);
        this.startService(getfile);
        */
    }

    //vérifie les permissions internet, de stockage et d'écriture dans le téléphone
    public void CheckPerm() {
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            Log.v(TAG, "asking for permissions");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        MainActivity.PERM_COMPLETED_STORAGE_ACCESS);
            }
        } else {
            logger.append("Contact Write Access: Granted\n");
            downloadFile();
        }
    }
}