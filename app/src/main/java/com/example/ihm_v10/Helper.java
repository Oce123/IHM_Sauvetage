package com.example.ihm_v10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

//Classe nous permettant de créer la base de données
public class Helper extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    public Helper(@Nullable Context context) {
        super(context, "salle_libre", null, 1);
    }

    //création de la base de données
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE salle_disponible(_id INTEGER PRIMARY KEY, numero TEXT,heure REAL, heure2 REAL)");
    }

    //permet de mettre a jour la base de données en cas de changement
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS salle_disponible");
        onCreate(db);
    }

    /*@Override
    public void onOpen(SQLiteDatabase db) {
        db = this.getWritableDatabase();
        super.onOpen(db);
    }*/

    //insertion de nos données dans la base de données
    public void insertSalle(Salle_disponible s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("numero", s.getNumero());
        cv.put("heure", s.getHeure());
        cv.put("heure2", s.getHeure2());

        db.insert("salle_disponible", null, cv);
        db.close();
    }

    //mis a jour de la base de données
    public void updtaeSalle(Salle_disponible s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("numero", s.getNumero());
        cv.put("heure", s.getHeure());
        cv.put("heure2", s.getHeure2());

        db.update("salle_disponible", cv, "_id=?", new String[]{String.valueOf(s.getId())});
        db.close();
    }

    //suppression de salle dans la base de données
    public void deleteSalle(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("salle_disponible", " _id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    //2 méthodes différentes de 2 types différents pour retourner les salles disponibles
    //1ere méthode
    public Cursor getAllSalle() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM salle_disponible", null);
        return c;
    }
    //2nde méthode
    public List<Salle_disponible> getAllSalle2(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Salle_disponible> salle = new ArrayList<Salle_disponible>();
        Cursor c = db.rawQuery("SELECT * FROM salle_disponible", null);
        if(c.moveToFirst()){
            do {
                Salle_disponible s = new Salle_disponible(c.getInt(0), c.getString(1), c.getDouble(2), c.getDouble(3));
                salle.add(s);
            } while(c.moveToNext());
        }
        return salle;
    }

    //méthode pour nous retourner une seule salle
    public Salle_disponible getOneSalle(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query("salle_disponible", new String[]{"_id", "numero", "heure", "heure2"},
                "_id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (c != null)
            c.moveToFirst();
            Salle_disponible s = new Salle_disponible(c.getInt(0), c.getString(1), c.getDouble(2), c.getDouble(3));
            return s;
    }

    //méthode pour fermer la base de données
    public void close(){
        db.close();
    }

    //getter de la base de données, retourne la base de données
    public SQLiteDatabase getDb(){
        return db;
    }
}
