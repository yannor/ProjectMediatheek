package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
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

    @FXML
    private Button btnInloggen, btnZoeken;

    @FXML
    private Label lblWelkom;

    public HomePage_uit() {

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
    public void inloggen() {
        String gebruikersNaam = "Gebruiker";
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Inloggen");
        dialog.setHeaderText("Wat is jouw naam");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        String inlog = result.toString().substring(9, result.toString().length() - 1);

        if (inlog.equalsIgnoreCase("Yannick")) {

            gebruikersNaam = inlog;

            Scene scene = new Scene(new HomePage(gebruikersNaam));
            Stage stage = new Stage();
            Image applicationIcon = new Image("gui/afbeeldingen/logo_krekel.png");
            stage.getIcons().add(applicationIcon);
            //stage.close();
            Stage dezeStage = (Stage) btnInloggen.getScene().getWindow();
            dezeStage.close();
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.setOnShown((WindowEvent t) -> {
                stage.setMinWidth(scene.getWidth());
                stage.setMinHeight(scene.getHeight());
            });

            stage.show();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Inloggen");
            alert.setHeaderText("Foute gebruikersnaam");

            alert.showAndWait();
        }

// The Java 8 way to get the response value (with lambda expression).
    }

    @FXML
    public void zoeken() {

        Stage stage = new Stage();
        //stage.close();
        Image applicationIcon = new Image("gui/afbeeldingen/logo_krekel.png");
        stage.getIcons().add(applicationIcon);
        Stage dezeStage = (Stage) btnZoeken.getScene().getWindow();
        dezeStage.close();
        Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
        double width= screenSize.getWidth();
        double height= screenSize.getHeight();
        Scene scene = new Scene(new gui.zoeken.Zoeken(false, ""));
         stage.setScene(scene);
        
        stage.setWidth(width);
        stage.setHeight(height);
        
       
        stage.setMaximized(true);
        stage.setTitle("Zoeken");

        stage.show();

    }

}
