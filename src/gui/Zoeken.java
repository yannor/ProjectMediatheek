package gui;

import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.layout.AnchorPane;

public class Zoeken extends AnchorPane {

     ScreenSwitcher switcher;
     
     

    public Zoeken(ScreenSwitcher switcher) {

        this.switcher=switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Zoeken.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
      

    }

   






}

   