package gui;

import domein.Gebruiker;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class ScreenSwitcher extends BorderPane {

    
    
    
    
    
     
    private final UitleningPage uitleningen;
    private final HomePage homePage;
     private Gebruiker gebruiker;
    
    

    public ScreenSwitcher() {
        
         gebruiker = new Gebruiker();
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreenSwitcher.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
       
         uitleningen = new UitleningPage(this);
         homePage = new HomePage(this);
         //setCenter(zoeken);
           //zoeken.show();
       
    }

    


    public void homePageIn() {
        setCenter(homePage);
    }

     public void zoeken()
    {

        setCenter(new Zoeken(this));

    }
    
     public void uitleningen() {
       
    }
     
     public void beheer() {
       setCenter(new BeheerPage(this));
    }
     
     public void itemBeheer()
     {
         setCenter(new BeheerItems(this));
     }
     
     public void leerlingenBeheer()
     {
         setCenter(new BeheerLeerlingen(this));
     }
     
     
     
     public void deleteLeerling()
     {
         setCenter(new DeleteLeerling());
     }
     public void editLeerling()
     {
         setCenter(new EditLeerling(this));
     }
     public void itemToevoegen()
     {
         setCenter(new ItemToevoegen(this));
     }
     
     public void editItem()
     {
         setCenter(new EditItem(this));
     }
     
     public void deleteItem()
     {
         setCenter(new DeleteItem(this));
     }
     
     public void wijzigWachtwoord()
     {
         setCenter(new WijzigWachtwoord(this));
     }
     
     
         public Gebruiker getGebruiker()
    {
        return gebruiker;
    }

    public void setGebruiker(Gebruiker gebruiker)
    {
        this.gebruiker = gebruiker;
    }

      
    public void addBoek()
    {
        setCenter(new AddBoek(this));
    }
    
    public void addCd()
    {
        setCenter(new AddCd(this));
    }
    
    public void addFilm()
    {
        setCenter(new AddFilm(this));
    }
    public void addSpel()
    {
        setCenter(new AddSpel(this));
    }
    public void addTas()
    {
        setCenter(new AddTas(this));
    }
}



