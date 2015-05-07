package gui;

import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.layout.BorderPane;

import util.ItemManagement;

public class SelectGebruiker extends BorderPane {

    
    ItemManagement man;

    public SelectGebruiker() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectGebruiker.fxml"));
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