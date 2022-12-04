package com.example.ihm_v10;

public class Heure_de_fin_12 {

    private int Heure_de_fin_12;
    private String Minute_de_fin_12;
    private String Format;

    public Heure_de_fin_12(int heure_de_fin_12, String minute_de_fin_12, String Format) {
        this.Heure_de_fin_12 = heure_de_fin_12;
        this.Minute_de_fin_12 = minute_de_fin_12;
        this.Format = Format;
    }

    public int getHeure_de_fin_12() {
        return Heure_de_fin_12;
    }

    public void setHeure_de_fin_12(int Heure_de_fin_12) {
        this.Heure_de_fin_12 = Heure_de_fin_12;
    }

    public String getMinute_de_fin_12() {
        return Minute_de_fin_12;
    }

    public void setMinute_de_fin_12(String Minute_de_fin_12) { this.Minute_de_fin_12 = Minute_de_fin_12; }

    public String Format() {
        return Format;
    }

    public void Format(String Format) { this.Format = Format; }

    public String getFullHeure_de_fin_12()  { return this.Heure_de_fin_12 + ":" + this.Minute_de_fin_12+ "    " + this.Format; }

    // Text show in Spinner
    @Override
    public String toString()  {
        return this.getFullHeure_de_fin_12();
    }

}
