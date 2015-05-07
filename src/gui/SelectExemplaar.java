package gui;

import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.layout.BorderPane;

import util.ItemManagement;

public class SelectExemplaar extends BorderPane {

    
    ItemManagement man;

    public SelectExemplaar() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectExemplaar.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        man= new ItemManagement();

    }
}