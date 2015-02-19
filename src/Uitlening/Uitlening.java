package Uitlening;

import gebruikers.Gebruiker;
import item.Item;
import java.util.Date;

public class Uitlening 
{
    private int id; //auto-nummeren
    private Item item;
    private Gebruiker gebruiker;
    private Date datumUitgeleend;
    private Date datumTerugVerwacht;

    public Uitlening(Item item, Gebruiker gebruiker, Date datumUitgeleend, Date datumTerugVerwacht) 
    {
        this.item = item;
        this.gebruiker = gebruiker;
        this.datumUitgeleend = datumUitgeleend;
        this.datumTerugVerwacht = datumTerugVerwacht;
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

    public Date getDatumUitgeleend() 
    {
        return datumUitgeleend;
    }

    public void setDatumUitgeleend(Date datumUitgeleend) 
    {
        this.datumUitgeleend = datumUitgeleend;
    }

    public Date getDatumTerugVerwacht() 
    {
        return datumTerugVerwacht;
    }

    public void setDatumTerugVerwacht(Date datumTerugVerwacht) 
    {
        this.datumTerugVerwacht = datumTerugVerwacht;
    }
    
}

