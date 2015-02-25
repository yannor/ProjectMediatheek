package item;

public class Boek extends Item{
    private int aantalBlz;
    private String isbn;
    private String auteur;
    private String moeilijkheidgraad;

    
    
    public Boek(String titel, int id, int aantalExemplaren, String beschrijving, String thema, int aantalBlz, String isbn, String auteur, String moeilijkheidgraad) {
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
    
     public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getMoeilijkheidgraad() {
        return moeilijkheidgraad;
    }

    public void setMoeilijkheidgraad(String moeilijkheidgraad) {
        this.moeilijkheidgraad = moeilijkheidgraad;
    }
    
}
