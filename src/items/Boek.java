package items;

public class Boek extends Item{
    
    private String beschrijving;
    private String auteur;
    private String uitgeverij;

    
    
    public Boek(int boekId, String naam, String thema, String beschrijving, String leeftijd, int aantal, String auteur, String uitgeverij) 
    {
        super(boekId, naam, thema, beschrijving, leeftijd, aantal);
        this.auteur=auteur;
        this.uitgeverij = uitgeverij;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getUitgeverij()
    {
        return uitgeverij;
    }

    public void setUitgeverij(String uitgeverij)
    {
        this.uitgeverij = uitgeverij;
    }
}