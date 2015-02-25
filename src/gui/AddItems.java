package gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

public class AddItems extends GridPane {

    public AddItems() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddItems.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
