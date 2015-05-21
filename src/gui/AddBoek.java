package gui;

import items.Boek;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.ItemManagement;

public class AddBoek extends BorderPane
{

    @FXML
    private Label lblTitel, lblAuteur, lblLeeftijd, lblUitgeverij, lblThema, lblBeschrijving;
    
    @FXML
    private TextField txtTitel, txtAuteur, txtLeeftijd, txtUitgeverij, txtThema, txtBeschrijving;
    
    @FXML
    private Button btnToevoegen, btnCancel;
    
    private final ObservableList<String> themas = FXCollections.observableArrayList();
    
    ScreenSwitcher switcher;
    ItemManagement man = new ItemManagement();
    
     public AddBoek(ScreenSwitcher switcher) 
     {
        this.switcher = switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddBoek.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       

    }
  
     @FXML
     public void cancel()
     {
         switcher.itemToevoegen();
     }
    
    
     @FXML
     public void addBoek()
     {
         themas.add(txtThema.getText());
         man.create(new Boek(txtTitel.getText(), txtLeeftijd.getText(), themas, txtAuteur.getText(), txtUitgeverij.getText(), txtBeschrijving.getText()));
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Boek toegevoegd");
         alert.setHeaderText(null);
         alert.setContentText("Het boek: " + txtTitel.getText() + " is toegevoegd aan de databank en kan nu worden uitgeleend.");
         alert.showAndWait();
         
         cancel();
         
     }
    
}
