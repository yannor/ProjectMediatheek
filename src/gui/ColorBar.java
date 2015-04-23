package gui;

import java.io.IOException;
import java.util.Optional;
import javafx.fxml.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;

public class ColorBar extends AnchorPane {

    @FXML
    private Label lblTitel;

    @FXML
    private Button btnHome, btnAanmelden;

    ScreenSwitcher switcher;

    public ColorBar(ScreenSwitcher switcher) {
        this.switcher = switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ColorBar.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
btnAanmelden.setVisible(false);
btnHome.setVisible(true);
    }

    public ColorBar(ScreenSwitcher switcher, boolean aangemeld) {
        this.switcher = switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ColorBar.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        btnHome.setVisible(aangemeld);
        btnAanmelden.setVisible(!aangemeld);

    }

    public void setTitel(String titel) {
        this.lblTitel.setText(titel);
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aanmelden");
            alert.setHeaderText("Foute gebruikersnaam");

            alert.showAndWait();
        }

// The Java 8 way to get the response value (with lambda expression).
    }

    @FXML
    public void home() {
        switcher.homePageIn();
    }

}
