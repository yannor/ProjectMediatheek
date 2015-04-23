package Oud;

import gui.ScreenSwitcher;
import gui.ScreenSwitcher;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.*;

public class HomePage_uit extends Pane {

    ScreenSwitcher switcher;
    @FXML
    private Button btnInloggen, btnZoeken;

    @FXML
    private Label lblWelkom;

    public HomePage_uit(ScreenSwitcher switcher) {
this.switcher=switcher;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage_uit.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
     
//schermUitgebreid.setVisible(false);

    }

    @FXML
    public void aanmelden() {
        
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Aanmelden");
        dialog.setHeaderText("Wat is jouw naam");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        String inlog = result.toString().substring(9, result.toString().length() - 1);

        if (inlog.equalsIgnoreCase("Yannick")) {

           // Image applicationIcon = new Image("gui/afbeeldingen/logo_krekel.png");
            //stage.getIcons().add(applicationIcon);
            //stage.close();
            
            switcher.homePageIn();
          
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Aanmelden");
            alert.setHeaderText("Foute gebruikersnaam");

            alert.showAndWait();
        }

// The Java 8 way to get the response value (with lambda expression).
    }

//    @FXML
//    public void zoeken() {
//
//        Stage stage = new Stage();
//        //stage.close();
//        Image applicationIcon = new Image("gui/afbeeldingen/logo_krekel.png");
//        stage.getIcons().add(applicationIcon);
//        Stage dezeStage = (Stage) btnZoeken.getScene().getWindow();
//        dezeStage.close();
//        Scene scene = new Scene(new gui.zoeken.Zoeken(false, ""));
//        stage.setScene(scene);
//       
//        stage.setMaximized(true);
//        stage.setTitle("Zoeken");
//
//        stage.show();
//
//    }

}
