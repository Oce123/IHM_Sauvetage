package com.example.ihm_v10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Helper extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    public Helper(@Nullable Context context) {
        super(context, "salle_libre", null, 1);
    }

    //création de la base de donnée
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE salle_disponible(_id INTEGER PRIMARY KEY, numero TEXT,heure REAL, heure2 REAL)");
    }

    //permet de mettre a jour la base de donnée en cas de changement
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS salle_disponible");
        onCreate(db);
    }

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

    public void updtaeSalle(Salle_disponible s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("numero", s.getNumero());
        cv.put("heure", s.getHeure());
        cv.put("heure2", s.getHeure2());

        db.update("salle_disponible", cv, "_id=?", new String[]{String.valueOf(s.getId())});
        db.close();
    }

    public void deleteSalle(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("salle_disponible", " _id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Cursor getAllSalle() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM salle_disponible", null);
        return c;
    }


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

    public Salle_disponible getOneSalle(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query("salle_disponible", new String[]{"_id", "numero", "heure", "heure2"},
                "_id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (c != null)
            c.moveToFirst();
            Salle_disponible s = new Salle_disponible(c.getInt(0), c.getString(1), c.getDouble(2), c.getDouble(3));
            return s;
    }

    public void opened(){
        db = this.getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    public SQLiteDatabase getDb(){
        return db;
    }
}
