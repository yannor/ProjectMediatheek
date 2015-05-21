package gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import util.ItemManagement;


public class BeheerPage extends BorderPane {

   @FXML
   private Button btnHome,btnItems, btnLeerlingen, btnWachtwoord;

   @FXML
   private Label lblBeheer;
 
   private ScreenSwitcher switcher;
   
   //constructor switcher maken (ik krijg altijd een wit scherm ipv mijne gui als ik het doe zoals bij andere pagina's
   //indien nodig ook klasse switcher zelf aanpassen
   public BeheerPage(ScreenSwitcher switcher) {
        this.switcher = switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BeheerPage.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       ItemManagement man = new ItemManagement();

    }
   @FXML
   private void home()
   {
      switcher.homePageIn();
   }
   
   @FXML
   private void items()
   {
       switcher.itemBeheer();
   }
   
   @FXML
   private void leerlingen()
   {
       switcher.leerlingenBeheer();
   }
   @FXML
   private void wachtwoord()
   {
       switcher.wijzigWachtwoord();
   }
   
}