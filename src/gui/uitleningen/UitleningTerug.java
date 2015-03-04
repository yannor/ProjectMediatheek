package gui.uitleningen;

import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;

import gui.HomePage;

public class UitleningTerug extends BorderPane {

    @FXML
    private Button btnBackHome;

    private String gebruikersNaam;

    @FXML
    private TableView TableUitleningen;

    public UitleningTerug(String gebruikersNaam) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UitleningPage.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.gebruikersNaam = gebruikersNaam;

    }

    @FXML
    public void backHome() {

        Scene scene = new Scene(new HomePage(gebruikersNaam));
        Stage stage = new Stage();
        Image applicationIcon = new Image("gui/afbeeldingen/logo_krekel.png");
        stage.getIcons().add(applicationIcon);
        //stage.close();
        Stage dezeStage = (Stage) btnBackHome.getScene().getWindow();
        dezeStage.close();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.setOnShown((WindowEvent t) -> {
            stage.setMinWidth(scene.getWidth());
            stage.setMinHeight(scene.getHeight());
        });
        stage.show();

    }

    public void vulTable() {

    }

}
