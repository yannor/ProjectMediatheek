package gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class ScreenSwitcher extends BorderPane {

    /**
 *
 * @author Yannick
 */
    
    
    
    
     private final Zoeken zoeken;
    private final UitleningPage uitleningen;
    private final HomePage homePage;
    
    

    public ScreenSwitcher() {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ScreenSwitcher.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
         zoeken = new Zoeken(this, false);
         uitleningen = new UitleningPage(this);
         homePage = new HomePage(this);
         //setCenter(zoeken);
           //zoeken.show();
       
    }

    


    public void homePageIn() {
        setCenter(homePage);
    }

    public void zoeken(boolean aangemeld) {
       
        
        setCenter(new Zoeken(this, aangemeld));
       
       
    }
    
     public void uitleningen() {
       
    }
     
     public void beheer() {
       setCenter(new BeheerPage(this));
    }


}
