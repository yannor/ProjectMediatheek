package items;

public class Boek extends Item{
    
    private String beschrijving;
    private String auteur;
    private int paginas;

    
    
    public Boek( int boekId, String naam, String thema, String leeftijd, int aantal, 
            String beschrijving, String auteur, int paginas) {
        super(boekId, naam, thema, leeftijd,aantal);
        this.beschrijving= beschrijving;
        this.auteur=auteur;
        this.paginas=paginas;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    
}