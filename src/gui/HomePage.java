package gui;

import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import util.ItemManagement;

public class HomePage extends Pane implements Screen {

    ScreenSwitcher switcher;
    @FXML
    private Button btnUitloggen, btnZoeken, btnUitleningen, btnBeheer;
    
    ItemManagement it= new ItemManagement();

    @FXML
    private Label lblWelkom;

    private String gebruikersNaam;

    public HomePage(ScreenSwitcher switcher) {

       this.switcher=switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        lblWelkom.setText("Welkom ");

    }

    @FXML
    public void afmelden() {

       switcher.zoeken(false);
      
        

    }
//
    @FXML
    public void zoeken() {

    
    
    switcher.zoeken(true);
    
   }
//
    @FXML
    public void uitleningTerugbrengen() {

        switcher.uitleningen();

    }
//
//    @FXML
//    public void beheer() {
//
//        Stage stage = new Stage();
//        //stage.close();
//        Image applicationIcon = new Image("gui/afbeeldingen/logo_krekel.png");
//        stage.getIcons().add(applicationIcon);
//        Stage dezeStage = (Stage) btnBeheer.getScene().getWindow();
//        dezeStage.close();
//
//        Scene scene = new Scene(new gui.instellingen.BeheerVrij(gebruikersNaam));
//        stage.setScene(scene);
//        stage.setTitle("Beheer");
//
//        stage.show();
//
//    }

    
    public void beheer()
    {
        switcher.beheer();
        
    }
}
