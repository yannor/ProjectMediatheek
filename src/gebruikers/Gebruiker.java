package gebruikers;

public class Gebruiker
{
    private int id;
    private String naam;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
    
    //alle gebruikers kunnen een item opzoeken
    public void itemOpzoeken(/*Item item*/)
    {
        // item opzoeken
    }
    
    
    //methodes uitleningen
   
  
  
  public void voegUitleningToe(int leerlingId,int itemId)
  {
      //uitleningen.add(leerlingId, itemId, date.Today());
  }
  
  public void wijzigUitlening(int uitleenId)
  {
      
  }
  
  public void verwijderUitlening(int uitleenId)
  {
      //uitleningen.remove(uitleenId);
  }
    
    //filtermethodes uitlenen
    
}
