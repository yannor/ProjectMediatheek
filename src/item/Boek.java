package item;

public class Boek extends Item{
    private int aantalBlz;
    private int isbn;
    private String auteur;
    private String moeilijkheidgraad;

    
    
    public Boek(String titel, int id, int aantalExemplaren, String beschrijving, String thema, int aantalBlz, int isbn, String auteur, String moeilijkheidgraad) {
        super(titel, id, aantalExemplaren, beschrijving, thema);
        this.aantalBlz= aantalBlz;
        this.isbn=isbn;
        this.auteur=auteur;
        this.moeilijkheidgraad=moeilijkheidgraad;
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

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }
}
