package gui;

import items.Verteltas;
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

public class AddTas extends BorderPane 
{
    @FXML
    private Button btnToevoegen, btnCancel;
    
    @FXML
    private Label lblTitel, lblLeeftijd, lblThema;
    
    @FXML
    private TextField txtTitel, txtLeeftijd, txtThema;
    
    ScreenSwitcher switcher;
    private final ObservableList<String> themas = FXCollections.observableArrayList();
    ItemManagement man = new ItemManagement();
    
     public AddTas(ScreenSwitcher switcher) 
     {
        this.switcher = switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTas.fxml"));
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
    public void addTas()
    {
        themas.add(txtThema.getText());
        man.create(new Verteltas(txtTitel.getText(), txtLeeftijd.getText(), themas));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Verteltas toegevoegd");
        alert.setHeaderText(null);
        alert.setContentText("De verteltas: " + txtTitel.getText() + " is toegevoegd aan de databank en kan nu worden uitgeleend.");
        alert.showAndWait();

        cancel();
        
    }
    
}
