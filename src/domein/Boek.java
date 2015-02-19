package domein;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Boek extends Item{
    private int aantalBlz;
    private float isbn;

    public Boek(String titel, int id, int aantalExemplaren, String beschrijving, String thema, int aantalBlz, float isbn) {
        super(titel, id, aantalExemplaren, beschrijving, thema);
        this.aantalBlz= aantalBlz;
        this.isbn=isbn;
    }

    public int getAantalBlz() {
        return aantalBlz;
    }

    public void setAantalBlz(int aantalBlz) {
        this.aantalBlz = aantalBlz;
    }
    
     public float getIsbn() {
        return isbn;
    }

    public void setIsbn(float isbn) {
        this.isbn = isbn;
    }
}
