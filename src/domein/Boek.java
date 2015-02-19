package domein;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Boek extends Item{
    private int aantalBlz;

    public Boek(String titel, int nummer, int aantalExemplaren, String beschrijving, String thema, int aantalBlz) {
        super(titel, nummer, aantalExemplaren, beschrijving, thema);
        this.aantalBlz= aantalBlz;
    }

    public int getAantalBlz() {
        return aantalBlz;
    }

    public void setAantalBlz(int aantalBlz) {
        this.aantalBlz = aantalBlz;
    }
    
}
