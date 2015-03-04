package gui;

import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.*;

public class HomePage extends Pane {

    @FXML
    private Button btnUitloggen, btnZoeken, btnUitleningen, btnBeheer;

    @FXML
    private Label lblWelkom;

    private String gebruikersNaam;

    public HomePage(String gebruikersnaam) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
//schermUitgebreid.setVisible(false);
        lblWelkom.setText("Welkom " + gebruikersnaam);
        this.gebruikersNaam = gebruikersnaam;

    }

    @FXML
    public void uitloggen() {

        Scene scene = new Scene(new HomePage_uit());
        Stage stage = new Stage();
        //stage.close();
        Image applicationIcon = new Image("gui/afbeeldingen/logo_krekel.png");
        stage.getIcons().add(applicationIcon);
        Stage dezeStage = (Stage) btnUitloggen.getScene().getWindow();
        dezeStage.close();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.setOnShown((WindowEvent t) -> {
            stage.setMinWidth(scene.getWidth());
            stage.setMinHeight(scene.getHeight());
        });
        stage.show();

    }

    @FXML
    public void zoeken() {

        Stage stage = new Stage();
        //stage.close();
        Image applicationIcon = new Image("gui/afbeeldingen/logo_krekel.png");
        stage.getIcons().add(applicationIcon);
        Stage dezeStage = (Stage) btnZoeken.getScene().getWindow();
        dezeStage.close();

        Scene scene = new Scene(new gui.zoeken.Zoeken(true, gebruikersNaam));
        stage.setScene(scene);
        stage.setTitle("Zoeken");

        stage.show();

    }

    @FXML
    public void uitleningTerugbrengen() {

        Stage stage = new Stage();
        //stage.close();
        Image applicationIcon = new Image("gui/afbeeldingen/logo_krekel.png");
        stage.getIcons().add(applicationIcon);
        Stage dezeStage = (Stage) btnUitleningen.getScene().getWindow();
        dezeStage.close();

        Scene scene = new Scene(new gui.uitleningen.UitleningTerug(gebruikersNaam));
        stage.setScene(scene);
        stage.setTitle("Uitlening Terugbrengen");

        stage.show();

    }

    @FXML
    public void beheer() {

        Stage stage = new Stage();
        //stage.close();
        Image applicationIcon = new Image("gui/afbeeldingen/logo_krekel.png");
        stage.getIcons().add(applicationIcon);
        Stage dezeStage = (Stage) btnBeheer.getScene().getWindow();
        dezeStage.close();

        Scene scene = new Scene(new gui.instellingen.BeheerVrij(gebruikersNaam));
        stage.setScene(scene);
        stage.setTitle("Beheer");

        stage.show();

    }

}
