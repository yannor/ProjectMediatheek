package gui;

import items.Cd;
import java.io.IOException;
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
import util.ItemManagement;

public class AddCd extends BorderPane
{
    @FXML
    private Label lblTitel, lblLeeftijd, lblThema;
    
    @FXML
    private Button btnToevoegen, btnCancel;
    
    
    @FXML
    private TextField txtTitel, txtLeeftijd, txtThema, txtBeschr;
    
    ScreenSwitcher switcher;
    
    private ObservableList<String> liedjes = FXCollections.observableArrayList();
    private final ObservableList<String> themas = FXCollections.observableArrayList();
    
    ItemManagement man = new ItemManagement();
    
     public AddCd(ScreenSwitcher switcher) 
     {
        this.switcher = switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCd.fxml"));
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
     public void addCd()
     {
         themas.add(txtThema.getText());
        
         
         man.create(new Cd(txtTitel.getText(), txtLeeftijd.getText(), themas, txtBeschr.getText()));
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("CD toegevoegd");
         alert.setHeaderText(null);
         alert.setContentText("De CD: " + txtTitel.getText() + " is toegevoegd aan de databank en kan nu worden uitgeleend.");
         alert.showAndWait();
         
         cancel();
     }
}
