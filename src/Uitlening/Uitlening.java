package Uitlening;

import gebruikers.Gebruiker;
import item.Item;
import java.util.Date;

public class Uitlening 
{
    private int id; //auto-nummeren
    private int item;
    private int gebruiker;
    private Date datumUitgeleend;
    

    public Uitlening(Item item, Gebruiker gebruiker, Date datumUitgeleend) 
    {
        this.item=item.getNummer();
        this.gebruiker=gebruiker.getId();
        
        this.datumUitgeleend = datumUitgeleend;
       
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }
    
    
    public int getItem() 
    {
        return item;
    }

    public void setItem(int item) 
    {
        this.item = item;
    }

    public int getGebruiker() 
    {
        return gebruiker;
    }

    public void setGebruiker(int gebruiker) 
    {
        this.gebruiker = gebruiker;
    }

    public Date getDatumUitgeleend() 
    {
        return datumUitgeleend;
    }

    public void setDatumUitgeleend(Date datumUitgeleend) 
    {
        this.datumUitgeleend = datumUitgeleend;
    }

    
}

