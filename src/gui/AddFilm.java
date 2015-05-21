package gui;

import items.Dvd;
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

public class AddFilm extends BorderPane 
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
    
    public AddFilm(ScreenSwitcher switcher) 
     {
        this.switcher = switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddFilm.fxml"));
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
    public void addFilm()
    {
        themas.add(txtThema.getText());
        man.create(new Dvd(txtTitel.getText(),txtLeeftijd.getText(),themas));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("DVD toegevoegd");
        alert.setHeaderText(null);
        alert.setContentText("De DVD: " + txtTitel.getText() + " is toegevoegd aan de databank en kan nu worden uitgeleend.");
        alert.showAndWait();

        cancel();
    }
    
}
