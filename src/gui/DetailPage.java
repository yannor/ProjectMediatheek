package gui;

import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class DetailPage extends AnchorPane {

     ScreenSwitcher switcher= new ScreenSwitcher();
     
    

    public DetailPage(ScreenSwitcher switcher) {

        this.switcher=switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailPage.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    

    }
    
   
}

   