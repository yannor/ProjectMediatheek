package domein;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Item {
    
    private String titel;
    private int id;
    private int aantalExemplaren;
    private String beschrijving;
    private String thema;

    public Item(String titel, int nummer, int aantalExemplaren, String beschrijving, String thema) {
        this.titel = titel;
        this.id = nummer;
        this.aantalExemplaren = aantalExemplaren;
        this.beschrijving = beschrijving;
        this.thema = thema;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getNummer() {
        return id;
    }

    public void setNummer(int nummer) {
        this.id = nummer;
    }

    public int getAantalExemplaren() {
        return aantalExemplaren;
    }

    public void setAantalExemplaren(int aantalExemplaren) {
        this.aantalExemplaren = aantalExemplaren;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public String getThema() {
        return thema;
    }

    public void setThema(String thema) {
        this.thema = thema;
    }
    
    
}
