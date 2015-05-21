package gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class WijzigWachtwoord extends BorderPane 
{
    @FXML
    private Button btnWijzigWachtwoord, btnCancel;
    
    @FXML
    private TextField txtHW, txtNW, txtBNW;
    
    @FXML
    private ChoiceBox cbGebruiker;
    
    @FXML
    private Label lblGebruiker, lblHW, lblNW, lblBNW;
    
    ScreenSwitcher switcher;
    
    public WijzigWachtwoord(ScreenSwitcher switcher) 
     {
        this.switcher = switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WijzigWachtwoord.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    @FXML
    private void wijzigWachtwoord()
    {
        //wachtwoord wijzigen
    }
    
    @FXML
    private void cancel()
    {
        switcher.beheer();
    }
        
}
