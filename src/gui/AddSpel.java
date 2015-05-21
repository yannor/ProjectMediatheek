package gui;

import items.Spel;
import java.io.IOException;
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

public class AddSpel extends BorderPane 
{
    @FXML
    private Label lblTitel, lblLeeftijd, lblUitgeverij, lblThema;
    
    @FXML
    private TextField txtTitel, txtLeeftijd, txtUitgeverij, txtThema;
    
    @FXML
    private Button btnToevoegen, btnCancel;
    
    private final ObservableList<String> themas = FXCollections.observableArrayList();
    
    ScreenSwitcher switcher;
    ItemManagement man = new ItemManagement();
    
    public AddSpel(ScreenSwitcher switcher) 
     {
        this.switcher = switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddSpel.fxml"));
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
    public void addSpel()
    {
        themas.add(txtThema.getText());
        man.create(new Spel(txtTitel.getText(),txtLeeftijd.getText(), themas, txtUitgeverij.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Spel toegevoegd");
        alert.setHeaderText(null);
        alert.setContentText("Het spel: " + txtTitel.getText() + " is toegevoegd aan de databank en kan nu worden uitgeleend.");
        alert.showAndWait();
         
        cancel();
    }
}
