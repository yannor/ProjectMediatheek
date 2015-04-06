package items;



public class Item {
    
    private int id;
    private String naam;
    private String thema;
    private String leeftijd;
    private int aantal;
    private boolean beschadigd;
    private String beschrijving;

    public Item(int id, String naam, String thema, String beschrijving, String leeftijd, int aantal ) {
        
        this.id = id;
        this.naam = naam;
        this.thema = thema;
        this.leeftijd = leeftijd;
        this.aantal = aantal;
        this.beschadigd=false;
        this.beschrijving = beschrijving;
    }


    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getThema() {
        return thema;
    }

    public void setThema(String thema) {
        this.thema = thema;
    }

    public String getLeeftijd() {
        return leeftijd;
    }

    public void setLeeftijd(String leeftijd) {
        this.leeftijd = leeftijd;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public boolean isBeschadigd() {
        return beschadigd;
    }

    public void setBeschadigd(boolean beschadigd) {
        this.beschadigd = beschadigd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeschrijving()
    {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving)
    {
        this.beschrijving = beschrijving;
    }
}
