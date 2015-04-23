package gui;

import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.layout.AnchorPane;

public class UitleningPage extends AnchorPane {

     ScreenSwitcher switcher= new ScreenSwitcher();
     
    

    public UitleningPage(ScreenSwitcher switcher) {

        this.switcher=switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UitleningPage.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    

    }
    
   
}

   