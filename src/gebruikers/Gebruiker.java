package gebruikers;

import java.util.List;
import uitleningen.Uitlening;

public class Gebruiker 
{
    private List<Uitlening> uitleningen;

    public List<Uitlening> getUitleningen() 
    {
        return uitleningen;
    }

    public void setUitleningen(List<Uitlening> uitleningen) 
    {
        this.uitleningen = uitleningen;
    }
    
    public void addUitlening(Uitlening uitlening){
        uitleningen.add(uitlening);
    }
    
    public void removeUitlening(Uitlening uitlening){
        uitleningen.remove(uitlening);
    }
    

    
}
