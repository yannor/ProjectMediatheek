package uitleningen;

import gebruikers.Gebruiker;
import items.Item;
import java.util.Date;

public class Uitlening 
{
    private int id;
    private Item item;
    private Gebruiker gebruiker;
    private Date uitleenDatum;

    public Uitlening(Item item, Gebruiker gebruiker, Date uitleenDatum) 
    {
        
        this.item = item;
        this.gebruiker = gebruiker;
        this.uitleenDatum = uitleenDatum;
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    
    public Item getItem() 
    {
        return item;
    }

    public void setItem(Item item) 
    {
        this.item = item;
    }

    public Gebruiker getGebruiker() 
    {
        return gebruiker;
    }

    public void setGebruiker(Gebruiker gebruiker)
    {
        this.gebruiker = gebruiker;
    }

    public Date getUitleenDatum() 
    {
        return uitleenDatum;
    }

    public void setUitleenDatum(Date uitleenDatum) 
    {
        this.uitleenDatum = uitleenDatum;
    }
    
}
