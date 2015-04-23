package gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class Login extends GridPane {

    ScreenSwitcher switcher;

   @FXML
  private TextField txtGebruikersnaam;

    @FXML
    private PasswordField txtWachtwoord;

    @FXML
    private Button btnLogin;

    public Login(ScreenSwitcher switcher) {
        this.switcher=switcher;
	FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
	try {
	    loader.setRoot(this);
	    loader.setController(this);
	    loader.load();

	    
	} catch (IOException ex) {
	    throw new RuntimeException(ex);
	}
    }


@FXML
public void login(ActionEvent event)
{
    if (txtWachtwoord.getText().equalsIgnoreCase("Yannick")) {

        
            
            switcher.homePageIn();
          
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aanmelden");
            alert.setHeaderText("Foute gebruikersnaam");

            alert.showAndWait();
        }
}

   

   
}
