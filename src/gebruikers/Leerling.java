package gebruikers;


public class Leerling 
{
    private String naam, voornaam, adres,klas, email;


    public Leerling(String naam, String voornaam, String adres, String klas, String email) 
    {
        this.naam = naam;
        this.voornaam = voornaam;
        this.adres = adres;
        this.klas = klas;
        this.email =email;
    }
    
    


    public String getNaam() {
        return naam;
    }


    public void setNaam(String naam) {
        this.naam = naam;
    }


    public String getVoornaam() {
        return voornaam;
    }


    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }


    public String getAdres() {
        return adres;
    }


    public void setAdres(String adres) {
        this.adres = adres;
    }


    public String getKlas() {
        return klas;
    }


    public void setKlas(String klas) {
        this.klas = klas;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }
    
    
}