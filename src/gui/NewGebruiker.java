package gui;

import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.layout.*;
import javafx.scene.control.TextField;

import util.GebruikerRepository;
import domein.*;
import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class NewGebruiker extends GridPane {

   private final GebruikerRepository gebrMan= new GebruikerRepository();
   
   @FXML
   private TextField txtNaam, txtVoornaam, txtAdres, txtEmail, txtGemeente, txtPostcode, txtKlas;
   
   ScreenSwitcher switcher;

    public NewGebruiker(ScreenSwitcher switcher) {
        
        this.switcher=switcher;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewGebruiker.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    @FXML
    public void addLeerling()
    {
        
       gebrMan.create(new Gebruiker(txtNaam.getText(), txtVoornaam.getText(), txtKlas.getText(), txtEmail.getText(), TypeGebruiker.LEERLING,txtAdres.getText(), txtGemeente.getText(),txtPostcode.getText()));
       
       
       Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Leerling toegevoegd");
alert.setHeaderText(null);
alert.setContentText(txtVoornaam.getText()+" "+txtNaam.getText()+" is toegevoegd.");
alert.showAndWait();

Stage stage = (Stage) txtNaam.getScene().getWindow();
stage.close();
       

        
    }
    
    @FXML
    public void cancel()
    {
        switcher.beheer();
    }

}
