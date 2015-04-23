package gui;

import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.layout.AnchorPane;

public class BoekenPage extends AnchorPane {

    ScreenSwitcher switcher;

    public BoekenPage(ScreenSwitcher switcher) {
        this.switcher = switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoekenPage.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
